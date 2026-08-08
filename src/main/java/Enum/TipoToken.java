/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enum;

/**
 *
 * @author cacerola
 */
public enum TipoToken {
    DIRECTIVA, PALABRA_RESERVADA, COMANDO_IA, CONECTOR, OPERADOR, 
    DELIMITADOR, /*COMENTARIO,*/ ENTERO, CADENA, DECIMAL, IDENTIFICADOR;
    
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
