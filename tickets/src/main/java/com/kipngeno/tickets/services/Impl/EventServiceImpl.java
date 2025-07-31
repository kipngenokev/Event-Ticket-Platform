package com.kipngeno.tickets.services.Impl;

import com.kipngeno.tickets.domain.CreateEventRequest;
import com.kipngeno.tickets.domain.entities.Event;
import com.kipngeno.tickets.domain.entities.TicketType;
import com.kipngeno.tickets.domain.entities.User;
import com.kipngeno.tickets.exceptions.UserNotFoundException;
import com.kipngeno.tickets.repositories.EventRepository;
import com.kipngeno.tickets.repositories.UserRepository;
import com.kipngeno.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new UserNotFoundException(String
                        .format("User with ID '%s' not found", organizerId))
                );

        List<TicketType> ticketTypesToCreate= event.getTicketType().stream().map(ticketType -> {
            TicketType ticketTypeToCreate = new TicketType();
            ticketTypeToCreate.setName(ticketType.getName());
            ticketTypeToCreate.setDescription(ticketType.getDescription());
            ticketTypeToCreate.setPrice(ticketType.getPrice());
            ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
            return ticketTypeToCreate;
        }).toList();

         Event eventToCreate = new Event();

         eventToCreate.setName(event.getName());
         eventToCreate.setStart(event.getStart());
         eventToCreate.setEnd(event.getEnd());
         eventToCreate.setVenue(event.getVenue());
         eventToCreate.setSalesStart(event.getSalesStart());
         eventToCreate.setSalesEnd(event.getSalesEnd());
         eventToCreate.setStatus(event.getStatus());
         eventToCreate.setOrganizer(organizer);
         eventToCreate.setTicketTypes(ticketTypesToCreate);

          return eventRepository.save(eventToCreate);

    }
}
