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
    private Integer numeroPagina;
    private Integer limite;
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
                Login usuarioLogadoAtual = new Login();
                usuarioLogadoAtual.setNomeUsuario(usuario);
                usuarioLogadoAtual.setNomeCompletoUsuario(resultSet.getString("nomeCompletoUsuario"));
                usuarioLogadoAtual.setSenhaUsuario(senha);
                usuarioLogadoAtual.setAcessoUsuario(resultSet.getString("AcessoUsuario"));
                //System.out.println("O connection está fechado? "+connection.isClosed());
                return usuarioLogadoAtual;
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
                usuario1.setDataUltimoAcesso(resultSet1.getString("dataUltimoAcesso"));
                usuario1.setDataCadastro(resultSet1.getDate("dataCadastro"));
                usuario1.setNomeCompletoUsuario(resultSet1.getString("nomeCompletoUsuario"));
                usuario1.setNomeUsuario(resultSet1.getString("nomeUsuario"));
                usuario1.setSenhaUsuario(resultSet1.getString("senhaUsuario"));
                usuario1.setGruposUsuario(resultSet1.getString("gruposUsuario"));
                usuario1.setAtivo(resultSet1.getInt("ativo"));
                listaUsuarios.add(usuario1);
            }
            return listaUsuarios;
        }
        return null;
    }

    public List<Login> getListaUsuariosPaginada(Integer limite,Integer numeroPagina) throws SQLException {
        String strLimite =String.valueOf(limite);
        String offset =String.valueOf( (limite* numeroPagina) - limite);
        connection = new ConexaoFactory().getConnection();
        String SQL = "select * from login limit "+ strLimite+" offset "+offset;
        //System.out.println("\nSQL:"+SQL);
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ResultSet resultSet1 = ps1.executeQuery();
        List<Login> listaUsuarios;
        listaUsuarios = new ArrayList<>();
        if (resultSet1.isBeforeFirst()) {
            while (resultSet1.next()) {
                Login usuario1 = new Login();
                usuario1.setAcessoUsuario(resultSet1.getString("acessoUsuario"));
                usuario1.setDataUltimoAcesso(resultSet1.getString("dataUltimoAcesso"));
                usuario1.setDataCadastro(resultSet1.getDate("dataCadastro"));
                usuario1.setNomeCompletoUsuario(resultSet1.getString("nomeCompletoUsuario"));
                usuario1.setNomeUsuario(resultSet1.getString("nomeUsuario"));
                usuario1.setSenhaUsuario(resultSet1.getString("senhaUsuario"));
                usuario1.setGruposUsuario(resultSet1.getString("gruposUsuario"));
                usuario1.setAtivo(resultSet1.getInt("ativo"));
                //System.out.println("\nNome:"+usuario1.getNomeUsuario());
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

    public boolean getPermissao(String acessoUsuario, String permissaoNecessaria) {
        //System.out.println("Passando em getPermissao - " + acessoUsuario + " contem " + permissaoNecessaria + " ?");

        return (acessoUsuario.contains(permissaoNecessaria));
    }

    public void alterarUsuario(String nomeUsuario,String nomeCompletoUsuario,String acessoUsuario,String gruposUsuario,String ativo) {
        try {
            connection = new ConexaoFactory().getConnection();
            int usuarioAtivo;
            if (ativo==null){
                ativo="0";
                usuarioAtivo=0;} else{
            usuarioAtivo= ativo.equalsIgnoreCase("on")?1:0;
            }
            String SQL = "update login set"
                    +" nomeCompletoUsuario=\""+nomeCompletoUsuario
                    +"\" ,acessoUsuario=\""+acessoUsuario
                    +"\",gruposUsuario=\""+gruposUsuario
                    +"\",ativo=\""+usuarioAtivo
                    +"\" where nomeUsuario=\""+nomeUsuario+"\"";
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            //System.out.println("\n SQL: "+SQL);
            ps1.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   public void registrarNovoUsuario(String nomeUsuario,String nomeCompletoUsuario,String acessoUsuario,String gruposUsuario,String ativo,String dataCadastro, String Senha) {
       //System.out.println("Nome Usuario:"+nomeUsuario); 
       HashSenhasArgo2 maquinaHash = new HashSenhasArgo2();
        String senhaHash=maquinaHash.criaHashSenha(Senha);       
        try {
            connection = new ConexaoFactory().getConnection();
            int usuarioAtivo= ativo.equalsIgnoreCase("on")?1:0;
            String SQL = "insert into login"
                    +"(nomeUsuario"
                    +",acessoUsuario"
                    +",gruposUsuario"
                    +",ativo"
                    +",senhaUsuario"
                    +",dataCadastro"
                    +",nomeCompletoUsuario)"
                    +" values(?,?,?,?,?,?,?)";
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            
            ps1.setString(1, nomeUsuario);
            ps1.setString(2, acessoUsuario);
            ps1.setString(3, gruposUsuario);
            ps1.setInt(4, usuarioAtivo);
            ps1.setString(5, senhaHash);
            ps1.setString(6, dataCadastro);
            ps1.setString(7, nomeCompletoUsuario);
            
            ps1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
public void registrarDataUltimoLoginPerfilUsuario(String usuarioAlterarEstado)    {
  this.usuarioAlterarEstado = usuarioAlterarEstado;
    try {
         connection = new ConexaoFactory().getConnection();
         String hoje = new java.text.SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new java.util.Date());
         //System.out.println("hoje "+hoje);
         String SQL = "update login set dataUltimoAcesso=\""+hoje+ "\" where nomeUsuario=\""+usuarioAlterarEstado+"\"";
         //System.out.println("SQL "+SQL);
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            ps1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void alterarSenha(String nomeUsuario, String senhaNovaHash){
    try{     
    connection = new ConexaoFactory().getConnection();
    String SQL = "update login set senhaUsuario= \""+senhaNovaHash +"\" where nomeUsuario=\""+nomeUsuario+"\"";
        //System.out.println("SQL "+SQL);
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ps1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
}

public String contagemRegistros(){
connection = new ConexaoFactory().getConnection();
String SQL = "select count(*) as contagem from login";
        PreparedStatement ps1;
        ResultSet rs = null;
        String contagem = null;
        try {
            ps1 = connection.prepareStatement(SQL);
            rs = ps1.executeQuery();
            rs.next();
            contagem= rs.getString("contagem");
     } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
 return contagem;       
}
    

}
