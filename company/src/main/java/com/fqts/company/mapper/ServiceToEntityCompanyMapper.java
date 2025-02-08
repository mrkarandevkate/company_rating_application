package com.fqts.company.mapper;

import com.fqts.company.entity.Company;
import com.fqts.company.service.model.CompanyList;
import com.fqts.company.service.model.CompanyUpdateRequest;
import com.fqts.company.service.model.CreateCompanyRequest;

import java.util.List;

public class ServiceToEntityCompanyMapper {
    public static com.fqts.company.entity.Company mapEntityToService(CreateCompanyRequest createCompanyRequest) {
        com.fqts.company.entity.Company companyEntity = new com.fqts.company.entity.Company();
        companyEntity.setCompanyName(createCompanyRequest.getCompanyName());
        companyEntity.setIndustry(createCompanyRequest.getIndustry());
        companyEntity.setDescription(createCompanyRequest.getDescription());
        return companyEntity;
    }
    public static com.fqts.company.entity.Company mapUpdateCompany(CompanyUpdateRequest companyUpdateRequest) {
        com.fqts.company.entity.Company companyRequest =new  com.fqts.company.entity.Company();
        companyRequest.setCompanyId(companyUpdateRequest.getCompanyId());
        companyRequest.setCompanyName(companyUpdateRequest.getCompanyName());
        companyRequest.setIndustry(companyUpdateRequest.getIndustry());
        companyRequest.setDescription(companyUpdateRequest.getDescription());
        return companyRequest;
    }
    public static CompanyList mapEntityToServiceCompanylist(List<Company> companyListDetails) {
        CompanyList companyList = new CompanyList();
        for(com.fqts.company.entity.Company company :companyListDetails){
            com.fqts.company.service.model.Company serviceCompany = new com.fqts.company.service.model.Company();
            serviceCompany.setCompanyId(company.getCompanyId());
            serviceCompany.setCompanyName(company.getCompanyName());
            serviceCompany.setIndustry(company.getIndustry());
            serviceCompany.setCreatedAt(company.getCreated_At());
            serviceCompany.setDescription(company.getDescription());
            companyList.add(serviceCompany);
        }
        return companyList;
    }
    public static com.fqts.company.service.model.Company mapEntityToServiceCompany(com.fqts.company.entity.Company company) {
        com.fqts.company.service.model.Company serviceCompany = new com.fqts.company.service.model.Company();
        serviceCompany.setCompanyId(company.getCompanyId());
        serviceCompany.setCompanyName(company.getCompanyName());
        serviceCompany.setIndustry(company.getIndustry());
        serviceCompany.setCreatedAt(company.getCreated_At());
        serviceCompany.setDescription(company.getDescription());
        return serviceCompany;
    }
}
