/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.funcoes;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author luiz.souza
 */
public class teste {


    public static void main (String[] args) {
//        HashSenhasArgo2 maquinaHash = new HashSenhasArgo2();
//        
//        String cifrada ="123";
//        String hash = maquinaHash.criaHashSenha(cifrada);
//        System.out.println("Senha cifrada: "+hash);
//        System.out.println(maquinaHash.checaHashSenha(hash,cifrada));
//        System.out.println(maquinaHash.checaHashSenha(hash,cifrada));

Dotenv dotenv =Dotenv.load();
String url= dotenv.get("DB_URL");
        System.out.println("Url:"+url);
    }

    
    }

