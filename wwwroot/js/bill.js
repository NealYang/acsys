(function($) {
	$(document).ready(function() {
		// For page refresh start
    	for(var i=0; i<$(".attendant-checkbox").size(); i++) {
			var checkbox = $(".attendant-checkbox").get(i);
			if(checkbox.checked != null && checkbox.checked != undefined && checkbox.checked == true) {
				$(checkbox).parent().next().prop("hidden", false);
			} else {
				$(checkbox).parent().next().prop("hidden", true);
			}
		}
    	setAttendantsForForm()
    	// For page refresh end
		
		$("#grouping-left").change(groupingChange);
	    $("#grouping-right").change(groupingChange);
	    
		$("#go-dutch").click(function() {
			if($(".user-amount").attr("disabled")=="disabled"){
	            $(".user-amount").attr("disabled", false);
	        }
	    });
	    $("#ave-pay").click(function() {
	        if($(".user-amount").attr("disabled")!="disabled"){
	            $(".user-amount").attr("disabled", "disabled");
	        }
	        autoFillUserAmount();
	    });
	    $("#amount").keyup(function(){
	    	autoFillUserAmount();
	    	$("#amount-error").text("");
	    });
	    $("#amount").blur(function(){
	    	$("#save-btn").disabledBtn();
	    	autoFillUserAmount();
	    	$("#amount-error").text("");
	    	$("#save-btn").enabledBtn();
	    });
	    
	    $(".attendant-checkbox").click(function(){
	    	if($(this).prop("checked")) {
	    		$(this).parent().next().prop("hidden", false);
	    	} else {
	    		$(this).parent().next().prop("hidden", true);
	    	}
	    	autoFillUserAmount();
	    	setAttendantsForForm()
	    });
	    
	    $(".user-amount").keyup(totalAmountCheck);
	    
	    $("#save-btn").on("click", function(){
	    	$("#save-btn").disabledBtn();
	    	if(validateForm()) {
	    		$("#form-bill").submit();
	    	}
	    });
	});
	
	function validateForm() {
		var result = validateGrouping();
		result = validatePayer() && result;
		result = validateAmount() && result;
		result = validateAttendant() && result;
		result = totalAmountCheck() && result;
		
		if(!result) {
			$(".error").qtip("destroy");
			$(".error").qtip({
				position: {my: "left center", at: "center right"},
				style: {classes: "qtip-shadow qtip-rounded qtip-red"}
			});
			
			handleErrorMsg($("#form-bill"));
			$("#save-btn").enabledBtn();
		} else {
			$("#error").remove();
		}
		
		return result;
	}
	
	function validateGrouping() {
	    var errorMsg = "Please select a grouping.";
	    if(!!!$("#grouping-right").val()) {
	        setErrorMsg($("#grouping-right"), errorMsg);
	        return false;
	    } else {
	        removeError($("#grouping-right"));
	        return true;
	    }
	}
	
	function validatePayer() {
	    var errorMsg = "Please select a payer.";
	    if(!!!$("#payer").val()) {
	        setErrorMsg($("#payer"), errorMsg);
	        return false;
	    } else {
	        removeError($("#payer"));
	        return true;
	    }
	}
	
	function validateAmount() {
	    var errorMsg = "Please enter a valid amount.";
	    var amount = $("#amount").val();
	    var re = /^(-?\d+)(\.\d+)?$/;
	    if(!re.test(amount)) {
	        setErrorMsg($("#amount"), errorMsg);
	        return false;
	    } else {
	        removeError($("#amount"));
	        return true;
	    }
	}
	
	function validateAttendant() {
	    var errorMsg = "Please select at least one attendant.";
	    if($("#attendants-div").children().length < 1) {
	        setErrorMsg($("#attendants"), errorMsg);
	        return false;
	    } else {
	        removeError($("#attendants"));
	        return true;
	    }
	}
	
	function groupingChange() {
		$("#input-hidden").val($(this).val());
    	$("#form-hidden").submit();
    	//window.location.href = "/acsys/bill?groupingId="+$(this).val();
	}
	
	function getCheckedUserNum() {
		var num = 0;
    	for(var i=0; i<$(".attendant-checkbox").size(); i++) {
			var checkbox = $(".attendant-checkbox").get(i);
			if($(checkbox).prop("checked") != undefined && $(checkbox).prop("checked")) {
				num ++;
			}
		}
    	return num;
	}
	
	function autoFillUserAmount() {
		var checkedUserNum = getCheckedUserNum();
		if(checkedUserNum == 0) return;
		var amount = $("#amount").val();
        if(amount != null && amount != undefined) {
        	var aveAmount = amount;
    		aveAmount = amount/checkedUserNum;
        	$(".user-amount").val(aveAmount.toFixed(2));
        } else {
        	$(".user-amount").val(0);
        }
	}
	
	function totalAmountCheck() {
    	if($("#amount").val() == null || $("#amount").val() == undefined || $("#amount").val().trim() == "") {
    		var amount = 0;
    	} else {
    		var amount = parseFloat($("#amount").val());
    	}
    	if(getCheckedUserNum() < 1) return true;
    	var total = 0;
    	for(var i=0; i<$(".user-amount").size(); i++) {
    		var input = $(".user-amount").get(i);
    		if(input.hidden != undefined && !input.hidden) {
    			total += (input.value != null && input.value != undefined)?parseFloat(input.value):0;
    		}
    	}
    	if((amount-1) <= total && total <= (amount+1)) {
    		$("#amount-error").text("");
    		return true;
    	} else {
    		$("#amount-error").text("The sum doesn't equal amount!");
    		return false;
    	}
	}
	
	function setAttendantsForForm() {
		$("#attendants-div").html("");
		var count = 0;
    	for(var i=0; i<$(".attendant-checkbox").size(); i++) {
			var checkbox = $(".attendant-checkbox").get(i);
			if(checkbox.checked != null && checkbox.checked != undefined && checkbox.checked == true) {
				var userId = $(checkbox).next().val();
				var userName = $(checkbox).parent().text().replace(/\s+/g,"").replace(/[\r\n]/g,"");
				var amount = $(checkbox).parent().next().val();
				$("#attendants-div").append("<input type='text' name='bill.attendants[" + count + "].userId' value='" + userId + "' hidden=true />")
				.append("<input type='text' name='bill.attendants[" + count + "].userName' value='" + userName + "' hidden=true />")
				.append("<input type='text' name='bill.attendants[" + count + "].amount' value='" + amount + "' hidden=true />");
			count++;
			}
		}
	}
})(jQuery)