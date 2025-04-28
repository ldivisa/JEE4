/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.hopto.depositodivisa.factory.ConexaoFactory;
import org.hopto.depositodivisa.model.Login;

/**
 *
 * @author luiz.souza
 */
public class LoginDAO {

    public Connection connection;
    Login login = new Login();
    private String usuario;
    private String senha;
    private ResultSet resultSet;
    private PreparedStatement ps;
    private List<Login> listaUsuariosBanco;


    
    public void LoginDAO() {
        login = new Login();
        usuario = null;
        senha = null;
        resultSet = null;
        ps = null;
        listaUsuariosBanco = null;
        

    }

public boolean verificaUsuario(String usuario, String senha) throws SQLException {
        this.usuario = usuario;
        this.senha = senha;
        
        
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
        }
        
    }
          
    
}
