package org.siu.myboot.server.service;

import com.querydsl.core.types.OrderSpecifier;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.qo.Sort;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.Oauths;
import org.siu.myboot.server.entity.po.QOauths;
import org.siu.myboot.server.repository.OauthsRepository;
import org.siu.myboot.server.repository.dsl.OauthsRepositoryQueryDsl;
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
 * Oauths service层
 *
 * @author @Author Siu
 * @Date 2020-02-29 11:43:18
 * @Version 0.0.1
 */
@Slf4j
@Service
public class OauthsService {

	@Resource
	private OauthsRepository repository;
	@Resource
	private OauthsRepositoryQueryDsl repositoryQueryDsl;
    /**
     * add 
     *
     * @param entity
     * @return
     */
    public Oauths save(Oauths entity) {
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
    public Oauths update(Oauths entity) {
        return repositoryQueryDsl.save(entity);
    }

  /**
     * find by ID
     *
     * @param id
     * @return
     */
    public Optional<Oauths> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }

   /**
     * get list by page
     *
     * @param params
     * @return
     */
    public PageData getPage(PageParams<Oauths> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QOauths.oauths);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<Oauths> data = repositoryQueryDsl.queryPage(pageable, params.getTerms());

        return new PageData(data, params);
    }

    /**
     * get list
     *
     * @return
     */
    public List<Oauths> getList(Oauths oauths, List<Sort> sorts) {
        List<OrderSpecifier<?>> sort = QueryBuilder.buildOrderSpecifier(sorts, QOauths.oauths);
        return repositoryQueryDsl.queryList(oauths, sort);
    }
}
