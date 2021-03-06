<%-- 
    Document   : mainbranch
    Created on : Nov 7, 2015, 6:52:28 AM
    Author     : TUHAMA
--%>

<div class="BodyDiv">
    <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>

                <td width="185" align="right" valign="top" bgcolor="#00a99d">
                    <%@ include file="/indexing/jspf/sidebar_indexing.jspf" %> 
                </td>
                <td align="center" valign="top" style="padding-bottom: 20px">
                    <div align="center" class="HomePage_Blktop">
                        <div align="center" style="width:620 ;margin-top: 5px ">


                            <table id="mainbranch_table"  class="display" cellspacing="0" width="400">
                                <thead>
                                    <tr>
                                        <th>رقم</th>
                                        <th>اسم </th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${!empty mainbranches}">
                                        <c:forEach var="mainbranch" items="${mainbranches}" varStatus="iter">

                                            <tr>
                                                <td>${mainbranch.id}</td>
                                                <td>${mainbranch.name}</td>
                                                <td><input type="button" value="تعديل" name="edit_b" onclick="show_edit_dialog('${mainbranch.id}', '${mainbranch.name}'"/></td>        
                                                <td><input type="button" value="حذف" name="delete_b" onclick="show_delete_dialog('${mainbranch.id}')"/></td>
                                            </tr>

                                        </c:forEach> 
                                    </c:if>
                                </tbody>
                            </table>
                            <br>
                            <input type="button" value="إضافة" name="add_b" onclick="show_add_dialog()"/>
                            <div id="edit_dialog" title="تعديل" >
                                <form action="editMainbranch" method="post" id="e_d_form">
                                    <table>
                                        <tr><td><input type="text" name="id" id="e_d_id" hidden="hidden"/></td></tr>
                                        <tr>
                                            <td><p> الاسم</p></td>
                                            <td>
                                                <input type="text" name="name" id="e_d_name" tabindex="1" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="submit" value="تعديل" /></td>
                                            <td><input type="button" value="إلغاء" onclick='$("#edit_dialog").dialog("close")'/></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>

                            <div id="delete_dialog" title="حذف">
                                <form action="deleteMainbranch" method="post" id="d_d_form">
                                    <p>هل أنت متأكد من القيام بعملية الحذف؟</p>
                                    <input type="text" name="id" id="d_d_id" hidden="hidden" />
                                    <input type="submit" value="حذف" />
                                    <input type="button" value="إلغاء" onclick='$("#delete_dialog").dialog("close")'/>
                                </form>
                            </div>
                            <div id="add_dialog" title="إضافة">
                                <form id="form1" name="form1" method="post" action="<c:url value='addMainBranch'/>" accept-charset="UTF-8">

                                    <table>
                                        <tr>
                                            <td><p> الاسم</p></td>
                                            <td>
                                                <input type="text" name="name" tabindex="1" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="submit" value="إضافة" /></td>
                                            <td><input type="button" value="إلغاء" onclick='$("#add_dialog").dialog("close")'/></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>



                        </div>
                    </div>       
                </td>

            </tr>
        </tbody></table>

</div>
