/**
 * Created by Nikolay on 24.07.2017.
 */
var energyValue;

function initEditor(){
    $("#comment-form\\:txtEditor").Editor();
}

function initEnergyVaelue(){
    var baseUrl = document.location.origin;
    var apiUrl = '/api/recipe/recipeProductInfo?recipeId=';
    var param = getParamFromUrl('recipeId');


    $.getJSON( baseUrl+apiUrl+param, function( data ) {
        energyValue = data;
    });
}

$(document).ready(function() {
    initEditor();
    initEnergyVaelue();

});