package com.microservices.ecommerce.service;

import com.microservices.ecommerce.customer.Customer;
import com.microservices.ecommerce.customer.CustomerRequest;
import com.microservices.ecommerce.exception.CustomerNotFoundException;
import com.microservices.ecommerce.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public String createUser(CustomerRequest customerRequest){

        Customer map = this.modelMapper.map(customerRequest, Customer.class);

        UUID uuid = UUID.randomUUID();

        map.setAddress(customerRequest.address());
        map.setLastname(customerRequest.lastname());
        map.setEmail(customerRequest.email());
        map.setFirstname(customerRequest.firstname());
        map.setId(uuid.toString());

        Customer save = this.customerRepository.save(map);

        return save.getId();

    }

    public String updateCustomer(CustomerRequest request){

        Customer customer = this.customerRepository.findById(request.Id()).orElseThrow(() -> new CustomerNotFoundException("Can not update customer"));

        customer.setFirstname(request.firstname());
        customer.setLastname(request.lastname());
        customer.setEmail(request.email());
        customer.setAddress(request.address());

        this.customerRepository.save(customer);

        return "Customer Update Successfully";
    }

    public List<Customer> getAllCustomer(){
        List<Customer> customers = this.customerRepository.findAll();
        return customers;
    }

    public Customer getCustomer(String id){
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer Not Founds"));
        return customer;
    }

    public Boolean existCustomer(String id){
        Boolean customer = this.customerRepository.findById(id).isPresent();
        return customer;
    }

    public String deleteCustomer(String id){
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer Not Founds"));
        this.customerRepository.delete(customer);
        return "Customer Deleted Successfully";
    }
}
