package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.Event;

import java.util.List;

public interface EventService {

    List<Event> getAll();

    List<Event> findByUserId(Long userId);

}
