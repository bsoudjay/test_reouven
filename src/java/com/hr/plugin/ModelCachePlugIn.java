/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.plugin;

import javax.servlet.ServletException;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

public class ModelCachePlugIn implements PlugIn {

    private String modelEmployee;
    private String modelDepartment;

    public String getModelEmployee() {
        return modelEmployee;
    }

    public void setModelEmployee(String modelEmployee) {
        this.modelEmployee = modelEmployee;
    }

    public String getModelDepartment() {
        return modelDepartment;
    }

    public void setModelDepartment(String modelDepartment) {
        this.modelDepartment = modelDepartment;
    }
    
    @Override
    public void destroy() {
        System.out.println("ModelCachePlugIn Destroyed");
    }

    @Override
    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
        servlet.getServletContext().setAttribute("modelEmployee", this.modelEmployee);
        servlet.getServletContext().setAttribute("modelDepartment", this.modelDepartment);
        System.out.println("ModelCachePlugIn Created");
    } 
}

