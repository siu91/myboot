package org.siu.myboot.server.service;

import org.siu.myboot.core.entity.request.Params;
import org.siu.myboot.core.entity.request.QueryBuilder;
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


    public PageData getList(Params<Oauths> params) {
        QSort sort = QueryBuilder.buildSort(params.getSort(), QOauths.oauths);
        Pageable pageable = PageRequest.of(params.getPage(), params.getLimit(), sort);

        Page<Oauths> data = repositoryQueryDsl.query(pageable, params.getQuery());

        return new PageData(data, params);
    }

    /**
     * @param id
     * @return
     */
    public Optional<Oauths> findById(Long id) {
        return repositoryQueryDsl.findById(id);
    }


   /* private EntityPathBase getFieldValueByFieldName(String fieldName, QOauths qOauths) {
        try {
            Field field = qOauths.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            Object o = field.get(qOauths);
            if (o instanceof EntityPathBase) {
                return (EntityPathBase) o;
            }
        } catch (Exception e) {

            return null;
        }
        return null;
    }*/

}
