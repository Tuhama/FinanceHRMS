
function validate_forms()
{

    $("#personalInfoForm").validate({
        rules: {
            firstname: {
                required: true,
                minlength: 2
            },
            lastname: {
                required: true,
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
            lastname: {
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
                minlength: 2
            },
            branch_id: {
                required: true
            }
        },
        messages: {
            firstname: "الرجاء ادخال الاسم",
            lastname: "الرجاء ادخال الكنية",
            fathername: {
                required: "الرجاء ادخال اسم الاب",
                minlength: "Your username must consist of at least 2 characters"
            }
        }
    });
}



//not working
function override_error_messages() {
    /* var intputElements = document.getElementsByTagName("input");
     for (var i = 0; i < intputElements.length; i++) {
     intputElements[i].oninvalid = function (e) {
     e.target.setCustomValidity("");
     if (!e.target.validity.valid) {
     if (e.target.name == "email") {
     e.target.setCustomValidity("Please enter a valid email address.");
     } else {
     e.target.setCustomValidity("Please enter a password.");
     }
     }
     }
     }*/


    /*  $('input[required], input[required="required"]').each(function(i, e)
     {
     e.oninput = function(el)
     {
     el.target.setCustomValidity("");
     
     if (el.target.type == "number")
     {
     if (el.target.validity.patternMismatch || el.target.validity.typeMismatch )
     {
     el.target.setCustomValidity("الرجاء إدخال ارقام فقط");
     }
     }
     };
     
     e.oninvalid = function(el)
     {
     el.target.setCustomValidity(!el.target.validity.valid ? e.attributes.requiredmessage.value : "لا يمكنك ترك الحقل فارغا");
     };
     });*/


    var elements = document.getElementsByTagName("input");
    for (var i = 0; i < elements.length; i++) {
        elements[i].oninvalid = function (e) {
            e.target.setCustomValidity("");
            if (!e.target.validity.valid) {
                e.target.setCustomValidity("الرجاء التحقق من القيمةالمدخلة");
            }
        };
        elements[i].oninput = function (e) {
            e.target.setCustomValidity("");
        };
    }
    var intputElements = document.getElementsByTagName("INPUT");
    for (var i = 0; i < intputElements.length; i++) {
        intputElements[i].oninvalid = function (e) {
            e.target.setCustomValidity("");
            if (!e.target.validity.valid) {
                if (e.target.name == "username") {
                    e.target.setCustomValidity("The field 'Username' cannot be left blank");
                }
                else {
                    e.target.setCustomValidity("The field 'Password' cannot be left blank");
                }
            }
        };
    }
}