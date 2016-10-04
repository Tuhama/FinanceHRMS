<%-- 
    Document   : newEmployeeT
    Created on : Jan 2, 2016, 7:08:07 PM
    Author     : TUHAMA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="tabs" id="tabs">
        <ul class="tab-links">
        <li class="active"><a href="#tab1">بيانات أساسية</a></li>
        <li ><a href="#tab2">وقوعات</a></li>
        <li><a href="#tab3">إجازات بلا أجر</a></li>
        <li><a href="#tab4">دورات تدريبية</a></li>
        <li><a href="#tab5">مكافآت</a></li>
        <li><a href="#tab6">عقوبات</a></li>
        <li><a href="#tab7">إجازات صحية</a></li>
        <li><a href="#tab8">ضم الخدمة</a></li>
    </ul>
     
        <div class="tab-content">
               <div id="tab1" class="tab active">
                    <%@ include file="/WEB-INF/staffing/jspf/new_emp_tabs/tab_personal.jspf" %>  
                </div> 
                <div id="tab2" class="tab">
                    <%@ include file="/WEB-INF/staffing/jspf/new_emp_tabs/tab_events.jspf" %>
                </div>        
                <div id="tab3" class="tab">
                    <%@ include file="/WEB-INF/staffing/jspf/new_emp_tabs/tab_unpaidV.jspf" %>
                </div>         
                <div id="tab4" class="tab">
                    <%@ include file="/WEB-INF/staffing/jspf/new_emp_tabs/tab_training.jspf" %>
                </div>
                <div id="tab5" class="tab">
                    <%@ include file="/WEB-INF/staffing/jspf/new_emp_tabs/tab_rewards.jspf" %>
                </div>       
                <div id="tab6" class="tab">
                    <%@ include file="/WEB-INF/staffing/jspf/new_emp_tabs/tab_punishments.jspf" %>
                </div>
                <div id="tab7" class="tab">
                    <%@ include file="/WEB-INF/staffing/jspf/new_emp_tabs/tab_healthleaves.jspf" %>
                </div>
                <div id="tab8" class="tab">
                    <%@ include file="/WEB-INF/staffing/jspf/new_emp_tabs/tab_servicejoins.jspf" %>
                </div>
            </div>
</div>
