/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.controller;

import com.hr.struts.model.IEmployeeManagement;
import java.util.List;
import com.hr.struts.model.EmployeeManagement;
import com.hr.struts.model.entities.Employee;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

/**
 *
 * @author bahia
 */
public class EmployeeAction extends SuperAction {

    public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionErrors errors = new ActionErrors();
        //IEmployeeManagement employeeManagement = new EmployeeManagement();
        IEmployeeManagement employeeManagement = (IEmployeeManagement) getModel((String) request.getServletContext().getAttribute("modelEmployee"));

        List results;

        DynaActionForm searchForm = (DynaActionForm) form;
        if (request.getMethod().equals("POST")) {
            String name = searchForm.getString("firstName");
            String ssnum = searchForm.getString("ssNum");
            String telephone = searchForm.getString("telephone");
            if (name != null && name.trim().length() > 0) {
                try {
                    results = (List) employeeManagement.searchByName(name);
                } catch (Exception e) {
                    errors.add(null, new ActionMessage(e.getMessage()));
                    results = new ArrayList();
                } finally {
                    this.saveErrors(request, errors);
                }
            } else if (ssnum != null && isValidSsNum(ssnum)) {
                try {
                    results = (List) employeeManagement.searchBySsNum(ssnum);
                } catch (Exception e) {
                    errors.add(null, new ActionMessage(e.getMessage()));
                    results = new ArrayList();
                } finally {
                    this.saveErrors(request, errors);
                }
            } else if (telephone != null && isValidTelephone(telephone)) {
                try {
                    results = (List) employeeManagement.searchByTelephone(telephone);
                } catch (Exception e) {
                    errors.add(null, new ActionMessage(e.getMessage()));
                    results = new ArrayList();
                } finally {
                    this.saveErrors(request, errors);
                }
            } else {
                errors.add(null, new ActionMessage("Veuillez remplir au moins un champs"));
                results = new ArrayList();
            }
            searchForm.getMap().clear();
            searchForm.set("results", results);
        }
        return mapping.getInputForward();
    }

    public ActionForward show(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        IEmployeeManagement service = (IEmployeeManagement) getModel((String) request.getServletContext().getAttribute("modelEmployee"));

        ArrayList results;
        ActionMessages errors = new ActionMessages();

        DynaActionForm showForm = (DynaActionForm) form;

        // Perform the “show all the employees” function.
        try {
            results = service.findAll();
        } catch (Exception e) {
            errors.add(null, new ActionMessage(e.getMessage()));
            results = new ArrayList();
        } finally {
            this.saveErrors(request, errors);
        }

        // Cible par defaut
        String cible = new String("succes");

        // Cible en cas d'echec
        if (results == null) {
            cible = "echec";
            errors.add(null, new ActionMessage("error.show.employees.notfound"));
            // Signalement des erreurs a la page d'origine
            if (!errors.isEmpty()) {
                saveErrors(request, errors);
            }
        } else {
            // Place search results in EmployeesShowForm for access by JSP.
            showForm.getMap().clear();
            showForm.set("results", results);
        }
        // Transmission a la vue appropriee
        return mapping.findForward(cible);

    }

    public ActionForward add(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        IEmployeeManagement service = (IEmployeeManagement) getModel((String) request.getServletContext().getAttribute("modelEmployee"));

        DynaActionForm addForm = (DynaActionForm) form;
        String cible = "";
        ActionMessages errors = new ActionMessages();

        if (request.getMethod().equals("POST")) {
            String ssNum = addForm.getString("ssNum");
            String firstName = addForm.getString("firstName");
            String telephone = addForm.getString("telephone");

            cible = "succes";
            //ActionMessage actionMessage = null;
            if (ssNum == null) {
                cible = "echec";
                errors.add("ssNum", new ActionMessage("error.ssNum.missing"));
            } else if (!isValidSsNum(ssNum)) {
                cible = "echec";
                errors.add("ssNum", new ActionMessage("error.ssNum.invalid"));
            }

            if (firstName == null || firstName.equals("")) {
                cible = "echec";
                errors.add("firstName", new ActionMessage("error.firstName.missing"));
            }

            if (telephone == null) {
                cible = "echec";
                errors.add("telephone", new ActionMessage("error.telephone.missing"));
            } else if (!isValidTelephone(telephone)) {
                cible = "echec";
                errors.add("telephone", new ActionMessage("error.telephone.invalid"));
            }

            if (!errors.isEmpty()) {
                saveErrors(request, errors);
            } else {
                // Place search results in EmployeesShowForm for access by JSP.
                try {
                    addForm.set("results", service.add(firstName, ssNum, telephone));
                } catch (Exception e) {
                    errors.add(null, new ActionMessage(e.getMessage()));
                } finally {
                    this.saveErrors(request, errors);
                }
            }
        }

        // Forward control to this Action's input page.
        return mapping.findForward(cible);
    }

    public ActionForward delete(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        IEmployeeManagement service = (IEmployeeManagement) getModel((String) request.getServletContext().getAttribute("modelEmployee"));

        DynaActionForm deleteForm = (DynaActionForm) form;
        String cible = "";
        ActionMessages errors = new ActionMessages();
        if (request.getMethod().equals("POST")) {
            String ssNum = deleteForm.getString("ssNum");
            cible = "succes";

            if (ssNum == null) {
                cible = "echec";
                errors.add("ssNum", new ActionMessage("error.ssNum.missing"));
            } else if (!isValidSsNum(ssNum) || !service.delete(ssNum)) {
                cible = "echec";
                errors.add("ssNum", new ActionMessage("error.ssNum.invalid"));
            }

            if (!errors.isEmpty()) {
                saveErrors(request, errors);
            } else {
                try {
                    deleteForm.set("res", service.delete(ssNum));
                } catch (Exception e) {
                    errors.add(null, new ActionMessage(e.getMessage()));
                } finally {
                    this.saveErrors(request, errors);
                }
            }
        }
        // Forward control to this Action's input page.
        return mapping.findForward(cible);
    }

    public ActionForward update(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        IEmployeeManagement service = (IEmployeeManagement) getModel((String) request.getServletContext().getAttribute("modelEmployee"));

        DynaActionForm updateForm = (DynaActionForm) form;
        String cible = "";
        if (request.getMethod().equals("POST")) {
            String ssNum = updateForm.getString("ssNum");
            String firstName = updateForm.getString("firstName");
            String telephone = updateForm.getString("telephone");


            cible = "succes";
            ActionMessages errors = new ActionMessages();
            if (ssNum == null) {
                cible = "echec";
                errors.add("ssNum", new ActionMessage("error.ssNum.missing"));
            } else if (!isValidSsNum(ssNum)) {
                cible = "echec";
                errors.add("ssNum", new ActionMessage("error.ssNum.invalid"));
            }
            if (firstName == null || firstName.equals("")) {
                cible = "echec";
                errors.add("firstName", new ActionMessage("error.firstName.missing"));
            }
            if (telephone == null) {
                cible = "echec";
                errors.add("telephone", new ActionMessage("error.telephone.missing"));
            } else if (!isValidTelephone(telephone)) {
                cible = "echec";
                errors.add("telephone", new ActionMessage("error.telephone.invalid"));
            }

            try {
                ArrayList<Employee> listEmp = service.searchBySsNum(ssNum);
                boolean res = false;
                for (int i = 0; i < listEmp.size(); i++) {
                    res = service.update(listEmp.get(i), firstName, ssNum, telephone);
                }
                if (res == false) {
                    cible = "echec";
                    errors.add(null, new ActionMessage("error.ssNum.invalid"));
                } else {
                    updateForm.set("res", res);
                }
                
            } catch (Exception e) {
                errors.add(null, new ActionMessage(e.getMessage()));
            } finally {
                this.saveErrors(request, errors);
            }

            if (!errors.isEmpty()) {
                saveErrors(request, errors);
            }

        }
        // Forward control to this Action's input page.
        return mapping.findForward(cible);
    }

    // Validate format of social security number.
    private static boolean isValidSsNum(String ssNum) {
        if (ssNum.length() != 11) {
            return false;
        }
        for (int i = 0; i < 11; i++) {
            if (i == 3 || i == 6) {
                if (ssNum.charAt(i) != '-') {
                    return false;
                }
            } else if ("0123456789".indexOf(ssNum.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    // Validate format of telephone number.
    private static boolean isValidTelephone(String telephone) {
        if (telephone.length() != 14) {
            return false;
        }
        if (telephone.charAt(0) != '0') {
            return false;
        }
        for (int i = 1; i < 14; i++) {
            if (i == 2 || i == 5 || i == 8 || i == 11) {
                if (telephone.charAt(i) != '-') {
                    return false;
                }
            } else if ("0123456789".indexOf(telephone.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    private static Date transformDate(String s) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dt;
            dt = formatter.parse(s);
            return new Date(dt.getTime());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
