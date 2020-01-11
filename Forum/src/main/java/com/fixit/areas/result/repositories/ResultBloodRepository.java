package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.Result;
import com.fixit.areas.result.entities.ResultBlood;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ResultBloodRepository  extends ResultRepository {

}

