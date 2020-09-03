package com.company.springcrud.controller;

import com.company.springcrud.entity.Employee;
import com.company.springcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/company/{companyId}/employee")
    public List<Employee> getContactByCompanyId(@PathVariable long companyId) {
        return employeeService.getByCompanyId(companyId);
    }

    @PostMapping("/company/{companyId}/employee")
    public Employee addEmployee(@PathVariable long companyId, @Validated @RequestBody Employee employee) {
        return employeeService.addEmployee(companyId, employee);
    }

    @GetMapping("/employeeById/{id}")
    public Employee findEmployeeById(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/company/{companyId}/employee/{employeeId}")
    public String deleteEmploye(@PathVariable long companyId, @PathVariable long employeeId) {
        return employeeService.deleteEmployee(companyId, employeeId);
    }

    @PutMapping("/company/{companyId}/employee/{employeeId}")
    public Employee updateEmployee(@PathVariable long companyId, @PathVariable long employeeId, @Validated @RequestBody Employee employee) {
        return employeeService.updateEmployee(companyId, employeeId, employee);
    }
}
