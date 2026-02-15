package com.sp3nyuumonhh.sample1app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp3nyuumonhh.sample1app.entity.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    public Optional<People> findById(Long name);
}
