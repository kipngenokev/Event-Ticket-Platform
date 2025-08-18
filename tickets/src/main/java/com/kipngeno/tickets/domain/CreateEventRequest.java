package com.kipngeno.tickets.domain;

import com.kipngeno.tickets.domain.entities.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequest {

    @NotBlank(message = "Event name is required")
    @Size(min = 3, max = 200, message = "Event name must be between 3 and 200 characters")
    private String name;
    
    @NotNull(message = "Start date is required")
    private LocalDateTime start;
    
    @NotNull(message = "End date is required")
    private LocalDateTime end;
    
    @NotBlank(message = "Venue is required")
    private String venue;
    
    @NotNull(message = "Sales start date is required")
    private LocalDateTime salesStart;
    
    @NotNull(message = "Sales end date is required")
    private LocalDateTime salesEnd;
    
    private EventStatusEnum status;
    
    @Valid
    @NotNull(message = "At least one ticket type is required")
    @Size(min = 1, message = "At least one ticket type must be provided")
    private List<CreateTicketTypeRequest> ticketType = new ArrayList<>();
}
