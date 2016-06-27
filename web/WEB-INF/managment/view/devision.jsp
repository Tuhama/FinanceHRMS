<%-- 
    Document   : department
    Created on : Nov 7, 2015, 6:52:28 AM
    Author     : TUHAMA
--%>

<c:if test="${!empty devisions}">
    <table  id="devision_table"  class="display" cellspacing="0"   width="400" >
        <thead>      
            <tr>
                <th>رقم الشعبة</th>
                <th>اسم الشعبة</th>
                <th>اسم الدائرة</th>
                <th>اسم القسم</th>
                <th></th>
                <th></th>
            </tr>
        </thead>

        <c:forEach var="devision" items="${devisions}" varStatus="iter">

            <tr>
                <td>${devision.id}</td>
                <td>${devision.name}</td>
                <c:forEach var="section" items="${sections}" varStatus="iter">

                    <c:if test="${devision.sectionId eq section}">
                        <td>${section.name}</td>
                        <c:forEach var="department" items="${departments}" varStatus="iter">
                            <c:if test="${section.departmentId eq department}">
                                <td>${department.name}</td>
                                <td><input type="button" value="تعديل" name="edit_b" onclick="show_edit_dialog3('${devision.id}', '${devision.name}', '${devision.sectionId}', '${section.departmentId}')"/></td>        
                                <td><input type="button" value="حذف" name="delete_b" onclick="show_delete_dialog('${devision.id}')"/></td>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
            </tr>
        </c:forEach>
    </table>
</c:if>


<input type="button" value="إضافة" name="add_b" onclick="show_add_dialog()"/>

<div id="edit_dialog" title="تعديل" >
    <form action="editDevision" method="post" id="e_d_form">
        <table>
             <tr><td><input type="text" name="id" id="e_d_id" hidden="hidden"/></td></tr>
            <tr>
                <td><p> الاسم</p></td>
                <td>
                    <input type="text" name="name" id="e_d_name" tabindex="1" />
                </td>
            </tr>
            <tr>
                <td><p>الدائرة: </p></td>
                <td>
                    <select name="section" id="e_d_section">
                        <c:if test="${!empty sections}">
                            <c:forEach var="section" items="${sections}" varStatus="iter">
                                <option value="${section.id}">${section.name}</option>
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
    <form action="deleteDevision" method="post" id="d_d_form">
        <p>هل أنت متأكد من القيام بعملية الحذف؟</p>
        <input type="text" name="id" id="d_d_id" hidden="hidden" />
        <input type="submit" value="حذف" />
        <input type="button" value="إلغاء" onclick="todo()"/>
    </form>
</div>

<div id="add_dialog" title="إضافة">
    <form id="form1" name="form1" method="post" action="<c:url value='addDevision'/>" accept-charset="UTF-8">
        <table> 
            <td>       
                <p>
                    اسم الشعبة        
                </p></td>
            <td>
                <input type="text" name="name" id="name" tabindex="1" />
            </td>
            <td>
                <select name="section">
                    <c:if test="${!empty sections}">
                        <c:forEach var="section" items="${sections}" varStatus="iter">
                            <option value="${section.id}">
                                ${section.name}
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
    function show_edit_dialog3(id, devisionname, department,section)
    {
        document.getElementById("e_d_id").value = id;
        document.getElementById("e_d_name").value = devisionname;
        ///todo  set selected value for combo
        document.getElementById("e_d_department").value = department;
        document.getElementById("e_d_section").value = section;
        
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

