/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author cacerola
 */
public enum Operador {
    //falta definir correctamente
    
    IGUAL("="), CONCATENAR("+"), CADENA, ENTERO, DECIMAL, ANALISTA, COMENTARIO_LINEA, COMENTARIO_LARGO;
    
    private final String simbolo;
    
    Operador(String simbolo){
        this.simbolo = simbolo;
    }
    Operador(){
        this.simbolo= null;
    }
}
