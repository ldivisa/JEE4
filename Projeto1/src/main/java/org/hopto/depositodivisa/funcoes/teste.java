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
        HashSenhas teste1 = new HashSenhas();
        String cifrada ="d";
        String hash= teste1.gerarArgon2(cifrada);
        System.out.println(teste1.checarArgon2(cifrada, hash));
    }


}
