<%-- 
    Document   : deleteEmployee
    Created on : 17 oct. 2014, 21:07:28
    Author     : bahia
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Employee</title>
    </head>
    <body>
        <h1>Delete Employee</h1>
        <html:errors/>
        <html:form action="/deleteEmployee">
            <table>
                <tr>
                    <td align="right"><bean:message key="label.ssNum"/>:</td>
                    <td><html:text property="ssNum"/> (xxx-xx-xxxx)</td>
                </tr>
                <tr>
                    <td></td>
                    <td><html:submit /></td>
                </tr>
            </table>
        </html:form>
    </body>
</html>
