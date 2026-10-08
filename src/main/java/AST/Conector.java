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
    private String id;
    private List<Termino> expresion;

    public Conector() {
        this.id = "";
        this.expresion = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Termino> getExpresion() {
        return expresion;
    }

    public void setExpresion(List<Termino> expresion) {
        this.expresion = expresion;
    }

}
