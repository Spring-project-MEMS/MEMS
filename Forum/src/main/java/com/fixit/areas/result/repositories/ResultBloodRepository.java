package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.Result;
import com.fixit.areas.result.entities.ResultBlood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
public interface ResultBloodRepository  extends BaseResultRepository<ResultBlood> {

}

