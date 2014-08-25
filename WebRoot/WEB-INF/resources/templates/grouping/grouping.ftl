<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Grouping Management</title>

<!-- Bootstrap -->
<link href="/includes/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/custom.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="/includes/js/html5shiv.min.js"></script>
    <script src="/includes/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="home">Accounting System</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="home">Home</a></li>
					<li class="active"><a href="grouping">Manage Grouping</a></li>
					<li><a href="account/logout">Log Out</a></li>
				</ul>
				<div class="navbar-form navbar-right">
					<button id="new-bill-btn" class="form-control btn btn-default">New Bill</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="col-sm-3 col-md-2 sidebar">
			<ul class="nav nav-sidebar">
				<#list groupings as grp>
					<#if groupingId==grp.id>
						<li class="active">
							<a href="javascript:void(0)">${grp.name}</a>
							<input hidden=true value="${grp.id}"/>
						</li>
					<#else>
						<li>
							<a href="javascript:void(0)">${grp.name}</a>
							<input hidden=true value="${grp.id}"/>
						</li>
					</#if>
				</#list>
			</ul>
		</div>
		<div class="col-sm-9 col-md-10 col-sm-offset-3 col-md-offset-2 grouping-form">
			
		</div>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/includes/js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/includes/js/bootstrap.min.js"></script>
	<script src="/js/common.js"></script>
	<script type="text/javascript">
		$(function(){
			$.ajax({
				url: "/acsys/grouping!loadGrouping?groupingId=${groupingId}",
				cache:false,
				context: document.body,
				beforeSend :function(){},
				success: function(msg){
					$(".grouping-form").html(msg);
				}
			});
			
	        $("#edit").click(function(){
	            $(".form-control").attr("disabled",false);
	            $(".btn-group2").removeClass("hide");
	        })
	        $("#new-bill-btn").click(function(){
	            window.location.href="bill"
	        })
		});
		$(".nav-sidebar li").click(function(){
			var id = this.children[1].value;
			$.ajax({
				url: "/acsys/grouping!loadGrouping?groupingId="+id,
				cache:false,
				context: document.body,
				beforeSend :function(){},
				success: function(msg){
					$(".grouping-form").html(msg);
				}
			});
		});
	</script>
</body>
</html>