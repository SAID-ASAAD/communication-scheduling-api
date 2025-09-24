package com.example.communication_scheduling_api.infrastructure.repositories;

import com.example.communication_scheduling_api.infrastructure.entities.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
}
