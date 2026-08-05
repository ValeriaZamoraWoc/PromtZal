/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import Enum.TipoToken;

/**
 *
 * @author cacerola
 */
public class Token {
    
    String lexema;
    TipoToken tipo;
    int fila, columna;
    
    //setters
    public void setLexema(String lexema){
        this.lexema = lexema;
    }
    
    public void setFila(int fila){
        this.fila= fila;
    }
    
    public void setColumna(int columna){
        this.columna= columna;
    }
    
    public void setTipo(TipoToken tipo){
        this.tipo = tipo;
    }
    
    //getters
    public Token getToken(){
        return this;
    }
}
