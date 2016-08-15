
function validate_forms()
{
    jQuery.validator.addMethod("arabiclettersonly", function (value, element) {
        return this.optional(element) || /^[\u0600-\u06ff ]+$/i.test(value);
    }, "الرجاء إدخال أحرف عربية فقط");

    jQuery.validator.addMethod("digitsonly", function (value, element) {
        return this.optional(element) || /^\d+$/i.test(value);
    }, "الرجاءادخال ارقام فقط");
    
    
    
    $("#personalInfoForm").validate({
        errorLabelContainer: $("#personalInfoForm div.error"),
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
    $("#personalInfoEditForm").validate({
        errorLabelContainer: $("#personalInfoEditForm div.error"),
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
        errorLabelContainer: $("#empEventForm div.error"),
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
        errorLabelContainer: $("#healthLeavForm div.error"),
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
    errorLabelContainer: $("#punishmentForm div.error"),
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
    errorLabelContainer: $("#rewardForm div.error"),
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
    errorLabelContainer: $("#serviceJoinForm div.error"),
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
    errorLabelContainer: $("#trainingForm div.error"),
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
    errorLabelContainer: $("#unpaidVForm div.error"),
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