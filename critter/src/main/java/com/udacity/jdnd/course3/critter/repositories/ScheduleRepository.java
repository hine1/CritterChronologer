package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findScheduleByPetsId(Long petId);
    List<Schedule> findScheduleByEmployeesId(Long employeeId);
//    @Query("select s from schedule s inner join pet p on p.id=s.pets_id where p.customer_id=:customerId")
    List<Schedule> findScheduleByPets_CustomerId(Long customerId);


}
