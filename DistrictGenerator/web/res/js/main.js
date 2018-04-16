$(document).ready(function () {

    var array = [];

    $('.selectprio').change(function () {
        var selectedId = $(this).prop('id');
        var selectedValue = $(this).val();
        if (selectedValue == "Historical") {
            $('#priority-med').prop("disabled", true);
            $('#priority-low').prop("disabled", true);
        }
        else {
            $('#priority-med').prop("disabled", false);
            $('#priority-low').prop("disabled", false);
            switch (selectedId) {
                case "priority-high":
                    array[0] = selectedValue;
                    break;
                case "priority-med":
                    array[1] = selectedValue;
                    break;
                case "priority-low":
                    array[2] = selectedValue;
                    break;
            }
            $('.selectprio option').each(function () {
                $(this).prop('disabled', false);
            });

            $('.selectprio option').each(function () {
                for (i = 0; i < array.length; i++) {
                    if ($(this).val() == array[i]) {
                        $(this).prop('disabled', true);
                    }
                }
            });
        }
    });

    $('#state-selector').change(function (event) {
        event.preventDefault();
        state = $('#state-selector').find("option:selected").val();
        $('#state-label').text(state);
        switch (state) {
            case 'Maryland':
                processLocation(39.045753, -77.3, maryland, 7.5, "#800000");
                $('#pop-label').text('5,773,552');
                $('#ethnicity-label').text('hispanic or latino(470,632), white(3,359,284), african american(1,700,298), asians(318,853)');
                $('#party-label').text("Democrat");
                $('#male-pop-label').text('2,791,762');
                $('#female-pop-label').text("2,981,790");
                $('#over18-label').text('4,420,588');
                $('#under18-label').text('1,352,964');
                break;
            case 'West Virginia':
                processLocation(38.9, -80.690674, westvirginia, 7, "#103486")
                $('#pop-label').text('1,852,994');
                $('#ethnicity-label').text('hispanic or latino(22268), white(1,739,988), african american(63,124), asians(12,406)');
                $('#party-label').text("Republican");
                $('#male-pop-label').text('913,586');
                $('#female-pop-label').text("939,408");
                $('#over18-label').text('1,465,576');
                $('#under18-label').text('387,418');
                break;
            case 'Rhode Island':
                processLocation(41.5800945, -71.4774291, rhodeisland, 9, "#CCCC00");
                $('#pop-label').text('1,052,567');
                $('#ethnicity-label').text('hispanic or latino(130,655), white(856,869), african american(60,189), asians(30,457)');
                $('#party-label').text("Democrat");
                $('#male-pop-label').text('508,400');
                $('#female-pop-label').text("544,167");
                $('#over18-label').text('828,611');
                $('#under18-label').text('223,956');
                break;

            case 'Missouri':
                processLocation(37.9642529, -91.8318334, missouri, 6, "#434444");
                $('#pop-label').text('1,052,567');
                $('#ethnicity-label').text('hispanic or latino(130,655), white(856,869), african american(60,189), asians(30,457)');
                $('#party-label').text("Democrat");
                $('#male-pop-label').text('508,400');
                $('#female-pop-label').text("544,167");
                $('#over18-label').text('828,611');
                $('#under18-label').text('223,956');
                break;
        }
    });

    $('#submit-filter').click(function (event) {
        event.preventDefault();
        $.ajax({
            url: '/algorithm',
            method: 'GET',
            success: function (data) {
                alert("The data is " + data);
            }
        });
    });

    $('#change-nickname-form').submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: '/user/changeNickname',
            data: $('#change-nickname-form').serialize(),
            success: function (data) {
                window.location.href = "/profile"
            }
        });
    });

    $('#change-password-form').submit(function (event) {
        event.preventDefault();
        if ($('#pw1').val() != $('#pw2').val() || $('#pw1').val().length < 6) {
            $('#error-div').css("visibility", "visible");
        } else {
            $.ajax({
                url: '/user/changePassword',
                data: $('#change-password-form').serialize(),
                success: function (data) {
                    window.location.href = "/profile"
                }
            });
        }
    });

    $('#admin-button').click(function (event) {
        event.preventDefault();
        window.location.href = "/ziZdQ9CSTT"
    });

    $('.admin-submit').click(function (event) {
        event.preventDefault();
        var optionId = $(this).prop('id');
        var url;
        if (optionId == "remove-user") {
            url = "/user/removeuser"
        } else if (optionId == "add-privilege") {
            url = "/user/addprivilege";

        } else if (optionId == "remove-privilege") {
            url = "/user/removeprivilege";

        }
        $.ajax({
            url: url,
            data: $('#panel-form').serialize(),
            success: function (data) {
                window.location.href = "/ziZdQ9CSTT"
            },
            error: function (XMLHttpRequest) {
                $('#error-label').css('color', 'red');
            }
        });
    });
});