$(document).ready(function(){
	$("#edit").click(function() {
		$(".form-control").attr("disabled", false);
		$(".btn-group2").removeClass("hide");
	})
});
$(".nav-sidebar li").click(function(){
	$(this).siblings("li").removeClass("active");
    $(this).addClass("active")
    
	var id = this.children[1].value;
	$.ajax({
		async:false,
		url: "/acsys/grouping!loadGrouping?groupingId="+id,
		cache:false,
		context: document.body,
		beforeSend :function(){
		},
		success: function(msg){
			$(".content-form").html(msg);
		}
	});
});