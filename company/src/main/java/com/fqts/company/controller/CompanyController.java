package com.fqts.company.controller;

import com.fqts.company.controller.model.*;
import com.fqts.company.mapper.ControllerToServiceCompanyMapper;
import com.fqts.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/company")
@Tag(name = "Company Management", description = "APIs for managing company data, including adding, updating, retrieving, and deleting companies.")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Operation(summary = "Add a new company", description = "Creates a new company in the system and returns its unique ID. This API is used when a new company is being registered in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Company created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data, missing required fields")
    })
    @PostMapping("/addcompany")
    public ResponseEntity<CompanyIdResponse> addCompany(@RequestBody CreateCompanyRequest createCompanyRequest) {
        com.fqts.company.service.model.CompanyIdResponse companyResponse =
                companyService.addCompanyDetails(ControllerToServiceCompanyMapper.mapServicetoControllerCreateUser(createCompanyRequest));
        CompanyIdResponse companyIdResponse =
                ControllerToServiceCompanyMapper.mapServiceCompanyIdToControllerCompanyId(companyResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyIdResponse);
    }

    @Operation(summary = "Update company details", description = "Updates the existing details of a company. This API is typically used when company information changes, such as name, address, or contact details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company updated successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found in the system")
    })

    @PutMapping("/updatecompany")
    public ResponseEntity<CompanyIdResponse> updateCompany(@RequestBody CompanyUpdateRequest companyUpdateRequest) {
        com.fqts.company.service.model.CompanyIdResponse companyResponse =
                companyService.updateCompanyDetails(ControllerToServiceCompanyMapper.mapServicetoControllerUdpateUser(companyUpdateRequest));
        CompanyIdResponse companyIdResponse =
                ControllerToServiceCompanyMapper.mapServiceCompanyIdToControllerCompanyId(companyResponse);
        return ResponseEntity.status(HttpStatus.OK).body(companyIdResponse);
    }

    @Operation(summary = "Get all companies", description = "Fetches a complete list of all registered companies in the system. Useful for displaying company directories or management dashboards.")
    @ApiResponse(responseCode = "200", description = "List of companies retrieved successfully")
    @GetMapping("/getallcompany")
    public ResponseEntity<CompanyList> getAllCompany() {
        com.fqts.company.service.model.CompanyList companyListDetails = companyService.getAllCompanyDetails();
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceCompanyMapper.mapServiceToCompanyLisT(companyListDetails));
    }

    @Operation(summary = "Get company by ID", description = "Retrieves details of a specific company using its unique ID. This is useful for viewing individual company profiles or verifying company information.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found in the system")
    })
    @GetMapping("/getcompany/{companyID}")
    public ResponseEntity<Company> getCompany(@PathVariable("companyID") int companyID) {
        com.fqts.company.service.model.Company company = companyService.getCompanyDetails(companyID);
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceCompanyMapper.mapServiceToControllerCompany(company));
    }

    @Operation(summary = "Delete company by ID", description = "Removes a company from the system using its unique ID. This action is irreversible and should be performed with caution.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Company deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found in the system")
    })
    @DeleteMapping("/deletecompany/{companyID}")
    public ResponseEntity<String> deleteUser(@PathVariable("companyID") int companyID) {
        companyService.deleteCompanyDetails(companyID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Company Deleted Successfully");
    }
}
