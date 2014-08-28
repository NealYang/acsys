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
	});
	
	function groupingChange() {
		$("#input-hidden").val($(this).val());
    	$("#form-hidden").submit();
    	//window.location.href = "/acsys/bill?groupingId="+$(this).val();
	}
	
	function autoFillUserAmount() {
		var checkedUserNum = getCheckedUserNum();
		if(checkedUserNum == 0) return;
		var amount = $("#amount").val();
        if(amount != null && amount != undefined && amount > 0) {
        	var aveAmount = amount;
    		aveAmount = amount/checkedUserNum;
        	$(".user-amount").val(aveAmount.toFixed(2));
        } else {
        	$(".user-amount").val(0);
        }
	}
	
	function getCheckedUserNum() {
		var num = 0;
    	for(var i=0; i<$(".attendant-checkbox").size(); i++) {
			var checkbox = $(".attendant-checkbox").get(i);
			if(checkbox.checked != null && checkbox.checked != undefined && checkbox.checked == true) {
				num ++;
			}
		}
    	return num;
	}
	
	function totalAmountCheck() {
		var amount = $("#amount").val();
    	if(amount == null || amount == undefined || amount < 0) {
    		amount = 0;
    	}
    	var total = 0;
    	for(var i=0; i<$(".user-amount").size(); i++) {
    		var input = $(".user-amount").get(i);
    		if(input.hidden != undefined && input.hidden==false) {
    			total += (input.value != null && input.value != undefined && input.value > 0)?parseFloat(input.value):0;
    		}
    	}
    	if(total!=amount) {
    		$("#amount-error").text("The sum doesn't equal amount!");
    	} else {
    		$("#amount-error").text("");
    	}
	}
	
	function setAttendantsForForm() {
		$("#attendants-div").html("");
    	for(var i=0; i<$(".attendant-checkbox").size(); i++) {
			var checkbox = $(".attendant-checkbox").get(i);
			if(checkbox.checked != null && checkbox.checked != undefined && checkbox.checked == true) {
				var userId = $(checkbox).next().val();
				var userName = $(checkbox).parent().text().replace(/\s+/g,"").replace(/[\r\n]/g,"");
				var amount = $(checkbox).parent().next().val();
				$("#attendants-div").append("<input type='text' name='bill.attendants[" + i + "].userId' value='" + userId + "' hidden=true />")
				.append("<input type='text' name='bill.attendants[" + i + "].userName' value='" + userName + "' hidden=true />")
				.append("<input type='text' name='bill.attendants[" + i + "].amount' value='" + amount + "' hidden=true />");
			}
		}
	}
})(jQuery)