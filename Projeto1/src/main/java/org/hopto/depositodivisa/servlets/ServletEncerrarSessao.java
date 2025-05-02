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
public class ServletEncerrarSessao extends HttpServlet {

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
        HttpSession sessao;
        sessao = request.getSession();
        RequestDispatcher rd;
        request.setAttribute("status", null);
        request.setAttribute("usuarioAtual", null);     
        sessao.setAttribute("usuarioAtual", null);
        sessao.setAttribute("nomeUsuarioCompleto", null);
        rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = null;
        HttpSession sessao = request.getSession();
        sessao.removeAttribute("usuarioAtual");
        sessao.removeAttribute("nomeUsuarioCompleto");
        request.setAttribute("status", null);
        request.removeAttribute("usuarioAtual");
        
        rd = request.getRequestDispatcher("/login.jsp");
        rd.forward(request, response);
    }

}
