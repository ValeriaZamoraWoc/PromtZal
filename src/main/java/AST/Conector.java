/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AST;

import AST.Termino.Termino;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cacerola
 */ 
public class Conector {
    private String nombre;
    private List<Termino> termino;

    public Conector() {
        this.nombre = "";
        this.termino = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Termino> getTermino() {
        return termino;
    }

    public void setTermino(List<Termino> termino) {
        this.termino = termino;
    }
    
}
