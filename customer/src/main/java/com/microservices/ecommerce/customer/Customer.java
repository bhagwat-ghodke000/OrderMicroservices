package com.microservices.ecommerce.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

   @Id
   private String Id;

   private String firstname;

   private String lastname;

   private String email;

   private Address address;


}
