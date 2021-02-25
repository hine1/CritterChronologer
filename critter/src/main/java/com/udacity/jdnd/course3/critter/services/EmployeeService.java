package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){return employeeRepository.save(employee);}
    public Employee findEmployee(Long employeeId){return employeeRepository.findEmployeeById(employeeId);}
    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId){employeeRepository.setAvailability(daysAvailable, employeeId);}
    public List<Employee> getAvailableEmployees(DayOfWeek day, Set<EmployeeSkill> skills){
        List<Employee> availableEmployees = employeeRepository.findByDaysAvailable(day);
        List<Employee> employeesForServices = new ArrayList<>();
        for(Employee e : availableEmployees){
            if(e.getSkills().containsAll(skills)) {
                employeesForServices.add(e);
            }
        }
        return employeesForServices;
    }
}
