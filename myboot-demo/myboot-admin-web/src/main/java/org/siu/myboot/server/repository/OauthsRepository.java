package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.Oauths;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA 可以根据接口方法名来实现数据库操作
 * 主要的语法是
 * findXXBy
 * readAXXBy
 * queryXXBy
 * countXXBy
 * getXXBy
 *
 * @Author Siu
 * @Date 2020/2/20 11:41
 * @Version 0.0.1
 */
public interface OauthsRepository extends JpaRepository<Oauths, Long> {


}