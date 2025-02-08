package com.fqts.company.controller;
import com.fqts.company.controller.model.*;
import com.fqts.company.mapper.ControllerToServiceCompanyMapper;
import com.fqts.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping("/addcompany")
    public ResponseEntity<CompanyIdResponse> addCompany(@RequestBody CreateCompanyRequest createCompanyRequest){
        com.fqts.company.service.model.CompanyIdResponse companyResponse = companyService.addCompanyDetails(ControllerToServiceCompanyMapper.mapServicetoControllerCreateUser(createCompanyRequest));
        CompanyIdResponse  companyIdResponse= ControllerToServiceCompanyMapper.mapServiceCompanyIdToControllerCompanyId(companyResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyIdResponse);
    }

    @PutMapping("/udpatecompany")
    public ResponseEntity<CompanyIdResponse> udpateCompany(@RequestBody CompanyUpdateRequest companyUpdateRequest){
        com.fqts.company.service.model.CompanyIdResponse companyResponse = companyService.updateCompanyDetails(ControllerToServiceCompanyMapper.mapServicetoControllerUdpateUser(companyUpdateRequest));
        CompanyIdResponse  companyIdResponse=ControllerToServiceCompanyMapper.mapServiceCompanyIdToControllerCompanyId(companyResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyIdResponse);
    }

    @GetMapping("/getallcompany")
    public ResponseEntity<CompanyList> getAllCompany(){
        com.fqts.company.service.model.CompanyList companyListDetails = companyService.getAllCompanyDetails();
        return  ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceCompanyMapper.mapServiceToCompanyLisT(companyListDetails));
    }

    @GetMapping("/getcompany/{companyID}")
    public ResponseEntity<Company> getCompany(@PathVariable("companyID") int companyID){
        com.fqts.company.service.model.Company company= companyService.getCompanyDetails(companyID);
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceCompanyMapper.mapServiceToControllerCompany(company));
    }

    @DeleteMapping("/deletecompany/{companyID}")
    public ResponseEntity<String> deleteUser(@PathVariable("companyID") int companyID){
        companyService.deletCompanyDetails(companyID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Company Deleted Successfully");
    }
}
