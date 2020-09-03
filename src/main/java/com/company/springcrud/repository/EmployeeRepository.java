package com.company.springcrud.repository;

import com.company.springcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCompanyId(Long companyId);
}
