/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.funcoes;

/**
 *
 * @author luiz.souza
 */
public class teste {


    public static void main (String[] args) {
        HashSenhasArgo2 maquinaHash = new HashSenhasArgo2();
        
        String cifrada ="123";
        String hash = maquinaHash.criaHashSenha(cifrada);
        System.out.println("Senha cifrada: "+hash);
        System.out.println(maquinaHash.checaHashSenha(hash,cifrada));
        System.out.println(maquinaHash.checaHashSenha(hash,cifrada));
    }

    
    }

