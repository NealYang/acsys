<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Grouping Management</title>

<!-- Bootstrap -->
<link href="/includes/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="/includes/js/html5shiv.min.js"></script>
    <script src="/includes/js/respond.min.js"></script>
    <![endif]-->
</head>

<#import "../dashboard/dashboard.ftl" as dashboard />

<body>
	<@dashboard.common />
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
			<div class="sub-header clearfix">
				<div class="row">
					<h2 class="col-md-10">Manage Grouping</h2>
					<div class="col-md-offset-10">
						<button type="button" class="btn btn-primary">Add</button>
						<button id="edit" type="button" class="btn btn-link">Edit</button>
						<button type="button" class="btn btn-link">Delete</button>
					</div>
				</div>
			</div>
			<div class="content-form">
			</div>
		</div>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/includes/js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/includes/js/bootstrap.min.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/grouping.js"></script>
	<script type="text/javascript">
		$(function(){
			$.ajax({
				url: "/acsys/grouping!loadGrouping?groupingId=${groupingId}",
				cache:false,
				context: document.body,
				beforeSend :function(){
				},
				success: function(msg){
					$(".content-form").html(msg);
				}
			});
		});
	</script>
</body>
</html>