/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hopto.depositodivisa.factory.ConexaoFactory;
import org.hopto.depositodivisa.funcoes.HashSenhasArgo2;
import org.hopto.depositodivisa.model.Login;

/**
 *
 * @author luiz.souza
 */
public class LoginDAO {

    public static String url;
    public Connection connection;
    private String usuario;
    private String senha;
    private ResultSet resultSet;
    private PreparedStatement ps;
    public static String nomeCompletoUsuario;
    private Login usuarioLogado;
    private String usuarioAlterarEstado;

    public void LoginDAO() {
        url = null;
        usuario = null;
        senha = null;
        resultSet = null;
        ps = null;
        nomeCompletoUsuario = null;

    }

    public boolean verificaUsuario(String usuario, String senha) throws SQLException {
        this.usuario = usuario;
        this.senha = senha;
        url = new ConexaoFactory().getUrl();

        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from login where nomeUsuario=? and senhaUsuario=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            resultSet = ps.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ps.close();
            resultSet.close();
            connection.close();
        }

    }

    public boolean verificaUsuario1(Login login) throws SQLException {
        this.usuario = login.getNomeUsuario();
        this.senha = login.getSenhaUsuario();

        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from login where nomeUsuario=? and senhaUsuario=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            resultSet = ps.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ps.close();
            resultSet.close();
            connection.close();
        }

    }

    public Login getLogin2(String usuario, String senha) throws SQLException {
        this.usuario = usuario;
        this.senha = senha;

        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from login where nomeUsuario=? and senhaUsuario=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Login usuarioLogado = new Login();
                usuarioLogado.setNomeUsuario(usuario);
                usuarioLogado.setNomeCompletoUsuario(resultSet.getString("nomeCompletoUsuario"));
                usuarioLogado.setSenhaUsuario(senha);
                usuarioLogado.setAcessoUsuario(resultSet.getString("AcessoUsuario"));
                //System.out.println("O connection está fechado? "+connection.isClosed());
                return usuarioLogado;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ps.close();
            resultSet.close();
            connection.close();
        }
        return null;
    }

    public Login getLogin1(Login login) {
        this.usuario = login.getNomeUsuario();
        this.senha = login.getSenhaUsuario();
        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from login where nomeUsuario=? and senhaUsuario=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                usuarioLogado = new Login();
                usuarioLogado.setNomeUsuario(usuario);
                usuarioLogado.setNomeCompletoUsuario(resultSet.getString("nomeCompletoUsuario"));
                usuarioLogado.setSenhaUsuario(senha);
                usuarioLogado.setAcessoUsuario(resultSet.getString("AcessoUsuario"));
                return usuarioLogado;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Login getLogin(Login login) throws SQLException {
        this.usuario = login.getNomeUsuario();
        this.senha = login.getSenhaUsuario();
        //System.out.println("This senha "+senha);
        HashSenhasArgo2 maquinaSenha = new HashSenhasArgo2();
        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from login where nomeUsuario=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario);
            resultSet = ps.executeQuery();

            if (resultSet.next() && maquinaSenha.checaHashSenha(resultSet.getString("senhaUsuario"), this.senha)) {
                usuarioLogado = new Login();
                usuarioLogado.setNomeUsuario(resultSet.getString("nomeUsuario"));
                usuarioLogado.setNomeCompletoUsuario(resultSet.getString("nomeCompletoUsuario"));
                usuarioLogado.setSenhaUsuario(resultSet.getString("senhaUsuario"));
                usuarioLogado.setAcessoUsuario(resultSet.getString("AcessoUsuario"));
                usuarioLogado.setAtivo(resultSet.getInt("ativo"));
                //System.out.println("Passou no teste de nome no banco e hash "+usuarioLogado.getAtivo());
                return usuarioLogado;
            } else {
                //System.out.println(" Nao Passou no teste de nome no banco e hash");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ps.close();
            connection.close();
        }
    }

    public List<Login> getListaUsuarios() throws SQLException {
        connection = new ConexaoFactory().getConnection();
        String SQL = "select * from login";
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ResultSet resultSet1 = ps1.executeQuery();
        List<Login> listaUsuarios;
        listaUsuarios = new ArrayList<>();
        if (resultSet1.isBeforeFirst()) {
            while (resultSet1.next()) {
                Login usuario1 = new Login();
                usuario1.setAcessoUsuario(resultSet1.getString("acessoUsuario"));
                usuario1.setDataUltimoAcesso(resultSet1.getDate("dataUltimoAcesso"));
                usuario1.setDataCadastro(resultSet1.getDate("dataCadastro"));
                usuario1.setNomeCompletoUsuario(resultSet1.getString("nomeCompletoUsuario"));
                usuario1.setNomeUsuario(resultSet1.getString("nomeUsuario"));
                usuario1.setSenhaUsuario(resultSet1.getString("senhaUsuario"));
                usuario1.setGrupoUsuarios(resultSet1.getString("grupoUsuarios"));
                usuario1.setAtivo(resultSet1.getInt("ativo"));
                listaUsuarios.add(usuario1);
            }
            return listaUsuarios;
        }
        return null;
    }

    public void desativarUsuario(String usuarioAlterarEstado) {

        try {
            this.usuarioAlterarEstado = usuarioAlterarEstado;
            connection = new ConexaoFactory().getConnection();
            String SQL = "update login set ativo=0 where nomeUsuario='" + this.usuarioAlterarEstado + "'";
            Statement st = connection.createStatement();
            st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ativarUsuario(String usuarioAlterarEstado) {
        try {
            this.usuarioAlterarEstado = usuarioAlterarEstado;
            connection = new ConexaoFactory().getConnection();
            String SQL = "update login set ativo=1 where nomeUsuario='" + this.usuarioAlterarEstado + "'";
            //System.out.println("SQL " + SQL);
            Statement ps1 = connection.createStatement();
            ps1.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
public boolean getPermissao(String acessoUsuario,String permissaoNecessaria)  {
    System.out.println("Passando em getPermissao - "+acessoUsuario+ " contem "+permissaoNecessaria+" ?");    
    return(acessoUsuario.contains(permissaoNecessaria));
}
}
