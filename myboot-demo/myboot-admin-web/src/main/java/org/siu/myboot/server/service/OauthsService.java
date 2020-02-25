package org.siu.myboot.server.service;

import org.siu.myboot.core.entity.request.Page;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.Oauths;
import org.siu.myboot.server.repository.OauthsRepository;
import org.siu.myboot.server.repository.dsl.OauthsRepositoryQueryDsl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Oauths service层
 *
 * @author @Author Siu
 * @Date 2020-02-25 09:02:29
 * @Version 0.0.1
 */
@Service
public class OauthsService {

    @Resource
    private OauthsRepository repository;
    @Resource
    private OauthsRepositoryQueryDsl repositoryQueryDsl;



    public PageData<Oauths> getList(Page page,Oauths oauths){

    	return null;
	}
	/**
	 *
	 * @param id
	 * @return
	 */
	public Optional<Oauths> findById(Long id) {
		return repositoryQueryDsl.findById(id);
    }
}
