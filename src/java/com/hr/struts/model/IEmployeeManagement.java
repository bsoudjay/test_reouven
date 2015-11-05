/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model;

import com.hr.struts.model.entities.Employee;
import java.util.ArrayList;

/**
 *
 * @author bahia
 */
public interface IEmployeeManagement {

    public ArrayList searchByName(String name);

    public ArrayList searchBySsNum(String ssNum);

    public ArrayList searchByTelephone(String telephone);

    public boolean delete(Employee get);

    public boolean delete(String ssNum);

    public ArrayList findAll();

    public boolean add(String name, String ssNum, String telephone);

    public boolean update(Employee e, String name, String ssNum, String telephone);
}
