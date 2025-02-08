package com.fqts.company.service;

import com.fqts.company.exception.CompanyNotFoundException;  // Custom exception for company not found
import com.fqts.company.mapper.ServiceToEntityCompanyMapper;
import com.fqts.company.service.model.CompanyIdResponse;
import com.fqts.company.repository.CompanyRepository;
import com.fqts.company.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    // Add company details
    public CompanyIdResponse addCompanyDetails(CreateCompanyRequest createCompanyRequest) {
        try {
            com.fqts.company.entity.Company savedEntity = companyRepository.save(ServiceToEntityCompanyMapper.mapEntityToService(createCompanyRequest));
            return new CompanyIdResponse().companyId(savedEntity.getCompanyId());
        } catch (Exception e) {
            // Catch any unexpected exception and throw a general runtime exception
            throw new RuntimeException("An error occurred while adding the company details", e);
        }
    }

    // Update company details
    public CompanyIdResponse updateCompanyDetails(CompanyUpdateRequest companyUpdateRequest) {
        try {
            com.fqts.company.entity.Company savedEntity = companyRepository.save(ServiceToEntityCompanyMapper.mapUpdateCompany(companyUpdateRequest));
            return new CompanyIdResponse().companyId(savedEntity.getCompanyId());
        } catch (Exception e) {
            // Catch any unexpected exception and throw a general runtime exception
            throw new RuntimeException("An error occurred while updating the company details", e);
        }
    }

    // Get all company details
    public CompanyList getAllCompanyDetails() {
        try {
            List<com.fqts.company.entity.Company> companyListDetails = companyRepository.findAll();
            CompanyList companyList = ServiceToEntityCompanyMapper.mapEntityToServiceCompanylist(companyListDetails);
            return companyList;
        } catch (Exception e) {
            // Catch any unexpected exception and throw a general runtime exception
            throw new RuntimeException("An error occurred while fetching the company details", e);
        }
    }

    // Get company details by ID
    public Company getCompanyDetails(int companyID) {
        com.fqts.company.entity.Company company = companyRepository.findById(companyID).orElse(null);
        if (company == null) {
            throw new CompanyNotFoundException("Company with ID " + companyID + " not found");
        }
        return ServiceToEntityCompanyMapper.mapEntityToServiceCompany(company);
    }

    // Delete company details by ID
    public void deletCompanyDetails(int companyID) {
        if (!companyRepository.existsById(companyID)) {
            throw new CompanyNotFoundException("Company with ID " + companyID + " does not exist");
        }
        try {
            companyRepository.deleteById(companyID);
        } catch (Exception e) {
            // Catch any unexpected exception and throw a general runtime exception
            throw new RuntimeException("An error occurred while deleting the company details", e);
        }
    }
}
