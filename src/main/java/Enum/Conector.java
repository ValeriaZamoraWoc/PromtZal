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
    
    
    //constructor
    Conector(String simbolo){
        this.simbolo= simbolo;
    }
    
}
