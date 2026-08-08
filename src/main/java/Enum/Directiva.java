/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author cacerola
 */
public enum Directiva {
    MODELO("@modelo"), ROL("@rol"), FORMATO("@formato");
     
    private final String simbolo;

    Directiva(String simbolo){
        this.simbolo = simbolo;
    }
    
    private String getSimbolo(){
        return simbolo;
    }
    
    public static boolean esDirectiva(String identificador){
        if(identificador == null)return false;
        
        for (Directiva d : Directiva.values()) {
            if(d.getSimbolo().equals(identificador)){
                return true;
            }
            
        }
        
        return false;
    }
    
    public static String getDirectivaLexema(String identificador){
        if(identificador == null)return null;
        
        for (Directiva d : Directiva.values()) {
            if(d.getSimbolo().equals(identificador)){
                return d.name();
            }
            
        }
        
        return null;
    }
}
