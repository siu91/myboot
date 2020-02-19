package org.siu.myboot.admin.dao;

import org.siu.myboot.admin.param.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @Author Siu
 * @Date 2020/2/18 15:17
 * @Version 0.0.1
 */
@Slf4j
public class UserSql {

    public String getCount(UserParam userParam) {
        String sql = new SQL() {{
            SELECT("count(1)");
            FROM("users");
            if (StringUtils.isEmpty(userParam.getName())) {
                WHERE("name = #{name}");
            }

        }}.toString();
        log.info(sql);
        return sql;
    }
}
