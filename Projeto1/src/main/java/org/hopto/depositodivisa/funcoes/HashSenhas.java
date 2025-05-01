/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.funcoes;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Helper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author luiz.souza
 */
public class HashSenhas {

    private String senhaOriginal;
        
    public void gerarArgon2(String senhaOriginal) {
        this.senhaOriginal = "Teste";
        Argon2 argon2 = Argon2Factory.create(
                Argon2Factory.Argon2Types.ARGON2id,
                32,
                64);
        char[] senha = "Teste".toCharArray();
        Instant start = Instant.now();
        try{
        //iterations = 10;
        //memory = 16m;
        //parallelism = 1;
        String hash = argon2.hash(22,65536,1,senha);
            System.out.println(hash);
            
            if(argon2.verify(hash,senha)){
                System.out.println("Hash matches password.");
            }
            
            int iterations = Argon2Helper.findIterations(argon2,1000,65536,1);
            System.out.println(iterations);
        }
        finally{
        argon2.wipeArray(senha);
        }
        Instant end = Instant.now();
        System.out.println(String.format("Hashing took %s ms",ChronoUnit.MILLIS.between(start,end)
        ));
    }
}