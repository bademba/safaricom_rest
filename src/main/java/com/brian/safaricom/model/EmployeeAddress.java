/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.safaricom.model;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Brian Ademba <brian.ademba@gmail.com>
 */
public class EmployeeAddress {

    public String street;
    public String houseNumber;
    public String zipCode;
    public String boxNumber;

    private static final String DEFAULT_VALUE = "";
    
    @NotNull
    public String getStreet() {
        if (street == null) {
            street = DEFAULT_VALUE;
        }
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @NotNull
    public String getHouseNumber() {
        if (houseNumber == null) {
            houseNumber = DEFAULT_VALUE;
        }
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @NotNull
    public String getZipCode() {
        if (zipCode == null) {
            zipCode = DEFAULT_VALUE;
        }
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @NotNull
    public String getBoxNumber() {
        if (boxNumber == null) {
            boxNumber = DEFAULT_VALUE;
        }
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

//    @Override
//    public String toString()
//    {
//        return "ClassPojo [Street = "+Street+", HouseNumber = "+HouseNumber+", ZipCode = "+ZipCode+", BoxNumber = "+BoxNumber+"]";
//    }
}
