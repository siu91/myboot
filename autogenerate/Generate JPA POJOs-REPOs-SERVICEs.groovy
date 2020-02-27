import com.intellij.database.model.DasTable
import com.intellij.database.model.ObjectKind
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

import javax.annotation.Generated
import javax.swing.*
import java.awt.Dialog
import java.lang.reflect.Method
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

/**
 * 自动生成代码脚本：JPA + QueryDSL版本
 *
 * 使用方法：
 * 1、将此groovy脚本放到 \.IntelliJIdea2019.3\config\extensions\com.intellij.database\schema
 * 2、配置数据源： IDEA右侧导航视图选择 Database => +(添加) =>配置数据源
 * 3、生成：
 *        1） IDEA右侧导航视图选择 Database
 *        2）选择某个数据源
 *        3）选择 tables 下一张或多张表
 *        4）右键选择 Scripted Extensions
 *        5）选择你的groovy脚本
 *        6）视图界面打开选择生成的目录 => 完成
 *
 * Tips：
 *      1） Windows 可以用mklink 方式 放到\.IntelliJIdea2019.3\config\extensions\com.intellij.database\schema
 *          命令： mklink /h "C:\{IDEA目录}\config\extensions\com.intellij.database\schema\Generate JPA POJOs-REPOs-SERVICEs.groovy" "C:\{文件原路径}\Generate JPA POJOs-REPOs-SERVICEs.groovy"
 *      2） Linux/MacOS 可以用对应的命令操作 （ln命令）
 *
 *
 * intellij API源码(com.intellij.database): JetBrains\IntelliJ IDEA 2019.3.1\lib\src\src_database-openapi.zip
 *
 * @Author Siu
 * @Date 2020/2/23 23:18
 * @Version 0.0.1
 */
config = [
        // 自动生成开关
        generate: [
                // 实体对象，对应 DO/PO
                entity            : true,
                // JPA QueryDSL 工具实体对象
                entityQueryDSL    : true,
                // 数据访问对象 DAO
                repository        : true,
                // JPA QueryDSL 数据访问对象
                repositoryQueryDSL: true,
                // 服务层对象
                service           : true
        ],
        // 实体生成设置
        entity    : [
                // 继承父类设置
                parent             : [
                        // 是否继承父类
                        enable    : true,
                        // 父类名称
                        name      : "BaseEntity",
                        // 父类包名
                        package   : "org.siu.myboot.core.entity",
                        // 父类的属性，父类已有属性不在出现在生成的实体内
                        properties: ["version123"],
                ],
                // jpa相关设置
                jpaConfig: [
                        // 是否使用jpa，设置为 false 可以生成与 jpa 无关的实体
                        enable                            : true,
                        // 是否自动填充时间类型字段
                        autoFillDateTypeField             : true,
                        // 自动填充的字段名称
                        autoFillFieldCreatedDateNames     : ["create_time", "insert_time", "add_time"],
                        autoFillFieldLastModifiedDateNames: ["update_time", "modified_time"],
                        // 是否配置软删除
                        softDelete                        : true,
                        // 软删除字段名: soft_delete 设计为int8，0-未删除，其他-删除；与需要唯一约束的字段组成联合唯一
                        softDeleteFieldName               : "soft_delete",
                        // 软删除更新使用的序列/自增,未配置为空，将使用 ”set softDeleteFieldName = 1“
                        softDeleteSeqName                 : "public_soft_delete_seq"
                ],
                // 数据库类型
                dbType             : "postgres",
                // 是否序列化
                impSerializable    : true,
                // 是否生成 swagger 文档相关注解，相关说明来数据库注释
                useSwagger         : true,
                // 是否使用 lombok 注解代替 get、set方法
                useLombok          : true

        ],
        // entityQueryDSL 生成设置
        entityQueryDSL: [
                // 参照 entity 部分的 parent
                implement: [
                        enable : true,
                        name   : "QBuiler",
                        package: "org.siu.myboot.core.dsl"
                ]
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
                           schemaName  : DasUtil.getSchema(table),
                           catalog     : DasUtil.getCatalog(table)
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

        // entityQueryDSL
        if (config.generate.entityQueryDSL) {
            // 生成 queryDSl 工具对象
            Utils.createPath("${dir}\\entity")
            Utils.createFile("${dir}\\entity\\po", "Q${entityName}.java").withWriter("utf8") {
                writer -> genEntityQueryDSL(writer, config, config.entity.parent, table, entityName, fields, basePackage)
            }
        }


        // rep
        if (config.generate.repository) {
            Utils.createFile("${dir}\\repository", "${entityName}Repository.java").withWriter("utf8") {
                writer -> genRepository(writer, config, entityName, fields, basePackage, pkType)
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

    // 生成实体 QueryDSL
    def static genEntityQueryDSL(writer, config, parentConfig, table, entityName, fieldList, basePackage) {

        def lEntityName = Utils.theFirstLetterLowercase(entityName)

        writer.writeLine "package ${basePackage}.entity.po;"
        writer.writeLine ""
        writer.writeLine "import static com.querydsl.core.types.PathMetadataFactory.*;"
        writer.writeLine "import com.querydsl.core.types.dsl.*;"
        writer.writeLine "import com.querydsl.core.types.PathMetadata;"
        writer.writeLine "import javax.annotation.Generated;"
        writer.writeLine "import com.querydsl.core.types.Path;"
        if (config.entityQueryDSL.implement.enable) {
            writer.writeLine "import ${config.entityQueryDSL.implement.package}.${config.entityQueryDSL.implement.name};"
        }
        writer.writeLine ""
        writer.writeLine "import java.util.Objects;"
        //writer.writeLine "import ${basePackage}.entity.po.${entityName};"
        writer.writeLine ""
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine " * QUserInfo is a Querydsl query type for UserInfo"
        writer.writeLine " */"
        writer.writeLine "@Generated(\"com.querydsl.codegen.EntitySerializer\")"
        def impStr = config.entityQueryDSL.implement.enable ? " implements QBuiler" : ""
        writer.writeLine "public class Q${entityName} extends EntityPathBase<${entityName}>${impStr} {"
        writer.writeLine ""
        writer.writeLine "\tprivate static final long serialVersionUID = 1L;"
        writer.writeLine ""
        writer.writeLine "\tpublic static final Q${entityName} ${lEntityName} = new Q${entityName}(\"${lEntityName}\");"

        fieldList.each() { field -> genQueryDSLEntityProperties(writer, config, parentConfig, field) }


        if (config.entityQueryDSL.implement.enable) {
            // 增加order 方法用于排序
            writer.writeLine ""
            writer.writeLine "\t/**"
            writer.writeLine "\t * get property"
            writer.writeLine "\t *"
            writer.writeLine "\t * @param property"
            writer.writeLine "\t * @return"
            writer.writeLine "\t */"
            writer.writeLine "\t@Override"
            writer.writeLine "\tpublic ComparableExpressionBase order(String property) {"
            writer.writeLine "\t\tif (Objects.isNull(property)) {"
            writer.writeLine "\t\t\treturn null;"
            writer.writeLine "\t\t}"
            writer.writeLine "\t\tswitch (property) {"

            fieldList.each() { field ->
                writer.writeLine "\t\t\t case \"${field.name}\":"
                writer.writeLine "\t\t\t\treturn ${field.name};"
            }

            writer.writeLine "\t\t\tdefault:"
            writer.writeLine "\t\t\t\treturn null;"
            writer.writeLine "\t\t}"
            writer.writeLine "\t}"

            // 增加condition 方法用于查询
            writer.writeLine ""
            writer.writeLine "\t/**"
            writer.writeLine "\t * get property"
            writer.writeLine "\t *"
            writer.writeLine "\t * @param property"
            writer.writeLine "\t * @return"
            writer.writeLine "\t */"
            writer.writeLine "\t@Override"
            writer.writeLine "\tpublic SimpleExpression condition(String property) {"
            writer.writeLine "\t\tif (Objects.isNull(property)) {"
            writer.writeLine "\t\t\treturn null;"
            writer.writeLine "\t\t}"
            writer.writeLine "\t\tswitch (property) {"

            fieldList.each() { field ->
                writer.writeLine "\t\t\t case \"${field.name}\":"
                writer.writeLine "\t\t\t\treturn ${field.name};"
            }

            writer.writeLine "\t\t\tdefault:"
            writer.writeLine "\t\t\t\treturn null;"
            writer.writeLine "\t\t}"
            writer.writeLine "\t}"
        }


        writer.writeLine ""
        writer.writeLine "\tpublic Q${entityName}(String variable) {"
        writer.writeLine "\t\tsuper(${entityName}.class, forVariable(variable));"
        writer.writeLine "\t}"

        writer.writeLine ""
        writer.writeLine "\tpublic Q${entityName}(Path<? extends ${entityName}> path) {"
        writer.writeLine "\t\tsuper(path.getType(), path.getMetadata());"
        writer.writeLine "\t}"

        writer.writeLine ""
        writer.writeLine "\tpublic Q${entityName}(PathMetadata metadata) {"
        writer.writeLine "\t\tsuper(${entityName}.class, metadata);"
        writer.writeLine "\t}"

        writer.writeLine "}"
    }

    // 实体属性 QueryDSL
    def static genQueryDSLEntityProperties(writer, config, parentConfig, field) {
        writer.writeLine ""
        if ("String" == field.type) {
            writer.writeLine "\tpublic final StringPath ${field.name} = createString(\"${field.name}\");"
        } else if ("Long" == field.type) {
            writer.writeLine "\tpublic final NumberPath<Long> ${field.name} = createNumber(\"${field.name}\", Long.class);"
        } else if ("Integer" == field.type) {
            writer.writeLine "\tpublic final NumberPath<Integer> ${field.name} = createNumber(\"${field.name}\", Integer.class);"
        } else if ("Date" == field.type || "java.util.Date" == field.type) {
            writer.writeLine "\tpublic final DateTimePath<java.util.Date> ${field.name} = createDateTime(\"${field.name}\", java.util.Date.class);"
        } else if ("Double" == field.type) {
            writer.writeLine "\tpublic final NumberPath<Double> ${field.name} = createNumber(\"${field.name}\", Double.class);"
        } else if ("Boolean" == field.type) {
            writer.writeLine "\tpublic final BooleanPath ${field.name} = createBoolean(\"${field.name}\");"
        } else {
            writer.writeLine "\tpublic final StringPath ${field.name} = createString(\"${field.name}\");"
        }
    }


    // 生成实体
    def static genEntity(writer, config, parentConfig, table, entityName, fieldList, basePackage) {

        // 配置软删除标记
        def softDeleteFlag = false
        def pkName = null
        // 遍历字段判断是否有软删除定义的字段 && 有主键 && 软删除字段必须定义为Long 类型
        if (config.entity.jpaConfig.enable && config.entity.jpaConfig.softDelete) {
            fieldList.each() { field ->
                if (config.entity.jpaConfig.softDeleteFieldName == field.column) {
                    if (field.type == "Long") {
                        softDeleteFlag = true
                    }
                }
                if (field.isPrimaryKey) {
                    pkName = field.column
                }
            }
        }
        if (pkName == null) {
            softDeleteFlag = false
        }

        //writer.writeLine "// ${softDeleteFlag}"
        writer.writeLine "package ${basePackage}.entity.po;"
        writer.writeLine ""
        if (config.entity.useSwagger) {
            writer.writeLine "import io.swagger.annotations.ApiModel;"
            writer.writeLine "import io.swagger.annotations.ApiModelProperty;"
        }
        if (parentConfig.enable) {
            writer.writeLine "import ${parentConfig.package}.${parentConfig.name};"
        }
        if (config.entity.jpaConfig.enable) {
            writer.writeLine "import javax.persistence.*;"
        }
        if (softDeleteFlag) {
            writer.writeLine "import org.hibernate.annotations.SQLDelete;"
            writer.writeLine "import org.hibernate.annotations.Where;"
        }
        if (config.entity.useLombok) {
            if (parentConfig.enable) {
                writer.writeLine "import lombok.EqualsAndHashCode;"
            }
            writer.writeLine "import lombok.Data;"
            writer.writeLine "import lombok.experimental.Accessors;"
            if (config.entity.jpaConfig.enable && config.entity.jpaConfig.autoFillDateTypeField) {
                writer.writeLine "import org.springframework.data.annotation.CreatedDate;"
                writer.writeLine "import org.springframework.data.annotation.LastModifiedDate;"
                writer.writeLine "import org.springframework.data.jpa.domain.support.AuditingEntityListener;"
            }
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
            writer.writeLine "@Accessors(chain = true)"
        }
        if (config.entity.jpaConfig.enable) {
            writer.writeLine "@Entity"
            if (config.entity.jpaConfig.autoFillDateTypeField) {
                writer.writeLine "@EntityListeners(AuditingEntityListener.class)"
            }
            writer.writeLine "@Table(name = \"${table.name}\")"
        }
        if (config.entity.useSwagger) {
            writer.writeLine "@ApiModel(value = \"${tableComment}\")"
        }

        if (softDeleteFlag) {
            if (config.entity.jpaConfig.softDeleteSeqName == "") {
                writer.writeLine "@SQLDelete(sql = \"UPDATE ${table.name} SET ${config.entity.jpaConfig.softDeleteFieldName} = 1 WHERE ${pkName} = ?\")"
            } else {
                writer.writeLine "@SQLDelete(sql = \"UPDATE ${table.name} SET ${config.entity.jpaConfig.softDeleteFieldName} = nextval( '${config.entity.jpaConfig.softDeleteSeqName}' ) WHERE ${pkName} = ?\")"
            }
            writer.writeLine "@Where(clause = \"${config.entity.jpaConfig.softDeleteFieldName} = 0\")"
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

        if (field.isPrimaryKey && config.entity.jpaConfig.enable) {
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
            // 字段注释中有标记"(qp)",代表为查询参数，会加上 ApiModelProperty
            // 默认主键是查询条件无需标记
            if((field.comment!=null && field.comment.contains("(qp)")) || field.isPrimaryKey){
                writer.writeLine "\t@ApiModelProperty(value = \"${comment}\")"
            }else{
                writer.writeLine "\t@ApiModelProperty(value = \"${comment}\", hidden = true)"
            }
        }

        if (config.entity.jpaConfig.enable) {
            if (config.entity.jpaConfig.autoFillDateTypeField) {
                // create_time update_time 自动填充
                if ("Date" == field.type || "java.util.Date" == field.type || field.type.contains("Date")) {
                    if (config.entity.jpaConfig.autoFillFieldCreatedDateNames.contains(field.column.toLowerCase())) {
                        writer.writeLine "\t@CreatedDate"
                    } else if (config.entity.jpaConfig.autoFillFieldLastModifiedDateNames.contains(field.column.toLowerCase())) {
                        writer.writeLine "\t@LastModifiedDate"
                    }
                }
            }

            def lenStr = ""
            if (field.len.toInteger() >= 0 && !field.type.contains("java")) {
                lenStr = ", length = $field.len"
            }
            writer.writeLine "\t@Column(name = \"${field.column}\", nullable = ${field.nullable}$lenStr)"
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

        writer.writeLine "import org.siu.myboot.core.entity.qo.Params;"
        writer.writeLine "import org.siu.myboot.core.utils.QueryBuilder;"
        writer.writeLine "import org.siu.myboot.core.entity.vo.PageData;"
        writer.writeLine "import ${basePackage}.entity.po.${entityName};"
        writer.writeLine "import ${basePackage}.entity.po.Q${entityName};"
        writer.writeLine "import ${basePackage}.repository.${entityName}Repository;"
        writer.writeLine "import ${basePackage}.repository.dsl.${entityName}RepositoryQueryDsl;"
        if (parentConfig.enable) {
            writer.writeLine "import $parentConfig.package.$parentConfig.name;"
            writer.writeLine "import ${basePackage}.entity.$entityName;"
        }
        writer.writeLine "import org.springframework.data.domain.Page;"
        writer.writeLine "import org.springframework.data.domain.PageRequest;"
        writer.writeLine "import org.springframework.data.domain.Pageable;"
        writer.writeLine "import org.springframework.data.querydsl.QSort;"
        writer.writeLine "import org.springframework.stereotype.Service;"

        writer.writeLine "import javax.annotation.Resource;"
        writer.writeLine "import java.util.Optional;"
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


        writer.writeLine "    /**\n" +
                "     * add \n" +
                "     *\n" +
                "     * @param entity\n" +
                "     * @return\n" +
                "     */\n" +
                "    public ${entityName} save(${entityName} entity) {\n" +
                "        return repositoryQueryDsl.save(entity);\n" +
                "    }"

        writer.writeLine ""

        writer.writeLine "    /**\n" +
                "     * delete by ID\n" +
                "     *\n" +
                "     * @param id\n" +
                "     */\n" +
                "    public void delete(Long id) {\n" +
                "        repositoryQueryDsl.deleteById(id);\n" +
                "    }"

        writer.writeLine ""

        writer.writeLine "    /**\n" +
                "     * update\n" +
                "     *\n" +
                "     * @param entity\n" +
                "     * @return\n" +
                "     */\n" +
                "    public ${entityName} update(${entityName} entity) {\n" +
                "        return repositoryQueryDsl.save(entity);\n" +
                "    }"

        writer.writeLine ""


        writer.writeLine "  /**\n" +
                "     * find by ID\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    public Optional<${entityName}> findById(Long id) {\n" +
                "        return repositoryQueryDsl.findById(id);\n" +
                "    }"
        
        
        
        writer.writeLine ""

        def lEntityName = Utils.theFirstLetterLowercase(entityName)
        // 分页查询列表
        writer.writeLine "  /**\n" +
                "     * get list by page\n" +
                "     *\n" +
                "     * @param params\n" +
                "     * @return\n" +
                "     */\n" +
                "    public PageData getList(Params<${entityName}> params) {\n" +
                "        QSort sort = QueryBuilder.buildSort(params.getSort(), Q${entityName}.${lEntityName});\n" +
                "        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);\n" +
                "        Page<${entityName}> data = repositoryQueryDsl.query(pageable, params.getTerms());\n" +
                "\n" +
                "        return new PageData(data, params);\n" +
                "    }"



        writer.writeLine "}"
    }

    // 生成rep
    def static genRepository(writer, config, entityName, fieldList, basePackage, pkType) {
        writer.writeLine "package ${basePackage}.repository;"
        writer.writeLine ""
        writer.writeLine "import ${basePackage}.entity.po.$entityName;"
        writer.writeLine "import org.springframework.data.jpa.repository.JpaRepository;"
        writer.writeLine ""
        writer.writeLine "import java.util.Date;"
        writer.writeLine "import java.util.List;"
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
        // 增加模板代码
        fieldList.each() { field -> genRepositoryExampleQuery(writer, config, entityName, field) }
        // 4、多个唯一类型生成Or（未实现） UserInfo findByUserNameOrPhone(String userName, String phone);
        def orMethodName = "findBy"
        def orParms = "("
        fieldList.each() { field ->
            // 非主键
            if (!field.isPrimaryKey) {
                // 唯一字段无法从定义中获取，只能用注释标记”唯一“（field.comment）
                if (field.comment != null && field.comment.contains("\u552f\u4e00")) {
                    orMethodName = orMethodName + Utils.theFirstLetterUppercase(field.name) + "Or"
                    orParms = orParms + field.type + " " + field.name + ","
                }
            }

        }
        if (orMethodName.endsWith("Or") && orParms.endsWith(",")) {
            orMethodName = orMethodName.substring(0, orMethodName.length() - 2)
            orParms = orParms.substring(0, orParms.length() - 1) + ")"
        }

        if (orMethodName.contains("Or") && orParms.contains(",")) {
            // 方法注释
            writer.writeLine ""
            writer.writeLine "\t/**"
            writer.writeLine "\t * ${orMethodName}"
            writer.writeLine "\t *@return"
            writer.writeLine "\t */"
            writer.writeLine "\t${entityName} ${orMethodName}${orParms};"
        }
        // 增加模板代码结束
        writer.writeLine ""
        writer.writeLine "}"
    }

    // 生成repo 模板查询代码
    // 1、唯一字段类型 findByxxx(type field); eg: UserInfo findByUserName(String userName);
    // 2、时间类型 List<UserInfo> findByCreateTimeBefore(Date createTime);
    // 3、时间类型 List<UserInfo> findByCreateTimeAfter(Date createTime);

    def static genRepositoryExampleQuery(writer, config, entityName, field) {
        // 非主键
        if (!field.isPrimaryKey) {
            //writer.writeLine "// ${field.comment}"
            def methodName = Utils.theFirstLetterUppercase(field.name)
            def comment = Utils.getDefaultValIfCurrentValIsBlank(field.comment, field.name)
            // 唯一字段无法从定义中获取，只能用注释标记”唯一“（field.comment）
            if (field.comment != null && field.comment.contains("\u552f\u4e00")) {
                // 方法注释
                writer.writeLine ""
                writer.writeLine "\t/**"
                writer.writeLine "\t * ${comment}"
                writer.writeLine "\t *@param ${field.name}"
                writer.writeLine "\t *@return"
                writer.writeLine "\t */"
                writer.writeLine "\t${entityName} findBy${methodName}(${field.type} ${field.name});"
            } else if ("Date" == field.type || "java.util.Date" == field.type) {
                // 方法注释
                writer.writeLine "\t/**"
                writer.writeLine "\t * ${comment}"
                writer.writeLine "\t *@param ${field.name}"
                writer.writeLine "\t *@return"
                writer.writeLine "\t */"
                writer.writeLine "\tList<${entityName}> findBy${methodName}Before(Date ${field.name});"
                writer.writeLine ""
                // 方法注释
                writer.writeLine "\t/**"
                writer.writeLine "\t * ${comment}"
                writer.writeLine "\t *@param ${field.name}"
                writer.writeLine "\t *@return"
                writer.writeLine "\t */"
                writer.writeLine "\tList<${entityName}> findBy${methodName}After(Date ${field.name});"
            }


        }

    }


    // 生成 RepositoryQueryDSL
    def static genRepositoryQueryDSL(writer, parentConfig, entityName, basePackage, pkType) {
        writer.writeLine "package ${basePackage}.repository.dsl;"
        writer.writeLine ""
        writer.writeLine "import com.querydsl.jpa.impl.JPAQuery;"
        writer.writeLine "import $parentConfig.package.$parentConfig.name;"
        writer.writeLine "import org.siu.myboot.core.utils.QueryBuilder;"
        writer.writeLine "import ${basePackage}.entity.po.Q${entityName};"
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
        writer.writeLine "\t/**"
        writer.writeLine "\t * Q${entityName} QueryDSL Object: Use jpaQueryFactory build JPAQuery"
        writer.writeLine "\t */"
        def lEntityName = Utils.theFirstLetterLowercase(entityName)
        writer.writeLine "\tprivate static final Q${entityName} q${entityName} = Q${entityName}.${lEntityName};"
        writer.writeLine ""
        writer.writeLine "\t/**"
        writer.writeLine "\t * queryExample"
        writer.writeLine "\t *"
        writer.writeLine "\t * @param pageable"
        writer.writeLine "\t * @return"
        writer.writeLine "\t */"
        writer.writeLine "\tpublic Page<${entityName}> queryExample(Pageable pageable) {"
        writer.writeLine "\t\tJPAQuery<${entityName}> countQuery = jpaQueryFactory.selectFrom(q${entityName});"
        writer.writeLine "\t\treturn basePageQuery(countQuery, pageable);"
        writer.writeLine "\t}"
        
        
        writer.writeLine ""
        
        writer.writeLine "    /**\n" +
                "     * queryPage\n" +
                "     *\n" +
                "     * @param pageable\n" +
                "     * @return\n" +
                "     */\n" +
                "    public Page<${entityName}> queryPage(Pageable pageable, ${entityName} params) {\n" +
                "        JPAQuery<${entityName}> countQuery = jpaQueryFactory.selectFrom(q${entityName});\n" +
                "        QueryBuilder.buildCondition(countQuery, q${entityName}, params);\n" +
                "        return basePageQuery(countQuery, pageable);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * queryList\n" +
                "     * \n" +
                "     * @param params\n" +
                "     * @return\n" +
                "     */\n" +
                "    public List<${entityName}> queryList(${entityName} params, List<OrderSpecifier<?>> orderSpecifiers) {\n" +
                "        JPAQuery<${entityName}> query = jpaQueryFactory.selectFrom(q${entityName});\n" +
                "        QueryBuilder.buildCondition(query, q${entityName}, params);\n" +
                "        if (Objects.nonNull(orderSpecifiers)) {\n" +
                "            for (OrderSpecifier<?> orderSpecifier : orderSpecifiers) {\n" +
                "                query.orderBy(orderSpecifier);\n" +
                "            }\n" +
                "        }\n" +
                "        return query.fetch();\n" +
                "    }"

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

    static def theFirstLetterLowercase(strs) {
        def part1 = strs.substring(0, 1).toLowerCase()
        def part2 = strs.substring(1, strs.length())
        return part1 + part2

    }

    static def theFirstLetterUppercase(strs) {
        def part1 = strs.substring(0, 1).toUpperCase()
        def part2 = strs.substring(1, strs.length())
        return part1 + part2

    }
}
