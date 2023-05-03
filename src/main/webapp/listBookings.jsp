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
                                <li>
					<a href="HandleBooking?action=list">
						<div class="parent-icon"><i class="bx bxs-book-alt"></i>
						</div>
						<div class="menu-title">Booking Management</div>
					</a>
				</li>
				<li>
					<a href="HandleVenue?action=list">
						<div class="parent-icon"> <i class="bx bxs-building-house"></i>
						</div>
						<div class="menu-title">Venue Management</div>
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
							<img src="assets/images/avatars/avatar-2.png" class="user-img" alt="user avatar">
							<div class="user-info ps-3">
								<p class="user-name mb-0"> <%= session.getAttribute("userName") %> Pauline Seitz</p>
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
					<div class="breadcrumb-title pe-3">Booking Management</div>
					<div class="ps-3">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb mb-0 p-0">
								<li class="breadcrumb-item"><a href="javascript:;"><i class="bx bx-home-alt"></i></a>
								</li>
								<li class="breadcrumb-item active" aria-current="page">Booking Management</li>
							</ol>
						</nav>
					</div>
				</div>
				<!--end breadcrumb-->
<!--				<h6 class="mb-0 text-uppercase">Venue Management</h6>-->
<!--				<hr/>-->
				<div class="card">
                                    <div class="card-body">
                                       <table class="table">
                                                <thead>
                                                  <tr>
                                                    <th>#</th>
                                                    <th>Member ID</th>
                                                    <th>Venue</th>
                                                    <th>Booking Status</th>
                                                    <th>Create Time</th>
                                                    <th>Fee</th>
                                                    <th>Approve Booking</th>
                                                    <th>Check In</th>
                                                    <th>Check Out</th>
                                                  </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        ArrayList<BookingBean> bookings = (ArrayList<BookingBean>)request.getAttribute("bookings");
                                                        System.out.println(bookings);
                                                        
                                                       String status = "";
                                                       String venue = "";

                                                        // loop through the customer array to display each customer record
                                                        for (int i = 0; i < bookings.size(); i++) {
                                                           BookingBean c = bookings.get(i);
                                                           status = (c.getStatus() == 0) ? "Pending" : (c.getStatus() == 1) ? "Approve" : "Declined";
                                                           venue = (c.getVenueId() == 1) ? "Tuen Mun" : (c.getVenueId() == 2) ? "Sha Tin" : (c.getVenueId() == 3) ? "Tsing Yi": (c.getVenueId() == 4) ? "Lee Wai Lee" : "Chai Wan";
                                                           
                                                           out.println("<tr>");

                                                           out.println("<td>" + c.getId() + "</td>");
                                                           out.println("<td>" + c.getMemberId() + "</td>");
                                                           out.println("<td>" + venue + "</td>");
                                                           out.println("<td>" + status + "</td>");
                                                           out.println("<td>" + c.getCreateTime() + "</td>");
                                                           out.println("<td>" + c.getFee() + "</td>");
                                                           out.println("<td><button type='button' class='btn btn-light'><i class='bx bxs-badge-check'></i></button></td>");
                                                           out.println("<td><button type='button' class='btn btn-light'><i class='bx bx-log-in'></i></button></td>");
                                                           out.println("<td><button type='button' class='btn btn-light' data-toggle='modal' data-target='#checkout'><i class='bx bx-log-out'></i></button></td>");
                                                           //out.println("<td>" + c.getAge() + "</td>");
                                                           //out.println("<td><a href=\"handleCustomer?action=delete&id=" + c.getCustId() + "\">delete</a></td>");
                                                           //out.println("<td><a href=\"handleCustomer?action=getEditCustomer&id=" + c.getCustId() + "\">edit</a></td>");

                                                           out.println("</tr>");

                                                        }
                                                        out.println("</table>");
                                                    %>
                                                </tbody>
                                        </table>
                                    

                                
                                <!-- Modal for checkout-->
<!--                                <div class="modal fade" id="checkout" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                  <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                      <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Checkout</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                          <span aria-hidden="true">&times;</span>
                                        </button>
                                      </div>
                                      <div class="modal-body">
                                          <form action="HandleBooking?action=checkout" method="post">
                                                
                                              Staff Comment <textarea class="form-control" id="w3review" name="w3review" rows="4" cols="50">
                                          
                                              
                                          
                                      </div>
                                      <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <input type="submit" value="Submit" class="btn btn-primary">
                                            </form>
                                      </div>
                                    </div>
                                  </div>
                                </div>-->
                                                
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