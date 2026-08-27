/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AFD;

/**
 *
 * @author cacerola
 */
public class Transicion {
    private Estado origen;
    private Estado destino;
    private String valor;

     public Transicion(Estado origen, Estado destino, String valor){
        this.origen= origen;
        this.destino= destino;
        this.valor= valor;
     }
    
    public Estado getOrigen() {
        return origen;
    }

    public Estado getDestino() {
        return destino;
    }
    
    public String getValor() {
        return valor;
    }

}
