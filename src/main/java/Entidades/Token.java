/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import Entidades.TipoToken;

/**
 *
 * @author cacerola
 */
public class Token {
    
    char lexema;
    TipoToken tipo;
    int fila, columna;
    
    Token(char c, int fila, int columna, TipoToken tipo){
        this.lexema=c;    
        this.fila = fila;
        this.columna= columna;
        this.tipo= tipo;
    }
    
    //getters
    
}
