package com.fqts.company.service;

import com.fqts.company.exception.CompanyNotFoundException;
import com.fqts.company.exception.DuplicateCompanyException;
import com.fqts.company.exception.NotNullException;
import com.fqts.company.mapper.ServiceToEntityCompanyMapper;
import com.fqts.company.service.model.CompanyIdResponse;
import com.fqts.company.repository.CompanyRepository;
import com.fqts.company.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImp implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public CompanyIdResponse addCompanyDetails(CreateCompanyRequest createCompanyRequest) {
        try {
            if (createCompanyRequest.getCompanyName() == null || createCompanyRequest.getCompanyName().isEmpty()) {
                throw new NotNullException("Company name cannot be null or empty");
            }
            if (createCompanyRequest.getIndustry() == null || createCompanyRequest.getIndustry().isEmpty()) {
                throw new NotNullException("Industry cannot be null or empty");
            }
            if (createCompanyRequest.getDescription() == null || createCompanyRequest.getDescription().isEmpty()) {
                throw new NotNullException("Description cannot be null or empty");
            }

            Optional<com.fqts.company.entity.Company> existingCompany = companyRepository.findByCompanyName(createCompanyRequest.getCompanyName());
            if (existingCompany.isPresent()) {
                throw new DuplicateCompanyException("Company with name " + createCompanyRequest.getCompanyName() + " already exists");
            }

            com.fqts.company.entity.Company savedEntity = companyRepository.save(ServiceToEntityCompanyMapper.mapEntityToService(createCompanyRequest));
            return new CompanyIdResponse().companyId(savedEntity.getCompanyId());

        } catch (NotNullException e) {
            throw e;
        } catch (DuplicateCompanyException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while adding the company details", e);
        }
    }

    public CompanyIdResponse updateCompanyDetails(CompanyUpdateRequest companyUpdateRequest) {
        try {
            // Check if any required fields are null
            if (companyUpdateRequest.getCompanyName() == null || companyUpdateRequest.getCompanyName().isEmpty()) {
                throw new NotNullException("Company name cannot be null or empty");
            }
            if (companyUpdateRequest.getIndustry() == null || companyUpdateRequest.getIndustry().isEmpty()) {
                throw new NotNullException("Industry cannot be null or empty");
            }
            if (companyUpdateRequest.getDescription() == null || companyUpdateRequest.getDescription().isEmpty()) {
                throw new NotNullException("Description cannot be null or empty");
            }

            com.fqts.company.entity.Company existingCompany = companyRepository.findById(companyUpdateRequest.getCompanyId())
                    .orElseThrow(() -> new CompanyNotFoundException("Company with ID " + companyUpdateRequest.getCompanyId() + " not found"));

            com.fqts.company.entity.Company savedEntity = companyRepository.save(ServiceToEntityCompanyMapper.mapUpdateCompany(companyUpdateRequest));
            return new CompanyIdResponse().companyId(savedEntity.getCompanyId());

        } catch (NotNullException e) {
            throw e;
        } catch (CompanyNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the company details", e);
        }
    }

    public CompanyList getAllCompanyDetails() {
        try {
            List<com.fqts.company.entity.Company> companyListDetails = companyRepository.findAll();
            CompanyList companyList = ServiceToEntityCompanyMapper.mapEntityToServiceCompanylist(companyListDetails);
            return companyList;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the company details", e);
        }
    }

    public Company getCompanyDetails(int companyID) {
        try {
            com.fqts.company.entity.Company company = companyRepository.findById(companyID).orElseThrow(
                    () -> new CompanyNotFoundException("Company with ID " + companyID + " not found"));

            return ServiceToEntityCompanyMapper.mapEntityToServiceCompany(company);
        } catch (CompanyNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the company details", e);
        }
    }

    public void deleteCompanyDetails(int companyID) {
        try {
            if (!companyRepository.existsById(companyID)) {
                throw new CompanyNotFoundException("Company with ID " + companyID + " does not exist");
            }
            companyRepository.deleteById(companyID);
        } catch (CompanyNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the company details", e);
        }
    }
}
