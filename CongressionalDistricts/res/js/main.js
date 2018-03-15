$(document).ready(function() {

    var array = [];

    $('.selectprio').change(function() {

        var selectedId = $(this).prop('id');
        var selectedValue = $(this).val();
        var DisplayHistoricalMap="DisplayHistoricalMap"
        switch (selectedId){
            case "priority-high":
                array[0] = selectedValue;
                // console.log($(this).val());
                break;
            case "priority-med":
                array[1] = selectedValue;
                break;
            case "priority-low":
                array[2] = selectedValue;
                break;
                          }
              // console.log($(this).val());
              if($(this).val() == DisplayHistoricalMap)
              {
                $('.selectprio1 option').each(function() {
                    $(this).prop('disabled', true);
                });
                $('.selectprio2 option').each(function() {
                    $(this).prop('disabled', true);
                });
              }
              else{
                $('.selectprio1 option').each(function() {
                    $(this).prop('disabled', false);
                });
                $('.selectprio2 option').each(function() {
                    $(this).prop('disabled', false);
                });
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
