package org.siu.myboot.datasource.core.strategy;


import javax.sql.DataSource;
import java.util.List;
import java.util.Random;

/**
 * 负载均衡策略实现
 *
 * @Author Siu
 * @Date 2020/3/19 14:09
 * @Version 0.0.1
 */
public class RandomDataSourceSelectionStrategy implements DataSourceSelectionStrategy {

    Random random = new Random();

    @Override
    public DataSource lookup(List<DataSource> candidate) {
        return candidate.get(random.nextInt(candidate.size()));
    }
}
