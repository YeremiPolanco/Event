package com.idat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.demo.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    
}
