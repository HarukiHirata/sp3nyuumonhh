package com.sp3nyuumonhh.sample1app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp3nyuumonhh.sample1app.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
