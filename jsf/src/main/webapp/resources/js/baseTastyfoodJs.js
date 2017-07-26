/**
 * Created by Nikolay on 24.07.2017.
 */

function getParamFromUrl(paramName){
    return getUrlVars()[paramName];
}

function getUrlVars()
{
    var vars = [], param;
    var params = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < params.length; i++)
    {
        param = params[i].split('=');
        vars.push(param[0]);
        vars[param[0]] = param[1];
    }
    return vars;
}