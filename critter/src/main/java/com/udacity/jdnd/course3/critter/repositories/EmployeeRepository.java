package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //get employee by id
    Employee findEmployeeById(Long id);
    //set employee's availability
    @Modifying
    @Query("Update Employee e set e.daysAvailable=:daysAvailable where e.id=:id")
    void setAvailability(Set<DayOfWeek> daysAvailable, Long id);

    //find available employee for a task on a chosen date
    List<Employee> findByDaysAvailable(DayOfWeek day);
}
