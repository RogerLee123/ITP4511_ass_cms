<%-- 
    Document   : test
    Created on : 2023?5?2?, ??1:21:53
    Author     : roger
--%>

<%@page import="java.sql.*"%>
<%
    // Retrieve filter criteria from HTTP request
    String filterCriteria = request.getParameter("filterCriteria");

    // Connect to database
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_assignment", "root", "");

    // Build SQL query with WHERE clause to filter records
    String query = "SELECT * FROM booking WHERE venueid = ?";
    PreparedStatement pstmt = con.prepareStatement(query);
    pstmt.setString(1, filterCriteria);

    // Execute query and retrieve results
    ResultSet rs = pstmt.executeQuery();

    // Generate HTML code to display filtered results
    StringBuilder html = new StringBuilder();
    html.append("<table>");
    html.append("<tr><th>ID</th><th>Member ID</th><th>Venue ID</th><th>Status</th><th>Create Time</th><th>Fee</th><th>Date</th><th>Start From</th><th>Total Hour</th><th>Checkin Time</th><th>Checkout Time</th></tr>");
    while (rs.next()) {
        html.append("<tr>");
        html.append("<td>").append(rs.getInt("id")).append("</td>");
        html.append("<td>").append(rs.getInt("memberid")).append("</td>");
        html.append("<td>").append(rs.getInt("venueid")).append("</td>");
        html.append("<td>").append(rs.getInt("status")).append("</td>");
        html.append("<td>").append(rs.getString("createtime")).append("</td>");
        html.append("<td>").append(rs.getInt("fee")).append("</td>");
        html.append("<td>").append(rs.getInt("startdate")).append("</td>");
        html.append("<td>").append(rs.getInt("starthour")).append("</td>");
        html.append("<td>").append(rs.getInt("totalhour")).append("</td>");
        html.append("<td>").append(rs.getInt("checkintime")).append("</td>");
        html.append("<td>").append(rs.getInt("checkouttime")).append("</td>");
        html.append("</tr>");
    }
    html.append("</table>");

    // Close database connection
    rs.close();
    pstmt.close();
    con.close();

    // Write HTML code to response output stream
    out.println(html.toString());
%>
