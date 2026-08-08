/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author cacerola
 */
public enum ComandoIA {
   
    PREGUNTAR, GENERAR, RESUMIR, ANALIZAR, TRADUCIR, CLASIFICAR, EXTRAER;
    
    public static boolean esComandoIA(String identificador){
        if(identificador == null)return false;
        
        for (ComandoIA cia : ComandoIA.values()) {
            if(cia.name().equals(identificador)){
                return true;
            }  
        }
        return false;
    }
    
    public static String getComandoIALexema(String identificador){
        for (ComandoIA cia : ComandoIA.values()) {
            if(cia.name().equals(identificador)){
                return cia.name();
            }  
        }
        return null;
    }
}
