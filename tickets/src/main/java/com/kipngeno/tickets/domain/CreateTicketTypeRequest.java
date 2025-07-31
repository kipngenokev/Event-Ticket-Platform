package com.kipngeno.tickets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeRequest {

    private String name;
    private String description;
    private double price;
    private Integer totalAvailable;

}
