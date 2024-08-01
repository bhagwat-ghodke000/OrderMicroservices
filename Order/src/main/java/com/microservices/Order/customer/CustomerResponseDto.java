package com.microservices.Order.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private String Id;

    private String firstname;

    private String lastname;

    private String email;


}
