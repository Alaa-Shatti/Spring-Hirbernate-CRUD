package com.company.springcrud.service;

import com.company.springcrud.entity.Employee;
import com.company.springcrud.exception.NotFoundException;
import com.company.springcrud.repository.CompanyRepository;
import com.company.springcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List<Employee> getByCompanyId(long companyId) {

        if (!companyRepository.existsById(companyId)) {
            throw new NotFoundException("Company Not Found!");
        }
        return employeeRepository.findByCompanyId(companyId);
    }

    public Employee addEmployee(long companyId, Employee employee) {
        return companyRepository.findById(companyId)
                .map(company -> {
                    employee.setCompany(company);
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new NotFoundException("Company Not Found!"));
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(long companyId, long employeeId, Employee employeeUpdated) {
        if (!companyRepository.existsById(companyId)) {
            throw new NotFoundException("Company Not Found!");
        }
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    employee.setNo(employeeUpdated.getNo());
                    employee.setTc(employeeUpdated.getTc());
                    employee.setName(employeeUpdated.getName());
                    employee.setSurname(employeeUpdated.getSurname());
                    employee.setTel(employeeUpdated.getTel());
                    employee.setSex(employeeUpdated.getSex());
                    employee.setDepartment(employeeUpdated.getDepartment());
                    employee.setEmail(employeeUpdated.getEmail());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new NotFoundException("Employee Not Found!"));
    }

    public String deleteEmployee(long companyId, long employeeId) {
        if (!companyRepository.existsById(companyId)) {
            throw new NotFoundException("Company Not Found!");
        }
        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return "Deleted Successfully!" + employeeId;
                }).orElseThrow(() -> new NotFoundException("Contact Not Found"));
    }

}
