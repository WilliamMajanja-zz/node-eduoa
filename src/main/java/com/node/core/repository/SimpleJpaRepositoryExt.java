package com.node.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SimpleJpaRepositoryExt<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements JpaRepositoryExt<T, ID> {
    private final EntityManager em;

    public SimpleJpaRepositoryExt(JpaEntityInformation<T, ?> tJpaEntityInformation, EntityManager entityManager) {
        super(tJpaEntityInformation, entityManager);
        this.em = entityManager;
    }

    public SimpleJpaRepositoryExt(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    @Override
    public TypedQuery<T> createTypedQuery(String jpql, Object... params) {
        Assert.hasText(jpql, "queryString不能为空");
        TypedQuery<T> query = (TypedQuery<T>) em.createQuery(jpql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                //ordinal parameters are 1-based
                query.setParameter(i + 1, params[i]);
            }
        }
        return query;
    }

    @Override
    public TypedQuery<T> createCacheableTypedQuery(String jpql, Object... params) {
        return createTypedQuery(jpql, params).setHint("org.hibernate.cacheable", true);
    }

    @Override
    public Query createQuery(String jpql, Object... params) {
        Assert.hasText(jpql, "queryString不能为空");
        Query query = em.createQuery(jpql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                //ordinal parameters are 1-based
                query.setParameter(i + 1, params[i]);
            }
        }
        return query;
    }

    @Override
    public Query createCacheableQuery(String jpql, Object... params) {
        return createQuery(jpql, params).setHint("org.hibernate.cacheable", true);
    }

    @Override
    public Query createNativeQuery(String sql, Object... params) {
        Assert.hasText(sql, "queryString不能为空");
        Query query = em.createNativeQuery(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                //ordinal parameters are 1-based
                query.setParameter(i + 1, params[i]);
            }
        }
        return query;
    }

    @Override
    public Query createCacheableNativeQuery(String sql, Object... params) {
        return createNativeQuery(sql, params).setHint("org.hibernate.cacheable", true);
    }

    @Override
    public Page<T> findPage(Pageable pageRequest, List<PropertyFilter> filters) {
        Assert.notNull(pageRequest, "page不能为空");
        Specification<T> specifications = buildCriterionByPropertyFilter(filters);
        return findAll(specifications, pageRequest);
    }

    /**
     * 按属性条件列表创建 Specification 数组,辅助函数.
     */
    private Specification<T> buildCriterionByPropertyFilter(final List<PropertyFilter> filters) {
        Specifications<T> specifications = null;
        for (PropertyFilter filter : filters) {
            Specification<T> criterion = buildSpecification(filter.getPropertyName(), filter.getMatchValue(),
                    filter.getMatchType());
            if (specifications == null) {
                specifications = Specifications.where(criterion);
            } else {
                specifications.and(criterion);
            }
        }
        return specifications;
    }

    /**
     * 按属性条件参数创建 Specification ,辅助函数.
     */
    @SuppressWarnings("unchecked")
    private Specification<T> buildSpecification(final String propertyName, final Object propertyValue, final PropertyFilter.MatchType matchType) {
        Assert.hasText(propertyName, "propertyName不能为空");
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = null;
                Path path = root.get(propertyName);
                if (path.getJavaType().getAnnotation(Entity.class) != null) {
                    path = root.get(propertyName).get("id");
                }
                //根据MatchType构造 Specification
                switch (matchType) {
                    case EQ:
                        predicate = builder.equal(path, propertyValue);
                        break;
                    case LIKE:
                        predicate = builder.like(path, (String) propertyValue, '%');
                        break;
                    case LE:
                        predicate = builder.le(path, (Number) propertyValue);
                        break;
                    case LT:
                        predicate = builder.lt(path, (Number) propertyValue);
                        predicate = builder.lessThan(path, builder.parameter(Date.class, propertyValue+""));
                        break;
                    case GE:
                        predicate = builder.ge(path, (Number) propertyValue);
                        break;
                    case GT:
                        predicate = builder.gt(path, (Number) propertyValue);
                        break;
                    case IN:
                        predicate = path.in(((List) propertyValue).toArray());
                }
                builder.or(predicate);
                return predicate;
            }
        };
    }

    /**
     * Merge the state of the given entity into the current persistence context.
     *
     * @param entity entity instance
     * @return the managed instance that the state was merged to
     */
    public T merge(T entity) {
        return em.merge(entity);
    }

}
