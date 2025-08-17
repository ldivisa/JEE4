/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.factory;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Loja
 */
public class ConexaoFactory {

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }
Dotenv ambiente =Dotenv.load();

    private final String usuario = ambiente.get("DB_USUARIO");
    private final String url = ambiente.get("DB_URL");
    private final String driver = ambiente.get("DB_DRIVER");
    private final String senha = ambiente.get("DB_SENHA");

    public Connection getConnection() {
        try {
            Class.forName(getDriver());
            return DriverManager.getConnection(getUrl(), getUsuario(), getSenha());
        } catch (SQLException e) {
            Logger.getLogger(ConexaoFactory.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException("Erro SQLException ao abrir a conexão em ConnectionFactory", e);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ConexaoFactory.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException("Erro ClassNotFoundException em ConnectionFactory", e);
        }

        
        
    }
   
}


