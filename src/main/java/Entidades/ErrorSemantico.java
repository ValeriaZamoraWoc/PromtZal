/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author cacerola
 */
public class ErrorSemantico {
    private String lexema;
    private String descripcion;
    private int fila;
    private int columna;

    public ErrorSemantico(String lexema, String descripcion, int fila, int columna) {
        this.lexema = lexema;
        this.descripcion = descripcion;
        this.fila = fila;
        this.columna = columna;
    }
    
    public String getError(){
        return "Error Semantico: '"+lexema+"' descripcion: '"+descripcion+"' en fila: "+ fila+ " columna: "+columna;
    }

    public String getLexema() {
        return lexema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    
    
}
