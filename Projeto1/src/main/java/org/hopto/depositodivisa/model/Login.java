/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.model;


import java.util.Date;


/**
 *
 * @author luiz.souza
 */
public class Login {

    public Login() {
        this.nomeUsuario = null;
        this.senhaUsuario = null;
        this.acessousuario = null;
        this.dataUltimoAcessoUsuario = null;
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @param nomeUsuario the nomeUsuario to set
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * @return the senhaUsuario
     */
    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    /**
     * @param senhaUsuario the senhaUsuario to set
     */
    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    /**
     * @return the acessousuario
     */
    public String getAcessousuario() {
        return acessousuario;
    }

    /**
     * @param acessousuario the acessousuario to set
     */
    public void setAcessousuario(String acessousuario) {
        this.acessousuario = acessousuario;
    }

    /**
     * @return the dataUltimoAcessoUsuario
     */
    public Date getDataUltimoAcessoUsuario() {
        return dataUltimoAcessoUsuario;
    }

    /**
     * @param dataUltimoAcessoUsuario the dataUltimoAcessoUsuario to set
     */
    public void setDataUltimoAcessoUsuario(Date dataUltimoAcessoUsuario) {
        this.dataUltimoAcessoUsuario = dataUltimoAcessoUsuario;
    }
    
   
    private String nomeUsuario;
    private String senhaUsuario;
    private String acessousuario;
    private Date dataUltimoAcessoUsuario;
    
    
}
