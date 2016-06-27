<%-- 
    Document   : department
    Created on : Nov 7, 2015, 6:52:28 AM
    Author     : TUHAMA
--%>


<table id="section_table"  class="display" cellspacing="0"  width="400" >
    <thead>
        <tr class="tableHeading">
            <th>رقم الدائرة</th>
            <th>اسم الدائرة</th>
            <th>اسم القسم</th>
        </tr></thead>
        <c:if test="${!empty sections}">
            <c:forEach var="section" items="${sections}" varStatus="iter">

            <tr>
                <td>${section.id}</td>
                <td>${section.name}</td>

                <c:forEach var="department" items="${departments}" varStatus="iter">

                    <c:if test="${section.departmentId eq department}">
                        <td>${department.name}</td>
                    </c:if>

                </c:forEach>
                <td><input type="button" value="تعديل" name="edit_b" onclick="show_edit_dialog2('${section.id}', '${section.name}', '${section.departmentId}')"/></td>        
                <td><input type="button" value="حذف" name="delete_b" onclick="show_delete_dialog('${section.id}')"/></td>

            </tr>

        </c:forEach>
    </c:if>
</table>
<input type="button" value="إضافة" name="add_b" onclick="show_add_dialog()"/>

<div id="edit_dialog" title="تعديل" >
    <form action="editSection" method="post" id="e_d_form">
        <table>
             <tr><td><input type="text" name="id" id="e_d_id" hidden="hidden"/></td></tr>
            <tr>
                <td><p> الاسم</p></td>
                <td>
                    <input type="text" name="name" id="e_d_name" tabindex="1" />
                </td>
            </tr>
            <tr>
                <td><p>الفرع الرئيسي: </p></td>
                <td>
                    <select name="department" id="e_d_department">
                        <c:if test="${!empty departments}">
                            <c:forEach var="department" items="${departments}" varStatus="iter">
                                <option value="${department.id}">${department.name}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="تعديل" /></td>
                <td><input type="button" value="إلغاء" onclick="todo()"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="delete_dialog" title="حذف">
    <form action="deleteSection" method="post" id="d_d_form">
        <p>هل أنت متأكد من القيام بعملية الحذف؟</p>
        <input type="text" name="id" id="d_d_id" hidden="hidden" />
        <input type="submit" value="حذف" />
        <input type="button" value="إلغاء" onclick="todo()"/>
    </form>
</div>

<div id="add_dialog" title="إضافة">
    <form id="form1" name="form1" method="post" action="<c:url value='addSection'/>" accept-charset="UTF-8">
        <table> 
            <td>       
                <p>
                    اسم الفرع:        
                </p></td>
            <td>
                <input type="text" name="name" id="name" tabindex="1" />
            </td>
            <td>
                <select name="department">
                    <c:if test="${!empty departments}">
                        <c:forEach var="department" items="${departments}" varStatus="iter">
                            <option value="${department.id}">
                                ${department.name}
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </td>
            <td>          <p>
                    <input type="submit" name="addB" id="addB" value="إضافة" />
                </p></td>

        </table>
    </form>
</div>
<script>
    function show_edit_dialog2(id, sectionname, department)
    {
        document.getElementById("e_d_id").value = id;
        document.getElementById("e_d_name").value = sectionname;
        ///todo  set selected value for combo
        document.getElementById("e_d_department").value = department;
        $("#edit_dialog").dialog("open");
    }
    function show_delete_dialog(id)
    {
        document.getElementById("d_d_id").value = id;
        $("#delete_dialog").dialog("open");
    }
    function show_add_dialog()
    {
        $("#add_dialog").dialog("open");
    }
</script>

