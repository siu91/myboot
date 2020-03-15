package org.siu.myboot.server.service;

import com.querydsl.core.types.OrderSpecifier;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.qo.Sort;
import org.siu.myboot.core.datasource.jpa.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.UserInfo;
import org.siu.myboot.server.entity.po.QUserInfo;
import org.siu.myboot.server.repository.UserInfoRepository;
import org.siu.myboot.server.repository.dsl.UserInfoRepositoryQueryDsl;
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
 * UserInfo service层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:42:19
 * @Version 0.0.1
 */
@Slf4j
@Service
public class UserInfoService {

	@Resource
	private UserInfoRepository repository;
	@Resource
	private UserInfoRepositoryQueryDsl repositoryQueryDsl;
    /**
     * add 
     *
     * @param entity
     * @return
     */
    public UserInfo save(UserInfo entity) {
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
    public UserInfo update(UserInfo entity) {
        return repositoryQueryDsl.save(entity);
    }

  /**
     * find by ID
     *
     * @param id
     * @return
     */
    public Optional<UserInfo> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }

   /**
     * get list by page
     *
     * @param params
     * @return
     */
    public PageData getPage(PageParams<UserInfo> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QUserInfo.userInfo);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<UserInfo> data = repositoryQueryDsl.queryPage(pageable, params.getTerms());

        return new PageData(data, params);
    }

    /**
     * get list
     *
     * @return
     */
    public List<UserInfo> getList(UserInfo userInfo, List<Sort> sorts) {
        List<OrderSpecifier<?>> sort = QueryBuilder.buildOrderSpecifier(sorts, QUserInfo.userInfo);
        return repositoryQueryDsl.queryList(userInfo, sort);
    }
}
