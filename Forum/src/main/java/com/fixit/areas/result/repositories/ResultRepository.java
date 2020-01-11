package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.Result;
import org.hibernate.type.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ResultRepository  extends BaseResultRepository<Result> {
 //   @Query("select e from #{#entityName} e")
   // List<EntityType> findThemAll();



}
