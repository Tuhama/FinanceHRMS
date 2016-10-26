
function validate_forms()
{
    jQuery.validator.addMethod("arabiclettersonly", function (value, element) {
        return this.optional(element) || /^[\u0600-\u06ff ]+$/i.test(value);
    });

    jQuery.validator.addMethod("digitsonly", function (value, element) {
        return this.optional(element) || /^\d+$/i.test(value);
    });



    $("#personalInfoForm").validate({
        submitHandler: function (form) {
            EmpInfo("addEmployee", $("form#personalInfoForm").serialize());
        },
        errorPlacement: function (error, element) {
            return false;
        },
        rules: {
            firstname: {
                required: true,
                arabiclettersonly: true,
                minlength: 2
            },
            lastname: {
                required: true,
                arabiclettersonly: true,
                minlength: 2
            },
            fathername: {
                required: true,
                minlength: 2
            },
            mothername: {
                required: true,
                minlength: 2
            },
            placeOfBirth: {
                required: true,
                minlength: 2
            },
            dateOfBirth: {
                required: true
            },
            registeinfo: {
                required: true,
                minlength: 2
            },
            nationalnumber: {
                required: true,
                digitsonly: true,
                minlength: 11
            },
            departments_select: {
                required: true
            },
            sections_select: {
                required: true
            }
            , devisions_select: {
                required: true
            },
            basesalary: {
                digitsonly: true,
                required: true
            },
            firstworkdate: {
                required: true
            }
            , workdocnumber: {
                required: true
            }
            ,
            workdocdate: {
                required: true
            }
        },
        messages: {
            required: "*"
        }
    });
    $("#personalInfoEditForm").validate({
        submitHandler: function (form) {
            EmpInfo("editEmployee", $("form#personalInfoEditForm").serialize());
        },
        errorPlacement: function (error, element) {
            return false;
        },
        rules: {
            firstname: {
                required: true,
                arabiclettersonly: true,
                minlength: 2
            },
            lastname: {
                required: true,
                arabiclettersonly: true,
                minlength: 2
            },
            fathername: {
                required: true,
                minlength: 2
            },
            mothername: {
                required: true,
                minlength: 2
            },
            placeOfBirth: {
                required: true,
                minlength: 2
            },
            dateOfBirth: {
                required: true
            },
            registeinfo: {
                required: true,
                minlength: 2
            },
            nationalnumber: {
                required: true,
                digitsonly: true,
                minlength: 11
            },
            departments_select: {
                required: true
            },
            sections_select: {
                required: true
            }
            , devisions_select: {
                required: true
            },
            basesalary: {
                digitsonly: true,
                required: true
            },
            firstworkdate: {
                required: true
            }
            , workdocnumber: {
                required: true
            }
            ,
            workdocdate: {
                required: true
            }
        }
    });
    $("#empEventForm").validate({
        submitHandler: function (form) {
            createEmpDetail("addEvent", $("form#empEventForm").serialize());
            // event.preventDefault();
            currentDataTable = $('#event_table');
        },
        errorPlacement: function (error, element) {
            return false;
        },
        rules: {
            name: {
                required: true,
                arabiclettersonly: true,
                minlength: 2
            },
            startdate: {
                required: true
            },
            salary: {
                required: true,
                digitsonly: true
            },
            doctype: {
                required: true
            },
            docnumber: {
                required: true
            },
            docdate: {
                required: true
            }
        }
    });
    $("#healthLeavForm").validate({
        submitHandler: function (form) {
            createEmpDetail("addHealthLeave", $("form#healthLeavForm").serialize());
            currentDataTable = $('#healthLeave_table');
        },
        errorPlacement: function (error, element) {
            return false;
        },
        rules: {
            startdate: {
                required: true
            },
            enddate: {
                required: true
            },
            year: {
                required: true,
                digitsonly: true
            },
            dayscount: {
                required: true,
                digitsonly: true
            }
        }
    });
}
$("#punishmentForm").validate({
    submitHandler: function (form) {
        createEmpDetail("addPunishment", $("form#punishmentForm").serialize());
        currentDataTable = $('#punishment_table');
    },
    errorPlacement: function (error, element) {
        return false;
    },
    rules: {
        reason: {
            required: true,
            arabiclettersonly: true,
            minlength: 2
        },
        date: {
            required: true
        },
        doctype: {
            required: true
        },
        docnumber: {
            required: true
        },
        docdate: {
            required: true
        }
    }
});
$("#rewardForm").validate({
    submitHandler: function (form) {
        createEmpDetail("addReward", $("form#rewardForm").serialize());
        currentDataTable = $('#reward_table');
    },
    errorPlacement: function (error, element) {
        return false;
    },
    rules: {
        kind: {
            required: true,
            arabiclettersonly: true,
            minlength: 2
        },
        amount: {
            required: true,
            digitsonly: true
        },
        doctype: {
            required: true
        },
        docnumber: {
            required: true
        },
        docdate: {
            required: true
        }
    }
});
$("#serviceJoinForm").validate({
    submitHandler: function (form) {
        createEmpDetail("addServiceJoin", $("form#serviceJoinForm").serialize());
        currentDataTable = $('#serviceJoin_table');
    },
    errorPlacement: function (error, element) {
        return false;
    },
    rules: {
        placeofservice: {
            required: true,
            arabiclettersonly: true,
            minlength: 2
        },
        daysduration: {
            required: true,
            digitsonly: true
        },
        monthsduration: {
            required: true,
            digitsonly: true
        },
        doctype: {
            required: true
        },
        docnumber: {
            required: true
        },
        docdate: {
            required: true
        }
    }
});
$("#trainingForm").validate({
    submitHandler: function (form) {
        createEmpDetail("addTraining", $("form#trainingForm").serialize());
        currentDataTable = $('#training_table');
    },
    errorLabelContainer: $("#trainingForm div.error"), errorPlacement: function (error, element) {
        return false;
    },
    rules: {
        kind: {
            required: true,
            arabiclettersonly: true,
            minlength: 2
        },
        place: {
            required: true,
            arabiclettersonly: true,
            minlength: 2
        },
        startdate: {
            required: true
        },
        enddate: {
            required: true
        },
        duration: {
            required: true,
            digitsonly: true
        }
    }
});
$("#unpaidVForm").validate({
    submitHandler: function (form) {
        deleteEmpDetail("deleteUnpaidV", $("form#deleteUnpaidV_form").serialize());
        currentDataTable = $('#unpaidV_table');
    },
    errorPlacement: function (error, element) {
        return false;
    },
    rules: {
        startdate: {
            required: true
        },
        enddate: {
            required: true
        }, reason: {
            required: true,
            arabiclettersonly: true,
            minlength: 2
        },
        doctype: {
            required: true
        },
        docnumber: {
            required: true
        },
        docdate: {
            required: true
        }
    }
});