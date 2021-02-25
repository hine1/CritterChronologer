package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    public static Pet convertPetDTOToEntity(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    public static PetDTO convertEntityToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Customer owner = customerService.findById(petDTO.getOwnerId());
        Pet pet = convertPetDTOToEntity(petDTO);

        pet.setCustomer(owner);
        petService.save(pet);

        return convertEntityToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertEntityToPetDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getPets().stream().map(pet -> convertEntityToPetDTO(pet)).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByOwnerId(ownerId).stream().map(pet -> convertEntityToPetDTO(pet)).collect(Collectors.toList());
    }
}
