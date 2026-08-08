/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author cacerola
 */
public enum Conector {
    SOBRE("SOBRE"), COMO("COMO"), DESDE("DESDE"), EN("EN"), FLECHA("->");
    
    private final String simbolo;
    
    public static boolean esConector(String identificador){
        if(identificador == null) return false;
        for (Conector c : Conector.values()) {
            if(c.getSimbolo().equals(identificador)){
                return true;
            }
        }
        return false;
    }
    
    //constructor
    Conector(String simbolo){
        this.simbolo= simbolo;
    }
    
    //getter
    private String getSimbolo(){
        return simbolo;
    }
    
    public static String getConectorLexema(String identificador){
        if(identificador == null) return null;
        for (Conector c : Conector.values()) {
            if(c.getSimbolo().equals(identificador)){
                return c.getSimbolo();
            }
        }
        return null;
    }
}
