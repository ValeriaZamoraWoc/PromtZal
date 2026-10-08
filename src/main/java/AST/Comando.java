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
public class Comando {
    private String nombre;
    private List<Termino> terminos;
    private List<Conector> conectores;
    private String id;

    public Comando() {
        this.nombre = "";
        this.terminos = new ArrayList<>();
        this.conectores = new ArrayList<>();
        this.id = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Conector> getConectores() {
        return conectores;
    }

    public void setConectores(List<Conector> conectores) {
        this.conectores = conectores;
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
