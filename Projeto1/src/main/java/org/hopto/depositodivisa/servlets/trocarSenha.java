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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hopto.depositodivisa.dao.LoginDAO;
import org.hopto.depositodivisa.funcoes.HashSenhasArgo2;


/**
 *
 * @author luiz.souza
 */
public class trocarSenha extends HttpServlet {

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
        
        
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
            response.setContentType("text/html;charset=UTF-8");
            HttpSession sessao;
            LoginDAO login = new LoginDAO();
            sessao = request.getSession();
            HashSenhasArgo2 hash = new HashSenhasArgo2();
            String usuarioAtual = (String) sessao.getAttribute("nomeUsuario");
            RequestDispatcher rd;
            String usuarioAlterarEstado=(String) sessao.getAttribute("usuarioAlterarEstado");
            String senhaAtual=request.getParameter("senhaAtual");
            String senhaNova1=request.getParameter("senhaNova1");
            String senhaNova2=request.getParameter("senhaNova2");
            String senhaUsuarioBanco = (String) sessao.getAttribute("senhaUsuarioBanco");
            String senhaAtualHash = hash.criaHashSenha(senhaNova1);
            if  (senhaAtual.equals(senhaNova1)||senhaAtual.equals(senhaNova2)){
                sessao.setAttribute("mensagem", "A senha nova precisar ser diferente da atual");
                rd = request.getRequestDispatcher("trocarSenha.jsp");
                rd.forward(request, response);
                return;
            }
            
            if (!senhaNova1.equals(senhaNova2)){
                sessao.setAttribute("mensagem", "Os dois campos de registro da nova senha precisam ser iguais");
                rd = request.getRequestDispatcher("trocarSenha.jsp");
                rd.forward(request, response);
                return;
            }
            
            if(!hash.checaHashSenha(senhaUsuarioBanco,senhaAtual)){
                //System.out.println("senha atual digitada "+senhaAtual);
                //System.out.println("senha banco :        "+senhaUsuarioBanco);
                sessao.setAttribute("mensagem", "A senha atual não confere");
                rd = request.getRequestDispatcher("trocarSenha.jsp");
                rd.forward(request, response);
                return;
            }
            
            login.alterarSenha(usuarioAtual, senhaAtualHash);
                rd = request.getRequestDispatcher("ServletListarUsuarios");
                rd.forward(request, response);
        
                
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
