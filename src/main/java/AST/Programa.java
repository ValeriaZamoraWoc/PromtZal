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
        this.exportar= new Exportar();
    }

    public List<String> getDirectivas() {
        return directivas;
    }

    public void setDirectivas(List<String> directivas) {
        this.directivas = directivas;
    }

    public List<Agente> getAgentes() {
        return agentes;
    }

    public void setAgentes(List<Agente> agentes) {
        this.agentes = agentes;
    }

    public List<Ejecutar> getEjecuciones() {
        return ejecuciones;
    }

    public void setEjecuciones(List<Ejecutar> ejecuciones) {
        this.ejecuciones = ejecuciones;
    }

    public Exportar getExportar() {
        return exportar;
    }

    public void setExportar(Exportar exportar) {
        this.exportar = exportar;
    }
    
    
}
