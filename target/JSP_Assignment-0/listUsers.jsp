<%-- 
    Document   : listMember.jsp
    Created on : 2023?5?3?, ??4:38:53
    Author     : roger
--%>

<%@page import="ict.bean.UserBean"%>
<%@page import="ict.bean.BookingBean"%>
<%@page import="java.util.ArrayList"%>
<!doctype html>
<html lang="en" class="semi-dark">

<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--favicon-->
	<link rel="icon" href="assets/images/favicon-32x32.png" type="image/png" />
	<!--plugins-->
	<link href="assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
	<link href="assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
	<link href="assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />
	<link href="assets/plugins/datatable/css/dataTables.bootstrap5.min.css" rel="stylesheet" />
	<!-- loader-->
	<link href="assets/css/pace.min.css" rel="stylesheet" />
	<script src="assets/js/pace.min.js"></script>
	<!-- Bootstrap CSS -->
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="assets/css/bootstrap-extended.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
	<link href="assets/css/app.css" rel="stylesheet">
	<link href="assets/css/icons.css" rel="stylesheet">
	<!-- Theme Style CSS -->
	<link rel="stylesheet" href="assets/css/dark-theme.css" />
	<link rel="stylesheet" href="assets/css/semi-dark.css" />
	<link rel="stylesheet" href="assets/css/header-colors.css" />
	<title>Event Point</title>
</head>

<body>
	<!--wrapper-->
	<div class="wrapper">
		<!--sidebar wrapper -->
		<div class="sidebar-wrapper" data-simplebar="true">
			<div class="sidebar-header">
				<div>
					<img src="assets/images/logo-icon.png" class="logo-icon" alt="logo icon">
				</div>
				<div>
					<h4 class="logo-text">Event Point</h4>
				</div>
				<div class="toggle-icon ms-auto"><i class='bx bx-arrow-to-left'></i>
				</div>
			</div>
			<!--navigation-->
			<ul class="metismenu" id="menu">
<!--                                <li>
					<a href="handleUser?action=report">
						<div class="parent-icon"><i class=""></i>
						</div>
						<div class="menu-title">Report</div>
					</a>
				</li>-->
				<li>
					<a href="handleUser?action=list">
						<div class="parent-icon"> <i class=""></i>
						</div>
						<div class="menu-title">User Management</div>
					</a>
				</li>
			</ul>
			<!--end navigation-->
		</div>
		<!--end sidebar wrapper -->
		<!--start header -->
		<header>
			<div class="topbar d-flex align-items-center">
				<nav class="navbar navbar-expand">
					<div class="mobile-toggle-menu"><i class='bx bx-menu'></i>
					</div>

					<div class="top-menu ms-auto">

					</div>
					<div class="user-box dropdown">
						<a class="d-flex align-items-center nav-link dropdown-toggle dropdown-toggle-nocaret" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<img src="assets/images/avatars/avatar-3.png" class="user-img" alt="user avatar">
							<div class="user-info ps-3">
								<p class="user-name mb-0"> <%= session.getAttribute("userName") %> Amy Wong</p>
							</div>
						</a>
						<ul class="dropdown-menu dropdown-menu-end">
							<li><a class="dropdown-item" href="login.jsp"><i class='bx bx-log-out-circle'></i><span>Logout</span></a>
							</li>
						</ul>
					</div>
				</nav>
			</div>
		</header>
		<!--end header -->
		<!--start page wrapper -->
		<div class="page-wrapper">
			<div class="page-content">
				<!--breadcrumb-->
				<div class="page-breadcrumb d-none d-sm-flex align-items-center mb-3">
					<div class="breadcrumb-title pe-3">User Management</div>
					<div class="ps-3">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb mb-0 p-0">
								<li class="breadcrumb-item"><a href="javascript:;"><i class="bx bx-home-alt"></i></a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">User Management</li>
							</ol>
						</nav>
					</div>
				</div>
				<!--end breadcrumb-->
<!--				<h6 class="mb-0 text-uppercase">Venue Management</h6>-->
<!--				<hr/>-->
				<div class="card">
                                    <div class="card-body">
                                       <button type="button" class="btn btn-primary px-5" data-toggle='modal' data-target='#addUser'>Add User</button>
                                        <p></p>
                                       <table class="table">
                                                <thead>
                                                  <tr>
                                                    <th>#</th>
                                                    <th>First Name</th>
                                                    <th>Last Name</th>
                                                    <th>Phone</th>
                                                    <th>Role</th>
                                                    <th>Edit</th>
                                                    <th>Delete</th>
                                                  </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        ArrayList<UserBean> users = (ArrayList<UserBean>)request.getAttribute("users");
                                                        //System.out.println(users);
                                                        
                                                       String status = "";
                                                       String venue = "";

                                                        // loop through the customer array to display each customer record
                                                        for (int i = 0; i < users.size(); i++) {
                                                           UserBean c = users.get(i);
                                                           String role = (c.getRole() == 0) ? "Member" : (c.getRole() == 1) ? "Staff" : "Senior Management";
                                                           
                                                                                                                     
                                                           out.println("<tr>");

                                                           out.println("<td>" + c.getId() + "</td>");
                                                           out.println("<td>" + c.getFname() + "</td>");
                                                           out.println("<td>" + c.getLname() + "</td>");
                                                           out.println("<td>" + c.getPhone() + "</td>");
                                                           out.println("<td>" + role + "</td>");
                                                           out.println("<td><button type='button' class='btn btn-light' data-toggle='modal' data-target='#'><i class='bx bxs-edit-alt'></i></button></td>");
                                                           out.println("<td><button type='button' class='btn btn-light'><a href='handleUser?action=delete&id=" + c.getId() +"'><i class='bx bx-trash-alt'></i></button></td>");

                                                           out.println("</tr>");

                                                        }
                                                        out.println("</table>");
                                                    %>
                                                </tbody>
                                        </table>
                                    
                                
                               <!-- Modal -->
                                <div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                  <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                      <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Add User</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                          <span aria-hidden="true">&times;</span>
                                        </button>
                                      </div>
                                      <div class="modal-body">
                                          <form action="handleUser?action=add" method="post">
                                                
                                                First Name <input type="text" class="form-control" name="fname"><br>
                                                Last Name <input type="text" class="form-control" name="lname"><br>
                                                Phone <input type="text" class="form-control" name="phone"><br>
                                                Email <input type="email" class="form-control" name="email"><br>
                                                Password <input type="password" class="form-control" name="pwd"><br>
                                                Role <select name="role" class="form-select">
                                                        <option selected>Please choose a role</option>
                                                        <option value="0">Member</option>
                                                        <option value="1">Staff</option>
                                                        <option value="2">Senior Management</option>
                                                     </select><br>
                                              
                                          
                                      </div>
                                      <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <input type="submit" value="Submit" class="btn btn-primary">
                                            </form>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                                
			</div>
		</div>


							
                                        </div>
                                </div>
                                    
				</div>
				
			</div>
		</div>
		<!--end page wrapper -->
		<!--start overlay-->
		<div class="overlay toggle-icon"></div>
		<!--end overlay-->
		<!--Start Back To Top Button--> <a href="javaScript:;" class="back-to-top"><i class='bx bxs-up-arrow-alt'></i></a>
		<!--End Back To Top Button-->
	</div>
	<!--end wrapper-->
	
	<!-- Bootstrap JS -->
	<script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<!--plugins-->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/plugins/simplebar/js/simplebar.min.js"></script>
	<script src="assets/plugins/metismenu/js/metisMenu.min.js"></script>
	<script src="assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
	<script src="assets/plugins/datatable/js/jquery.dataTables.min.js"></script>
	<script src="assets/plugins/datatable/js/dataTables.bootstrap5.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		  } );
	</script>
	
	<!--app JS-->
	<script src="assets/js/app.js"></script>
</body>

</html>