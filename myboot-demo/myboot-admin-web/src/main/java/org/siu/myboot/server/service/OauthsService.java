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
 * @Date 2020-02-26 22:12:56
 * @Version 0.0.1
 */
@Service
public class OauthsService {

	@Resource
	private OauthsRepository repository;
	@Resource
	private OauthsRepositoryQueryDsl repositoryQueryDsl;
    /**
     * add 
     *
     * @param entity
     * @return
     */
    public Oauths save(Oauths entity) {
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
    public Oauths update(Oauths entity) {
        return repositoryQueryDsl.save(entity);
    }

  /**
     * find by ID
     *
     * @param id
     * @return
     */
    public Optional<Oauths> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }

  /**
     * get list by page
     *
     * @param params
     * @return
     */
    public PageData getList(Params<Oauths> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QOauths.oauths);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);
        Page<Oauths> data = repositoryQueryDsl.query(pageable, params.getTerms());

        return new PageData(data, params);
    }
}
