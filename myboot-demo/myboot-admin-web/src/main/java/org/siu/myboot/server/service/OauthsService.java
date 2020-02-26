package org.siu.myboot.server.service;

import org.siu.myboot.core.entity.qo.Params;
import org.siu.myboot.core.utils.QueryBuilder;
import org.siu.myboot.core.entity.vo.PageData;
import org.siu.myboot.server.entity.po.Oauths;
import org.siu.myboot.server.entity.po.QOauths;
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


    /**
     * 新增
     *
     * @param oauths
     * @return
     */
    public Oauths save(Oauths oauths) {
        return repositoryQueryDsl.save(oauths);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    public void delete(Long id) {
        repositoryQueryDsl.deleteById(id);
    }

    /**
     * 更新
     *
     * @param oauths
     * @return
     */
    public Oauths update(Oauths oauths) {
        return repositoryQueryDsl.save(oauths);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public Optional<Oauths> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }


    /**
     * 分页查询列表
     *
     * @param params
     * @return
     */
    public PageData getList(Params<Oauths> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QOauths.oauths);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<Oauths> data = repositoryQueryDsl.query(pageable, params.getQuery());

        return new PageData(data, params);
    }

}
