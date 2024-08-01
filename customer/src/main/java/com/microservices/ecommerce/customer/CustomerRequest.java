package com.microservices.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String Id,

         @NotNull(message = "Customer first name is required")
         String firstname,

         @NotNull(message = "Customer last Name is required")
         String lastname,

         @NotNull(message = "Customer email is required")
         @Email(message = "Customer email is nor valid from email id")
         String email,
         Address address
) {
}
