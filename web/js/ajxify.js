/////////ajaxify the form submition process for insert and update operations
var req;
var isIE;
var empId = "";
var currentDataTable;//to reach the curently edited data table

function overrideSubmits() {

   /* $("form#personalInfoForm").submit(function (event) {
        EmpInfo("addEmployee", $("form#personalInfoForm").serialize());
        event.preventDefault();
    });
    $("form#personalInfoEditForm").submit(function (event) {
        EmpInfo("editEmployee", $("form#personalInfoEditForm").serialize());
        event.preventDefault();
    });


    $("form#empEventForm").submit(function (event) {
       // createEmpDetail("addEvent", $("form#empEventForm").serialize());
       // event.preventDefault();
        currentDataTable = $('#event_table');
    });
     
    $("form#unpaidVForm").submit(function (event) {
        createEmpDetail("addUnpaidV", $("form#unpaidVForm").serialize());
        event.preventDefault();
        currentDataTable = $('#unpaidV_table');
    });
        $("form#serviceJoinForm").submit(function (event) {
        createEmpDetail("addServiceJoin", $("form#serviceJoinForm").serialize());
        event.preventDefault();
        currentDataTable = $('#serviceJoin_table');
    });
    
    $("form#healthLeavForm").submit(function (event) {
        createEmpDetail("addHealthLeave", $("form#healthLeavForm").serialize());
        event.preventDefault();
        currentDataTable = $('#healthLeave_table');
    });
    
    $("form#trainingForm").submit(function (event) {
        createEmpDetail("addTraining", $("form#trainingForm").serialize());
        event.preventDefault();
        currentDataTable = $('#training_table');
    });
    
    $("form#rewardForm").submit(function (event) {
        createEmpDetail("addReward", $("form#rewardForm").serialize());
        event.preventDefault();
        currentDataTable = $('#reward_table');
    });
    
    $("form#punishmentForm").submit(function (event) {
        createEmpDetail("addPunishment", $("form#punishmentForm").serialize());
        event.preventDefault();
        currentDataTable = $('#punishment_table');
    });
     **/
    $("form#editEventForm").submit(function (event) {
        editEmpDetail("editEvent", $("form#editEventForm").serialize());
        event.preventDefault();
        currentDataTable = $('#event_table');
    });
        $("form#editUnpaidVForm").submit(function (event) {
        editEmpDetail("editUnpaidV", $("form#editUnpaidVForm").serialize());
        event.preventDefault();
        currentDataTable = $('#unpaidV_table');
    });
            $("form#editServiceJoinForm").submit(function (event) {
        editEmpDetail("editServiceJoin", $("form#editServiceJoinForm").serialize());
        event.preventDefault();
        currentDataTable = $('#serviceJoin_table');
    });
                $("form#editHealthLeaveForm").submit(function (event) {
        editEmpDetail("editHealthLeave", $("form#editHealthLeaveForm").serialize());
        event.preventDefault();
        currentDataTable = $('#healthLeave_table');
    });
    $("form#deleteEvent_form").submit(function (event) {
        deleteEmpDetail("deleteEvent", $("form#deleteEvent_form").serialize());
        event.preventDefault();
        currentDataTable = $('#event_table');
    });

    $("form#deleteUnpaidV_form").submit(function (event) {
        deleteEmpDetail("deleteUnpaidV", $("form#deleteUnpaidV_form").serialize());
        event.preventDefault();
        currentDataTable = $('#unpaidV_table');
    });

    $("form#deleteServiceJoin_form").submit(function (event) {
        deleteEmpDetail("deleteServiceJoin", $("form#deleteServiceJoin_form").serialize());
        event.preventDefault();
        currentDataTable = $('#serviceJoin_table');
    });

    $("form#deleteHealthLeave_form").submit(function (event) {
        deleteEmpDetail("deleteHealthLeave", $("form#deleteHealthLeave_form").serialize());
        event.preventDefault();
        currentDataTable = $('#healthLeave_table');
    });

    $("form#deleteTraining_form").submit(function (event) {
        deleteEmpDetail("deleteTraining", $("form#deleteTraining_form").serialize());
        event.preventDefault();
        currentDataTable = $('#training_table');
    });

    $("form#deleteReward_form").submit(function (event) {
        deleteEmpDetail("deleteReward", $("form#deleteReward_form").serialize());
        event.preventDefault();
        currentDataTable = $('#reward_table');
    });

    $("form#deletePunishment_form").submit(function (event) {
        deleteEmpDetail("deletePunishment", $("form#deletePunishment_form").serialize());
        event.preventDefault();
        currentDataTable = $('#punishment_table');
    });
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') !== -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function EmpInfo(uri, form_data) {
    var url = uri + "?" + form_data;
    req = initRequest();
    req.open("POST", url, true);
    //!!!wrong: req.onreadystatechange = callbackEmp()....using "()" will call the method 
    req.onreadystatechange = callbackEmp;
    req.send(null);
}

function callbackEmp() {
    if (req.readyState === 4) {
        if (req.status === 200) {
            empId = req.responseText;
            alert("تم الادخال بنجاح");

            //fill the employee info to div in all tabs
            var content = "الموظف الحالي: " + $('#firstname').val() + " " + $('#fathername').val() + " " + $('#lastname').val();
            $('div.emp_breif').html(content);

            //move focus to next tab
            var active = $("#tabs").tabs("option", "active");
            $("#tabs").tabs("option", "active", active + 1);

        }
    }
}

function createEmpDetail(uri, form_data) {
    if (empId !== "") {
        var url = uri + "?" + form_data + "&currentEmp=" + empId;
        req = initRequest();
        req.open("POST", url, true);
        req.onreadystatechange = callbackDetail;
        req.send(null);
    }
    else
    {
        alert("يجب حفظ البانات الأساسية للموظف أولا");
        //move to the first tab (not working)
        //  $("#tabs").tabs("option", "active",0);
    }
}
function callbackDetail() {
    if (req.status === 417) {
        alert("خطأ في الادخال تحقق من المدخلات ..");
    }
    else if (req.readyState === 4) {
        if (req.status === 200) {
            currentDataTable.DataTable().row.add($.parseJSON(req.responseText)).draw(false);
            ///this didn't work  var is not obj it seems!!      >>>  $('#addEvent_table').DataTable().row.add( responseJson ).draw(false);
            alert("تم الادخال بنجاح");
        }
        else
            alert("خطأ في الطلب .." + req.status);
    }

}
function editEmpDetail(uri, form_data) {
alert("");
    var url = uri + "?" + form_data; //+ "&currentEmp=" + empId;
    req = initRequest();
    req.open("POST", url, true);
    req.onreadystatechange = callbackEditDetail;
    req.send(null);
}
function callbackEditDetail() {
    if (req.readyState === 4) {
        if (req.status === 200) {
            //edit the table row
            //currentDataTable.DataTable().row.add($.parseJSON(req.responseText)).draw(false);
            ///this didn't work  var is not obj it seems!!      >>>  $('#addEvent_table').DataTable().row.add( responseJson ).draw(false);
            edit_table_row(currentDataTable);
            alert("تم التعديل بنجاح");

        }
        else
            alert("خطأ في الادخال .." + req.status);
    }

}

function deleteEmpDetail(uri, form_data) {
    var url = uri + "?" + form_data;
    req = initRequest();
    req.open("POST", url, true);
    req.onreadystatechange = callbackDeleteDetail;
    req.send(null);
}
function callbackDeleteDetail() {
    if (req.readyState === 4) {
        if (req.status === 200) {
            currentDataTable.DataTable().row('.selected').remove().draw(false);
        }
        else
            alert("خطأ في الادخال .." + req.status);
    }

}
