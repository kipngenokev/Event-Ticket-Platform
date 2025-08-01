package com.kipngeno.tickets.mappers;

import com.kipngeno.tickets.domain.CreateEventRequest;
import com.kipngeno.tickets.domain.CreateTicketTypeRequest;
import com.kipngeno.tickets.domain.dtos.CreateEventRequestDto;
import com.kipngeno.tickets.domain.dtos.CreateEventResponseDto;
import com.kipngeno.tickets.domain.dtos.CreateTicketTypeRequestDto;
import com.kipngeno.tickets.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);
}
