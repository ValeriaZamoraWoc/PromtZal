/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AST;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cacerola
 */
public class Programa {
    private List<String> directivas;
    private List<Agente> agentes;
    private List<Ejecutar> ejecuciones;
    private Exportar exportar;

    public Programa() {
        this.directivas = new ArrayList<>();
        this.agentes = new ArrayList<>();
        this.ejecuciones = new ArrayList<>();
    }
    
    
}
