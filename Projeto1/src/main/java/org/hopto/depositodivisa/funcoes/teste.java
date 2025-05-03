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
        HashSenhasArgo2 maquinaHash = new HashSenhasArgo2();
        
        String cifrada ="1234";
        String hash = maquinaHash.criaHashSenha(cifrada);
        //String hash="$argon2id$v=19$m=15360,t=2,p=1$m6m4sQC357UwTgeQRrH7nohkdENmQsmVyt8UAmKTijM$NDfgp65xeuefMF44r3X65R426RpGjxK56mROJ0UCE/m9J1x6du2ryk6jD1ttTEu2FqiopA0BU0ZpZQfj3Meqng";
        
        System.out.println("Senha cifrada: "+hash);
        System.out.println(" "+maquinaHash.checaHashSenha(hash,cifrada));
 
/*Dotenv dotenv =Dotenv.load();
String url= dotenv.get("DB_URL");
        System.out.println("Url:"+url);
*/
    }

    
    }

