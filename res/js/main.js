$(document).ready(function() {

    var array = [];

    $('.selectprio').change(function() {

        var selectedId = $(this).prop('id');
        var selectedValue = $(this).val();


        if (selectedValue == "Historical") {
            $('#priority-med').prop("disabled", true);
            $('#priority-low').prop("disabled", true);
        }
        else {
            $('#priority-med').prop("disabled", false);
            $('#priority-low').prop("disabled", false);
            switch (selectedId){
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

            $('.selectprio option').each(function() {
                $(this).prop('disabled', false);
            });

            $('.selectprio option').each(function() {
                for (i = 0; i < array.length; i++) {
                    if ($(this).val() == array[i]) {
                        $(this).prop('disabled', true);
                    }
                }
            });
        }
    });
});