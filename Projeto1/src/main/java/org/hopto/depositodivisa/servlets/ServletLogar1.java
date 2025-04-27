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
import org.hopto.depositodivisa.model.Login;

/**
 *
 * @author luiz.souza
 */
public class ServletLogar1 extends HttpServlet {

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
        String nomeUsuario = request.getParameter("usuario");
        String senhaUsuario = request.getParameter("senha");
        String status = null;
        String usuarioAtivo = null;
        Login login = new Login();
        login.setNomeUsuario(nomeUsuario);
        login.setSenhaUsuario(senhaUsuario);
        RequestDispatcher rd = null;
        
        
        if (login.verificaUsuario()){
            HttpSession sessao = request.getSession();
            status="Usuário válido";
            usuarioAtivo = login.getNomeUsuario();
            request.setAttribute("status", "Usuário Válido");
            sessao.setAttribute("usuarioAtual", nomeUsuario);
            rd = request.getRequestDispatcher("/index2.jsp");
            rd.forward(request,response);
            //response.sendRedirect("index2.jsp");
        } else {
            request.setAttribute("status", "Usuário e/ou senha inválidos");
            rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request,response);
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletLogar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(status);
            out.println("<h1>Usuario "+nomeUsuario+" Logou " + request.getContextPath() + "</h1>");
            out.println("<h1> Senha"+senhaUsuario);
            out.println("<h1>"+status+"</h1>");
            out.println("</body>");
            out.println("</html>");
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
