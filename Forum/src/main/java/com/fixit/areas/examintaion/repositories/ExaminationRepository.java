package com.fixit.areas.examintaion.repositories;

import com.fixit.areas.examintaion.enitities.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {
}
