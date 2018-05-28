$(document).ready(function () {
    $('#state-selector').change(function (event) {
        event.preventDefault();
        mapHandler();
        resetAll();
        resetLayer();
    });

    $('#submit-properties').click(function (event) {
        event.preventDefault();
        $.ajax({
            url: '/user/updateproperties',
            data: $('#propertiesForm').serialize(),
            success: function (data) {
                alert('Success');
            }
        });
    })


    $('#reset-button').click(function () {
        event.preventDefault();
        resetAll();
    });

    function resetAll() {
        $('#submit-filter').css({'background': '#5279b3'})
        bannedDistricts = [];
        bannedPrecincts = [];
        districtNames = [];
        myMap.clear();
        districtMap.clear();
        init = 0;
        mapHandler();
        resetAllInfo();
        $('#submit-filter').text('Start')
        enableWeights()
        $.ajax({
            url: '/algorithm/reset'
        });
    }

    $('#download-button').click(function () {
        event.preventDefault();
        state = $('#state-selector').find("option:selected").val();
        if (state == "none") {
            $('#state-error').css("visibility", "visible");
        } else {
            $('#state-error').css("visibility", "hidden");
            var fileName;
            var d1 = document.createElement('a');
            switch (state) {
                case 'Maryland':
                    fileName = 'Maryland';
                    d1.setAttribute('href', 'res/js/maryland2010Final.json');
                    break;
                case 'West Virginia':
                    fileName = 'WestVirginia';
                    d1.setAttribute('href', 'res/js/westvirginia2010Final.json');

                    break;
                case 'Missouri':
                    fileName = 'Missouri'
                    d1.setAttribute('href', 'res/js/missouri2010Final.json');
                    break;
            }
            d1.setAttribute('download', fileName + '.json');
            d1.click();
        }
    })

    function mapHandler() {
        infoWindow.close();
        state = $('#state-selector').find("option:selected").val();
        $('#state-label').text(state);
        setRepresentatives(state);
        switch (state) {
            case 'Maryland':
                processLocation(39.045753, -77.3, "maryland2010Final.json", 7.5, "marylandDistricts2010.json");
                $('#pop-label').text('5,773,552');
                $('#ethnicity-label').text('hispanic or latino(470,632), white(3,359,284), african american(1,700,298), asians(318,853)');
                $('#party-label').text("Democrat");
                $('#male-pop-label').text('2,791,762');
                $('#female-pop-label').text("2,981,790");
                $('#over18-label').text('4,420,588');
                $('#under18-label').text('1,352,964');
                break;
            case 'West Virginia':
                processLocation(38.9, -80.690674, "westvirginia2010Final.json", 7, "westvirginiaDistricts2010.json")
                $('#pop-label').text('1,852,994');
                $('#ethnicity-label').text('hispanic or latino(22268), white(1,739,988), african american(63,124), asians(12,406)');
                $('#party-label').text("Republican");
                $('#male-pop-label').text('913,586');
                $('#female-pop-label').text("939,408");
                $('#over18-label').text('1,465,576');
                $('#under18-label').text('387,418');
                break;
            case 'Missouri':
                processLocation(37.9642529, -91.8318334, "missouri2010Final.json", 6, "missouriDistricts2010.json");
                $('#pop-label').text('1,052,567');
                $('#ethnicity-label').text('hispanic or latino(130,655), white(856,869), african american(60,189), asians(30,457)');
                $('#party-label').text("Democrat");
                $('#male-pop-label').text('508,400');
                $('#female-pop-label').text("544,167");
                $('#over18-label').text('828,611');
                $('#under18-label').text('223,956');
                break;
        }
    }

    $('#submit-filter').click(function (event) {
        event.preventDefault();
        state = $('#state-selector').find("option:selected").val()
        if (state == "none") {
            $('#state-error').css("visibility", "visible")
        } else {
            $('#state-error').css("visibility", "hidden")
            var val = $('#submit-filter').text()
            if (val == 'Start') {
                $('#submit-filter').text('Pause');
                callAlgorithm()
            } else if (val == 'Pause') {
                $('#submit-filter').text('Start')
            }
        }
        disableWeights();
    });

    function callAlgorithm() {
        $.ajax({
            url: '/algorithm',
            method: 'GET',
            data: $('#filter-form').serialize() + '&bannedPrecincts=' + bannedPrecincts + "&bannedDistricts=" + bannedDistricts,
            success: function (data) {
                if (typeof data == "string") {
                    data = data.split("#")
                    setOriginalStateInfo(data)
                }
                else {
                    data = JSON.stringify(data)
                    jsonObj = JSON.parse(data)
                    if (jsonObj.termination == 0) {
                        var jsonObj2 = {
                            "polsbyPopper": jsonObj.prevPolsbyPopper,
                            "schwartzberg": jsonObj.prevSchwartzberg,
                            "reock": jsonObj.prevReock,
                            "areaConvexHull": jsonObj.prevAreaConvexHull,
                            "efficiencyGap": jsonObj.prevEfficiencyGap,
                            "equalPopulation": jsonObj.prevEqualPopulation,
                            "racialFairness": jsonObj.prevRacialFairness
                        }
                        myMap.set(jsonObj.districtName, jsonObj)
                        myMap.set(jsonObj.prevDistrictName, jsonObj2)
                        updateMap(jsonObj)
                    } else if (jsonObj.termination == 100) {
                        $('#submit-filter').text('Terminated');
                        $('#submit-filter').prop('disabled', true);
                        $('#submit-filter').css({'background': 'red'})
                    }
                }
            },
            complete: function () {
                if ($('#submit-filter').text() == "Pause") {
                    setTimeout(function () {
                        callAlgorithm();
                    }, 333);
                }
            }
        });
    }

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

    $('#toggleLayer').click(function (event) {
        event.preventDefault();

        if ($('#toggleLayer').text() == 'Display Original') {
            $('#toggleLayer').text('Display Current');
            loadLayer(0)
        } else {
            $('#toggleLayer').text('Display Original');
            loadLayer(1)
        }
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