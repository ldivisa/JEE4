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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hopto.depositodivisa.dao.LoginDAO;
import org.hopto.depositodivisa.funcoes.HashSenhasArgo2;
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
        Integer limite = 4, numeroPagina = 1;
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        Integer pagMax = 1;
        if ((String) session.getAttribute("limite") != null) {
            limite = Integer.valueOf((String) session.getAttribute("limite"));
        } else {
            session.setAttribute("limite", String.valueOf(limite));
        }
        if (session.getAttribute("usuarioPesquisar")!=request.getParameter("usuarioPesquisar")){
        numeroPagina=1;
        session.setAttribute("numeroPagina", "1");
        }
        if ((String) session.getAttribute("numeroPagina") != null) {
            numeroPagina = Integer.valueOf((String) session.getAttribute("numeroPagina"));
        } else {
            session.setAttribute("numeroPagina", String.valueOf(numeroPagina));
        }
        String processar = request.getParameter("processar");

        if (request.getParameter("ordenacaoUsuario") != null) {
            session.setAttribute("ordenacaoUsuario", request.getParameter("ordenacaoUsuario"));
            } else {
            session.setAttribute("ordenacaoUsuario","nomeCompletoUsuario");
        }
        System.out.println("\ntipoPesquisa (USUARIOSCONTROLLER): "+request.getParameter("tipoPesquisa"));
        if ((request.getParameter("tipoPesquisa") != null)) {
            session.setAttribute("tipoPesquisa", request.getParameter("tipoPesquisa"));
        } else
            session.setAttribute("tipoPesquisa","nomeCompletoUsuario");
        
        if (request.getParameter("usuarioPesquisar") != null) {
  //          session.setAttribute("usuarioPesquisar", "");
              session.setAttribute("usuarioPesquisar", request.getParameter("usuarioPesquisar"));
        } else {
//            session.setAttribute("tipoPesquisa", "nomeCompletoUsuario");
              session.setAttribute("usuarioPesquisar", "");
    }
        if (session.getAttribute("pagMax") != null) {
            pagMax = Integer.valueOf((String) session.getAttribute("pagMax"));
        }

        if (request.getParameter("processar") == null) {
            processar = "listar";
        }

        if (processar.equalsIgnoreCase("listar")) {
            listaUsuarios = loginDAO.getListaUsuariosPaginada(limite, numeroPagina, (String) session.getAttribute("ordenacaoUsuario"), (String) session.getAttribute("usuarioPesquisar"),(String) session.getAttribute("tipoPesquisa"));
            request.setAttribute("sessaoListaUsuarios", listaUsuarios);
            session.setAttribute("sessaoListaUsuarios", listaUsuarios);
            rd = request.getRequestDispatcher("listausuariosPaginada.jsp");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("novo")) {
            loginDAO.registrarNovoUsuario(request.getParameter("nomeUsuario"), request.getParameter("nomeCompletoUsuario"), request.getParameter("acessoUsuario"), request.getParameter("gruposUsuario"), request.getParameter("ativo"), request.getParameter("dataCadastro"), request.getParameter("senha"));
            rd = request.getRequestDispatcher("UsuariosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("alterar")) {
            loginDAO.alterarUsuario(request.getParameter("nomeUsuario"), request.getParameter("nomeCompletoUsuario"), request.getParameter("acessoUsuario"), request.getParameter("gruposUsuario"), request.getParameter("ativo"));
            rd = request.getRequestDispatcher("UsuariosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("gravar")) {
            loginDAO.alterarUsuario(request.getParameter("nomeUsuario"), request.getParameter("nomeCompletoUsuario"), request.getParameter("acessoUsuario"), request.getParameter("gruposUsuario"), request.getParameter("ativo"));
            rd = request.getRequestDispatcher("listausuariosPaginada.jsp");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("desativar")) {
            String usuarioAlterarEstado = (String) request.getParameter("usuarioAlterarEstado");
            loginDAO.desativarUsuario(usuarioAlterarEstado);
            rd = request.getRequestDispatcher("UsuariosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("ativar")) {
            String usuarioAlterarEstado = (String) request.getParameter("usuarioAlterarEstado");
            loginDAO.ativarUsuario(usuarioAlterarEstado);
            rd = request.getRequestDispatcher("UsuariosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("proximaPagina")) {
            //System.out.println("\nproximaPagina "+numeroPagina+" "+pagMax);
            if (numeroPagina < pagMax) {
                numeroPagina++;
            }
            session.setAttribute("numeroPagina", String.valueOf(numeroPagina));
            rd = request.getRequestDispatcher("UsuariosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("paginaAnterior")) {
            System.out.println("\nproximaPagina " + numeroPagina + " " + pagMax);
            if (numeroPagina > 1) {
                numeroPagina--;
            }
            session.setAttribute("numeroPagina", String.valueOf(numeroPagina));
            rd = request.getRequestDispatcher("UsuariosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("trocarSenha")) {
            HashSenhasArgo2 hash = new HashSenhasArgo2();
            String usuarioAtual = (String) session.getAttribute("nomeUsuario");
            String usuarioAlterarEstado = (String) session.getAttribute("usuarioAlterarEstado");
            String senhaAtual = request.getParameter("senhaAtual");
            String senhaNova1 = request.getParameter("senhaNova1");
            String senhaNova2 = request.getParameter("senhaNova2");
            String senhaUsuarioBanco = (String) session.getAttribute("senhaUsuarioBanco");
            String senhaAtualHash = hash.criaHashSenha(senhaNova1);
            if (senhaAtual.equals(senhaNova1) || senhaAtual.equals(senhaNova2)) {
                session.setAttribute("mensagem", "A senha nova precisar ser diferente da atual");
                rd = request.getRequestDispatcher("trocarSenha.jsp");
                rd.forward(request, response);
                return;
            }
            if (!senhaNova1.equals(senhaNova2)) {
                session.setAttribute("mensagem", "Os dois campos de registro da nova senha precisam ser iguais");
                rd = request.getRequestDispatcher("trocarSenha.jsp");
                rd.forward(request, response);
                return;
            }
            if (!hash.checaHashSenha(senhaUsuarioBanco, senhaAtual)) {
                session.setAttribute("mensagem", "A senha atual não confere");
                rd = request.getRequestDispatcher("trocarSenha.jsp");
                rd.forward(request, response);
                return;
            }

            loginDAO.alterarSenha(usuarioAtual, senhaAtualHash);
            rd = request.getRequestDispatcher("UsuariosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("encerrarSessao")) {
            session.removeAttribute("usuarioAtual");
            session.removeAttribute("nomeUsuarioCompleto");
            request.setAttribute("status", null);
            request.removeAttribute("usuarioAtual");
            rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("logar")) {
            response.setContentType("text/html;charset=UTF-8");
            Login usuarioChecado = new Login();
            usuarioChecado.setNomeUsuario(request.getParameter("usuario"));
            usuarioChecado.setSenhaUsuario(request.getParameter("senha"));

            if (loginDAO.getLogin(usuarioChecado) == null) {
                request.setAttribute("status", "Usuário e/ou senha inválidos");
                rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            } else {
                Login usuarioCarregado = loginDAO.getLogin(usuarioChecado);
                if (usuarioCarregado.getAtivo() == 0) {
                    request.setAttribute("status", "Usuário desativado");
                    rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                } else {
                    session.setAttribute("usuarioAtual", usuarioCarregado.getNomeUsuario());
                    session.setAttribute("nomeUsuario", usuarioCarregado.getNomeUsuario());
                    session.setAttribute("nomeCompletoUsuario", usuarioCarregado.getNomeCompletoUsuario());
                    session.setAttribute("senhaUsuarioBanco", usuarioCarregado.getSenhaUsuario());
                    session.setAttribute("acessoUsuario", usuarioCarregado.getAcessoUsuario());
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }
            }
        } else if (processar.equalsIgnoreCase("checarPermissao")) {
            String permissaoNecesssaria = (String) request.getParameter("permissaoNecessaria");
            String direitos = (String) session.getAttribute("acessoUsuario");
            loginDAO.getPermissao(direitos, permissaoNecesssaria);
            if (!direitos.contains(permissaoNecesssaria)) {
                rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        } else if (processar.equalsIgnoreCase("pesquisar")) {
            //out.print("\nPesquisar - "+request.getParameter("usuarioPesquisar"));
            if (request.getParameter("usuarioPesquisar")!=null)
            session.setAttribute("usuarioPesquisar", request.getParameter("usuarioPesquisar"));
            if (request.getParameter("tipoPesquisa")!=null)
            session.setAttribute("tipoPesquisa", request.getParameter("tipoPesquisa"));
            rd = request.getRequestDispatcher("UsuariosController?processar=listar");
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
