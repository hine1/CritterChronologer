package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;

    public Customer save(Customer customer){return customerRepository.save(customer);}

    public Customer findById(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) return optionalCustomer.get();
        else return null;
    }

    public List<Customer> getAllCustomers(){return customerRepository.findAll();}
    public Customer getPetOwner(Long petId){
        Pet pet=petRepository.getOne(petId);
        return customerRepository.findByPets(pet);
    }
}
