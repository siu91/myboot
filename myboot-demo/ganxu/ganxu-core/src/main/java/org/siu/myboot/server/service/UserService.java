package org.siu.myboot.server.service;

import com.querydsl.core.types.OrderSpecifier;
import org.siu.myboot.core.entity.qo.PageParams;
import org.siu.myboot.core.entity.qo.Sort;
import org.siu.myboot.core.data.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.core.exception.UserRegisterException;
import org.siu.myboot.server.entity.po.*;
import org.siu.myboot.server.entity.dto.LoginUserVO;
import org.siu.myboot.server.repository.RoleRepository;
import org.siu.myboot.server.repository.UserRepository;
import org.siu.myboot.server.repository.UserRoleRepository;
import org.siu.myboot.server.repository.dsl.UserRepositoryQueryDsl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
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
@ConditionalOnClass(BCryptPasswordEncoder.class)
public class UserService {

    @Resource
    private UserRepository repository;

    @Resource
    private UserRoleRepository userRoleRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepositoryQueryDsl repositoryQueryDsl;

    @Autowired
    PasswordEncoder passwordEncoder;


    // region custom

    public User register(User entity, int userType) throws UserRegisterException {
        // 判断用户是否存在
        User userExist = repository.findByUserNameOrPhone(entity.getUserName(), entity.getPhone());
        if (userExist != null) {
            throw new UserRegisterException("用户名或手机号已存在");
        }
        // 注册
        return doRegister(entity, userType);
    }

    @Transactional
    User doRegister(User entity, int userType) {
        // 添加用户
        String pass = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(pass);
        entity.setDeleteStatus(0);
        entity.setVersion(0L);
         User user = repository.save(entity);
        // TODO 添加用户信息 userInfo

        // 添加默认角色
        Role defaultRole = roleRepository.findByRoleName("USER");
        UserRole userRoleDefault = new UserRole();
        userRoleDefault.setRole(defaultRole.getRoleName());
        userRoleDefault.setRoleId(defaultRole.getId());
        userRoleDefault.setUserId(user.getId());
        userRoleRepository.save(userRoleDefault);

        Role role;
        if (userType == 10) {
            role = roleRepository.findByRoleName("ADMIN");
            if (role != null) {
                // 添加管理员角色
                UserRole adminRole = new UserRole();
                adminRole.setRole(role.getRoleName());
                adminRole.setRoleId(role.getId());
                adminRole.setUserId(user.getId());
                userRoleRepository.save(adminRole);
            }
        }

        return user.setPassword("");
    }


    // endregion

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

    public LoginUserVO loginWithUsernameOrPhone(String userNameOrPhone, String password) {
        return repositoryQueryDsl.findUserInfoByIDsAndPass(userNameOrPhone, password);
    }
}
