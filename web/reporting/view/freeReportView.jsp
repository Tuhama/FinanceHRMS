<%-- 
    Document   : freeReportView
    Created on : Oct 9, 2016, 9:57:40 PM
    Author     : TUHAMA
--%>

<div class="BodyDiv">
    <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>

                <td width="185" align="right" valign="top" bgcolor="#00a99d">
                    <%@ include file="/reporting/jspf/sidebar_reporting.jspf" %> 
                </td>
                <td align="center" valign="top" style="padding-bottom: 20px">
                    <div align="center" class="HomePage_Blktop">
                        <div align="center" style="width:620 ;margin-top: 5px ">

                            <form action="printFreeRep" method="post">
                                <fieldset><legend>خيارات التقرير:</legend>
                                    <table width="75%">
                                        <tbody>
                                            <tr>
                                                <td><input type="checkbox" onChange="$('#nationality').attr('disabled', !this.checked);">&nbsp;</td>
                                                <th scope="row"><label>الجنسية:</label>&nbsp;</th>
                                                <td><select name="nationality" id="nationality" disabled>
                                                        <c:forEach var="natianality" items="${natianalities}" varStatus="iter">
                                                            <option value="${natianality.id}">
                                                                ${natianality.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>&nbsp;</td>

                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" onChange="$('#gender').attr('disabled', !this.checked);">&nbsp;</td>
                                                <th scope="row"><label>الجنس:</label>&nbsp;</th>
                                                <td><select name="gender" id="gender" disabled>
                                                        <c:forEach var="gender" items="${genders}" varStatus="iter">
                                                            <option value="${gender}">
                                                                ${gender}
                                                            </option>
                                                        </c:forEach>
                                                    </select>&nbsp;</td>

                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" onChange="$('#familystatus').attr('disabled', !this.checked);">&nbsp;</td>
                                                <th scope="row"><label>الحالة العائلية:</label>&nbsp;</th>
                                                <td><select name="familystatus" id="familystatus" disabled>
                                                        <c:forEach var="familystatus" items="${familystatuss}" varStatus="iter">
                                                            <option value="${familystatus.id}">
                                                                ${familystatus.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>&nbsp;</td>

                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" onChange="$('#martialstatus').attr('disabled', !this.checked);">&nbsp;</td>
                                                <th scope="row"><label>حالة الخدمة العسكرية:</label>&nbsp;</th>
                                                <td><select name="martialstatus" id="martialstatus" disabled>
                                                        <c:forEach var="martialstatus" items="${martialstatuss}" varStatus="iter">
                                                            <option value="${martialstatus.id}">
                                                                ${martialstatus.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>&nbsp;</td>

                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" onChange="$('#certificate').attr('disabled', !this.checked);">&nbsp;</td>
                                                <th scope="row"><label>الشهادة:</label>&nbsp;</th>      
                                                <td><select name="certificate" id="certificate" disabled>
                                                        <c:if test="${!empty certificates}">
                                                            <c:forEach var="certificate" items="${certificates}" varStatus="iter">

                                                                <option value="${certificate.id}">
                                                                    ${certificate.name}
                                                                </option>

                                                            </c:forEach>
                                                        </c:if>      
                                                    </select>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" onChange="$('#branch').attr('disabled', !this.checked);">&nbsp;</td>
                                                <th scope="row"><label>المديرية:</label>&nbsp;</th>
                                                <td><select name="branch" id="branch" disabled>
                                                        <c:if test="${!empty branches}">
                                                            <c:forEach var="branch" items="${branches}" varStatus="iter">

                                                                <option value="${branch.id}">
                                                                    ${branch.name}
                                                                </option>

                                                            </c:forEach>
                                                        </c:if>
                                                    </select>&nbsp;</td>

                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" onChange="$('#department').attr('disabled', !this.checked);">&nbsp;</td>
                                                <th scope="row"><label>القسم:</label>&nbsp;</th>
                                                <td><select name="department" id="department" disabled>
                                                        <c:if test="${!empty departments}">
                                                            <c:forEach var="department" items="${departments}" varStatus="iter">

                                                                <option value="${department}">
                                                                    ${department.name}
                                                                </option>

                                                            </c:forEach>
                                                        </c:if>
                                                    </select>&nbsp;</td>

                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" onChange="$('#category').attr('disabled', !this.checked);">&nbsp;</td>
                                                <th scope="row"><label>الفئة:</label>&nbsp;</th>
                                                <td><select name="category" id="category" disabled>
                                                        <c:forEach var="category" items="${categories}" varStatus="iter">
                                                            <option value="${category.id}">
                                                                ${category.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>&nbsp;</td>

                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" onChange="$('#workstatus').attr('disabled', !this.checked);">&nbsp;</td>
                                                <th scope="row"><label>الوضع الوظيفي:</label>&nbsp;</th>
                                                <td><select name="workstatus" id="workstatus" disabled>
                                                        <c:forEach var="workstatus" items="${workstatuss}" varStatus="iter">
                                                            <option value="${workstatus.id}">
                                                                ${workstatus.name}
                                                            </option>
                                                        </c:forEach> 
                                                    </select>&nbsp;</td>

                                            </tr>
                                        </tbody>
                                    </table>
 <button type="submit" value="Submit" tabindex="160">طباعة</button>
                                </fieldset>
                            </form>

                        </div>
                    </div>       
                </td>

            </tr>
        </tbody></table>

</div>
