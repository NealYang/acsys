(function(){
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
})(jQuery);