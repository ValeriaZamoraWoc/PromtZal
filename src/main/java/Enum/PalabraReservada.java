/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author cacerola
 */
public enum PalabraReservada {
    AGENTE, contexto, variable, EJECUTAR, EXPORTAR;
    
    public static boolean esPalabraReservada(String identificador){
        if(identificador == null)return false;
        
        for (PalabraReservada cia : PalabraReservada.values()) {
            if(cia.name().equals(identificador)){
                return true;
            }  
        }
        return false;
    }
    
    public static String getPalabraReservadaLexema(String identificador){
        if(identificador == null)return null;
        
        for (PalabraReservada cia : PalabraReservada.values()) {
            if(cia.name().equals(identificador)){
                return cia.getSimbolo();
            }  
        }
        return null;
    }
    
    private String getSimbolo(){
        return this.name();
    }
}
