/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.hopto.depositodivisa.servlets;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 *
 * @author luiz.souza
 */
public class ServletPaginaAnteriorUsuarios extends HttpServlet {

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
        HttpSession session;
        session = request.getSession();
        if (session.getAttribute("numeroPagina")==null||Integer.parseInt((String)session.getAttribute("numeroPagina"))<1){
            session.setAttribute("numeroPagina", "1");}
        else {
            session.setAttribute("numeroPagina",
                String.valueOf(
                        Integer.parseInt(
                        (String)session.getAttribute("numeroPagina"))
                                -1));
        }
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("ServletListarUsuariosPaginada");
        rd.forward(request, response);
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session;
        session = request.getSession();
        if (session.getAttribute("numeroPagina")==null||Integer.parseInt((String)session.getAttribute("numeroPagina"))<1){
            session.setAttribute("numeroPagina", "1");}
        else {
            session.setAttribute("numeroPagina",
                String.valueOf(
                        Integer.parseInt(
                        (String)session.getAttribute("numeroPagina"))
                                -1));
        }
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("ServletListarUsuariosPaginada");
        rd.forward(request, response);
    }

}
