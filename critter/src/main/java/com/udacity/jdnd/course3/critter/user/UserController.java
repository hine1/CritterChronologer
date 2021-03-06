package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;

    private CustomerDTO convertEntityToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    private Customer convertCustomerDTOToEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }
    private EmployeeDTO convertEntityToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private Employee convertEmployeeDTOToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        return employee;
    }


    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer targetCustomer = convertCustomerDTOToEntity(customerDTO);
        return convertEntityToCustomerDTO(customerService.save(targetCustomer));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers().stream().map(customer -> convertEntityToCustomerDTO(customer)).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return convertEntityToCustomerDTO(customerService.getPetOwner(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertEmployeeDTOToEntity(employeeDTO);
        return convertEntityToEmployeeDTO(employeeService.save(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEntityToEmployeeDTO(employeeService.findEmployee(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable,employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
//        return employeeService.getAllEmployees().stream().map(employee -> convertEntityToEmployeeRequestDTO(employee)).collect(Collectors.toList());
        DayOfWeek requestedDay = employeeRequestDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills = employeeRequestDTO.getSkills();
        return employeeService.getAvailableEmployees(requestedDay, skills).stream().map(employee -> convertEntityToEmployeeDTO(employee)).collect(Collectors.toList());
    }

}
