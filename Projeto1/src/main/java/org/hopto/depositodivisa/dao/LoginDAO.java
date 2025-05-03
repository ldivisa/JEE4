/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    Login login = new Login();
    private String usuario;
    private String senha;
    private ResultSet resultSet;
    private PreparedStatement ps;
    private List<Login> listaUsuariosBanco;
    public static String nomeCompletoUsuario;
    
    
    public void LoginDAO() {
        url = null;
        login = new Login();
        usuario = null;
        senha = null;
        resultSet = null;
        ps = null;
        listaUsuariosBanco = null;
        nomeCompletoUsuario = null;
        
    }


public boolean verificaUsuario(String usuario, String senha) throws SQLException {
        this.usuario = usuario;
        this.senha = senha;
        url=new ConexaoFactory().getUrl();
        
        try {
            connection = new ConexaoFactory().getConnection();
            
                    
            String SQL = "select * from login where nomeUsuario=? and senhaUsuario=? and ativo=true";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            resultSet = ps.executeQuery();
            return resultSet.next();
                
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
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
            String SQL = "select * from login where nomeUsuario=? and senhaUsuario=? and ativo=true";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            resultSet = ps.executeQuery();
            return resultSet.next();
                
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }            
 finally {
        ps.close();
        resultSet.close();
        connection.close();
    }
    
}


public Login getLogin2(String usuario, String senha) throws SQLException {
        this.usuario = usuario;
        this.senha =senha;

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
        }
        finally{
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
                Login usuarioLogado = new Login();
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


public Login getLogin(Login login) {
        this.usuario = login.getNomeUsuario();
        this.senha = login.getSenhaUsuario();
        HashSenhasArgo2 maquinaSenha = new HashSenhasArgo2();
        
        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from login where nomeUsuario=? and ativo=true";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, usuario);
            //ps.setString(2, senha);
            resultSet = ps.executeQuery();
            if (resultSet.next()&&maquinaSenha.checaHashSenha(resultSet.getString("senhaUsuario"), this.senha)) {
                Login usuarioLogado = new Login();
                usuarioLogado.setNomeUsuario(usuario);
                usuarioLogado.setNomeCompletoUsuario(resultSet.getString("nomeCompletoUsuario"));
                usuarioLogado.setSenhaUsuario(senha);
                usuarioLogado.setAcessoUsuario(resultSet.getString("AcessoUsuario"));
                usuarioLogado.setAtivo(resultSet.getInt("ativo"));
                return usuarioLogado;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


public List<Login> getListaUsuarios() throws SQLException {
            connection = new ConexaoFactory().getConnection();
    String SQL = "select * from login";
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    List<Login> listaUsuarios;
    try {
        ps = connection.prepareStatement(SQL);
        resultSet = ps.executeQuery();
        listaUsuarios = new ArrayList<Login>();
        while (resultSet.next()) {
            Login usuario = new Login();
            usuario.setAcessoUsuario(resultSet.getString("acessoUsuario"));
            usuario.setDataUltimoAcesso(resultSet.getDate("dataUltimoAcesso"));
            usuario.setDataCadastro(resultSet.getDate("dataCadastro"));
            usuario.setNomeCompletoUsuario(resultSet.getString("nomeCompletoUsuario"));
            usuario.setNomeUsuario(resultSet.getString("nomeUsuario"));
            usuario.setSenhaUsuario(resultSet.getString("senhaUsuario"));
            usuario.setGrupoUsuarios(resultSet.getString("grupoUsuarios"));
            usuario.setAtivo(resultSet.getInt("ativo"));
            listaUsuarios.add(usuario);
        }
        return listaUsuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    finally{
        resultSet.close();
        ps.close();
        connection.close();
    }
        
    }


}
