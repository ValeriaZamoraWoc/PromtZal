/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import AST.Agente;

/**
 *
 * @author cacerola
 */
public class Simbolo {
    private String nombre;
    private String tipo;
    private Agente agente;
    private int fila;

    public Simbolo(String nombre, String tipo, Agente agente) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.agente = agente;
        //this.fila = fila;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public Agente getAgente() {
        return agente;
    }

    public int getFila() {
        return fila;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
