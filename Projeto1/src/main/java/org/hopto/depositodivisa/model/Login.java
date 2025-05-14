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

    /**
     * @return the ativo
     */
    public int getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(int ativo) {
        this.ativo = ativo;
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
     * @return the acessoUsuario
     */
    public String getAcessoUsuario() {
        return acessoUsuario;
    }

    /**
     * @param acessoUsuario the acessoUsuario to set
     */
    public void setAcessoUsuario(String acessoUsuario) {
        this.acessoUsuario = acessoUsuario;
    }

    /**
     * @return the dataUltimoAcesso
     */
    public String getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    /**
     * @param dataUltimoAcesso the dataUltimoAcesso to set
     */
    public void setDataUltimoAcesso(String dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the grupoUsuarios
     */
    public String getGruposUsuario() {
        return gruposUsuario;
    }

    /**
     * @param gruposUsuario the grupoUsuarios to set
     */
    public void setGruposUsuario(String gruposUsuario) {
        this.gruposUsuario = gruposUsuario;
    }

    /**
     * @return the nomeCompletoUsuario
     */
    public String getNomeCompletoUsuario() {
        return nomeCompletoUsuario;
    }

    /**
     * @param nomeCompletoUsuario the nomeCompletoUsuario to set
     */
    public void setNomeCompletoUsuario(String nomeCompletoUsuario) {
        this.nomeCompletoUsuario = nomeCompletoUsuario;
    }

    /**
     * @return the dataCadastro
     */

    private String nomeUsuario;
    private String senhaUsuario;
    private String acessoUsuario;
    private String dataUltimoAcesso;
    private Date dataCadastro;
    private String gruposUsuario;
    private String nomeCompletoUsuario;
    private int ativo;
    
}
