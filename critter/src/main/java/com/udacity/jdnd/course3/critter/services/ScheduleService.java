package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule){return scheduleRepository.save(schedule);}
    public List<Schedule> getAllSchedules(){return scheduleRepository.findAll();}
    public List<Schedule> getScheduleForPet(Long petId){return scheduleRepository.findScheduleByPetsId(petId);}
    public List<Schedule> getScheduleForEmployee(Long employeeId){return scheduleRepository.findScheduleByEmployeesId(employeeId);}
    public List<Schedule> getScheduleForCustomer(Long customerId){return scheduleRepository.findScheduleByPets_CustomerId(customerId);}

}
