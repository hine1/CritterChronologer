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
public class PetService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Pet save(Pet pet) {
        petRepository.save(pet);
        Customer customer = pet.getCustomer();
        customer.addPet(pet);
        customerRepository.save(customer);
        return pet;
    }

    public Pet getPet(Long petId){
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isPresent()) return optionalPet.get();
        else return null;
    }

    public List<Pet> getPets(){return petRepository.findAll();}

    public List<Pet> getPetsByOwnerId(Long ownerId){return petRepository.findByCustomerId(ownerId);}

}
