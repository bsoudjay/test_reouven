/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.controller;

import com.hr.struts.model.EmployeeManagement;
import com.hr.struts.model.IEmployeeManagement;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.actions.MappingDispatchAction;

/**
 *
 * @author bahia
 */
public abstract class SuperAction extends MappingDispatchAction {

    protected ActionErrors errors = new ActionErrors();

    /*protected IEmployeeManagement getModel() {
        IEmployeeManagement model = new EmployeeManagement();
        return model;
    }*/

    protected IEmployeeManagement getModel(String model) {
        Factory f = new Factory();
        IEmployeeManagement employeeModel = (IEmployeeManagement) f.instantiate(model);
        return employeeModel;

    }
}
