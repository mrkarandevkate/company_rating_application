package com.fqts.rating.entity;

public class Company {

    private int companyId;
    private String companyName;
    private String industry ;
    private String description;

    public Company() {
    }

    public Company(int companyId, String companyName, String industry, String description) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.industry = industry;
        this.description = description;
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
}
