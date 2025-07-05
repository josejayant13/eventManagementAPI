package com.josejayant.EventManagementAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.josejayant.EventManagementAPI.model.Event;


@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    
}
