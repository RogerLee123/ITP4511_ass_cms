package ict.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ict.bean.UserBean;
import ict.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roger
 */
@WebServlet(urlPatterns = {"/handleUser"})
public class HandleUserController extends HttpServlet {

    private UserDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new UserDB(dbUrl, dbUser, dbPassword);
    }  
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String action = request.getParameter("action");
        
        if ("list".equalsIgnoreCase(action)) {
           // call the query db to get retrieve for all booking 
            ArrayList<UserBean> users = db.queryUser();
            // set the result into the attribute	 
            request.setAttribute("users", users);
            // redirect the result to the listBookings.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/listUsers.jsp");
            rd.forward(request, response);
            
        } else if ("delete".equalsIgnoreCase(action)) {
            int id = Integer.parseInt(request.getParameter("id"));

            db.delUser(id);
            response.sendRedirect("handleVenue?action=list");

        } else if ("add".equalsIgnoreCase(action)) { 
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            int phone = Integer.parseInt(request.getParameter("phone"));
            String pwd = request.getParameter("pwd");
            int role = Integer.parseInt(request.getParameter("role"));

            db.addUser(fname, lname, email, phone, pwd, role);
            response.sendRedirect("handleUser?action=list");

        } else if ("edit".equalsIgnoreCase(action)){
            int id = Integer.parseInt(request.getParameter("id"));
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            int phone = Integer.parseInt(request.getParameter("phone"));
            String pwd = request.getParameter("pwd");
            int role = Integer.parseInt(request.getParameter("role"));

            db.editUser(id, fname, lname, email, phone, pwd, role);
            response.sendRedirect("handleUser?action=list");

        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
