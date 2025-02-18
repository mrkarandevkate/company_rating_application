package com.fqts.company.repository;

import com.fqts.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Optional<Company> findByCompanyName(String companyName);
}
