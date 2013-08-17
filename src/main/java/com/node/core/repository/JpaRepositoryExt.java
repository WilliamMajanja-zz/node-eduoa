package com.node.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface JpaRepositoryExt<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 根据查询JPQL与参数列表创建Query对象.
     * 与find()函数可进行更加灵活的操作.
     *
     * @param params 数量可变的参数,按顺序绑定.
     */
    TypedQuery<T> createTypedQuery(final String jpql, final Object... params);

    TypedQuery<T> createCacheableTypedQuery(final String jpql, final Object... params);

    Query createQuery(final String jpql, final Object... params);

    Query createCacheableQuery(final String jpql, final Object... params);

    Query createNativeQuery(final String sql, final Object... params);

    Query createCacheableNativeQuery(final String sql, final Object... params);

    /**
     * 按属性过滤条件列表分页查找对象.
     */
    public Page<T> findPage(final Pageable pageRequest, final List<PropertyFilter> filters);

    /**
     * Merge the state of the given entity into the current persistence context.
     *
     * @param entity entity instance
     * @return the managed instance that the state was merged to
     */
    public T merge(T entity);
}
