package com.fqts.company.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int companyId;
    @Column(unique = true,nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String industry;
    @Column(nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date created_At= new Date();

    public Company() {
    }

    public Company(int companyId, String companyName, String industry, String description, Date created_At) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.industry = industry;
        this.description = description;
        this.created_At = created_At;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }
}
