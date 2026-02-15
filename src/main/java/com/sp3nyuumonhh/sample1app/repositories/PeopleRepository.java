package com.sp3nyuumonhh.sample1app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.query.Param;

import com.sp3nyuumonhh.sample1app.entity.People;
import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    @Query("SELECT d FROM People d ORDER BY d.name")
    List<People> findAllOrderByName();

    @Query("from People where age > :min and age < :max")
    public List<People> findByAge(@Param("min") int min, @Param("max") int max);

    public Optional<People> findById(Long name);
}
