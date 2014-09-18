$(document).ready(function () {
	switch ($("body").attr("id")) {
		case "home":
			$(".navbar-nav li").eq(0).addClass("active");
			break;
		case "bill":
			$(".navbar-nav li").eq(1).addClass("active");
			break;
		case "grouping":
			$(".navbar-nav li").eq(2).addClass("active");
			break;
	}
});

var stopPropagationButton = function(event){
    event.preventDefault();
    event.stopPropagation();
    return false;
}

function setErrorMsg(obj, errorMsg) {
	var parentClass = obj.parent().attr('class');
	if (parentClass == 'has-error') {
		obj.parent().attr('title', errorMsg);
	} else {
		obj.wrap('<span class="has-error" title="' + errorMsg + '" />');
	}
}

function removeError(obj) {
	var parentClass = obj.parent().attr('class');
	if (parentClass == 'has-error') {
		obj.unwrap();
	}
}

$.fn.extend({
    disabledBtn: function(){
        return this.each(function(){
            $(this).addClass('disabled');
            $(this).bind('click', stopPropagationButton);
        });
    },
    enabledBtn: function(){
        return this.each(function(){
            $(this).removeClass('disabled');
            $(this).unbind('click', stopPropagationButton);
        })
    }
});

function handleErrorMsg(formObj) {
	var errorMsg = "We're sorry, we're experiencing a temporary technical issue. Please wait a few minutes, then try again.";
	if ($('.form-error').length > 0) {
		errorMsg = "Please correct the indicated field(s) below. Hover over the field for more details.";
	}
	var field = 'field';
	if ($('.form-error').length > 1) {
		field = 'fields';
	}
	if ($('#error').length == 0) {
		formObj.before('<div class="form-error" id="error"><p class="heading">' + errorMsg + '</p></div>');
	} else {
		$('#form-error>p').text(errorMsg);
	}
	if ($('.has-error:first').length > 0) {
        var inputObj = $('.has-error:first').children().eq(0);
        if (!inputObj.hasClass('hasDatepicker')) {
		    inputObj.focus();
        }
	}
}