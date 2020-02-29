package org.siu.myboot.server.service;

import com.querydsl.core.types.OrderSpecifier;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.qo.Sort;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.UserPointLog;
import org.siu.myboot.server.entity.po.QUserPointLog;
import org.siu.myboot.server.repository.UserPointLogRepository;
import org.siu.myboot.server.repository.dsl.UserPointLogRepositoryQueryDsl;
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
 * UserPointLog service层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Slf4j
@Service
public class UserPointLogService {

	@Resource
	private UserPointLogRepository repository;
	@Resource
	private UserPointLogRepositoryQueryDsl repositoryQueryDsl;
    /**
     * add 
     *
     * @param entity
     * @return
     */
    public UserPointLog save(UserPointLog entity) {
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
    public UserPointLog update(UserPointLog entity) {
        return repositoryQueryDsl.save(entity);
    }

  /**
     * find by ID
     *
     * @param id
     * @return
     */
    public Optional<UserPointLog> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }

   /**
     * get list by page
     *
     * @param params
     * @return
     */
    public PageData getPage(PageParams<UserPointLog> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QUserPointLog.userPointLog);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<UserPointLog> data = repositoryQueryDsl.queryPage(pageable, params.getTerms());

        return new PageData(data, params);
    }

    /**
     * get list
     *
     * @return
     */
    public List<UserPointLog> getList(UserPointLog userPointLog, List<Sort> sorts) {
        List<OrderSpecifier<?>> sort = QueryBuilder.buildOrderSpecifier(sorts, QUserPointLog.userPointLog);
        return repositoryQueryDsl.queryList(userPointLog, sort);
    }
}
