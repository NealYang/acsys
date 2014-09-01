<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Register and Create Your Account</title>

<!-- Bootstrap -->
<link href="/includes/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/sign.css" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="/includes/js/html5shiv.min.js"></script>
    <script src="/includes/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="container">
		<form class="form-signin" id="signForm" name="signForm" action="signup!submit" role="form" method="post">
			<h3 class="form-signin-heading">Sign Up</h3>
			<h5>
				already hava an account? <a href="login" id="sign-in-link">Sign In</a>
			</h5>
			<#if uiError!?contains("email")>
				<div class="form-group has-error">
					<label for="email-name" class="control-label">Email Address</label>
					<input class="form-control" id="email-name" name="email" value="${email!}" />
					<#assign fieldError = (action.getFieldErrors().get("email").get(0))!""/>
					<span class="help-block">${fieldError}</span>
				</div>
			<#else>
				<div class="form-group">
					<label for="email-name" class="control-label">Email Address</label>
					<input class="form-control" id="email-name" name="email" value="${email!}" />
				</div>
			</#if>
			
			<#if uiError!?contains("password")>
				<div class="form-group has-error">
					<label for="password" class="control-label">Password</label>
					<input class="form-control" type="password" id="password" name="password" />
					<#assign fieldError = (action.getFieldErrors().get("password").get(0))!""/>
					<span class="help-block">${fieldError}</span>
				</div>
			<#else>
				<div class="form-group">
					<label for="password" class="control-label">Password</label>
					<input class="form-control" type="password" id="password" name="password" />
				</div>
			</#if>

			<#if uiError!?contains("conPassword")>
				<div class="form-group has-error">
					<label for="conPassword" class="control-label">Confirm Password</label>
					<input class="form-control" type="password" id="conPassword" name="conPassword" />
					<#assign fieldError = (action.getFieldErrors().get("conPassword").get(0))!""/>
					<span class="help-block">${fieldError}</span>
				</div>
			<#else>
				<div class="form-group" id="confirm-password">
					<label for="conPassword" class="control-label">Confirm Password</label>
					<input class="form-control" type="password" id="conPassword" name="conPassword" />
				</div>
			</#if>
			
			<div class="form-group" id="select-group">
				<label for="grouping" class="control-label">Grouping</label>
				<select class="form-control" name="groupingId" id="grouping">
					<#assign hasGroupings = (groupings.size() > 0)/>
					<#if hasGroupings>
						<option value="">Not selected</option>
						<#list groupings as grouping>
							<option value="${grouping.id}">${grouping.name}</option>
						</#list>
					<#else>
						<option value="">Not selected</option>
					</#if>
				</select>
			</div>
			<button id="sign-up" class="btn btn-lg btn-success btn-block" type="submit">Sign Up</button>
		</form>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/includes/js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/includes/js/bootstrap.min.js"></script>
</body>
</html>