/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semantica;

import Entidades.Simbolo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cacerola
 */
public class TablaSimbolo {
    private List<Simbolo> simbolos;

    public TablaSimbolo() {
        this.simbolos = new ArrayList<>();
    }
    
    public void registrar(Simbolo simbolo){
        simbolos.add(simbolo);
    }
    
    public Simbolo buscarSimbolo(String nombre){
        for (int i = 0; i < simbolos.size(); i++) {
            String n = simbolos.get(i).getNombre();
            if(n.equals(nombre)){
                return simbolos.get(i);
            }
        }
        return null;
    }
    
    public boolean existe(String nombre){
        for (int i = 0; i < simbolos.size(); i++) {
            String n = simbolos.get(i).getNombre();
            if(n.equals(nombre)){
                return true;
            }
        }
        return false;
    }

    public List<Simbolo> getSimbolos() {
        return simbolos;
    }
}
