
function validate_forms()
{
jQuery.validator.addMethod("arabiclettersonly", function(value, element) {
  return this.optional(element) || /^[\u0600-\u06ff ]+$/i.test(value);
},"الرجاء إدخال أحرف عربية فقط"); 

jQuery.validator.addMethod("digitsonly", function(value, element) {
  return this.optional(element) || /^\d+$/i.test(value);
},"الرجاءادخال ارقام فقط"); 
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
                digitsonly :true,
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
                digitsonly :true,
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
}
