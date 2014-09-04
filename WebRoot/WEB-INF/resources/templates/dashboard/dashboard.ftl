<#macro common>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="home">Accounting System</a>
				<#if currentUser?exists>
					<p class="navbar-text" style="color: white;">Welcome ${currentUser.name!}</p>
					<input type="text" hidden=true id="currentUser" value="${currentUser.name!}"/>
				<#else>
					<p>Welcome</p>
					<input type="text" hidden=true id="currentUser" value=""/>
				</#if>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="home">Home</a></li>
					<li><a href="bill">Bill</a></li>
					<li><a href="grouping">Manage Grouping</a></li>
					<li><a href="account/logout">Log Out</a></li>
				</ul>
			</div>
		</div>
	</div>
</#macro>

<#macro common_new>
	<div class="blog-masthead navbar-fixed-top">
		<div class="container">
			<nav class="blog-nav">
				<a href="#" class="blog-nav-item active">Home</a>
				<a href="#" class="blog-nav-item">New features</a>
				<a href="#" class="blog-nav-item">Press</a>
				<a href="#" class="blog-nav-item">New hires</a>
				<a href="#" class="blog-nav-item">About</a>
			</nav>
		</div>
	</div>
</#macro>