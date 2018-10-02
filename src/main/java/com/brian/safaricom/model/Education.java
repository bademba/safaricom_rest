/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.safaricom.model;

/**
 *
 * @author Brian Ademba <brian.ademba@gmail.com>
 */
public class Education {

    public String phd;

    public String masters;
    
    public String undergraduate;

    private static final String DEFAULT_VALUE = "";

    public String getPhd() {
        if (phd == null) {

            phd = DEFAULT_VALUE;
        }
        return phd;
    }

    public void setPhd(String phd) {
        this.phd = phd;
    }

    public String getUndergraduate() {
        if (undergraduate == null) {
            undergraduate = DEFAULT_VALUE;
        }
        return undergraduate;
    }

    public void setUndergraduate(String undergraduate) {
        this.undergraduate = undergraduate;
    }

    public String getMasters() {
        if (masters == null) {

            masters = DEFAULT_VALUE;
        }
        return masters;
    }

    public void setMasters(String masters) {
        this.masters = masters;
    }

    @Override
    public String toString() {
        return "ClassPojo [phd = " + phd + ", undergraduate = " + undergraduate + ", masters = " + masters + "]";
    }
}
