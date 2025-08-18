package com.kipngeno.tickets.controllers;

import com.kipngeno.tickets.domain.CreateEventRequest;
import com.kipngeno.tickets.domain.dtos.CreateEventRequestDto;
import com.kipngeno.tickets.domain.dtos.CreateEventResponseDto;
import com.kipngeno.tickets.domain.entities.Event;
import com.kipngeno.tickets.mappers.EventMapper;
import com.kipngeno.tickets.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
@Tag(name = "Event Management", description = "APIs for managing events")
public class EventController {

    private final EventMapper eventMapper;

    private final EventService eventService;

    @PostMapping
    @Operation(
        summary = "Create a new event",
        description = "Creates a new event with the provided details and ticket types"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Event created successfully",
            content = @Content(schema = @Schema(implementation = CreateEventResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Unauthorized - Invalid or missing JWT token"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )
    })
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Parameter(description = "Event creation request", required = true)
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto
    ) {
        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
        UUID userId = UUID.fromString(jwt.getSubject());
        Event createdEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createdEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }
}
