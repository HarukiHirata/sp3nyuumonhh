package com.sp3nyuumonhh.sample1app.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<People> find(String fstr) {
        List<People> list = null;
        String qstr = "from People where id = ?1 or name like ?2 or mail like ?3";
        Long fid = 0L;
        try {
            fid = Long.parseLong(fstr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Query query = entityManager.createQuery(qstr)
                .setParameter(1, fid)
                .setParameter(2, "%" + fstr + "%")
                .setParameter(3, fstr + "%@%");
        list = query.getResultList();
        return list;
    }

    @Override
    public List<People> getPage(int page, int limit) {
        int offset = page * limit;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<People> query = builder.createQuery(People.class);
        Root<People> root = query.from(People.class);
        query.select(root);
        return (List<People>) entityManager
                .createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<People> findByAge(int min, int max) {
        return (List<People>) entityManager.createQuery("from People where age between " + min + "and " + max)
                .getResultList();
    }
}
