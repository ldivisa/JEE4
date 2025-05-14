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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hopto.depositodivisa.dao.LoginDAO;
import org.hopto.depositodivisa.model.Login;
/**
 *
 * @author luiz.souza
 */
public class ServletLogar3 extends HttpServlet {

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
        HttpSession session = request.getSession();
        LoginDAO login = new LoginDAO();
        Login usuarioChecado = new Login();
        RequestDispatcher rd;
        usuarioChecado.setNomeUsuario(request.getParameter("usuario"));
        usuarioChecado.setSenhaUsuario(request.getParameter("senha"));
        try {
            if (login.getLogin(usuarioChecado)==null) {
                request.setAttribute("status", "Usuário e/ou senha inválidos");
                rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            } else {
                Login usuarioCarregado = login.getLogin(usuarioChecado);
                if (usuarioCarregado.getAtivo() == 0) {
                    request.setAttribute("status", "Usuário desativado");
                    rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                } else{
                //request.setAttribute("status", "Usuário Válido");
                //request.setAttribute("usuarioAtual", usuarioCarregado.getNomeUsuario());
                //request.setAttribute("nomeCompletoUsuario", usuarioCarregado.getNomeCompletoUsuario());
                session.setAttribute("usuarioAtual", usuarioCarregado.getNomeUsuario());
                session.setAttribute("nomeUsuario", usuarioCarregado.getNomeUsuario());
                session.setAttribute("nomeCompletoUsuario", usuarioCarregado.getNomeCompletoUsuario());
                session.setAttribute("senhaUsuarioBanco", usuarioCarregado.getSenhaUsuario());
                session.setAttribute("acessoUsuario", usuarioCarregado.getAcessoUsuario());
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServletLogar3.class.getName()).log(Level.SEVERE, null, ex);
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
