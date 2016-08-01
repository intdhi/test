<%@ include file="/common/customtag.jsp" %>
<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
<!-- 			<li class="sidebar-search">
				<div class="input-group custom-search-form">
					<input type="text" class="form-control" placeholder="Search...">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div> /input-group
			</li> -->
			<li><a href="<c:url value="/main" />"><i class="fa fa-dashboard fa-fw"></i>
					Dashboard</a></li>
			<li><a href="<c:url value="/core/merchant" />"><i class="fa fa-table fa-fw"></i>
					Merchant</a></li>
			<li><a href="#"><i class="fa fa-table fa-fw"></i>
					Menu 2</a></li>
			<li><a href="#"><i class="fa fa-table fa-fw"></i>
					Menu 3</a></li>
			<li><a href="#"><i class="fa fa-table fa-fw"></i>
					Menu 4</a></li>
			<li><a href="#"><i class="fa fa-sitemap fa-fw"></i>
					Menu Dropdown<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="#">Second Level Item</a></li>
					<li><a href="#">Second Level Item</a></li>
					<li><a href="#">Third Level <span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li><a href="#">Third Level Item</a></li>
							<li><a href="#">Third Level Item</a></li>
							<li><a href="#">Third Level Item</a></li>
							<li><a href="#">Third Level Item</a></li>
						</ul> <!-- /.nav-third-level --></li>
				</ul> <!-- /.nav-second-level --></li>
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->