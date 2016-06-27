<%-- 
    Document   : employeeSearch
    Created on : Dec 7, 2015, 6:01:01 PM
    Author     : TUHAMA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div align="center" > 
    <form  name="autofillform">
        <table border="0" cellpadding="5"> 
            <tbody>
                <tr>
                    <td><strong>اسم الموظف:

                        </strong></td>
                    <td>
                        <input type="text"
                               size="40" 
                               id="complete-field"
                               onkeyup="doCompletion()">
                    </td>
                </tr>
                <tr>
                    <td id="auto-row" colspan="2">
                        <table id="complete-table" class="popupBox"></table>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</div>

