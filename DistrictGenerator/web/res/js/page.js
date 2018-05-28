function getPolsbyPopperWeight() {
    var sliderValue = document.getElementById('polsbyPopper').value;
    document.getElementById('polsbyPopperWeight').innerHTML = sliderValue;
    document.getElementById('polsbyPopperWeightInput').value = sliderValue;
    enableButton();
}

function getSchwartzbergWeight() {
    var sliderValue = document.getElementById('schwartzberg').value;
    document.getElementById('schwartzbergWeight').innerHTML = sliderValue;
    document.getElementById('schwartzbergWeightInput').value = sliderValue;
    enableButton()
}

function getReockWeight() {
    var sliderValue = document.getElementById('reock').value;
    document.getElementById('reockWeight').innerHTML = sliderValue;
    document.getElementById('reockWeightInput').value = sliderValue;
    enableButton()
}

function getAreaConvexHullWeight() {
    var sliderValue = document.getElementById('areaConvexHull').value;
    document.getElementById('areaConvexHullWeight').innerHTML = sliderValue;
    document.getElementById('areaConvexHullWeightInput').value = sliderValue;
    enableButton()
}

function getEfficiencyGap() {
    var sliderValue1 = document.getElementById('efficiencyGap').value;
    document.getElementById('efficiencyGapWeight').innerHTML = sliderValue1;
    document.getElementById('efficiencyGapWeightInput').value = sliderValue1;
    enableButton()
}

function getEqualVoteWeightWeight() {
    var sliderValue = document.getElementById('equalVoteWeight').value;
    document.getElementById('equalVoteWeightWeight').innerHTML = sliderValue;
    document.getElementById('equalVoteWeightWeightInput').value = sliderValue;
    enableButton()
}

function getRacialFairnessWeight() {
    var sliderValue2 = document.getElementById('racial-fairness').value;
    document.getElementById('RacialFairnessWeight').innerHTML = sliderValue2;
    document.getElementById('RacialFairnessWeightInput').value = sliderValue2;
    enableButton()
}

function getEqualPopulationWeight() {
    var sliderValue3 = document.getElementById('equal-population').value;
    document.getElementById('EqualPopulationWeight').innerHTML = sliderValue3;
    document.getElementById('EqualPopulationWeightInput').value = sliderValue3;
    enableButton()
}

function enableButton() {
    if (document.getElementById('polsbyPopperWeightInput').value != 0 || document.getElementById('schwartzbergWeightInput').value != 0 ||
        document.getElementById('reockWeightInput').value != 0 ||  document.getElementById('areaConvexHullWeightInput').value != 0 ||
        document.getElementById('efficiencyGapWeightInput').value != 0 ||  document.getElementById('equalVoteWeightWeightInput').value != 0 ||
        document.getElementById('RacialFairnessWeightInput').value != 0 || document.getElementById('EqualPopulationWeightInput').value != 0) {
        document.getElementById('submit-filter').disabled = false;
    } else {
        document.getElementById('submit-filter').disabled = true;
    }
}

function disableWeights() {
    document.getElementById('polsbyPopper').disabled = true;
    document.getElementById('schwartzberg').disabled = true;
    document.getElementById('reock').disabled = true;
    document.getElementById('areaConvexHull').disabled = true;
    document.getElementById('efficiencyGap').disabled = true;
    document.getElementById('equalVoteWeight').disabled = true;
    document.getElementById('racial-fairness').disabled = true;
    document.getElementById('equal-population').disabled = true;
}

function enableWeights() {
    document.getElementById('polsbyPopper').disabled = false;
    document.getElementById('polsbyPopper').value = 0;
    document.getElementById('polsbyPopperWeight').innerHTML = 0;
    document.getElementById('polsbyPopperWeightInput').value = 0;
    document.getElementById('schwartzberg').disabled = false;
    document.getElementById('schwartzberg').value = 0;
    document.getElementById('schwartzbergWeight').innerHTML = 0;
    document.getElementById('schwartzbergWeightInput').value = 0;
    document.getElementById('reock').disabled = false;
    document.getElementById('reock').value = 0;
    document.getElementById('reockWeight').innerHTML = 0;
    document.getElementById('reockWeightInput').value = 0;
    document.getElementById('areaConvexHull').disabled = false;
    document.getElementById('areaConvexHull').value = 0;
    document.getElementById('areaConvexHullWeight').innerHTML = 0;
    document.getElementById('areaConvexHullWeightInput').value = 0;
    document.getElementById('efficiencyGap').disabled = false;
    document.getElementById('efficiencyGap').value = 0;
    document.getElementById('efficiencyGapWeight').innerHTML = 0;
    document.getElementById('efficiencyGapWeightInput').value = 0;
    document.getElementById('equalVoteWeight').disabled = false;
    document.getElementById('equalVoteWeight').value = 0;
    document.getElementById('equalVoteWeightWeight').innerHTML = 0;
    document.getElementById('equalVoteWeightWeightInput').value = 0;
    document.getElementById('racial-fairness').disabled = false;
    document.getElementById('racial-fairness').value = 0;
    document.getElementById('RacialFairnessWeight').innerHTML = 0;
    document.getElementById('RacialFairnessWeightInput').value = 0;
    document.getElementById('equal-population').disabled = false;
    document.getElementById('equal-population').value = 0;
    document.getElementById('EqualPopulationWeight').innerHTML = 0;
    document.getElementById('EqualPopulationWeightInput').value = 0;
    document.getElementById('submit-filter').disabled = true;
}