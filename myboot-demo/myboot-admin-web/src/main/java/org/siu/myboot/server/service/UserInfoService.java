package org.siu.myboot.server.service;
import org.siu.myboot.core.entity.qo.Params;
import org.siu.myboot.core.utils.QueryBuilder;
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
import java.util.Optional;

/**
 * UserInfo service层
 *
 * @author @Author Siu
 * @Date 2020-02-26 22:12:56
 * @Version 0.0.1
 */
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
    public PageData getList(Params<UserInfo> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QUserInfo.userInfo);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<UserInfo> data = repositoryQueryDsl.query(pageable, params.getTerms());

        return new PageData(data, params);
    }
}
