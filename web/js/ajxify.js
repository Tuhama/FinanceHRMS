/////////ajaxify the form submition process for insert and update operations
var req;
var isIE;
var empId = "";

function overrideSubmits() {
    $("form#personalInfoForm").submit(function (event) {
        EmpInfo("addEmployee", $("form#personalInfoForm").serialize());
        event.preventDefault();
    });
        $("form#personalInfoEditForm").submit(function (event) {
        EmpInfo("editEmployee", $("form#personalInfoEditForm").serialize());
        event.preventDefault();
    });
    $("form#empEventForm").submit(function (event) {
        createEmpDetail("addEvent", $("form#empEventForm").serialize());
        event.preventDefault();
    });

    $("form#unpaidVForm").submit(function (event) {
        createEmpDetail("addUnpaidVacation", $("form#unpaidVForm").serialize());
        event.preventDefault();
    });

    $("form#serviceJoinForm").submit(function (event) {
        createEmpDetail("addServiceJoin", $("form#serviceJoinForm").serialize());
        event.preventDefault();
    });

    $("form#healthLeavForm").submit(function (event) {
        createEmpDetail("addHealthLeave", $("form#healthLeavForm").serialize());
        event.preventDefault();
    });

    $("form#trainingForm").submit(function (event) {
        createEmpDetail("addTrainingCourse", $("form#trainingForm").serialize());
        event.preventDefault();
    });
    $("form#rewardForm").submit(function (event) {
        createEmpDetail("addReward", $("form#rewardForm").serialize());
        event.preventDefault();
    });

    $("form#punishmentForm").submit(function (event) {
        createEmpDetail("addPunishment", $("form#punishmentForm").serialize());
        event.preventDefault();
    });
}
    
function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
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
    if (req.readyState == 4) {
        if (req.status == 200) {
            empId = req.responseText;
            alert("تم الادخال بنجاح");
        }
    }
}

function createEmpDetail(uri, form_data) {
    if (empId != "") {
        var url = uri + "?" + form_data +"&currentEmp="+empId;
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

    if (req.readyState == 4) {
        if (req.status == 200) {
            processCallback(req.responseText);
            alert("تم الادخال بنجاح");           
        }
    }
}

function processCallback(responseJson){
    //var datatable = $('#addEvent_table').DataTable();
    alert("%%"+responseJson+"***");
  //datatable.row.add( responseJson ).draw(false);
    $('#addEvent_table').DataTable().row.add($.parseJSON(  responseJson) ).draw(false);
    ///this didn't work  var is not obj it seems!!      >>>  $('#addEvent_table').DataTable().row.add( responseJson ).draw(false);

}