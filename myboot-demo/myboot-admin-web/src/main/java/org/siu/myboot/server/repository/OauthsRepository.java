package org.siu.myboot.server.repository;

import org.siu.myboot.server.entity.po.Oauths;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Oauths Repository层
 *
 * @author @Author Siu
 * @Date 2020-02-23 15:13:42
 * @Version 0.0.1
 */
public interface OauthsRepository extends JpaRepository<Oauths, Long> {

}
