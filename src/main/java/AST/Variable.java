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
public class Variable {
    private String id;
    private List<Termino> terminos;

    public Variable() {
        this.id = "";
        this.terminos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public List<Termino> getTermino() {
        return terminos;
    }

    public void setTermino(List<Termino> termino) {
        this.terminos = termino;
    }
    
}
