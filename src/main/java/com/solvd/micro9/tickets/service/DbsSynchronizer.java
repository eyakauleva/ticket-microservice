package com.solvd.micro9.tickets.service;

import com.solvd.micro9.tickets.domain.es.Es;

public interface DbsSynchronizer {

    void sync(Es event);

}
