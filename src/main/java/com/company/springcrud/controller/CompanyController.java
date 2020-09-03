package com.company.springcrud.controller;

import com.company.springcrud.entity.Company;
import com.company.springcrud.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companys")
    public List<Company> findAllCompany() {
        return companyService.getAllCompanys();
    }

    @GetMapping("/company/{id}")
    public Company getCompanyByID(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping("/addcompany")
    public Company addCompany(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @DeleteMapping("/company/{id}")
    public String deleteCompany(@PathVariable long id) {
        return companyService.deleteCompany(id);
    }

    @PutMapping("/updates/{id}")
    public Company updateCompany(@PathVariable long id,@Validated @RequestBody Company company) {
        return companyService.updateCompany(id,company);
    }
}
