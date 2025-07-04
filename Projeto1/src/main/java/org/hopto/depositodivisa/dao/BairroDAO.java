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
import org.hopto.depositodivisa.model.BairroModel;

/**
 *
 * @author luiz.souza
 */
public class BairroDAO {

    public static String url;
    public Connection connection;
    private String bairroNome;
    private ResultSet resultSet;
    private PreparedStatement ps;
    private String bairroAlterarEstado;
    private Integer numeroPagina;
    private Integer limite;

    public void BairroDAO() {
        bairroNome = null;
        resultSet = null;
        ps = null;        
    }

    public boolean verificaBairro(String bairro, String senha) throws SQLException {
         
        url = new ConexaoFactory().getUrl();

        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from bairros where bairroNome=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, bairro);
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

    public boolean verificaBairro1(BairroModel bairro) throws SQLException {
         
        try {
            connection = new ConexaoFactory().getConnection();
            String SQL = "select * from bairros where bairroNome=?";
            ps = connection.prepareStatement(SQL);
            ps.setString(1, bairroNome);
          
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

 
    public List<BairroModel> getListaBairros() throws SQLException {
        connection = new ConexaoFactory().getConnection();
        String SQL = "select * from bairros";
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ResultSet resultSet1 = ps1.executeQuery();
        List<BairroModel> listaBairros;
        listaBairros = new ArrayList<>();
        if (resultSet1.isBeforeFirst()) {
            while (resultSet1.next()) {
                BairroModel bairro1 = new BairroModel();
                bairro1.setBairroNome(resultSet1.getString("bairroNome"));
                bairro1.setAtivo(resultSet1.getInt("ativo"));

                listaBairros.add(bairro1);
            }
            return listaBairros;
        }
        return null;
    }

    public List<BairroModel> getListaBairrosPaginada(Integer limite,Integer numeroPagina,String ordenacao,String pesquisa, String tipoPesquisa) throws SQLException {
        String strLimite =String.valueOf(limite);
        String offset =String.valueOf( (limite* numeroPagina) - limite);
        if (pesquisa==null )
            pesquisa="";
        if (tipoPesquisa==null)
            tipoPesquisa="bairro";
        connection = new ConexaoFactory().getConnection();
        String SQL = "select * from bairros where "+tipoPesquisa+" like '%"+pesquisa +"%' order by "+ordenacao+" limit "+ strLimite+" offset "+offset;
        //System.out.println("\nSQL:"+SQL);
        PreparedStatement ps1 = connection.prepareStatement(SQL);
        ResultSet resultSet1 = ps1.executeQuery();
        List<BairroModel> listaBairros;
        listaBairros = new ArrayList<>();
        if (resultSet1.isBeforeFirst()) {
            while (resultSet1.next()) {
                BairroModel bairro1 = new BairroModel();
                bairro1.setBairroNome(resultSet1.getString("bairroNome"));
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
            String SQL = "update bairros set ativo=0 where bairroNome='" + this.bairroAlterarEstado + "'";
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
            String SQL = "update bairros set ativo=1 where bairroNome='" + this.bairroAlterarEstado + "'";
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

    public void alterarBairro(String bairroNome,String ativo,String bairroOriginal) {
        try {
            connection = new ConexaoFactory().getConnection();
            int bairroAtivo;
            if (ativo==null){
                ativo="0";
                bairroAtivo=0;} else{
            bairroAtivo= ativo.equalsIgnoreCase("on")?1:0;
            }
            String SQL = "update bairros set"
                    +" bairroNome=\""+bairroNome
                    +"\",ativo=\""+bairroAtivo
                    +"\" where bairroNome=\""+bairroOriginal+"\"";
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            System.out.println("\n SQL: "+SQL);
            ps1.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   public void registrarNovoBairro(String bairroNome,String ativo) {
       //System.out.println("Nome Bairro:"+bairroNome); 
       
        try {
            connection = new ConexaoFactory().getConnection();
            int bairroAtivo= ativo.equalsIgnoreCase("on")?1:0;
            String SQL = "insert into bairros"
                    +"(bairroNome,ativo)"
                    +" values(?,?)";
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            
            ps1.setString(1, bairroNome);
            ps1.setString(2, String.valueOf(bairroAtivo));
            System.out.println("\nSQL: "+SQL);
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
         String SQL = "update bairros set dataUltimoAcesso=\""+hoje+ "\" where bairroNome=\""+bairroAlterarEstado+"\"";
         //System.out.println("SQL "+SQL);
            PreparedStatement ps1 = connection.prepareStatement(SQL);
            ps1.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
}

public String contagemRegistros(String pesquisa,String tipoPesquisa){
connection = new ConexaoFactory().getConnection();
String SQL;
if (pesquisa==null)
    pesquisa="";
SQL = "select count(*) as contagem from bairros where "+tipoPesquisa+" like '%"+pesquisa+"%'"; 
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
