package com.sp3nyuumonhh.sample1app.dao;

import java.io.Serializable;
import java.util.List;

public interface PeopleDAO<T> extends Serializable {
    public List<T> getAll();

    public T findById(long id);

    public List<T> findByName(String name);
}
