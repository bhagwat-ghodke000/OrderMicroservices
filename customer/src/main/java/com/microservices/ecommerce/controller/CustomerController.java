package com.microservices.ecommerce.controller;

import com.microservices.ecommerce.customer.Customer;
import com.microservices.ecommerce.customer.CustomerRequest;
import com.microservices.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){

        String user = this.customerService.createUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest request){
        String customer = this.customerService.updateCustomer(request);
        return new ResponseEntity<>(customer,HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> allCustomer = this.customerService.getAllCustomer();
        return new ResponseEntity<>(allCustomer,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String id){
        Customer customer = this.customerService.getCustomer(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }


    @GetMapping("exist_customer/{id}")
    public ResponseEntity<Boolean> CustomerIsPresent(@PathVariable String id){
        Boolean customer = this.customerService.existCustomer(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteCustomer(@PathVariable String id){
        String customer = this.customerService.deleteCustomer(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

}
