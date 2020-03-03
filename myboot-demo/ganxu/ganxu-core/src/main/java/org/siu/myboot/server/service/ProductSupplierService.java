package org.siu.myboot.server.service;

import com.querydsl.core.types.OrderSpecifier;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.qo.Sort;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.ProductSupplier;
import org.siu.myboot.server.entity.po.QProductSupplier;
import org.siu.myboot.server.repository.ProductSupplierRepository;
import org.siu.myboot.server.repository.dsl.ProductSupplierRepositoryQueryDsl;
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
 * ProductSupplier service层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:45:14
 * @Version 0.0.1
 */
@Slf4j
@Service
public class ProductSupplierService {

	@Resource
	private ProductSupplierRepository repository;
	@Resource
	private ProductSupplierRepositoryQueryDsl repositoryQueryDsl;
    /**
     * add 
     *
     * @param entity
     * @return
     */
    public ProductSupplier save(ProductSupplier entity) {
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
    public ProductSupplier update(ProductSupplier entity) {
        return repositoryQueryDsl.save(entity);
    }

  /**
     * find by ID
     *
     * @param id
     * @return
     */
    public Optional<ProductSupplier> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }

   /**
     * get list by page
     *
     * @param params
     * @return
     */
    public PageData getPage(PageParams<ProductSupplier> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QProductSupplier.productSupplier);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<ProductSupplier> data = repositoryQueryDsl.queryPage(pageable, params.getTerms());

        return new PageData(data, params);
    }

    /**
     * get list
     *
     * @return
     */
    public List<ProductSupplier> getList(ProductSupplier productSupplier, List<Sort> sorts) {
        List<OrderSpecifier<?>> sort = QueryBuilder.buildOrderSpecifier(sorts, QProductSupplier.productSupplier);
        return repositoryQueryDsl.queryList(productSupplier, sort);
    }
}
