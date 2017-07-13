function fireEventIfPresent() {
    if ($._data(document, "events")['myevent']) { // check if eventhandler is present
        $(document).trigger("myevent");
    } else {
        alert('No event handler in parent page');
    }
}

function logOut() {
    var _csrf = $('.csrf').val();

    $.post("/j_spring_security_logout", {
        '_csrf': _csrf
    }, location.reload())
}
function openLoginPopUp(){

    var full = window.location.href;
    var cur = window.location.pathname;
    var result = full.substr(full.indexOf(cur),100);
    $.cookie.raw=true;
    $.cookie('url',result);
    $.cookie.raw=false;
    PF('loginPopUpWidget').show();
    return false;
}

function removeUrlCookie(){
    $.removeCookie('url')
}


function validateRegistration(){
    $('#register_form')
        .formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                'register_form:username': {
                    // The messages for this field are shown as usual
                    validators: {
                        notEmpty: {
                            message: 'Нужно ввести логин'
                        },
                        remote: {
                            url: '/login/check',
                            data: function(validator, $field, value) {
                                return {
                                    login: validator.getFieldElements('register_form:username').val()
                                };
                            },
                            message: 'логин занят',
                            type: 'GET'
                        }
                    }
                },

                inputPasswordConfirm: {
                    validators: {
                        identical: {
                            field: 'register_form:inputPassword',
                            message: 'Пароль не совпадает'
                        }
                    }
                }
            }
        });
}

