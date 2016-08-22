
$(function () {
    $("[id$='dialog']").dialog({
        autoOpen: false,
        width: 'auto'
    });
});

jQuery(document).ready(function () {

    prepareTabs();

    tab_enter_key();

    prepareDataTables();

    validate_forms();
    
    prepareCalendar();
});


function prepareCalendar()
{
    $('input[type="date"]').mask("9999/99/99",{placeholder:"يوم/شهر/سنة"});
}

function prepareTabs()
{
    jQuery('.tabs .tab-links a').on('click', function (e) {
        var currentAttrValue = jQuery(this).attr('href');

        // Show/Hide Tabs
        jQuery('.tabs ' + currentAttrValue).show().siblings().hide();

        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');
        //move focus to the first input element of the active tab
        $('form').find('*').filter(':input:visible:first').focus();
        e.preventDefault();
    });
}

function tab_enter_key() {
    var input_types;
    input_types = "input, select, button, textarea";

    return $("body").on("keydown", input_types, function (e) {
        var enter_key, form, input_array, move_direction, move_to, new_index, self, tab_index, tab_key;
        enter_key = 13;
        tab_key = 9;

        if (e.keyCode === tab_key || e.keyCode === enter_key) {
            self = $(this);

            // some controls should react as designed when pressing enter
            if (e.keyCode === enter_key && (self.prop('type') === "submit")) {
                return true;
            }

            form = self.parents('form:eq(0)');

            // Sort by tab indexes if they exist
            tab_index = parseInt(self.attr('tabindex'));
            if (tab_index) {
                input_array = form.find("[tabindex]").filter(':visible').sort(function (a, b) {
                    return parseInt($(a).attr('tabindex')) - parseInt($(b).attr('tabindex'));
                });
            } else {
                input_array = form.find(input_types).filter(':visible');
            }

            // reverse the direction if using shift
            move_direction = e.shiftKey ? -1 : 1;
            new_index = input_array.index(this) + move_direction;

            // wrap around the controls
            if (new_index === input_array.length) {
                new_index = 0;
            } else if (new_index === -1) {
                new_index = input_array.length - 1;
            }

            move_to = input_array.eq(new_index);
            move_to.focus();
            move_to.select();
            return false;
        }
    });
}


//الغرض من نمطDate يتم ارساله كاملا مع توقيت لذلك يجب صياغته قبل العرض 2015-10-10
function formatDate_box(ds)
{
    var d = new Date(ds);
    var day = d.getDate();
    var month = d.getMonth();
    if (day < 10)
        day = "0" + d.getDate();

    if (month < 10)
        month = "0" + (d.getMonth() + 1);

    var st = d.getFullYear() + "-" + month + "-" + day;
    return st;
}
function formatDate(ds)
{
    var d = new Date(ds);
    var st = d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate();
    return st;
}


//used by indexing pages
function show_edit_dialog(id, name)
{
    document.getElementById("e_d_id").value = id;
    document.getElementById("e_d_name").value = name;
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

