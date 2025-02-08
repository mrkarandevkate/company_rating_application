package com.fqts.company.mapper;

import com.fqts.company.controller.model.*;

public class ControllerToServiceCompanyMapper {
    public static com.fqts.company.service.model.CreateCompanyRequest mapServicetoControllerCreateUser(CreateCompanyRequest createCompanyRequest) {
        com.fqts.company.service.model.CreateCompanyRequest companyRequest = new com.fqts.company.service.model.CreateCompanyRequest();
        companyRequest.setCompanyName(createCompanyRequest.getCompanyName());
        companyRequest.setIndustry(createCompanyRequest.getIndustry());
        companyRequest.setDescription(createCompanyRequest.getDescription());
        return companyRequest;
    }

    public static com.fqts.company.service.model.CompanyUpdateRequest mapServicetoControllerUdpateUser(CompanyUpdateRequest companyUpdateRequest) {
        com.fqts.company.service.model.CompanyUpdateRequest companyRequest = new com.fqts.company.service.model.CompanyUpdateRequest();
        companyRequest.setCompanyId(companyUpdateRequest.getCompanyId());
        companyRequest.setCompanyName(companyUpdateRequest.getCompanyName());
        companyRequest.setIndustry(companyUpdateRequest.getIndustry());
        companyRequest.setDescription(companyUpdateRequest.getDescription());
        return companyRequest;
    }

    public static CompanyIdResponse mapServiceCompanyIdToControllerCompanyId(com.fqts.company.service.model.CompanyIdResponse companyResponse) {
        CompanyIdResponse companyIdResponse = new CompanyIdResponse();
        return new CompanyIdResponse().companyId(companyResponse.getCompanyId());
    }

    public static CompanyList mapServiceToCompanyLisT(com.fqts.company.service.model.CompanyList companyListDetails) {
        CompanyList companyList = new CompanyList();
        for(com.fqts.company.service.model.Company company:companyListDetails){
            Company controllerCompany = new Company();
            controllerCompany.setCompanyId(company.getCompanyId());
            controllerCompany.setCompanyName(company.getCompanyName());
            controllerCompany.setIndustry(company.getIndustry());
            controllerCompany.setDescription(company.getDescription());
            controllerCompany.setCreatedAt(company.getCreatedAt());
            companyList.add(controllerCompany);
        }
        return companyList;
    }

    public static Company mapServiceToControllerCompany(com.fqts.company.service.model.Company company) {
        Company controllerCompany = new Company();
        controllerCompany.setCompanyId(company.getCompanyId());
        controllerCompany.setCompanyName(company.getCompanyName());
        controllerCompany.setIndustry(company.getIndustry());
        controllerCompany.setCreatedAt(company.getCreatedAt());
        controllerCompany.setDescription(company.getDescription());
        return controllerCompany;
    }
}
