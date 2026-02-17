package com.sp3nyuumonhh.sample1app.dao;

import java.io.Serializable;
import java.util.List;

public interface PeopleDAO<T> extends Serializable {
    public List<T> getAll();

    public T findById(long id);

    public List<T> findByName(String name);

    public List<T> find(String fstr);

    public List<T> getPage(int page, int limit);

    public List<T> findByAge(int min, int max);
}
