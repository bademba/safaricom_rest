/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.safaricom.runner;

import com.brian.safaricom.model.EmployeeResource;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author Brian Ademba <brian.ademba@gmail.com>
 */
public class Runner {
    public static void main(String[] args) throws JsonProcessingException {
        //EmployeeDAOImpl ed=new EmployeeDAOImpl();
        EmployeeResource er = new EmployeeResource();
        er.getByEmail("brian.ademba@gmail.com");
    }
    
}
