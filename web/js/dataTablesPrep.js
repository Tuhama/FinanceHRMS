/* كود تحضير جداول البيانات: تحديد الخيارات والاعمدة والتعريب
 */

function prepareDataTables()
{
    //اذا تم النقر على زر الحذف او التعديل في جدول سيتم تحديد السطرالموافق للزر
    $("[id$='_table'] tbody").on('click', 'td', function () {
        var _row = $(this).parents("tr")[0];
        if ($(this).find('button[name=delete_b]').length > 0 || $(this).find('button[name=edit_b]').length > 0)
        {
            $('tr.selected').removeClass('selected');
            $(_row).addClass('selected');
        }
    });
    
    
    
    
    var e_table = $("#event_table").DataTable({
        "language": {
            "search": "بحث",
            "info": " _START_ إلى _END_ / _TOTAL_ ",
            "sProcessing": "جاري التحميل...",
            "sLengthMenu": "إظهار _MENU_",
            "sZeroRecords": "لم يُعثر على أية سجلات",
            "sInfoEmpty": "يعرض 0 إلى 0 من أصل 0 سجلّ",
            "sInfoFiltered": "(منتقاة من مجموع _MAX_ سجل)",
            "sInfoPostFix": "",
            "sSearch": "ابحث:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "الأول",
                "sPrevious": "السابق",
                "sNext": "التالي",
                "sLast": "الأخير"
            }},
        "columns": [
            {"data": "id",
                "visible": false},
            {"data": "positionId"},
            {"data": "name"},
            {"data": "startdate"},
            {"data": "salary"},
            {"data": "category"},
            {"data": "doctype"},
            {"data": "docnumber"},
            {"data": "docdate"},
          //  {"data": "row_d"},
            {"data": null,
                "defaultContent": "<button name='edit_b'>تعديل</button>"},
            {"data": null,
                "defaultContent": "<button name='delete_b'>حذف</button>"}
        ]
    });

    
    $('#event_table tbody').on('click', 'button', function () {
        //
        var _row = $(this).parents("tr");
        var data = e_table.row($(_row)[0]).data();
        
        if($(this).attr('name')==='edit_b')show_edit_dialog_event(data);
        else if ($(this).attr('name')==='delete_b')show_delete_dialog_event(data['id']);
    });
    
    $("#addUnpaidv_table").dataTable({
        "language": {
            "search": "بحث",
            "info": " _START_ إلى _END_ / _TOTAL_ ",
            "sProcessing": "جاري التحميل...",
            "sLengthMenu": "إظهار _MENU_",
            "sZeroRecords": "لم يُعثر على أية سجلات",
            "sInfoEmpty": "يعرض 0 إلى 0 من أصل 0 سجلّ",
            "sInfoFiltered": "(منتقاة من مجموع _MAX_ سجل)",
            "sInfoPostFix": "",
            "sSearch": "ابحث:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "الأول",
                "sPrevious": "السابق",
                "sNext": "التالي",
                "sLast": "الأخير"
            }},
        "columns": [
            {"data": "col1"},
            {"data": "col2"},
            {"data": "col3"},
            {"data": "col4"},
            {"data": "col5"},
            {"data": "col6"},
            {"data": "col7"},
            {"data": "col8"},
            {"data": "row_d"}
        ]
    });
    $("#addTraining_table").DataTable({
        "language": {
            "search": "بحث",
            "info": " _START_ إلى _END_ / _TOTAL_ ",
            "sProcessing": "جاري التحميل...",
            "sLengthMenu": "إظهار _MENU_",
            "sZeroRecords": "لم يُعثر على أية سجلات",
            "sInfoEmpty": "يعرض 0 إلى 0 من أصل 0 سجلّ",
            "sInfoFiltered": "(منتقاة من مجموع _MAX_ سجل)",
            "sInfoPostFix": "",
            "sSearch": "ابحث:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "الأول",
                "sPrevious": "السابق",
                "sNext": "التالي",
                "sLast": "الأخير"
            }},
        "columns": [
            {"data": "col1"},
            {"data": "col2"},
            {"data": "col3"},
            {"data": "col4"},
            {"data": "col5"},
            {"data": "row_d"}
        ]
    });
    $("#addHealthLeave_table").DataTable({
        "language": {
            "search": "بحث",
            "info": " _START_ إلى _END_ / _TOTAL_ ",
            "sProcessing": "جاري التحميل...",
            "sLengthMenu": "إظهار _MENU_",
            "sZeroRecords": "لم يُعثر على أية سجلات",
            "sInfoEmpty": "يعرض 0 إلى 0 من أصل 0 سجلّ",
            "sInfoFiltered": "(منتقاة من مجموع _MAX_ سجل)",
            "sInfoPostFix": "",
            "sSearch": "ابحث:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "الأول",
                "sPrevious": "السابق",
                "sNext": "التالي",
                "sLast": "الأخير"
            }},
        "columns": [
            {"data": "col1"},
            {"data": "col2"},
            {"data": "col3"},
            {"data": "col4"},
            {"data": "col5"},
            {"data": "row_d"}
        ]
    });
    $("#addReward_table").DataTable({
        "language": {
            "search": "بحث",
            "info": " _START_ إلى _END_ / _TOTAL_ ",
            "sProcessing": "جاري التحميل...",
            "sLengthMenu": "إظهار _MENU_",
            "sZeroRecords": "لم يُعثر على أية سجلات",
            "sInfoEmpty": "يعرض 0 إلى 0 من أصل 0 سجلّ",
            "sInfoFiltered": "(منتقاة من مجموع _MAX_ سجل)",
            "sInfoPostFix": "",
            "sSearch": "ابحث:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "الأول",
                "sPrevious": "السابق",
                "sNext": "التالي",
                "sLast": "الأخير"
            }},
        "columns": [
            {"data": "col1"},
            {"data": "col2"},
            {"data": "col3"},
            {"data": "col4"},
            {"data": "col5"},
            {"data": "row_d"}
        ]
    });
    $("#addPunishment_table").DataTable({
        "language": {
            "search": "بحث",
            "info": " _START_ إلى _END_ / _TOTAL_ ",
            "sProcessing": "جاري التحميل...",
            "sLengthMenu": "إظهار _MENU_",
            "sZeroRecords": "لم يُعثر على أية سجلات",
            "sInfoEmpty": "يعرض 0 إلى 0 من أصل 0 سجلّ",
            "sInfoFiltered": "(منتقاة من مجموع _MAX_ سجل)",
            "sInfoPostFix": "",
            "sSearch": "ابحث:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "الأول",
                "sPrevious": "السابق",
                "sNext": "التالي",
                "sLast": "الأخير"
            }},
        "columns": [
            {"data": "col1"},
            {"data": "col2"},
            {"data": "col3"},
            {"data": "col4"},
            {"data": "col5"},
            {"data": "col6"},
            {"data": "row_d"}
        ]
    });
    $("#addServiceJoin_table").DataTable({
        "language": {
            "search": "بحث",
            "info": " _START_ إلى _END_ / _TOTAL_ ",
            "sProcessing": "جاري التحميل...",
            "sLengthMenu": "إظهار _MENU_",
            "sZeroRecords": "لم يُعثر على أية سجلات",
            "sInfoEmpty": "يعرض 0 إلى 0 من أصل 0 سجلّ",
            "sInfoFiltered": "(منتقاة من مجموع _MAX_ سجل)",
            "sInfoPostFix": "",
            "sSearch": "ابحث:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "الأول",
                "sPrevious": "السابق",
                "sNext": "التالي",
                "sLast": "الأخير"
            }},
        "columns": [
            {"data": "col1"},
            {"data": "col2"},
            {"data": "col3"},
            {"data": "col4"},
            {"data": "col5"},
            {"data": "col6"},
            {"data": "row_d"}
        ]
    });

}
