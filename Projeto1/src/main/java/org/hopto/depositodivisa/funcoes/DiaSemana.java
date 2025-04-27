/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hopto.depositodivisa.funcoes;

import java.util.Calendar;

/**
 *
 * @author Luiz
 */
public class DiaSemana {
private static int intDiaSemana;
private static String strDiaSemana;
private static Calendar dateDiaSemana;

public static String verificarDiaSemana(){
    dateDiaSemana = Calendar.getInstance();
    intDiaSemana = dateDiaSemana.get(Calendar.DAY_OF_WEEK);
    switch (intDiaSemana) {
        case 1:
            strDiaSemana = "Domingo";
            break;
        case 2:
            strDiaSemana = "Segunda-Feira";
            break;    
        case 3:
            strDiaSemana = "Terça-Feira";
            break;    
        case 4:
            strDiaSemana = "Quarta-Feira";
            break;    
        case 5:
            strDiaSemana = "Quinta-Feira";
            break;    
        case 6:
            strDiaSemana = "Sexta-Feira";
            break;   
        case 7:
            strDiaSemana = "Sábado";
            break;    
        default:
            strDiaSemana= "Dia nao identificado";
    }
    return strDiaSemana;
}

}
