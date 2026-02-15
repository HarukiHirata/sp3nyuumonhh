package com.sp3nyuumonhh.sample1app.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;

import com.sp3nyuumonhh.sample1app.entity.People;

@Repository
public class PeopleDAOPeopleImpl implements PeopleDAO<People> {
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    public PeopleDAOPeopleImpl() {
        super();
    }

    @Override
    public List<People> getAll() {
        Query query = entityManager.createQuery("from People");
        @SuppressWarnings("unchecked")
        List<People> list = query.getResultList();
        entityManager.close();
        return list;
    }

    @Override
    public People findById(long id) {
        return (People) entityManager.createQuery("from People where id = " + id).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<People> findByName(String name) {
        return (List<People>) entityManager.createQuery("from People where name = '" + name + "'").getResultList();
    }
}
