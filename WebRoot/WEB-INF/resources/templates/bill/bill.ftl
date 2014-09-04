<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>New Bill</title>

<!-- Bootstrap -->
<link href="/includes/css/bootstrap.css" rel="stylesheet">
<link href="/includes/css/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="/includes/DataTables-1.10.2/examples/resources/bootstrap/3/dataTables.bootstrap.css"  rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/bill.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="/includes/js/html5shiv.min.js"></script>
    <script src="/includes/js/respond.min.js"></script>
    <![endif]-->
    
    <!-- jQuery necessary for Bootstrap's JavaScript plugins) -->
    <script src="/includes/js/jquery-2.1.1.js"></script>
    <script src="/includes/js/bootstrap-datetimepicker.js"></script>
    <script src="/includes/DataTables-1.10.2/media/js/jquery.dataTables.js"></script>
    <script src="/includes/DataTables-1.10.2/examples/resources/bootstrap/3/dataTables.bootstrap.js"></script>
    <script>
        $(document).ready(function() {
            $('#amount-list').DataTable({
                "bFilter": false,
                "bLengthChange": false,
                "bPaginate": false,
                "bInfo": false
            });
		    $('.form_datetime').datetimepicker({
		    	format: "mm/dd/yyyy",
		    	minView: 2,
		    	autoclose: true,
		    	todayHighlight: true,
		    	todayBtn: true
		    }).datetimepicker('update', new Date());
        });
    </script>
</head>

<#import "../dashboard/dashboard.ftl" as dashboard />

<body id="bill">
	<@dashboard.common />
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<div class="input-group">
					<span class="input-group-addon">Grouping</span>
					<select class="form-control" id="grouping-left">
						<#list groupings as grouping>
							<option value="${grouping.id!}">${grouping.name!}</option>
						</#list>
					</select>
				</div>
				<table id="amount-list" class="table">
					<thead>
						<tr>
							<th>User</span></th>
							<th>Amount</span></th>
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
				<h2 class="sub-header">New Bill</h2>
				<form id="form-bill" action="bill!submit" role="form" method="post">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="date">Date</label>
								<!--input type="date" class="form-control" name="bill.date" id="date" /-->
								<div class="input-append input-group date form_datetime">
									<input id="date" size="16" type="text" class="form-control" name="bill.date" value="" readonly />
									<span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
							<div class="form-group">
								<label for="payer">Payer</label>
								<select id="payer" class="form-control" name="bill.payUserId">
									<#list users as user>
										<option value="${user.id!}">${user.name!}</option>
									</#list>
								</select>
								<input type="text" id="payerName" name="bill.payUserName" hidden=true value="" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="grouping">Grouping</label>
								<select id="grouping-right" class="form-control" name="bill.groupingId">
									<#list groupings as grouping>
										<option value="${grouping.id!}">${grouping.name!}</option>
									</#list>
								</select>
								<input type="text" id="groupingName" name="bill.groupingName" hidden=true value="" />
							</div>
							<div class="form-group">
								<label for="amount">Amount</label>
								<input type="text" class="form-control" id="amount" name="bill.amount" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="place">Place</label>
								<input type="text" class="form-control" id="place" name="bill.place" />
							</div>
							<div class="form-group radio-group">
								<label class="radio-inline">
									<input type="radio" checked id="ave-pay" name="radio" value="The Average Pay" />
									Average Pay
								</label>
								<label class="radio-inline">
									<input type="radio" id="go-dutch" name="radio" value="Go Dutch" />
									Go Dutch
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label>
							Attendant&nbsp;<span id="amount-error" style="color: red;font-size: 12px;"></span>
						</label>
						<div class="list-group list-overflow">
							<#list users as user>
								<div class="list-group-item checkbox">
									<label style="width: 110px;">
										<input type="checkbox" class="attendant-checkbox" />
										<input type="text" hidden=true value="${user.id!}" />&nbsp;&nbsp;${user.name!}
									</label>
									<input type="text" class="user-amount bootstrap-input" disabled="disabled" />
								</div>
							</#list>
						</div>
						<div hidden=true id="attendants-div">
						</div>
					</div>
					<div class="form-group">
						<label for="comment">Comment</label>
						<textarea id="comment" name="bill.remark" class="form-control" rows="2"></textarea>
					</div>
					<div class="btn-group2">
						<button type="submit" class="btn btn-primary">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<form hidden="true" id="form-hidden" action="bill" method="post">
		<input id="input-hidden" name="groupingId" value="" />
	</form>
	
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/includes/js/bootstrap.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/bill.js"></script>
	<script type="text/javascript">
	    (function($){
	    	<#list groupings as grouping>
				<#if groupingId?exists && groupingId==grouping.id>
					<#assign grpIndex=grouping_index />
					<#assign groupingName=grouping.name>
					<#break>
				</#if>
			</#list>
			$("#grouping-left").get(0).selectedIndex = ${grpIndex!0};
			$("#grouping-right").get(0).selectedIndex = ${grpIndex!0};
			$("#groupingName").val("${groupingName!}");
			
			<#list users as user>
				<#if user_index==0>
					<#assign userName=user.name>
				</#if>
				<#if currentUser?exists && currentUser.id==user.id>
					<#assign urIndex=user_index />
					<#assign userName=user.name>
					<#break>
				</#if>
			</#list>
			$("#payer").get(0).selectedIndex = ${urIndex!0};
			$("#payerName").val("${userName!}");
			$("#payer").change(function(){
				var index = this.selectedIndex;
				$("#payerName").val(this.options[index].text.replace(/\s+/g,"").replace(/[\r\n]/g,""));
			});
	    })(jQuery);
</script>
</body>
</html>