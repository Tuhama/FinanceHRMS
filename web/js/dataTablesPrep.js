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


    $("[id$='_table'] tbody").on('click', 'button', function () {
        //
        var _row = $(this).parents("tr");
        var _tables = $(this).parents("table");
        var _table = _tables[0];

        var data = $(_table).DataTable().row($(_row)[0]).data();

        if ($(this).attr('name') === 'edit_b')
            show_detail_edit_dialog(_table, data);
        else if ($(this).attr('name') === 'delete_b')
            show_detail_delete_dialog(_table, data['id']);
    });


    $("#event_table").DataTable({
        "filter": false,
        "paginate":false,
        "info":false,
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
            {"data": null,
                "defaultContent": "<button name='edit_b'>تعديل</button>"},
            {"data": null,
                "defaultContent": "<button name='delete_b'>حذف</button>"}
        ]
    });

    $("#unpaidV_table").DataTable({
         "filter": false,
        "paginate":false,
        "info":false,
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
            {"data": "typeunpaidvacationId"},
            {"data": "startdate"},
            {"data": "enddate"},
            {"data": "reason"},
            {"data": "folding",
             template:"{common.checkbox()}"},
            {"data": "doctype"},
            {"data": "docnumber"},
            {"data": "docdate"},
            {"data": null,
                "defaultContent": "<button name='edit_b'>تعديل</button>"},
            {"data": null,
                "defaultContent": "<button name='delete_b'>حذف</button>"}
        ]
    });
    $("#training_table").DataTable({
         "filter": false,
        "paginate":false,
        "info":false,
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
            {"data": "kind"},
            {"data": "place"},
            {"data": "startdate"},
            {"data": "enddate"},
            {"data": "duration"},
            {"data": null,
                "defaultContent": "<button name='edit_b'>تعديل</button>"},
            {"data": null,
                "defaultContent": "<button name='delete_b'>حذف</button>"}
        ]
    });
    $("#healthLeave_table").DataTable({
         "filter": false,
        "paginate":false,
        "info":false,
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
            {"data": "typehealthleaveId"},
            {"data": "startdate"},
            {"data": "enddate"},
            {"data": "year"},
            {"data": "dayscount"},
            {"data": null,
                "defaultContent": "<button name='edit_b'>تعديل</button>"},
            {"data": null,
                "defaultContent": "<button name='delete_b'>حذف</button>"}
        ]
    });
    $("#reward_table").DataTable({
         "filter": false,
        "paginate":false,
        "info":false,
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
            {"data": "kind"},
            {"data": "amount"},
            {"data": "doctype"},
            {"data": "docnumber"},
            {"data": "docdate"},
            {"data": null,
                "defaultContent": "<button name='edit_b'>تعديل</button>"},
            {"data": null,
                "defaultContent": "<button name='delete_b'>حذف</button>"}
        ]
    });
    $("#punishment_table").DataTable({
         "filter": false,
        "paginate":false,
        "info":false,
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
            {"data": "typepunishmentId"},
            {"data": "reason"},
            {"data": "date"},
            {"data": "doctype"},
            {"data": "docnumber"},
            {"data": "docdate"},
            {"data": null,
                "defaultContent": "<button name='edit_b'>تعديل</button>"},
            {"data": null,
                "defaultContent": "<button name='delete_b'>حذف</button>"}
        ]
    });
    $("#serviceJoin_table").DataTable({
         "filter": false,
        "paginate":false,
        "info":false,
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
            {"data": "placeofservice"},
            {"data": "daysduration"},
            {"data": "monthsduration"},
            {"data": "doctype"},
            {"data": "docnumber"},
            {"data": "docdate"},
            {"data": null,
                "defaultContent": "<button name='edit_b'>تعديل</button>"},
            {"data": null,
                "defaultContent": "<button name='delete_b'>حذف</button>"}
        ]
    });

}
/////proxy functions...call the function with the right table
function show_detail_edit_dialog(current_table, data)
{
    if ($(current_table).attr('id') === 'event_table')
        show_edit_dialog_event(data);
    else
    if ($(current_table).attr('id') === 'healthLeave_table')
        show_edit_dialog_healthleave(data);
    else
    if ($(current_table).attr('id') === 'unpaidV_table')
        show_edit_dialog_unpaidv(data);
    else
    if ($(current_table).attr('id') === 'training_table')
        show_edit_dialog_training(data);
    else
    if ($(current_table).attr('id') === 'punishment_table')
        show_edit_dialog_punisment(data);
    else
    if ($(current_table).attr('id') === 'reward_table')
        show_edit_dialog_reward(data);
    else
    if ($(current_table).attr('id') === 'serviceJoin_table')
        show_edit_dialog_servicejoin(data);

}

function show_detail_delete_dialog(current_table, id)
{
    if ($(current_table).attr('id') === 'event_table')
        show_delete_dialog_event(id);
    else
    if ($(current_table).attr('id') === 'healthLeave_table')
        show_delete_dialog_healthleave(id);
    else
    if ($(current_table).attr('id') === 'unpaidV_table')
        show_delete_dialog_unpaidv(id);
    else
    if ($(current_table).attr('id') === 'training_table')
        show_delete_dialog_training(id);
    else
    if ($(current_table).attr('id') === 'punishment_table')
        show_delete_dialog_punisment(id);
    else
    if ($(current_table).attr('id') === 'reward_table')
        show_delete_dialog_reward(id);
    else
    if ($(current_table).attr('id') === 'serviceJoin_table')
        show_delete_dialog_servicejoin(id);

}
function edit_table_row(current_table)
{
    if ($(current_table).attr('id') === 'event_table')
        edit_event_table_row();
    else
    if ($(current_table).attr('id') === 'healthLeave_table')
        edit_healthleave_table_row();
    else
    if ($(current_table).attr('id') === 'unpaidV_table')
        edit_unpaidV_table_row();
    else
    if ($(current_table).attr('id') === 'training_table')
        edit_training_table_row();
    else
    if ($(current_table).attr('id') === 'punishment_table')
        edit_punishment_table_row();
    else
    if ($(current_table).attr('id') === 'reward_table')
        edit_reward_table_row();
    else
    if ($(current_table).attr('id') === 'serviceJoin_table')
        edit_servicejoin_table_row();

}

