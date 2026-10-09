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
    private List<Termino> expresion;

    public void setId(String id) {
        this.id = id;
    }    

    public void setExpresion(List<Termino> expresion) {
        this.expresion = expresion;
    }

    public String getId() {
        return id;
    }

    public List<Termino> getExpresion() {
        return expresion;
    }

    
}
