package com.node.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public class JpaRepositoryFactoryBeanExt<T extends JpaRepository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected RepositoryFactorySupport doCreateRepositoryFactory() {
        return new JpaRepositoryFactoryExt(entityManager);
    }
}
