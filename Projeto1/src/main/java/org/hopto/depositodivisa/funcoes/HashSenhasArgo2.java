/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.funcoes;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

/**
 *
 * @author Luiz
 */
public class HashSenhasArgo2 {
    public Argon2PasswordEncoder encoder;
    
    public HashSenhasArgo2() {
        encoder = new Argon2PasswordEncoder(32, 64, 1, 15 * 1024, 2);
    }

    public String criaHashSenha(String senhaLimpa) {
        return encoder.encode(senhaLimpa);
    }

    public boolean checaHashSenha(String hash, String senha) {
        return encoder.matches(senha, hash);
    }
}
