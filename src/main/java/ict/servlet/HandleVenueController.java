/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.VenueBean;
import ict.db.VenueDB;
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
@WebServlet(name = "HandleVenueController", urlPatterns = {"/HandleVenue"})
public class HandleVenueController extends HttpServlet {

    private VenueDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        db = new VenueDB(dbUrl, dbUser, dbPassword);
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");

            if ("list".equalsIgnoreCase(action)) {
                // call the query db to get retrieve for all venues 
                    ArrayList<VenueBean> venues = db.queryVenue();
                // set the result into the attribute	 
                    request.setAttribute("venues", venues);
                // redirect the result to the listCustomers.jssp
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/listVenues.jsp");
                    rd.forward(request, response);

            } else if ("delete".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                
                db.delVenue(id);
                response.sendRedirect("handleVenue?action=list");

            } else if ("add".equalsIgnoreCase(action)) { 
                int staffId = Integer.parseInt(request.getParameter("staffId"));
                String name = request.getParameter("name");
                String address = request.getParameter("address");
                String desc = request.getParameter("desc");
                String img = request.getParameter("img");
                String type = request.getParameter("type");
                int capacity = Integer.parseInt(request.getParameter("capacity"));
                double fee = Double.parseDouble(request.getParameter("fee"));
                String lastModifiedFee = request.getParameter("lastModifiedFee");
                
                db.addVenue(staffId, name, address, desc, img, type, capacity, fee, lastModifiedFee);
                response.sendRedirect("handleVenue?action=list");

            } else if ("edit".equalsIgnoreCase(action)){
                int id = Integer.parseInt(request.getParameter("id"));
                int staffId = Integer.parseInt(request.getParameter("staffId"));
                String name = request.getParameter("name");
                String address = request.getParameter("address");
                String desc = request.getParameter("desc");
                String img = request.getParameter("img");
                String type = request.getParameter("type");
                int capacity = Integer.parseInt(request.getParameter("capacity"));
                double fee = Double.parseDouble(request.getParameter("fee"));
                String lastModifiedFee = request.getParameter("lastModifiedFee");
                
                db.editVenue(id, staffId, name, address, desc, img, type, capacity, fee, lastModifiedFee);
                response.sendRedirect("handleVenue?action=list");

            }
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
