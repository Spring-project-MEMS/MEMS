package com.fixit.areas.result.repositories;

import com.fixit.areas.result.entities.Result;

import javax.transaction.Transactional;

@Transactional
public interface ResultRepository  extends BaseResultRepository<Result> {



}
