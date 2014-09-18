$(document).ready(function(){
	$(".delBill").on("click", function(){
		$("#delBillId").val($(this).attr("value"));
	});
    $("#delete-confirm").on("click", function() {
		$.ajax({
			async: false,
			method: "post",
			data: {"billId": $("#delBillId").val()},
			url: "/acsys/bill!delBill",
			beforeSend: function(XHR) {
				$(".spinner").removeClass("hide");
		    	$("#delete-confirm").disabledBtn();
			},
			error: function(XMLHttpRequest, msg) {
				alert(msg);
				$("#form-hidden").submit();
			},
			success: function(msg) {
				if(msg.backParam != undefined && msg.backParam == "complete") {
					$("#form-hidden").submit();
				} else {
					$(".modal-footer .btn-primary").click();
					alert("Sorry, we are unable to process your request at this time. Please try again.");
					$("#form-hidden").submit();
				}
			}
		});
	});
});