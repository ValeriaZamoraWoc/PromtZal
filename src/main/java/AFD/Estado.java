/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AFD;

/**
 *
 * @author cacerola
 */
public class Estado {
    private String nombre;
    private boolean aceptacion;
    
    public Estado(String nombre, boolean aceptacion){
        this.nombre= nombre;
        this.aceptacion= aceptacion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public boolean esAceptacion() {
        return aceptacion;
    }
}
