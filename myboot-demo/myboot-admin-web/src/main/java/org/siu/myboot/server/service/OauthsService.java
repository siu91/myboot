package org.siu.myboot.server.service;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.siu.myboot.core.entity.request.PageAndSort;
import org.siu.myboot.core.entity.request.PageAndSortParams;
import org.siu.myboot.core.entity.request.QueryBuilder;
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
import java.lang.reflect.Field;
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


    public PageData getList(PageAndSortParams<Oauths> page) {
        QSort sort = QueryBuilder.buildSort(page.getSort(), QOauths.oauths);
        Pageable pageable = PageRequest.of(page.getPage(), page.getLimit(), sort);

        Page<Oauths> data = repositoryQueryDsl.query(pageable, page.getQuery());

        return new PageData(data, page);
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
