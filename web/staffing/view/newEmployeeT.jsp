<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<div class="BodyDiv">
    <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>

                <td width="185" align="right" valign="top" bgcolor="#00a99d">
                    <%@ include file="/staffing/jspf/sidebar_staffing.jspf" %> 
                </td>
                <td align="right" valign="top" style="padding-bottom: 20px">
                    <div align="right" class="HomePage_Blktop">
                        <div align="right" style="width:100% ;margin-top: 5px ">


                            <div id="tabs" align="right">
                                <ul>
                                    <li><a href="#tab1">بيانات أساسية</a></li>
                                    <li><a href="#tab2">وقوعات</a></li>
                                    <li><a href="#tab3">إجازات بلا أجر</a></li>
                                    <li><a href="#tab4">دورات تدريبية</a></li>
                                    <li><a href="#tab5">مكافآت</a></li>
                                    <li><a href="#tab6">عقوبات</a></li>
                                    <li><a href="#tab7">إجازات صحية</a></li>
                                    <li><a href="#tab8">ضم الخدمة</a></li>
                                </ul>

                                           <div id="tab1">
                                        <%@ include file="/staffing/jspf/new_emp_tabs/tab_personal.jspf" %>  
                                           </div> 
                                            <div id="tab2">
                                        <%@ include file="/staffing/jspf/new_emp_tabs/tab_events.jspf" %>
                                            </div>        
                                            <div id="tab3">
                                                <%@ include file="/staffing/jspf/new_emp_tabs/tab_unpaidV.jspf" %>
                                            </div>         
                                            <div id="tab4">
                                        <%@ include file="/staffing/jspf/new_emp_tabs/tab_training.jspf" %>
                                            </div>
                                            <div id="tab5">
                                        <%@ include file="/staffing/jspf/new_emp_tabs/tab_rewards.jspf" %>
                                            </div>       
                                            <div id="tab6">
                                        <%@ include file="/staffing/jspf/new_emp_tabs/tab_punishments.jspf" %>
                                            </div>
                                            <div id="tab7">
                                        <%@ include file="/staffing/jspf/new_emp_tabs/tab_healthleaves.jspf" %>
                                            </div>
                                            <div id="tab8">
                                        <%@ include file="/staffing/jspf/new_emp_tabs/tab_servicejoins.jspf" %>
                                            </div>
                                       
                            </div> 



                        </div>
                    </div>       
                </td>

            </tr>
        </tbody></table>

</div>
