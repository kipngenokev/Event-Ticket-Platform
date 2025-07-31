package com.kipngeno.tickets.services;

import com.kipngeno.tickets.domain.CreateEventRequest;
import com.kipngeno.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event );
}
