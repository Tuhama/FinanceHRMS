<%-- 
    Document   : department
    Created on : Nov 10, 2015, 10:46:50 AM
    Author     : Tuhama
--%>
<c:if test="${!empty departments}">
    <table  id="department_table"  class="display" cellspacing="0"  width="400" >
        <thead>
            <tr>
                <th>رقم</th>
                <th>اسم </th>
                <th></th>
                <th></th>
            </tr>
        </thead>

        <c:forEach var="department" items="${departments}" varStatus="iter">
            <tr>
                <td>${department.id}</td>
                <td>${department.name}</td>
                <td><input type="button" value="تعديل" name="edit_b" onclick="show_edit_dialog('${department.id}', '${department.name}')"/></td>        
                <td><input type="button" value="حذف" name="delete_b" onclick="show_delete_dialog('${department.id}')"/></td>             
            </tr>
        </c:forEach>
    </table>
</c:if>

<input type="button" value="إضافة" name="add_b" onclick="show_add_dialog()"/>

<div id="edit_dialog" title="تعديل" >
    <form action="editDepartment" method="post" id="e_d_form">
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
                <td><input type="button" value="إلغاء" onclick="todo()"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="delete_dialog" title="حذف">
    <form action="deleteDepartment" method="post" id="d_d_form">
        <p>هل أنت متأكد من القيام بعملية الحذف؟</p>
        <input type="text" name="id" id="d_d_id" hidden="hidden" />
        <input type="submit" value="حذف" />
        <input type="button" value="إلغاء" onclick="todo()"/>
    </form>
</div>

<div id="add_dialog" title="إضافة">
    <form id="form1" name="form1" method="post" action="<c:url value='addDepartment'/>" accept-charset="UTF-8">

        <table>
            <tr>
                <td><p> الاسم</p></td>
                <td>
                    <input type="text" name="name" tabindex="1" />
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="إضافة" /></td>
                <td><input type="button" value="إلغاء" onclick="todo()"/></td>
            </tr>
        </table>
    </form>
</div>