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
import org.hopto.depositodivisa.model.CidadeModel;

/**
 *
 * @author luiz.souza
 */
public class CidadeDAO {

    public static String url;
    public Connection connection;
    private String nomeCidade;
    private ResultSet resultSet;
    private PreparedStatement ps;
    private String cidadeAlterarEstado;
    private Integer numeroPagina;
    private Integer limite;

    public void CidadeDAO() {
        nomeCidade = null;
        resultSet = null;
        ps = null;        
    }

    public boolean verificaCidade(String cidade, String senha) throws SQLException {
         
        url = new ConexaoFactory().getUrl();

        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from cidades where nomeCidade=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, cidade);
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

    public boolean verificaCidade1(CidadeModel cidade) throws SQLException {
         
        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from cidadeswhere nomeCidade=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, nomeCidade);
          
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

 
    public List<CidadeModel> getListaCidades() throws SQLException {
        connection = new ConexaoFactory().getConnection();
        String SQL = "select * from cidades";
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ResultSet resultSet1 = ps1.executeQuery();
        List<CidadeModel> listaCidades;
        listaCidades = new ArrayList<>();
        if (resultSet1.isBeforeFirst()) {
            while (resultSet1.next()) {
                CidadeModel cidade1 = new CidadeModel();
                cidade1.setNomeCidade(resultSet1.getString("nomeCidade"));
                cidade1.setAtivo(resultSet1.getInt("ativo"));

                listaCidades.add(cidade1);
            }
            return listaCidades;
        }
        return null;
    }

    public List<CidadeModel> getListaBairrosPaginada(Integer limite,Integer numeroPagina,String ordenacao,String pesquisa, String tipoPesquisa) throws SQLException {
        String strLimite =String.valueOf(limite);
        String offset =String.valueOf( (limite* numeroPagina) - limite);
        if (pesquisa==null )
            pesquisa="";
        if (tipoPesquisa==null)
            tipoPesquisa="bairro";
        connection = new ConexaoFactory().getConnection();
        String SQL = "select * from cidades where "+tipoPesquisa+" like '%"+pesquisa +"%' order by "+ordenacao+" limit "+ strLimite+" offset "+offset;
        //System.out.println("\nSQL:"+SQL);
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ResultSet resultSet1 = ps1.executeQuery();
        List<CidadeModel> listaCidades;
        listaCidades = new ArrayList<>();
        if (resultSet1.isBeforeFirst()) {
            while (resultSet1.next()) {
                CidadeModel cidade1 = new CidadeModel();
                cidade1.setNomeCidade(resultSet1.getString("nomeCidade"));
                cidade1.setAtivo(resultSet1.getInt("ativo"));
                //System.out.println("\nNome:"+bairro1.getNomeBairro());
                listaCidades.add(cidade1);
            }
            return listaCidades;
        }
        return null;
    }

    public void desativarCidade(String cidadeAlterarEstado) {
        try {
            this.cidadeAlterarEstado = cidadeAlterarEstado;
            connection = new ConexaoFactory().getConnection();
            String SQL = "update cidades set ativo=0 where nomeCidade='" + this.cidadeAlterarEstado + "'";
            Statement st = connection.createStatement();
            st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ativarCidade(String cidadeAlterarEstado) {
        try {
            this.cidadeAlterarEstado = cidadeAlterarEstado;
            connection = new ConexaoFactory().getConnection();
            String SQL = "update cidades set ativo=1 where nomeCidade='" + this.cidadeAlterarEstado + "'";
            //System.out.println("SQL " + SQL);
            Statement ps1 = connection.createStatement();
            ps1.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getPermissao(String acessoCidade, String permissaoNecessaria) {
        //System.out.println("Passando em getPermissao - " + acessoBairro + " contem " + permissaoNecessaria + " ?");

        return (acessoCidade.contains(permissaoNecessaria));
    }

    public void alterarCidade(String nomeCidade,String ativo,String cidadeOriginal) {
        try {
            connection = new ConexaoFactory().getConnection();
            int cidadeAtivo;
            if (ativo==null){
                ativo="0";
                cidadeAtivo=0;} else{
            cidadeAtivo= ativo.equalsIgnoreCase("on")?1:0;
            }
            String SQL = "update cidades set"
                    +" nomeCidade=\""+nomeCidade
                    +"\",ativo=\""+cidadeAtivo
                    +"\" where nomeCidade=\""+cidadeOriginal+"\"";
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            System.out.println("\n SQL: "+SQL);
            ps1.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   public void registrarNovaCidade(String nomeCidade,String ativo) {
       //System.out.println("Nome Bairro:"+bairroNome); 
       
        try {
            connection = new ConexaoFactory().getConnection();
            int cidadeAtivo= ativo.equalsIgnoreCase("on")?1:0;
            String SQL = "insert into cidades"
                    +"(nomeCidade,ativo)"
                    +" values(?,?)";
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            
            ps1.setString(1, nomeCidade);
            ps1.setString(2, String.valueOf(cidadeAtivo));
            System.out.println("\nSQL: "+SQL);
            ps1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
public void registrarDataUltimaCidadePerfilCidade(String cidadeAlterarEstado)    {
  this.cidadeAlterarEstado = cidadeAlterarEstado;
    try {
         connection = new ConexaoFactory().getConnection();
         String hoje = new java.text.SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new java.util.Date());
         //System.out.println("hoje "+hoje);
         String SQL = "update cidades set dataUltimoAcesso=\""+hoje+ "\" where nomeCidade=\""+cidadeAlterarEstado+"\"";
         //System.out.println("SQL "+SQL);
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            ps1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void alterarSenha(String bairroNome, String senhaNovaHash){
    try{     
    connection = new ConexaoFactory().getConnection();
    String SQL = "update bairros set senhaBairro= \""+senhaNovaHash +"\" where bairroNome=\""+bairroNome+"\"";
        //System.out.println("SQL "+SQL);
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ps1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
}

public String contagemRegistros(String pesquisa,String tipoPesquisa){
connection = new ConexaoFactory().getConnection();
String SQL;
if (pesquisa==null)
    pesquisa="";
SQL = "select count(*) as contagem from cidades where "+tipoPesquisa+" like '%"+pesquisa+"%'"; 
    System.out.println("\n SQL CONTAGEMREGISTRS "+SQL);
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
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
     }finally{
    try {
        rs.close();
        
    } catch (SQLException ex) {
        System.getLogger(CidadeDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
        
        }
 return contagem;       
}
    

}
