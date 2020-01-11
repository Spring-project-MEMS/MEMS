package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.ResultIrm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
public interface ResultIrmRepository extends BaseResultRepository<ResultIrm> {
}
