/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author cacerola
 */
public class ErrorLexico {
    
    String lexema;
    int fila, columna;
    String descripcion;
    
    public ErrorLexico(String c, int fila, int columna){
        this.lexema=c;    
        this.fila = fila;
        this.columna= columna;
    }
    
    public String getError(){
        return "Error léxico en fila: "+ fila+ " columna: "+columna;
    }
}
