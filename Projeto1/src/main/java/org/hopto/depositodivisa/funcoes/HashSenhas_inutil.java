/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.funcoes;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Helper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

/**
 *
 * @author luiz.souza
 */
public class HashSenhas_inutil {


    /**
     * @return the argon2
     */
    public Argon2 getArgon2() {
        return argon2;
    }

    /**
     * @param argon2 the argon2 to set
     */
    public void setArgon2(Argon2 argon2) {
        this.argon2 = argon2;
    }
    private Argon2 argon2;
    
    
    public HashSenhas_inutil() {

    }
        
    
    
    public String gerarArgon2(String senhaOriginal) {
        char[] senha = senhaOriginal.toCharArray();
        //Instant start = Instant.now();
        try {
            //iterations = 10;
            //memory = 16m;
            //parallelism = 1;
            String hash = getArgon2().hash(22, 65536, 1, senha);
            //System.out.println(hash);

            if (getArgon2().verify(hash, senha)) {
              //  System.out.println("Hash matches password.");
            }
       
            int iterations = Argon2Helper.findIterations(getArgon2(), 1000, 65536, 1);
            //System.out.println(iterations);
             getArgon2().wipeArray(senha);
            //Instant end;
            //end = Instant.now();
            //System.out.println(String.format("Hashing took %s ms", ChronoUnit.MILLIS.between(start, end)));
            

            return hash;
        } finally {
           
        }
    }
        public boolean checarArgon2(String cifrada, String senha){
            char[] hash = senha.toCharArray();
        
        return getArgon2().verify(cifrada, hash);}
    }
