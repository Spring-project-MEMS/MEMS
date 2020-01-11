package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseResultRepository<EntityType extends Result> extends JpaRepository<Result, Long> {

        @Query("select e from #{#entityName} e")
        List<EntityType> findThemAll();

}

