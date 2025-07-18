
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
import org.hopto.depositodivisa.dao.CidadeDAO;
import org.hopto.depositodivisa.model.CidadeModel;

/**
 *
 * @author Luiz
 */
public class CidadesController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<CidadeModel> listaCidades = null;
        CidadeDAO CidadeDAO = new CidadeDAO();
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
        ses1 = (String) session.getAttribute("cidadePesquisar");
        par1 = request.getParameter("cidadePesquisar");
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

        /*if (request.getParameter("ordenacaoBairro") != null) {
            session.setAttribute("ordenacaoBairro", request.getParameter("ordenacaoBairro"));
        } else {
            session.setAttribute("ordenacaoBairro", "bairroNome");
        }*/
        //System.out.println("\ntipoPesquisa (USUARIOSCONTROLLER): " + request.getParameter("tipoPesquisa"));
        /*if ((request.getParameter("tipoPesquisa") != null)) {
            session.setAttribute("tipoPesquisa", request.getParameter("tipoPesquisa"));
        } else {
            session.setAttribute("tipoPesquisa", "bairroNome");
        }

        if (request.getParameter("bairroPesquisar") != null) {
            //          session.setAttribute("bairroPesquisar", "");
            session.setAttribute("bairroPesquisar", request.getParameter("bairroPesquisar"));
        } else {
            session.setAttribute("bairroPesquisar", "");
        }*/
        if (session.getAttribute("pagMax") != null) {
            pagMax = Integer.valueOf((String) session.getAttribute("pagMax"));
        }

        if (request.getParameter("processar") == null) {
            processar = "listar";
        }

        if (processar.equalsIgnoreCase("listar")) {
            listaCidades =  CidadeDAO.getListaCidades();
                    //BairroDAO.getListaBairrosPaginada(limite, numeroPagina, (String) session.getAttribute("ordenacaoBairro"), (String) session.getAttribute("bairroPesquisar"), (String) session.getAttribute("tipoPesquisa"));
            request.setAttribute("sessaoListaCidades", listaCidades);
            session.setAttribute("sessaoListaCidades", listaCidades);
            rd = request.getRequestDispatcher("listaCidades.jsp");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("novo")) {
            CidadeDAO.registrarNovaCidade(request.getParameter("cidadeNome"),  request.getParameter("ativo"));
            //BairroDAO.registrarNovoBairro(request.getParameter("bairroNome"),  request.getParameter("ativo"));
            rd = request.getRequestDispatcher("CidadesController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("alterar")) {
            CidadeDAO.alterarCidade(request.getParameter("nomeCidade"),request.getParameter("ativo"),(String)session.getAttribute("cidadeOriginal"));
            //BairroDAO.alterarBairro(request.getParameter("bairroNome"),request.getParameter("ativo"),(String)session.getAttribute("bairroOriginal"));
            rd = request.getRequestDispatcher("CidadesController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("gravar")) {
            CidadeDAO.alterarCidade(request.getParameter("nomeCidade"), request.getParameter("ativo"),(String) session.getAttribute("cidadeOriginal"));
            //BairroDAO.alterarBairro(request.getParameter("bairroNome"), request.getParameter("ativo"),(String) session.getAttribute("bairroOriginal"));
            rd = request.getRequestDispatcher("listaCidadesPaginada.jsp");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("desativar")) {
            String cidadeAlterarEstado = (String) request.getParameter("cidadeAlterarEstado");
            CidadeDAO.desativarCidades(cidadeAlterarEstado);
            //BairroDAO.desativarBairro(bairroAlterarEstado);
            rd = request.getRequestDispatcher("CidadesController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("ativar")) {
            String cidadeAlterarEstado = (String) request.getParameter("cidadeAlterarEstado");
            CidadeDAO.ativarCidade(cidadeAlterarEstado);            
            //BairroDAO.ativarBairro(bairroAlterarEstado);
            rd = request.getRequestDispatcher("CidadesController?processar=listar");
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("proximaPagina")) {
            System.out.println("\nproximaPagina " + numeroPagina + " pagmax: " + pagMax);
            if (numeroPagina < pagMax) {
                numeroPagina++;
                session.setAttribute("numeroPagina", String.valueOf(numeroPagina));
                System.out.println("\nproximaPagina " + numeroPagina + " pagmax: " + pagMax);
            }

            rd = request.getRequestDispatcher("CidadesController?processar=listar&tipoPesquisa="
                    + (String) session.getAttribute("tipoPesquisa")
                    + "&cidadePesquisar=" + (String) session.getAttribute("cidadePesquisar")
                    + "&numeroPagina=" + (String) session.getAttribute("numeroPagina"));
            rd.forward(request, response);
        } else if (processar.equalsIgnoreCase("paginaAnterior")) {
            System.out.println("\npagina Anterior" + numeroPagina + " " + pagMax);
            if (numeroPagina > 1) {
                numeroPagina--;
                session.setAttribute("numeroPagina", String.valueOf(numeroPagina));
            }

            rd = request.getRequestDispatcher("CidadesController?processar=listar"
                    + "&tipoPesquisa="
                    + (String) session.getAttribute("tipoPesquisa")
                    + "&cidadePesquisar=" + (String) session.getAttribute("cidadePesquisar")
                    + "&numeroPagina=" + (String) session.getAttribute("numeroPagina"));
            rd.forward(request, response);
             
        } else if (processar.equalsIgnoreCase("encerrarSessao")) {
            session.removeAttribute("cidadeAtual");
            request.removeAttribute("cidadeAtual");
            rd = request.getRequestDispatcher("/cidade.jsp");
            rd.forward(request, response);
                   
        } else if (processar.equalsIgnoreCase("checarPermissao")) {
            String permissaoNecesssaria = (String) request.getParameter("permissaoNecessaria");
            String direitos = (String) session.getAttribute("acessoCidades");
            CidadeDAO.getPermissao(direitos, permissaoNecesssaria);
            //BairroDAO.getPermissao(direitos, permissaoNecesssaria);
            if (!direitos.contains(permissaoNecesssaria)) {
                rd = request.getRequestDispatcher("/cidade.jsp");
                rd.forward(request, response);
            }
        } else if (processar.equalsIgnoreCase("pesquisar")) {
            //out.print("\nPesquisar - "+request.getParameter("bairroPesquisar"));
            if (request.getParameter("cidadePesquisar") != null) {
                session.setAttribute("cidadePesquisar", request.getParameter("cidadePesquisar"));
            }
            if (request.getParameter("tipoPesquisa") != null) {
                session.setAttribute("tipoPesquisa", request.getParameter("tipoPesquisa"));
            }
            rd = request.getRequestDispatcher("CidadesController?processar=listar"
                    + "&tipoPesquisa="
                    + (String) session.getAttribute("tipoPesquisa")
                    + "&cidadePesquisar=" + (String) session.getAttribute("cidadePesquisar")
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
            Logger.getLogger(CidadesController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CidadesController.class.getName()).log(Level.SEVERE, null, ex);
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
