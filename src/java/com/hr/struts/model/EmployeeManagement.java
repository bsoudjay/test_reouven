/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model;

import com.hr.struts.model.entities.Employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author bahia
 */
public class EmployeeManagement implements IEmployeeManagement
{
  /* Hard-coded sample data. Normally this would come from a real data source: database    */
private static List<Employee> employees = new ArrayList<Employee>(Arrays.asList(
    new Employee("Bob Davidson", "123-45-6789", "01-23-45-67-89"),
    new Employee("Mary Williams", "987-65-4321", "01-98-76-54-32"),
    new Employee("Jim Smith", "111-11-1111", "01-11-11-11-11"),
    new Employee("Beverly Harris", "222-22-2222", "01-22-22-22-22"),
    new Employee("Thomas Frank", "333-33-3333", "01-33-33-33-33"),
    new Employee("Jim Davidson", "444-44-4444", "01-44-44-44-44")
  ));
   
  // Search for employees by name.
	public ArrayList searchByName(String name) {
		ArrayList resultList = new ArrayList();
		for (Employee employee : employees) {
		   if (employee.getFirstName().toUpperCase().indexOf(name.toUpperCase()) != -1) {
			resultList.add(employee);
		   }
		}
		return resultList;
	}
   
  // Search for employee by social security number.
	public ArrayList searchBySsNum(String ssNum) {
		ArrayList resultList = new ArrayList();
		for (Employee employee : employees) {
			if (employee.getSsNum().equals(ssNum)) {
				resultList.add(employee);
			}
		}
		return resultList;
	}
        
    // Search for employee by social security number.
	public ArrayList searchByTelephone(String telephone) {
		ArrayList resultList = new ArrayList();
		for (Employee employee : employees) {
			if (employee.getTelephone().equals(telephone)) {
				resultList.add(employee);
			}
		}
		return resultList;
	}

	public boolean delete(Employee get) {
		employees.remove(get);
		return true;
	}
        
        public boolean delete(String ssNum) {
		Employee e = null;
		for (Employee employee : employees) {
			if (employee.getSsNum().equals(ssNum)) {
				e = employee;
			}
		}
                if (e == null) {
                    return false;
                } else {
                    this.delete(e);
                    return true;
                }
	}

	public ArrayList findAll() {
		return (ArrayList) employees;
	}

	public boolean add(String name, String ssNum, String telephone) {
		employees.add(new Employee(name, ssNum, telephone));
		return true;
	}

	public boolean update(Employee e, String name, String ssNum, String telephone) {
		for(int i = 0; i < employees.size(); i++){
			if(employees.get(i).equals(e)){
				employees.get(i).setFirstName(name);
				employees.get(i).setSsNum(ssNum);
                                employees.get(i).setTelephone(telephone);
			}
		}
		return true;
	}
}

