/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author cacerola
 */
public enum Directivas {
     modelo("modelo"), rol("rol"), formato("formato");
     
     private final String simbolo;
     
     Directivas(String simbolo){
         this.simbolo = simbolo;
     }
}
