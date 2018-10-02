/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.safaricom.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Brian Ademba <brian.ademba@gmail.com>
 */
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({"employeeName", "emailAddress", "age", "department", "reportingDate", "education"})
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY)
@JsonIgnoreProperties({"id"})
public class Employee {
    
    public int id;

    @JsonProperty("employeeName")
    public String employeeName;

    @JsonProperty("emailAddress")
    public String emailAddress;

    @JsonProperty("age")
    public int age;

    @JsonProperty("department")
    public String department;

    @JsonProperty("reportingDate")
    public Date reportingDate;

    @JsonProperty("phoneNumber")
    public String phoneNumber;

    @JsonProperty("identificationType")
    public String identificationType;

    @JsonProperty("identificationNumber")
    public String identificationNumber;

    @JsonProperty("nationality")
    public String nationality;

    public int days;

    @JsonProperty("education")
    public Education education;

    @JsonProperty("employeeAddress")
    public EmployeeAddress employeeAddress;

    private static final String DEFAULT_VALUE = "";

    public Employee() {
    }

    public Employee(String employeeName, String emailAddress, int age, String department, Date reportingDate, String phoneNumber, String identificationType, String identificationNumber, String nationality, Education education, EmployeeAddress employeeAddress) {

        this.employeeName = employeeName;
        this.emailAddress = emailAddress;
        this.age = age;
        this.department = department;
        this.reportingDate = reportingDate;
        this.phoneNumber = phoneNumber;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.nationality = nationality;
        this.education = education;
        this.employeeAddress = employeeAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getReportingDate() {
        return reportingDate;
    }

    public void setReportingDate(Date reportingDate) {
        this.reportingDate = reportingDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotNull
    public String getIdentificationType() {
        if (identificationType == null) {
            identificationType = DEFAULT_VALUE;

        }
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    @NotNull
    public String getIdentificationNumber() {
        if (identificationNumber == null) {
            identificationNumber = DEFAULT_VALUE;
        }
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonIgnore
    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public EmployeeAddress getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(EmployeeAddress employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

//     @Override
//    public String toString(){
//        return  " [Employee=" + EmployeeName + ", EmailAddress=" + EmailAddress + ", Age=" + Age + ", Department=" + Department + ", ReportingDate=" + ReportingDate + ", phoneNumber=" + phoneNumber + "]";
//               
//    }
}
