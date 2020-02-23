package org.siu.myboot.server.service;

import org.siu.myboot.server.repository.OauthsRepository;
import org.siu.myboot.server.repository.dsl.OauthsRepositoryQueryDsl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Oauths service层
 *
 * @author @Author Siu
 * @Date 2020-02-23 15:13:42
 * @Version 0.0.1
 */
@Service
public class OauthsService {

	@Resource
	private OauthsRepository repository;
	@Resource
	private OauthsRepositoryQueryDsl repositoryQueryDsl;
}
