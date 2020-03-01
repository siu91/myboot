package org.siu.myboot.server.service;

import com.querydsl.core.types.OrderSpecifier;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.qo.Sort;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.User;
import org.siu.myboot.server.entity.po.QUser;
import org.siu.myboot.server.entity.vo.UserVO;
import org.siu.myboot.server.repository.UserRepository;
import org.siu.myboot.server.repository.dsl.UserRepositoryQueryDsl;
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
 * User service层
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@Slf4j
@Service
public class UserService {

    @Resource
    private UserRepository repository;
    @Resource
    private UserRepositoryQueryDsl repositoryQueryDsl;

    /**
     * add
     *
     * @param entity
     * @return
     */
    public User save(User entity) {
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
    public User update(User entity) {
        return repositoryQueryDsl.save(entity);
    }

    /**
     * find by ID
     *
     * @param id
     * @return
     */
    public Optional<User> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }

    /**
     * get list by page
     *
     * @param params
     * @return
     */
    public PageData getPage(PageParams<User> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QUser.user);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<User> data = repositoryQueryDsl.queryPage(pageable, params.getTerms());

        return new PageData(data, params);
    }

    /**
     * get list
     *
     * @return
     */
    public List<User> getList(User user, List<Sort> sorts) {
        List<OrderSpecifier<?>> sort = QueryBuilder.buildOrderSpecifier(sorts, QUser.user);
        return repositoryQueryDsl.queryList(user, sort);
    }


    public User findByUserNameOrPhone(String userNameOrPhone) {
        return repository.findByUserNameOrPhone(userNameOrPhone, userNameOrPhone);
    }

    public UserVO loginWithUsernameOrPhone(String userNameOrPhone, String password) {
        return repositoryQueryDsl.loginWithUsernameOrPhone(userNameOrPhone, password);
    }
}
