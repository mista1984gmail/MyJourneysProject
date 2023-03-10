package com.example.MyJourneysProject.persistence.repository;


import com.example.MyJourneysProject.persistence.entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyRepository extends JpaRepository<Journey,Long> {
}
