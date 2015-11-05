<%-- 
    Document   : updateEmployee
    Created on : 17 oct. 2014, 22:09:13
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
        <title>Update Employee</title>
    </head>
    <body>
        <h1>Update Employee</h1>

    <html:errors/>
    <html:form action="/updateEmployee">
        <table>
            <tr>
                <td align="right"><bean:message key="label.firstName"/>:</td>
                <td><html:text property="firstName"/></td>
            </tr>
            <tr>
                <td></td>
                <td>-- and --</td>
            </tr>
            <tr>
                <td align="right"><bean:message key="label.ssNum"/>:</td>
            <td><html:text property="ssNum"/> (xxx-xx-xxxx)</td>
            </tr>
            <tr>
                <td></td>
                <td>-- and --</td>
            </tr>
            <tr>
                <td align="right"><bean:message key="label.telephone"/>:</td>
            <td><html:text property="telephone"/> (0x-xx-xx-xx-xx)</td>
            </tr>
            <tr>
                <td></td>
                <td><html:submit/></td>
            </tr>
        </table>
    </html:form>
</body>
</html>
