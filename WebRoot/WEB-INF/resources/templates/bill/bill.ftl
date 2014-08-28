<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>New Bill</title>

<!-- Bootstrap -->
<link href="/includes/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/custom.css" rel="stylesheet">
<link href="/css/bill.css" rel="stylesheet">

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
				<table class="table">
					<thead>
						<tr>
							<th>User<span class="caret"></span></th>
							<th>Amount<span class="caret"></span></th>
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
								<input type="date" class="form-control" name="bill.date" id="date" />
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
									<input type="radio" checked id="ave-pay" value="The Average Pay" />
									Average Pay
								</label>
								<label class="radio-inline">
									<input type="radio" id="go-dutch" value="Go Dutch" />
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
									<input type="text" class="user-amount" disabled="true" />
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
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/includes/js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/includes/js/bootstrap.min.js"></script>
	<script src="/js/bill.js"></script>
	<script type="text/javascript">
	    (function($){
	    	<#list groupings as grouping>
				<#if groupingId?exists && groupingId==grouping.id>
					<#assign index=grouping_index />
					<#assign groupingName=grouping.name>
					<#break>
				</#if>
			</#list>
			$("#grouping-left").get(0).selectedIndex = ${index!0};
			$("#grouping-right").get(0).selectedIndex = ${index!0};
			$("#groupingName").val("${groupingName!}");
			$("#payer").change(function(){
				var index = this.selectedIndex;
				$("#payerName").val(this.options[index].text.replace(/\s+/g,"").replace(/[\r\n]/g,""));
			});
	    })(jQuery);
</script>
</body>
</html>