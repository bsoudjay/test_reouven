/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.controller;

/**
 *
 * @author bahia
 */
public class Factory {

    public Object instantiate(String NomModelClass) {
        try {
            Class cls = Class.forName(NomModelClass);
            Object obj = cls.newInstance();
            return obj;
        } catch (Exception e) {
            return null;
        }
    }
}
