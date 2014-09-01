<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Accounting System</title>

<!-- Bootstrap -->
<link href="/includes/css/bootstrap.min.css" rel="stylesheet">
<link href="/includes/DataTables-1.10.2/examples/resources/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="/includes/js/html5shiv.min.js"></script>
    <script src="/includes/js/respond.min.js"></script>
    <![endif]-->

<!-- jQuery necessary for Bootstrap's JavaScript plugins) -->
<script src="/includes/js/jquery.min.js"></script>
<script src="/includes/DataTables-1.10.2/media/js/jquery.dataTables.js"></script>
<script src="/includes/DataTables-1.10.2/examples/resources/bootstrap/3/dataTables.bootstrap.js"></script>
<script>
        $(document).ready(function() {
            var table = $('#board-list').DataTable({
            	bLengthChange: false
            });
            var table2 = $('#amount-list').DataTable({
                "bFilter":   false,
                "bLengthChange":   false,
                "bPaginate":   false,
                "bInfo":     false
            });
        });
    </script>
</head>

<#import "../dashboard/dashboard.ftl" as dashboard />

<body id="homepage">
	<@dashboard.common />
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<div class="input-group">
					<span class="input-group-addon">Grouping</span>
					<select id="grouping" class="form-control" name="groupingId">
						<#list groupings as grouping>
							<option value="${grouping.id!}">${grouping.name!}</option>
						</#list>
					</select>
				</div>
				<table class="table" id="amount-list">
					<thead>
						<tr>
							<th>User</th>
							<th>Amount</th>
						</tr>
					</thead>
					<tbody>
						<#list users as user>
							<tr class="">
								<td>${user.name!}</td>
								<td>${user.amount!}</td>
							</tr> 
						</#list>
					</tbody>
				</table>
			</div>
			<div class="col-sm-9 col-md-10 col-sm-offset-3 col-md-offset-2 main">
				<div class="sub-header clearfix">
					<h2>Bill List</h2>
				</div>
				<div class="table-responsive">
					<table class="table table-striped" id="board-list">
						<thead>
							<tr>
								<th>Date</th>
								<th>Payer</th>
								<th>Attendant</th>
								<th>Place</th>
								<th>Amount</th>
								<th>Writer's IP</th>
								<th>Operation</th>
							</tr>
						</thead>
						<tbody>
							<#list bills as bill>
								<tr>
									<td>${bill.date?string("yyyy-MM-dd")}</td>
									<td><a href="#">${bill.payUserName!}</a></td>
									<td>
										<#assign attendants = bill.attendants/>
										<#if attendants?exists>
											<#list attendants as attendant>
												<a href="#">${attendant.userName}</a>
											</#list>
										</#if>
									</td>
									<td>${bill.place!}</td>
									<td>${bill.amount!}</td>
									<td>${bill.ipAddr!}</td>
									<td><a href="#">edit</a></td>
								</tr>
							</#list>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<form hidden="true" id="form-hidden" action="home" method="post">
		<input id="input-hidden" name="groupingId" value="" />
	</form>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/includes/js/bootstrap.min.js"></script>
	<script src="/js/common.js"></script>
	<script type="text/javascript">
	    (function($){
	    	<#list groupings as grouping>
				<#if groupingId?exists && groupingId==grouping.id>
					<#assign grpIndex=grouping_index />
					<#break>
				</#if>
			</#list>
			$("#grouping").get(0).selectedIndex = ${grpIndex!0};
			
			$("#grouping").change(function() {
				$("#input-hidden").val($(this).val());
		    	$("#form-hidden").submit();
		    	//window.location.href = "/acsys/bill?groupingId="+$(this).val();
			});
	    })(jQuery);
	</script>
</body>
</html>