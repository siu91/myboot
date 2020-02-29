package org.siu.myboot.server.service;

import com.querydsl.core.types.OrderSpecifier;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.qo.Sort;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.OrderCart;
import org.siu.myboot.server.entity.po.QOrderCart;
import org.siu.myboot.server.repository.OrderCartRepository;
import org.siu.myboot.server.repository.dsl.OrderCartRepositoryQueryDsl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * OrderCart service层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Slf4j
@Service
public class OrderCartService {

	@Resource
	private OrderCartRepository repository;
	@Resource
	private OrderCartRepositoryQueryDsl repositoryQueryDsl;
    /**
     * add 
     *
     * @param entity
     * @return
     */
    public OrderCart save(OrderCart entity) {
        return repositoryQueryDsl.save(entity);
    }

    /**
     * delete by ID
     *
     * @param id
     */
    public void delete(Long id) {
        repositoryQueryDsl.deleteById(id);
    }

    /**
     * update
     *
     * @param entity
     * @return
     */
    public OrderCart update(OrderCart entity) {
        return repositoryQueryDsl.save(entity);
    }

  /**
     * find by ID
     *
     * @param id
     * @return
     */
    public Optional<OrderCart> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }

   /**
     * get list by page
     *
     * @param params
     * @return
     */
    public PageData getPage(PageParams<OrderCart> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QOrderCart.orderCart);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<OrderCart> data = repositoryQueryDsl.queryPage(pageable, params.getTerms());

        return new PageData(data, params);
    }

    /**
     * get list
     *
     * @return
     */
    public List<OrderCart> getList(OrderCart orderCart, List<Sort> sorts) {
        List<OrderSpecifier<?>> sort = QueryBuilder.buildOrderSpecifier(sorts, QOrderCart.orderCart);
        return repositoryQueryDsl.queryList(orderCart, sort);
    }
}
