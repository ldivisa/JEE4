
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
import org.hopto.depositodivisa.dao.BairroDAO;
import org.hopto.depositodivisa.funcoes.HashSenhasArgo2;
import org.hopto.depositodivisa.model.BairroModel;

/**
 *
 * @author Luiz
 */
public class BairrosController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<BairroModel> listaBairros = null;
        BairroDAO BairroDAO = new BairroDAO();
        Integer limite = 4, numeroPagina = 1;
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        Integer pagMax = 1;
        if ((String) session.getAttribute("limite") != null) {
            limite = Integer.valueOf((String) session.getAttribute("limite"));
        } else {
            session.setAttribute("limite", String.valueOf(limite));
        }
        String par1, ses1;
        ses1 = (String) session.getAttribute("bairroPesquisar");
        par1 = request.getParameter("bairroPesquisar");
        boolean compok = false;
        if (ses1 != null && par1 != null) {
            compok = true;
        }
        if (compok) {
            if (!(ses1.equalsIgnoreCase(par1))) {
                numeroPagina = 1;
                session.setAttribute("numeroPagina", "1");
            }
        }

        if ((String) session.getAttribute("numeroPagina") != null) {
            numeroPagina = Integer.valueOf((String) session.getAttribute("numeroPagina"));
        } else {
            session.setAttribute("numeroPagina", String.valueOf(numeroPagina));
        }
        String processar = request.getParameter("processar");

        if (request.getParameter("ordenacaoBairro") != null) {
            session.setAttribute("ordenacaoBairro", request.getParameter("ordenacaoBairro"));
        } else {
            session.setAttribute("ordenacaoBairro", "bairroNome");
        }
        //System.out.println("\ntipoPesquisa (USUARIOSCONTROLLER): " + request.getParameter("tipoPesquisa"));
        if ((request.getParameter("tipoPesquisa") != null)) {
            session.setAttribute("tipoPesquisa", request.getParameter("tipoPesquisa"));
        } else {
            session.setAttribute("tipoPesquisa", "bairroNome");
        }

        if (request.getParameter("bairroPesquisar") != null) {
            //          session.setAttribute("bairroPesquisar", "");
            session.setAttribute("bairroPesquisar", request.getParameter("bairroPesquisar"));
        } else {
            session.setAttribute("bairroPesquisar", "");
        }
        if (session.getAttribute("pagMax") != null) {
            pagMax = Integer.valueOf((String) session.getAttribute("pagMax"));
        }

        if (request.getParameter("processar") == null) {
            processar = "listar";
        }

        if (processar.equalsIgnoreCase("listar")) {
            listaBairros = BairroDAO.getListaBairrosPaginada(limite, numeroPagina, (String) session.getAttribute("ordenacaoBairro"), (String) session.getAttribute("bairroPesquisar"), (String) session.getAttribute("tipoPesquisa"));
            request.setAttribute("sessaoListaBairros", listaBairros);
            session.setAttribute("sessaoListaBairros", listaBairros);
            rd = request.getRequestDispatcher("listaBairros.jsp");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("novo")) {
            BairroDAO.registrarNovoBairro(request.getParameter("bairroNome"),  request.getParameter("ativo"));
            rd = request.getRequestDispatcher("BairrosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("alterar")) {
            BairroDAO.alterarBairro(request.getParameter("bairroNome"), request.getParameter("bairroNome"), request.getParameter("acessoBairro"), request.getParameter("gruposBairro"), request.getParameter("ativo"));
            rd = request.getRequestDispatcher("BairrosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("gravar")) {
            BairroDAO.alterarBairro(request.getParameter("bairroNome"), request.getParameter("nomeCompletoBairro"), request.getParameter("acessoBairro"), request.getParameter("gruposBairro"), request.getParameter("ativo"));
            rd = request.getRequestDispatcher("listabairrosPaginada.jsp");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("desativar")) {
            String bairroAlterarEstado = (String) request.getParameter("bairroAlterarEstado");
            BairroDAO.desativarBairro(bairroAlterarEstado);
            rd = request.getRequestDispatcher("BairrosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("ativar")) {
            String bairroAlterarEstado = (String) request.getParameter("bairroAlterarEstado");
            BairroDAO.ativarBairro(bairroAlterarEstado);
            rd = request.getRequestDispatcher("BairrosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("proximaPagina")) {
            System.out.println("\nproximaPagina " + numeroPagina + " pagmax: " + pagMax);
            if (numeroPagina < pagMax) {
                numeroPagina++;
                session.setAttribute("numeroPagina", String.valueOf(numeroPagina));
                System.out.println("\nproximaPagina " + numeroPagina + " pagmax: " + pagMax);
            }

            rd = request.getRequestDispatcher("BairrosController?processar=listar&tipoPesquisa="
                    + (String) session.getAttribute("tipoPesquisa")
                    + "&bairroPesquisar=" + (String) session.getAttribute("bairroPesquisar")
                    + "&numeroPagina=" + (String) session.getAttribute("numeroPagina"));
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("paginaAnterior")) {
            System.out.println("\npagina Anterior" + numeroPagina + " " + pagMax);
            if (numeroPagina > 1) {
                numeroPagina--;
                session.setAttribute("numeroPagina", String.valueOf(numeroPagina));
            }

            rd = request.getRequestDispatcher("BairrosController?processar=listar"
                    + "&tipoPesquisa="
                    + (String) session.getAttribute("tipoPesquisa")
                    + "&bairroPesquisar=" + (String) session.getAttribute("bairroPesquisar")
                    + "&numeroPagina=" + (String) session.getAttribute("numeroPagina"));
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("trocarSenha")) {
            HashSenhasArgo2 hash = new HashSenhasArgo2();
            String bairroAtual = (String) session.getAttribute("bairroNome");
            String bairroAlterarEstado = (String) session.getAttribute("bairroAlterarEstado");
            String senhaAtual = request.getParameter("senhaAtual");
            String senhaNova1 = request.getParameter("senhaNova1");
            String senhaNova2 = request.getParameter("senhaNova2");
            String senhaBairroBanco = (String) session.getAttribute("senhaBairroBanco");
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
            if (!hash.checaHashSenha(senhaBairroBanco, senhaAtual)) {
                session.setAttribute("mensagem", "A senha atual não confere");
                rd = request.getRequestDispatcher("trocarSenha.jsp");
                rd.forward(request, response);
                return;
            }

            BairroDAO.alterarSenha(bairroAtual, senhaAtualHash);
            rd = request.getRequestDispatcher("BairrosController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("encerrarSessao")) {
            session.removeAttribute("bairroAtual");
            session.removeAttribute("bairroNomeCompleto");
            request.setAttribute("status", null);
            request.removeAttribute("bairroAtual");
            rd = request.getRequestDispatcher("/bairro.jsp");
            rd.forward(request, response);
                   
        } else if (processar.equalsIgnoreCase("checarPermissao")) {
            String permissaoNecesssaria = (String) request.getParameter("permissaoNecessaria");
            String direitos = (String) session.getAttribute("acessoBairro");
            BairroDAO.getPermissao(direitos, permissaoNecesssaria);
            if (!direitos.contains(permissaoNecesssaria)) {
                rd = request.getRequestDispatcher("/bairro.jsp");
                rd.forward(request, response);
            }
        } else if (processar.equalsIgnoreCase("pesquisar")) {
            //out.print("\nPesquisar - "+request.getParameter("bairroPesquisar"));
            if (request.getParameter("bairroPesquisar") != null) {
                session.setAttribute("bairroPesquisar", request.getParameter("bairroPesquisar"));
            }
            if (request.getParameter("tipoPesquisa") != null) {
                session.setAttribute("tipoPesquisa", request.getParameter("tipoPesquisa"));
            }
            rd = request.getRequestDispatcher("BairrosController?processar=listar"
                    + "&tipoPesquisa="
                    + (String) session.getAttribute("tipoPesquisa")
                    + "&bairroPesquisar=" + (String) session.getAttribute("bairroPesquisar")
                    + "&numeroPagina=" + (String) session.getAttribute("numeroPagina"));
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
            Logger.getLogger(BairrosController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BairrosController.class.getName()).log(Level.SEVERE, null, ex);
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
