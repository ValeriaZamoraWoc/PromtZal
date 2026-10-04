/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author cacerola
 */
public class Token {
    
    String lexema;
    TipoToken tipo;
    int fila, columna;
    
    public Token(String lexema, TipoToken tipo, int fila, int columna){
        this.lexema= lexema;
        this.tipo = tipo;
        this.fila=fila;
        this.columna= columna;
    }
    
    /*setters
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
    }*/
    
    //getters
    public Token getToken(){
        return this;
    }
    
    public String getLexema() {
        return lexema;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
