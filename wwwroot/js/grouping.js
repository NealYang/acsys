$(document).ready(function(){
    $(".add-btn").click(function() {
		$.ajax({
			async:false,
			url: "/acsys/grouping!loadGrouping?groupingId=",
			cache:false,
			context: document.body,
			beforeSend :function(){
			},
			success: function(msg){
				$(".content-form").html(msg);
				$(".add-btn, .edit-btn, .delete-btn").addClass("hide");
				$(".grouping-form .form-control").attr("disabled", false);
				$(".option-btn").attr("disabled", false);
				$(".btn-group2").removeClass("hide");
				$("#creater-name").attr("disabled", true);
				$("#create-date").attr("disabled", true);
			}
		});
	});
	
	$(".edit-btn").click(function() {
		$(".add-btn, .edit-btn, .delete-btn").addClass("hide");
		$(".option-btn").attr("disabled", false);
		$(".form-control").attr("disabled", false);
		$("#creater-name").attr("disabled", true);
		$("#create-date").attr("disabled", true);
		$(".btn-group2").removeClass("hide");
	});
	
	$("#delete-confirm").on("click", function(){
		$(".spinner").removeClass("hide");
		$("#delete-confirm").attr("disabled", true);
		window.location.href="acsys/grouping!deleteGrouping?groupingId=" + $("#group-id").val();
	});
});

$(".nav-sidebar li").click(function(){
	$(this).siblings("li").removeClass("active");
    $(this).addClass("active")
    $('.add-btn, .edit-btn, .delete-btn').removeClass("hide");
    
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

function findActiveGrouping() {
	var lis = $(".nav-sidebar").children();
	if(lis != null && lis != undefined && lis.size() > 0) {
		for (var i=0; i<lis.size(); i++) {
			var li = lis.get(i);
			if($(li).hasClass("active")) {
				return li;
			}
		}
	}
}

function moveOption() {
    $("#attendant").dblclick(function() {
        $("#attendant option:selected").prependTo("#others");
    })
    $("#others").dblclick(function() {
        $("#others option:selected").prependTo("#attendant");
    })
    $("#btn-right").click(function () {
        $("#attendant option:selected").prependTo("#others");
    });
    $("#btn-all-right").click(function () {
        $("#attendant option").prependTo("#others");
    });
    $("#btn-left").click(function () {
        $("#others option:selected").prependTo("#attendant");
    });
    $("#btn-all-left").click(function () {
        $("#others option").prependTo("#attendant");
    });
}

function setAttendants() {
	var oldUserIdStrs = $("#oldUserIds").val();
	var newUserIdStrs = "";
	var addUserIds = "";
	var delUserIds = "";
	
	var options = $("#attendant").get(0).options;
	if(options != null && options.length>0) {
		for(var i=0; i<options.length; i++) {
			newUserIdStrs += options[i].value + ",";
		}
	}
	
	if(oldUserIdStrs != null && oldUserIdStrs != "") {
		var oldUserIds = oldUserIdStrs.split(",");
		if(newUserIdStrs != null && newUserIdStrs != "") {
			var newUserIds = newUserIdStrs.split(",");
			for(var i=0; i<newUserIds.length; i++) {
				var newUserId = newUserIds[i];
				if(newUserId == null || newUserId == "") continue;
				for(var j=0; j<oldUserIds.length; j++) {
					var oldUserId = oldUserIds[j];
					if(newUserId == oldUserId) {
						break;
					}
					if(j == oldUserIds.length-1) {
						addUserIds += newUserId + ",";
					}
				}
			}
			for(var i=0; i<oldUserIds.length; i++) {
				var oldUserId = oldUserIds[i];
				if(oldUserId == null || oldUserId == "") continue;
				for(var j=0; j<newUserIds.length; j++) {
					var newUserId = newUserIds[j];
					if(oldUserId == newUserId) {
						break;
					}
					if(j == newUserIds.length-1) {
						delUserIds += oldUserId + ",";
					}
				}
			}
		} else {
			addUserIds = "";
			delUserIds = oldUserIdStrs;
		}
	} else {
		addUserIds = newUserIdStrs;
		delUserIds = "";
	}
	
	$("#addUserIds").val(addUserIds);
	$("#delUserIds").val(delUserIds);
}