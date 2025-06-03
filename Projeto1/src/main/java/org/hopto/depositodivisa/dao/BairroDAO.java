package org.hopto.depositodivisa.dao;

import jakarta.servlet.http.HttpSession;
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
import org.hopto.depositodivisa.model.BairroModel;

/**
 *
 * @author luiz.souza
 */
public class BairroDAO {

    public static String url;
    public Connection connection;
    private String bairro;
    private String senha;
    private ResultSet resultSet;
    private PreparedStatement ps;
    public static String nomeCompletoBairro;
    private String bairroAlterarEstado;
    private Integer numeroPagina;
    private Integer limite;

    public void BairroDAO() {
        bairro = null;
        resultSet = null;
        ps = null;
        nomeCompletoBairro = null;

    }

    public boolean verificaBairro(String bairro, String senha) throws SQLException {
        this.bairro = bairro;
        this.senha = senha;
        url = new ConexaoFactory().getUrl();

        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from bairros where nomeBairro=? and senhaBairro=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, bairro);
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

    public boolean verificaBairro1(BairroModel bairros) throws SQLException {
        this.bairro = bairros.getNomeBairro();
        this.senha = bairros.getSenhaBairro();

        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from bairros where nomeBairro=? and senhaBairro=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, bairro);
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

    public Bairro getBairro2(String bairro, String senha) throws SQLException {
        this.bairro = bairro;
        this.senha = senha;

        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from bairros where nomeBairro=? and senhaBairro=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, bairro);
            ps.setString(2, senha);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Bairro bairroLogadoAtual = new Bairro();
                bairroLogadoAtual.setNomeBairro(bairro);
                bairroLogadoAtual.setNomeCompletoBairro(resultSet.getString("nomeCompletoBairro"));
                bairroLogadoAtual.setSenhaBairro(senha);
                bairroLogadoAtual.setAcessoBairro(resultSet.getString("AcessoBairro"));
                //System.out.println("O connection está fechado? "+connection.isClosed());
                return bairroLogadoAtual;
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

    public Bairro getBairro1(Bairro bairros) {
        this.bairro = bairros.getNomeBairro();
        this.senha = bairros.getSenhaBairro();
        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from bairros where nomeBairro=? and senhaBairro=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, bairro);
            ps.setString(2, senha);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                bairroLogado = new Bairro();
                bairroLogado.setNomeBairro(bairro);
                bairroLogado.setNomeCompletoBairro(resultSet.getString("nomeCompletoBairro"));
                bairroLogado.setSenhaBairro(senha);
                bairroLogado.setAcessoBairro(resultSet.getString("AcessoBairro"));
                return bairroLogado;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Bairro getBairro(Bairro bairros) throws SQLException {
        this.bairro = bairros.getNomeBairro();
        this.senha = bairros.getSenhaBairro();
        //System.out.println("This senha "+senha);
        HashSenhasArgo2 maquinaSenha = new HashSenhasArgo2();
        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from bairros where nomeBairro=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, bairro);
            resultSet = ps.executeQuery();

            if (resultSet.next() && maquinaSenha.checaHashSenha(resultSet.getString("senhaBairro"), this.senha)) {
                bairroLogado = new Bairro();
                bairroLogado.setNomeBairro(resultSet.getString("nomeBairro"));
                bairroLogado.setNomeCompletoBairro(resultSet.getString("nomeCompletoBairro"));
                bairroLogado.setSenhaBairro(resultSet.getString("senhaBairro"));
                bairroLogado.setAcessoBairro(resultSet.getString("AcessoBairro"));
                bairroLogado.setAtivo(resultSet.getInt("ativo"));
                //System.out.println("Passou no teste de nome no banco e hash "+bairroLogado.getAtivo());
                return bairroLogado;
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

    public List<Bairro> getListaBairros() throws SQLException {
        connection = new ConexaoFactory().getConnection();
        String SQL = "select * from bairros";
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ResultSet resultSet1 = ps1.executeQuery();
        List<Bairro> listaBairros;
        listaBairros = new ArrayList<>();
        if (resultSet1.isBeforeFirst()) {
            while (resultSet1.next()) {
                Bairro bairro1 = new Bairro();
                bairro1.setAcessoBairro(resultSet1.getString("acessoBairro"));
                bairro1.setDataUltimoAcesso(resultSet1.getString("dataUltimoAcesso"));
                bairro1.setDataCadastro(resultSet1.getDate("dataCadastro"));
                bairro1.setNomeCompletoBairro(resultSet1.getString("nomeCompletoBairro"));
                bairro1.setNomeBairro(resultSet1.getString("nomeBairro"));
                bairro1.setSenhaBairro(resultSet1.getString("senhaBairro"));
                bairro1.setGruposBairro(resultSet1.getString("gruposBairro"));
                bairro1.setAtivo(resultSet1.getInt("ativo"));
                listaBairros.add(bairro1);
            }
            return listaBairros;
        }
        return null;
    }

    public List<Bairro> getListaBairrosPaginada(Integer limite,Integer numeroPagina,String ordenacao,String pesquisa, String tipoPesquisa) throws SQLException {
        String strLimite =String.valueOf(limite);
        String offset =String.valueOf( (limite* numeroPagina) - limite);
        if (pesquisa==null )
            pesquisa="";
        if (tipoPesquisa==null)
            tipoPesquisa="nomeCompletoBairro";
        connection = new ConexaoFactory().getConnection();
        String SQL = "select * from bairros where "+tipoPesquisa+" like '%"+pesquisa +"%' order by "+ordenacao+" limit "+ strLimite+" offset "+offset;
        //System.out.println("\nSQL:"+SQL);
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ResultSet resultSet1 = ps1.executeQuery();
        List<Bairro> listaBairros;
        listaBairros = new ArrayList<>();
        if (resultSet1.isBeforeFirst()) {
            while (resultSet1.next()) {
                Bairro bairro1 = new Bairro();
                bairro1.setAcessoBairro(resultSet1.getString("acessoBairro"));
                bairro1.setDataUltimoAcesso(resultSet1.getString("dataUltimoAcesso"));
                bairro1.setDataCadastro(resultSet1.getDate("dataCadastro"));
                bairro1.setNomeCompletoBairro(resultSet1.getString("nomeCompletoBairro"));
                bairro1.setNomeBairro(resultSet1.getString("nomeBairro"));
                bairro1.setSenhaBairro(resultSet1.getString("senhaBairro"));
                bairro1.setGruposBairro(resultSet1.getString("gruposBairro"));
                bairro1.setAtivo(resultSet1.getInt("ativo"));
                //System.out.println("\nNome:"+bairro1.getNomeBairro());
                listaBairros.add(bairro1);
            }
            return listaBairros;
        }
        return null;
    }

    public void desativarBairro(String bairroAlterarEstado) {
        try {
            this.bairroAlterarEstado = bairroAlterarEstado;
            connection = new ConexaoFactory().getConnection();
            String SQL = "update bairros set ativo=0 where nomeBairro='" + this.bairroAlterarEstado + "'";
            Statement st = connection.createStatement();
            st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ativarBairro(String bairroAlterarEstado) {
        try {
            this.bairroAlterarEstado = bairroAlterarEstado;
            connection = new ConexaoFactory().getConnection();
            String SQL = "update bairros set ativo=1 where nomeBairro='" + this.bairroAlterarEstado + "'";
            //System.out.println("SQL " + SQL);
            Statement ps1 = connection.createStatement();
            ps1.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getPermissao(String acessoBairro, String permissaoNecessaria) {
        //System.out.println("Passando em getPermissao - " + acessoBairro + " contem " + permissaoNecessaria + " ?");

        return (acessoBairro.contains(permissaoNecessaria));
    }

    public void alterarBairro(String nomeBairro,String nomeCompletoBairro,String acessoBairro,String gruposBairro,String ativo) {
        try {
            connection = new ConexaoFactory().getConnection();
            int bairroAtivo;
            if (ativo==null){
                ativo="0";
                bairroAtivo=0;} else{
            bairroAtivo= ativo.equalsIgnoreCase("on")?1:0;
            }
            String SQL = "update bairros set"
                    +" nomeCompletoBairro=\""+nomeCompletoBairro
                    +"\" ,acessoBairro=\""+acessoBairro
                    +"\",gruposBairro=\""+gruposBairro
                    +"\",ativo=\""+bairroAtivo
                    +"\" where nomeBairro=\""+nomeBairro+"\"";
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            //System.out.println("\n SQL: "+SQL);
            ps1.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   public void registrarNovoBairro(String nomeBairro,String nomeCompletoBairro,String acessoBairro,String gruposBairro,String ativo,String dataCadastro, String Senha) {
       //System.out.println("Nome Bairro:"+nomeBairro); 
       HashSenhasArgo2 maquinaHash = new HashSenhasArgo2();
        String senhaHash=maquinaHash.criaHashSenha(Senha);       
        try {
            connection = new ConexaoFactory().getConnection();
            int bairroAtivo= ativo.equalsIgnoreCase("on")?1:0;
            String SQL = "insert into bairros"
                    +"(nomeBairro"
                    +",acessoBairro"
                    +",gruposBairro"
                    +",ativo"
                    +",senhaBairro"
                    +",dataCadastro"
                    +",nomeCompletoBairro)"
                    +" values(?,?,?,?,?,?,?)";
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            
            ps1.setString(1, nomeBairro);
            ps1.setString(2, acessoBairro);
            ps1.setString(3, gruposBairro);
            ps1.setInt(4, bairroAtivo);
            ps1.setString(5, senhaHash);
            ps1.setString(6, dataCadastro);
            ps1.setString(7, nomeCompletoBairro);
            
            ps1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
public void registrarDataUltimoBairroPerfilBairro(String bairroAlterarEstado)    {
  this.bairroAlterarEstado = bairroAlterarEstado;
    try {
         connection = new ConexaoFactory().getConnection();
         String hoje = new java.text.SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new java.util.Date());
         //System.out.println("hoje "+hoje);
         String SQL = "update bairros set dataUltimoAcesso=\""+hoje+ "\" where nomeBairro=\""+bairroAlterarEstado+"\"";
         //System.out.println("SQL "+SQL);
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            ps1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void alterarSenha(String nomeBairro, String senhaNovaHash){
    try{     
    connection = new ConexaoFactory().getConnection();
    String SQL = "update bairros set senhaBairro= \""+senhaNovaHash +"\" where nomeBairro=\""+nomeBairro+"\"";
        //System.out.println("SQL "+SQL);
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ps1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
}

public String contagemRegistros(String pesquisa,String tipoPesquisa){
connection = new ConexaoFactory().getConnection();
String SQL;
if (pesquisa==null)
    pesquisa="";
SQL = "select count(*) as contagem from bairros where "+tipoPesquisa+" like '%"+pesquisa+"%'"; 
    System.out.println("\n SQL "+SQL);
        PreparedStatement ps1;
        ResultSet rs = null;
        String contagem = null;
        try {
            ps1 = connection.prepareStatement(SQL);
            rs = ps1.executeQuery();
            rs.next();
            contagem= rs.getString("contagem");
            System.out.println("\n\nContagem "+contagem);
     } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
     }finally{
    try {
        rs.close();
        
    } catch (SQLException ex) {
        System.getLogger(BairroDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
        
        }
 return contagem;       
}
    

}
