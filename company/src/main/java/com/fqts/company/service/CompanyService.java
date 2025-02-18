package com.fqts.company.service;

import com.fqts.company.service.model.*;

public interface CompanyService {

    CompanyIdResponse addCompanyDetails(CreateCompanyRequest createCompanyRequest);

    CompanyIdResponse updateCompanyDetails(CompanyUpdateRequest companyUpdateRequest);

    CompanyList getAllCompanyDetails();

    Company getCompanyDetails(int companyID);

    void deleteCompanyDetails(int companyID);
}
