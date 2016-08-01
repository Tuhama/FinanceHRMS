/* كود تحضير جداول البيانات: تحديد الخيارات والاعمدة والتعريب
 */

function prepareDataTables()
{
    $("#addEvent_table").dataTable({
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
