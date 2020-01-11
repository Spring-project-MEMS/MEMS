package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ResultRepository<EntityType extends Result> extends JpaRepository<Result, Long> {
    @Query("select e from #{#entityName} e")
    List<EntityType> findThemAll();



}
