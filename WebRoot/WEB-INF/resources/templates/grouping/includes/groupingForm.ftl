<div class="content-form">
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
	<form role="form">
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<label for="group-name">Grouping Name</label>
					<input hidden=true id="group-id" value="${grouping.id!}"/>
					<input type="text" class="form-control" id="group-name" value="${grouping.name!}" disabled>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label for="creater-name">Creater</label>
					<input hidden=true id="creater-id" value="${grouping.creatorID!}"/>
					<input type="text" class="form-control" id="creater-name" value="${grouping.creatorName!}" disabled>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<label for="create-date">Create Date</label>
					<input type="text" class="form-control" id="create-date" value="${grouping.created?string("yyyy-MM-dd")}" disabled>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="attendant">Members</label>
			<div class="row">
				<div class="col-md-5">
					<select id="attendant" name="attendant" multiple class="form-control" disabled size="7">
						<#list users as user>
							<option value="${user.id!}">${user.name!}</option>
						</#list>
					</select>
				</div>
				<div class="col-md-2">
					<button id="btn-right" type="button" class="btn btn-default btn-sm btn-block">
						<span class="glyphicon glyphicon-chevron-right"></span>
					</button>
					<button id="btn-all-right" type="button" class="btn btn-default btn-sm btn-block">
						<span class="glyphicon glyphicon-chevron-right"></span>
						<span class="glyphicon glyphicon-chevron-right"></span>
					</button>
					<button id="btn-left" type="button" class="btn btn-default btn-sm btn-block">
						<span class="glyphicon glyphicon-chevron-left"></span>
					</button>
					<button id="btn-all-left" type="button" class="btn btn-default btn-sm btn-block">
						<span class="glyphicon glyphicon-chevron-left"></span>
						<span class="glyphicon glyphicon-chevron-left"></span>
					</button>
				</div>
				<div class="col-md-5">
					<select name="attendant" multiple class="form-control" id="others" size="7" disabled>
						<#list allUsers as user>
							<option value="${user.id!}">${user.name!}</option>
						</#list>
					</select>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="comment">Comment</label>
			<textarea id="comment" class="form-control" rows="2" disabled>${grouping.remark!}</textarea>
		</div>
		<div class="btn-group2 hide">
			<button type="submit" class="btn btn-primary">Save</button>
			<button type="button" class="btn btn-link">Cancel</button>
		</div>
	</form>
</div>