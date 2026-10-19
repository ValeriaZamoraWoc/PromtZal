/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AST;

/**
 *
 * @author cacerola
 */
public class Directiva {
    private String nombre;
    private String cadena;
    
    public String getCadena() {
        return cadena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
private int fila, columna;

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getNombre() {
        return nombre;
    }

}
