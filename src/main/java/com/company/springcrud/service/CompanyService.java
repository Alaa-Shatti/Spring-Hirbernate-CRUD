package com.company.springcrud.service;

import com.company.springcrud.entity.Company;
import com.company.springcrud.exception.NotFoundException;
import com.company.springcrud.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(long id) {
        Optional<Company> optCompany = companyRepository.findById(id);
        if (optCompany.isPresent()) {
            return optCompany.get();
        } else {
            throw new NotFoundException("Student Not Found with id " + id);
        }
    }

    public List<Company> getAllCompanys() {
        return companyRepository.findAll();
    }

    public String deleteCompany(long id) {
        return companyRepository.findById(id)
                .map(company -> {
                    companyRepository.delete(company);
                    return "Delete Successfully!" + id;
                }).orElseThrow(() -> new NotFoundException("Company Not Found with id" + id));
    }

    public Company updateCompany(long id, Company companyUpdated) {
        return companyRepository.findById(id)
                .map(company -> {
                    company.setName(companyUpdated.getName());
                    company.setNumberEmployee(companyUpdated.getNumberEmployee());
                    company.setFoundingDate(companyUpdated.getFoundingDate());
                    company.setDepartment(companyUpdated.getDepartment());
                    return companyRepository.save(company);
                }).orElseThrow(() -> new NotFoundException("Company Not Found with id " + id));
    }

}
