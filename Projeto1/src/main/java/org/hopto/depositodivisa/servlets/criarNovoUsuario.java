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
import org.hopto.depositodivisa.dao.LoginDAO;

/**
 *
 * @author luiz.souza
 */
public class criarNovoUsuario extends HttpServlet {

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
        LoginDAO login = new LoginDAO();
        String nomeUsuario = request.getParameter("nomeUsuario");
        String nomeCompletoUsuario = request.getParameter("nomeCompletoUsuario");
        String acessoUsuario = request.getParameter("acessoUsuario");
        String gruposUsuario = request.getParameter("gruposUsuario");
        String ativo = request.getParameter("ativo");
        String dataCadastro = request.getParameter("dataCadastro");
        String senha = request.getParameter("senha");
       
        login.registrarNovoUsuario(nomeUsuario, nomeCompletoUsuario, acessoUsuario, gruposUsuario, ativo , dataCadastro, senha);
        
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("ServletListarUsuariosPaginada");
        rd.forward(request, response);

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
