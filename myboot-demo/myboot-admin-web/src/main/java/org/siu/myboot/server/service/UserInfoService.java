package org.siu.myboot.server.service;

import org.siu.myboot.server.repository.UserInfoRepository;
import org.siu.myboot.server.repository.dsl.UserInfoRepositoryQueryDsl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserInfo service层
 *
 * @author @Author Siu
 * @Date 2020-02-24 23:46:44
 * @Version 0.0.1
 */
@Service
public class UserInfoService {

	@Resource
	private UserInfoRepository repository;
	@Resource
	private UserInfoRepositoryQueryDsl repositoryQueryDsl;
}
