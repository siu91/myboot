import com.intellij.database.model.DasTable
import com.intellij.database.model.ObjectKind
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

import javax.swing.*
import java.awt.Dialog
import java.lang.reflect.Method
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

config = [
        // 生成开关
        generate  : [
                entity            : true,
                repository        : true,
                repositoryQueryDSL: true,
                service           : true
        ],
        // 实体生成设置
        entity    : [
                // 继承父类设置
                parent         : [
                        // 是否继承父类
                        enable    : true,
                        // 父类名称
                        name      : "BaseEntity",
                        // 父类包名
                        package   : "org.siu.myboot.core.entity",
                        // 父类的属性，父类已有属性不在出现在生成的实体内
                        properties: ["id", "createTime", "version"],
                ],
                // 数据库类型
                dbType         : "postgres",
                // 是否序列化
                impSerializable: true,
                // 是否生成 jpa 相关内容，设置为 false 可以生成与 jpa 无关的实体
                jpa            : true,
                // 是否生成 swagger 文档相关注解，相关说明来数据库注释
                useSwagger     : true,
                // 是否使用 lombok 注解代替 get、set方法
                useLombok      : true
        ],
        // repository 生成设置
        repository: [
                // 参照 entity 部分的 parent
                parent: [
                        enable : true,
                        name   : "BaseJpaRepository",
                        package: "org.siu.myboot.core.data.querydsljpa"
                ]
        ],
        // service 生成设置
        service   : [
                // 参照 entity 部分的 parent
                parent: [
                        enable : false,
                        name   : "BaseService",
                        package: "org.siu.myboot.core.common"
                ]
        ]
]

typeMapping = [
        (~/(?i)bool|boolean|tinyint/)     : "Boolean",
        (~/(?i)bigint/)                   : "Long",
        (~/(?i)int/)                      : "Integer",
        (~/(?i)float|double|decimal|real/): "Double",
        (~/(?i)datetime|timestamp/)       : "java.util.Date",
        (~/(?i)date/)                     : "java.sql.Date",
        (~/(?i)time/)                     : "java.sql.Time",
        (~/(?i)/)                         : "String"
]

// 选择文件夹 选择文件生成位置
FILES.chooseDirectoryAndSave("\u9009\u62e9\u6587\u4ef6\u5939", "\u9009\u62e9\u6587\u4ef6\u751f\u6210\u4f4d\u7f6e") { dir ->
    SELECTION.filter {
        it instanceof DasTable && it.getKind() == ObjectKind.TABLE
    }
            .each { table ->
                def fields = calcFields(table)
                Gen.main(config, table, fields, dir.toString())
            }
}

// 转换类型
def calcFields(table) {
    def pk = Utils.getPK(table)
    DasUtil.getColumns(table).reduce([]) { fields, col ->
//        console(col, i++)
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           name        : Utils.toCamelCase(col.getName().toString()),
                           column      : col.getName(),
                           type        : typeStr,
                           dataType    : Utils.firstMatched(col.getDataType(), /\b\w+\b/, ""),
                           len         : Utils.firstMatched(col.getDataType(), /(?<=\()\d+(?!=\))/, -1),
                           default     : col.getDefault(),
                           comment     : col.getComment(),
                           nullable    : !col.isNotNull(),
                           isPrimaryKey: null != pk && pk == col.getName(),
                   ]]
    }
}

class Gen {

    // 生成对应的文件
    def static main(config, table, fields, dir) {
        def entityName = Utils.toUpperCamelCase(table.getName())
        def basePackage = Utils.firstMatched(dir.toString(), /(?<=src\\main\\java\\).+/, "").replace("\\", ".")
        dir = dir.toString()
        def pkType = fields.find { it.isPrimaryKey }.type
        // entity
        if (config.generate.entity) {
            Utils.createPath("${dir}\\entity")
            Utils.createFile("${dir}\\entity\\po", "${entityName}.java").withWriter("utf8") {
                writer -> genEntity(writer, config, config.entity.parent, table, entityName, fields, basePackage)
            }
        }


        // rep
        if (config.generate.repository) {
            Utils.createFile("${dir}\\repository", "${entityName}Repository.java").withWriter("utf8") {
                writer -> genRepository(writer, config, entityName, basePackage, pkType)
            }
        }

        // rep QueryDSL
        if (config.generate.repositoryQueryDSL) {
            Utils.createFile("${dir}\\repository\\dsl", "${entityName}RepositoryQueryDsl.java").withWriter("utf8") {
                writer -> genRepositoryQueryDSL(writer, config.repository.parent, entityName, basePackage, pkType)
            }
        }

        // service
        if (config.generate.service) {
            Utils.createFile("${dir}\\service", "${entityName}Service.java").withWriter("utf8") {
                writer -> genService(writer, config, config.service.parent, entityName, pkType, basePackage)
            }
        }

    }

    // 生成实体
    def static genEntity(writer, config, parentConfig, table, entityName, fieldList, basePackage) {

        writer.writeLine "package ${basePackage}.entity.po;"
        writer.writeLine ""
        if (config.entity.useSwagger) {
            writer.writeLine "import io.swagger.annotations.ApiModel;"
            writer.writeLine "import io.swagger.annotations.ApiModelProperty;"
        }
        if (parentConfig.enable) {
            writer.writeLine "import ${parentConfig.package}.${parentConfig.name};"
        }
        if (config.entity.jpa) {
            writer.writeLine "import javax.persistence.*;"
        }
        if (config.entity.useLombok) {
            if (parentConfig.enable) {
                writer.writeLine "import lombok.EqualsAndHashCode;"
            }
            writer.writeLine "import lombok.Data;"
            writer.writeLine ""
        }
        if (config.entity.impSerializable) {
            writer.writeLine "import java.io.Serializable;"
            writer.writeLine ""
        }

        def tableComment = Utils.getDefaultValIfCurrentValIsBlank(table.getComment(), entityName)
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine " * $tableComment"
        writer.writeLine " *"
        writer.writeLine " * @author @Author Siu"
        writer.writeLine " * @Date ${Utils.localDateTimeStr()}"
        writer.writeLine " * @Version 0.0.1"
        writer.writeLine " */"
        if (config.entity.useLombok) {
            if (parentConfig.enable) {
                writer.writeLine "@EqualsAndHashCode(callSuper = true)"
            }
            writer.writeLine "@Data"
        }
        if (config.entity.jpa) {
            writer.writeLine "@Entity"
            writer.writeLine "@Table(name = \"${table.name}\")"
        }
        if (config.entity.useSwagger) {
            writer.writeLine "@ApiModel(value = \"${tableComment}\")"
        }

        def extendsStr = parentConfig.enable ? " extends $parentConfig.name" : "",
            impStr = config.entity.impSerializable ? " implements Serializable" : ""
        writer.writeLine "public class $entityName$extendsStr$impStr {"

        if (parentConfig.enable) {
            fieldList = fieldList.findAll { field -> !parentConfig.properties.contains(field.name) }
        }

        fieldList.each() { field -> genEntityProperties(writer, config, parentConfig, field) }

        if (!config.entity.useLombok) {
            fieldList.each() { field -> genEntityGetAndSetMethod(writer, field) }
        }
        writer.writeLine "}"
    }

    // 实体属性
    def static genEntityProperties(writer, config, parentConfig, field) {
        writer.writeLine ""
        def comment = Utils.getDefaultValIfCurrentValIsBlank(field.comment, field.name)
        writer.writeLine "\t/**"
        writer.writeLine "\t * ${comment}"
        writer.writeLine "\t * nullable : ${field.nullable}"
        writer.writeLine "\t * default  : ${field.default}"
        writer.writeLine "\t */"

        if (field.isPrimaryKey && config.entity.jpa) {
            writer.writeLine "\t@Id"
            // postgres 设置自增ID
            if ("postgres" == config.entity.dbType) {
                if (field.default != null) {
                    // @SequenceGenerator(name = "public_seq", sequenceName = "public_seq", allocationSize = 1)
                    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public_seq")
                    // * default  : nextval('public_seq'::regclass)
                    String defaultValue = field.default
                    if (defaultValue.startsWith("nextval")) {
                        def seqName = Utils.getPostgresSeqName(defaultValue)
                        writer.writeLine "\t@SequenceGenerator(name = \"${seqName}\", sequenceName = \"${seqName}\", allocationSize = 1)"
                        writer.writeLine "\t@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = \"${seqName}\")"
                    }
                }
            }
        }

        if (config.entity.useSwagger) {
            writer.writeLine "\t@ApiModelProperty(value = \"${comment}\")"
        }

        if (config.entity.jpa) {
            def lenStr = ""
            if (field.len.toInteger() >= 0 && !field.type.contains("java")) {
                lenStr = ", length = $field.len"
            }
            writer.writeLine "\t@Column(name = \"${field.column}\", nullable = ${!field.isNotNull}$lenStr)"
        }
        writer.writeLine "\tprivate ${field.type} ${field.name};"
    }

    // 生成get、get方法
    def static genEntityGetAndSetMethod(writer, field) {

        def methodName = Utils.toUpperCamelCase(field.name)

        // get
        writer.writeLine "\t"
        writer.writeLine "\tpublic ${field.type} get${methodName}() {"
        writer.writeLine "\t\treturn this.${field.name};"
        writer.writeLine "\t}"

        // set
        writer.writeLine "\t"
        writer.writeLine "\tpublic void set${methodName}($field.type $field.name) {"
        writer.writeLine "\t\tthis.${field.name} = ${field.name};"
        writer.writeLine "\t}"
    }

    // 生成Service
    def static genService(writer, config, parentConfig, entityName, pkType, basePackage) {
        writer.writeLine "package ${basePackage}.service;"
        writer.writeLine ""
        writer.writeLine "import ${basePackage}.repository.${entityName}Repository;"
        writer.writeLine "import ${basePackage}.repository.dsl.${entityName}RepositoryQueryDsl;"
        if (parentConfig.enable) {
            writer.writeLine "import $parentConfig.package.$parentConfig.name;"
            writer.writeLine "import ${basePackage}.entity.$entityName;"
        }
        writer.writeLine "import org.springframework.stereotype.Service;"
        writer.writeLine ""
        writer.writeLine "import javax.annotation.Resource;"
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine " * $entityName service\u5c42"
        writer.writeLine " *"
        writer.writeLine " * @author @Author Siu"
        writer.writeLine " * @Date ${Utils.localDateTimeStr()}"
        writer.writeLine " * @Version 0.0.1"
        writer.writeLine " */"
        writer.writeLine "@Service"

        def extendsStr = parentConfig.enable ? " extends ${parentConfig.name}<$entityName, $pkType>" : ""
        writer.writeLine "public class ${entityName}Service${extendsStr} {"
        writer.writeLine ""
        writer.writeLine "\t@Resource"
        writer.writeLine "\tprivate ${entityName}Repository repository;"
        writer.writeLine "\t@Resource"
        writer.writeLine "\tprivate ${entityName}RepositoryQueryDsl repositoryQueryDsl;"
        writer.writeLine "}"
    }

    // 生成rep
    def static genRepository(writer, config, entityName, basePackage, pkType) {
        // def customStr = config.generate.repositoryCustom ? ", ${entityName}RepositoryCustom" : ""

        writer.writeLine "package ${basePackage}.repository;"
        writer.writeLine ""
        writer.writeLine "import ${basePackage}.entity.po.$entityName;"
        writer.writeLine "import org.springframework.data.jpa.repository.JpaRepository;"
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine " * $entityName Repository\u5c42"
        writer.writeLine " *"
        writer.writeLine " * @author @Author Siu"
        writer.writeLine " * @Date ${Utils.localDateTimeStr()}"
        writer.writeLine " * @Version 0.0.1"
        writer.writeLine " */"
        writer.writeLine "public interface ${entityName}Repository extends JpaRepository<$entityName, $pkType> {"
        writer.writeLine ""
        writer.writeLine "}"
    }


    // 生成 RepositoryQueryDSL
    def static genRepositoryQueryDSL(writer, parentConfig, entityName, basePackage, pkType) {
        writer.writeLine "package ${basePackage}.repository.dsl;"
        writer.writeLine ""
        writer.writeLine "import com.querydsl.jpa.impl.JPAQuery;"
        writer.writeLine "import $parentConfig.package.$parentConfig.name;;"
        // writer.writeLine "import ${basePackage}.entity.po.Q${entityName};"
        writer.writeLine "import ${basePackage}.entity.po.${entityName};"
        writer.writeLine "import org.springframework.data.domain.Page;"
        writer.writeLine "import org.springframework.data.domain.Pageable;"
        writer.writeLine "import org.springframework.stereotype.Repository;"
        writer.writeLine ""
        writer.writeLine "import javax.persistence.EntityManager;"
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine " * $entityName \u81ea\u5b9a\u4e49Repository QueryDSL\u5c42"
        writer.writeLine " *"
        writer.writeLine " * @author @Author Siu"
        writer.writeLine " * @Date ${Utils.localDateTimeStr()}"
        writer.writeLine " * @Version 0.0.1"
        writer.writeLine " */"
        writer.writeLine "@Repository"
        writer.writeLine "public class ${entityName}RepositoryQueryDsl extends BaseJpaRepository<$entityName, $pkType>  {"
        writer.writeLine ""
        writer.writeLine "\tpublic ${entityName}RepositoryQueryDsl(EntityManager entityManager) {"
        writer.writeLine "\t\tsuper(${entityName}.class, entityManager);"
        writer.writeLine "\t}"
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine " * Q${entityName} QueryDSL Object: Use jpaQueryFactory build JPAQuery"
        writer.writeLine " */"
        writer.writeLine "//private static final Q${entityName} q${entityName} = Q${entityName}.${entityName};"
        writer.writeLine "}"
    }


}

class Utils {

    /**
     * 提示框
     * @param message
     * @return
     */
    static def dialog(message) {
        JOptionPane.showMessageDialog(null, message, "\u6807\u9898", JOptionPane.PLAIN_MESSAGE)
    }

    /**
     * 反射获取主键列名，
     * @param table
     * @return 若没有返回null
     */
    static def getPK(table) {
        def method = table.getClass().getMethod("getText")
        method.setAccessible(true)
        def text = method.invoke(table).toString()
        def reg = /(?<=\s{4,})\b[^\s]+\b(?!=.+\n\s+PRIMARY KEY,)/
        firstMatched(text, reg, null)
    }

    /**
     *  转换为大写驼峰
     * @param content
     * @return
     */
    static def toUpperCamelCase(content) {
        content.toString()
                .split(/_/)
                .toList()
                .stream()
                .filter { s -> s.length() > 0 }
                .map { s -> s.replaceFirst("^.", s.substring(0, 1).toUpperCase()) }
                .collect(Collectors.joining())
    }

    /**
     *  转换为驼峰
     * @param content
     * @return
     */
    static def toCamelCase(content) {
        content = content.toString()
        toUpperCamelCase(content).replaceFirst(/^./, content.substring(0, 1).toLowerCase())
    }

    /**
     * 寻找第一个匹配的值
     * @param content 匹配内容
     * @param reg 正则
     * @param defaultValue 默认值
     * @return 根据正则匹配，能匹配就返回匹配的值，不能则匹配默认值
     */
    static def firstMatched(content, reg, defaultValue) {
        if (null == content) {
            return defaultValue
        }
        def m = content =~ reg
        if (m.find()) {
            return m.group()
        }
        return defaultValue
    }

    static def localDateTimeStr() {
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }

    static def createFile(filePath, fileName) {
        def file = new File(filePath)

        if (!file.exists()) {
            file.mkdir()
        }

        file = new File(filePath + "/" + fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
        return file
    }

    static def createPath(filePath) {
        def file = new File(filePath)

        if (!file.exists()) {
            file.mkdir()
        }

        return file
    }

    static def getDefaultValIfCurrentValIsBlank(currentVal, defaultVal) {
        if (null == currentVal || currentVal.isEmpty()) {
            return defaultVal
        }
        return currentVal
    }

    static def getPostgresSeqName(defaultValue) {
        // nextval('public_seq'::regclass)
        return defaultValue
                .replace("nextval", "")
                .replace("(", "")
                .replace(")", "")
                .replace("::", "")
                .replace("'", "")
                .replace("regclass", "")
    }
}