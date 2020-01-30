package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.Result;
import com.fixit.areas.result.entities.ResultBlood;
import com.fixit.areas.users.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
public interface ResultBloodRepository  extends BaseResultRepository<ResultBlood> {
    Set<ResultBlood> findAllByPatient(Users user);

}

