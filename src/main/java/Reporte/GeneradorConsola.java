/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reporte;

import Entidades.ErrorLexico;
import Entidades.Token;
import java.util.List;

/**
 *
 * @author cacerola
 */
public class GeneradorConsola {
    public void imprimirTokensConsola(List<Token> tokens){
        System.out.println("Inicio lista tokens...");
        
        for (int i = 0; i < tokens.size(); i++) {
            int indice = i;
            String lexema = tokens.get(i).getLexema();
            String tipo = tokens.get(i).getTipo().name();
            int fila = tokens.get(i).getFila();
            int columna = tokens.get(i).getColumna();
            System.out.println(indice +" '"+ lexema +"' "+tipo +" "+fila +" "+columna);
        }
        
        System.out.println("Fin lista tokens.");
    }
    
    public void imprimirErroresConsola(List<ErrorLexico> errores){
        System.out.println("Inicio lista errores léxicos");
        for (int i = 0; i < errores.size(); i++) {
            String error = errores.get(i).getError();
            System.out.println(error);
        }
        System.out.println("Fin lista errores.");
    }
}
