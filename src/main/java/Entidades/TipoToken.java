/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author cacerola
 */
public enum TipoToken {
    DIRECTIVA, AGENTE, CONTEXTO, VARIABLE, EJECUTAR, EXPORTAR, COMANDO_IA, CARGAR,
    CONECTOR,FLECHA, IGUAL, MAS, LLAVE_A, LLAVE_C, PAR_A, PAR_C, COMA, ID, CADENA,
    NUMERO, EOF;
    
    public static TipoToken getTipoToken(String identificador){
        if(identificador == null)return null;
        
        for (TipoToken cia : TipoToken.values()) {
            if(cia.name().equals(identificador)){
                return cia;
            }  
        }
        return null;
    }
    
    private String getSimbolo(){
        return this.name();
    }
}
