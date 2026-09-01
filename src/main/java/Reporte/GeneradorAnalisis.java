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
public class GeneradorAnalisis {
    
    
    public String obtenerTokens(List<Token> listaTokens){    
        StringBuilder tokens = new StringBuilder();
        for (int i = 0; i < listaTokens.size(); i++) {
            int indice = i;
            String lexema = listaTokens.get(i).getLexema();
            String tipo = listaTokens.get(i).getTipo().name();
            int fila = listaTokens.get(i).getFila();
            int columna = listaTokens.get(i).getColumna();
            
            String concatenacion =indice +"   '"+ lexema +"' "+tipo +" "+fila +" "+columna+'\n';
            tokens.append(concatenacion);
        }
        
        System.out.println("Fin lista tokens.");
        return tokens.toString();
    }
    
    public String obtenerErrores(List<ErrorLexico> listaErrores){
        StringBuilder errores = new StringBuilder();
        for (int i = 0; i < listaErrores.size(); i++) {
            String concatenacion = listaErrores.get(i).getError()+'\n';
            errores.append(concatenacion);
        }
        System.out.println("Fin lista errores.");
        return errores.toString();
    }
}
