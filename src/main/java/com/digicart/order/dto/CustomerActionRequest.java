package com.digicart.order.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Request/response DTO: Customer Action Request.
 */
public class CustomerActionRequest {

    @NotBlank
    private String action;

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
}
