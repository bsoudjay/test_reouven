/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model.entities;

/**
 *
 * @author bahia
 */
public class Employee {

    private String firstName;
    private String ssNum;
    private String telephone;

    public Employee(String firstName, String ssNum, String telephone) {
        this.firstName = firstName;
        this.ssNum = ssNum;
        this.telephone = telephone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSsNum(String ssNum) {
        this.ssNum = ssNum;
    }

    public String getSsNum() {
        return ssNum;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
}
