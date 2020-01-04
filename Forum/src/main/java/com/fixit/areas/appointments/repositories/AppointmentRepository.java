package com.fixit.areas.appointments.repositories;

import com.fixit.areas.appointments.entities.Appointment;
import com.fixit.areas.ward.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Set<Appointment> findByWard(Ward ward);
}
