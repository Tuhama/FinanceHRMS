<%-- 
    Document   : login
    Created on : Aug 15, 2016, 10:28:58 AM
    Author     : Tuhama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>تسجيل الدخول</title>
        <link href="//css/login_style.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-3.1.0.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {

                // Check if JavaScript is enabled
                $('body').addClass('js');

                // Make the checkbox checked on load
                $('.login-form span').addClass('checked').children('input').attr('checked', true);

                // Click function
                $('.login-form span').on('click', function () {

                    if ($(this).children('input').attr('checked')) {
                        $(this).children('input').attr('checked', false);
                        $(this).removeClass('checked');
                    }

                    else {
                        $(this).children('input').attr('checked', true);
                        $(this).addClass('checked');
                    }

                });

            });
        </script>
    </head>
    <body>
        <div>

            <form name="login-form" action="#">
                <input type="text" name="username" placeholder="اسم المستخدم">
                <input type="password" name="password" placeholder="كلمة السر">
                <span>
                    <input type="checkbox" name="checkbox">
                    <label for="checkbox">تذكر</label>
                </span>
                <input type="submit" value="log in">
            </form>
        </div>
    </body>
</html>
