$(document).ready(function () {

    $('#register-form').submit(function (event) {
        event.preventDefault();

        if ($('#pw1').val() != $('#pw2').val() || $('#pw1').val().length < 6) {
            $('.ed-2').css("visibility", "visible");
        } else {
            $('.ed-2').css("visibility", "hidden");
            $.ajax({
                url: '/user/register',
                method: 'POST',
                data: $('#register-form').serialize(),
                success: function (data) {

                    if (data == "error") {
                        $('.ed-1').css("visibility", "visible");
                    } else if (data == "ok") {
                        window.location.href = "/"
                    }
                }
            });
        }
    });

    $('#login-form').submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: '/user/login',
            data: $('#login-form').serialize(),
            success: function (data) {
                $('#bContainer').empty();
                $('#bContainer').append(data);
            },
            error: function (data) {
                $('.ed-3').css("visibility", "visible");
            }, statusCode: {
                500: function (response) {
                    $('.ed-3').css("visibility", "visible");
                }
            }
        });
    });

    $('#logout-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            url: '/user/logout',
            success: function (data) {
                $('#bContainer').empty();
                $('#bContainer').append(data);
            }
        });
    });

});