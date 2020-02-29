package org.siu.myboot.server.service;

import com.querydsl.core.types.OrderSpecifier;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.qo.Sort;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.UserAccountLog;
import org.siu.myboot.server.entity.po.QUserAccountLog;
import org.siu.myboot.server.repository.UserAccountLogRepository;
import org.siu.myboot.server.repository.dsl.UserAccountLogRepositoryQueryDsl;
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
 * UserAccountLog service层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Slf4j
@Service
public class UserAccountLogService {

	@Resource
	private UserAccountLogRepository repository;
	@Resource
	private UserAccountLogRepositoryQueryDsl repositoryQueryDsl;
    /**
     * add 
     *
     * @param entity
     * @return
     */
    public UserAccountLog save(UserAccountLog entity) {
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
    public UserAccountLog update(UserAccountLog entity) {
        return repositoryQueryDsl.save(entity);
    }

  /**
     * find by ID
     *
     * @param id
     * @return
     */
    public Optional<UserAccountLog> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }

   /**
     * get list by page
     *
     * @param params
     * @return
     */
    public PageData getPage(PageParams<UserAccountLog> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QUserAccountLog.userAccountLog);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<UserAccountLog> data = repositoryQueryDsl.queryPage(pageable, params.getTerms());

        return new PageData(data, params);
    }

    /**
     * get list
     *
     * @return
     */
    public List<UserAccountLog> getList(UserAccountLog userAccountLog, List<Sort> sorts) {
        List<OrderSpecifier<?>> sort = QueryBuilder.buildOrderSpecifier(sorts, QUserAccountLog.userAccountLog);
        return repositoryQueryDsl.queryList(userAccountLog, sort);
    }
}
