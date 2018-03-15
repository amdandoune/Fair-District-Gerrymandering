$(document).ready(function() {

    var array = [];

    $('.selectprio').change(function() {

        var selectedId = $(this).prop('id');
        var selectedValue = $(this).val();
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
    });
});