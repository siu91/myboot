package org.siu.myboot.server.service;

import org.siu.myboot.core.entity.request.PageAndSort;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.Oauths;
import org.siu.myboot.server.entity.po.QOauths;
import org.siu.myboot.server.entity.po.UserInfo;
import org.siu.myboot.server.repository.OauthsRepository;
import org.siu.myboot.server.repository.dsl.OauthsRepositoryQueryDsl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
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


    public PageData getList(PageAndSort page, Oauths oauths) {

        QSort sort = new QSort(QOauths.oauths.createTime.asc()).and(QOauths.oauths.updateTime.asc());
        Pageable pageable = PageRequest.of(page.getPage(), page.getLimit(), sort);
        Page<Oauths> data = repositoryQueryDsl.queryExample(pageable);

        return new PageData(data, page);
    }

    /**
     * @param id
     * @return
     */
    public Optional<Oauths> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }
}
