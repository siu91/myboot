package org.siu.myboot.core.data.config.mp;


import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @Author Siu
 * @Date 2020/3/16 9:50
 * @Version 0.0.1
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource", name = "persistence", havingValue = "mybatis", matchIfMissing = false)
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * mybatis-plus 乐观锁插件
     *
     * 注解实体字段 @Version 必须要
     * 支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


}
