/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.hopto.depositodivisa.servlets;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hopto.depositodivisa.dao.LoginDAO;
import org.hopto.depositodivisa.model.Login;

/**
 *
 * @author Luiz
 */
public class UsuariosController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Login> listaUsuarios = null;
        LoginDAO loginDAO = new LoginDAO();
        Integer limite = 4 ,numeroPagina = 1;
        HttpSession session = request.getSession();
        if ((String) session.getAttribute("limite")!=null){
        limite = Integer.valueOf((String) session.getAttribute("limite"));
        } else {
        session.setAttribute("limite",String.valueOf(limite));}
        if ((String) session.getAttribute("numeroPagina")!=null){
        numeroPagina = Integer.valueOf((String) session.getAttribute("numeroPagina"));
        } else {
        session.setAttribute("numeroPagina", String.valueOf(numeroPagina));}
        
        String processar = request.getParameter("processar");
        
        if (request.getParameter("processar")==null)
            processar="listar";
        
        if (processar.equalsIgnoreCase("listar")){
            listaUsuarios = loginDAO.getListaUsuariosPaginada(limite,numeroPagina);
            request.setAttribute("sessaoListaUsuarios", listaUsuarios);
            session.setAttribute("sessaoListaUsuarios", listaUsuarios);
            RequestDispatcher rd = request.getRequestDispatcher("listausuariosPaginada.jsp");
            rd.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
