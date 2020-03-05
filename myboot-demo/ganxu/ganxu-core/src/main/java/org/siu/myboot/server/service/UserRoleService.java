package org.siu.myboot.server.service;

import com.querydsl.core.types.OrderSpecifier;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.qo.Sort;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.UserRole;
import org.siu.myboot.server.entity.po.QUserRole;
import org.siu.myboot.server.repository.UserRoleRepository;
import org.siu.myboot.server.repository.dsl.UserRoleRepositoryQueryDsl;
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
 * UserRole service层
 *
 * @author @Author Siu
 * @Date 2020-03-05 14:32:42
 * @Version 0.0.1
 */
@Slf4j
@Service
public class UserRoleService {

	@Resource
	private UserRoleRepository repository;
	@Resource
	private UserRoleRepositoryQueryDsl repositoryQueryDsl;
    /**
     * add 
     *
     * @param entity
     * @return
     */
    public UserRole save(UserRole entity) {
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
    public UserRole update(UserRole entity) {
        return repositoryQueryDsl.save(entity);
    }

  /**
     * find by ID
     *
     * @param id
     * @return
     */
    public Optional<UserRole> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }

   /**
     * get list by page
     *
     * @param params
     * @return
     */
    public PageData getPage(PageParams<UserRole> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QUserRole.userRole);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<UserRole> data = repositoryQueryDsl.queryPage(pageable, params.getTerms());

        return new PageData(data, params);
    }

    /**
     * get list
     *
     * @return
     */
    public List<UserRole> getList(UserRole userRole, List<Sort> sorts) {
        List<OrderSpecifier<?>> sort = QueryBuilder.buildOrderSpecifier(sorts, QUserRole.userRole);
        return repositoryQueryDsl.queryList(userRole, sort);
    }
}
