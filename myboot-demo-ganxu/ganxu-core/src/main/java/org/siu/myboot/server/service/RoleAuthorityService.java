package org.siu.myboot.server.service;

import com.querydsl.core.types.OrderSpecifier;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.qo.Sort;
import org.siu.myboot.core.data.jpa.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.RoleAuthority;
import org.siu.myboot.server.entity.po.QRoleAuthority;
import org.siu.myboot.server.repository.RoleAuthorityRepository;
import org.siu.myboot.server.repository.dsl.RoleAuthorityRepositoryQueryDsl;
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
 * RoleAuthority service层
 *
 * @author @Author Siu
 * @Date 2020-03-05 14:32:42
 * @Version 0.0.1
 */
@Slf4j
@Service
public class RoleAuthorityService {

	@Resource
	private RoleAuthorityRepository repository;
	@Resource
	private RoleAuthorityRepositoryQueryDsl repositoryQueryDsl;
    /**
     * add 
     *
     * @param entity
     * @return
     */
    public RoleAuthority save(RoleAuthority entity) {
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
    public RoleAuthority update(RoleAuthority entity) {
        return repositoryQueryDsl.save(entity);
    }

  /**
     * find by ID
     *
     * @param id
     * @return
     */
    public Optional<RoleAuthority> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }

   /**
     * get list by page
     *
     * @param params
     * @return
     */
    public PageData getPage(PageParams<RoleAuthority> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QRoleAuthority.roleAuthority);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<RoleAuthority> data = repositoryQueryDsl.queryPage(pageable, params.getTerms());

        return new PageData(data, params);
    }

    /**
     * get list
     *
     * @return
     */
    public List<RoleAuthority> getList(RoleAuthority roleAuthority, List<Sort> sorts) {
        List<OrderSpecifier<?>> sort = QueryBuilder.buildOrderSpecifier(sorts, QRoleAuthority.roleAuthority);
        return repositoryQueryDsl.queryList(roleAuthority, sort);
    }
}
