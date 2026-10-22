/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semantica;

import AST.Agente;
import AST.Comando;
import AST.Ejecutar;
import AST.Exportar;
import AST.Programa;
import AST.Termino.Termino;
import AST.Variable;
import Entidades.ErrorSemantico;
import Entidades.Simbolo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cacerola
 */
public class AnalizadorSemantico {
    private Programa programa;
    private TablaSimbolo tabla;
    private List<ErrorSemantico> errores;
    
    public AnalizadorSemantico(Programa programa){
        this.programa= programa;
        this.tabla = new TablaSimbolo();
        this.errores= new ArrayList<>();
    }
    
    public void analizar(){
        registrarAgentes();
        validarEjecuciones();
        validarExportacion();
    }
    
    private void registrarAgentes(){
        for(Agente a : programa.getAgentes()){
            String nombre = a.getId();
            String tipo= "Agente";
            Agente agente = a;
            int f = a.getFila();
            Simbolo simbolo = new Simbolo(nombre, tipo, agente.getId(), f);
            tabla.registrar(simbolo);
            
            registrarVariables(a);
            registrarResultados(a);
        }
    }
    
    private void registrarVariables(Agente a){
        List<Variable> variables = a.getVariables();
        
        for(Variable v : variables){
            if(!tabla.existe(v.getId())){
                String nombre = v.getId();
                String tipo= "Variable";
                Agente agente = a;
                int f = v.getFila();
                Simbolo simbolo = new Simbolo(nombre, tipo, agente.getId(), f);
                tabla.registrar(simbolo);
            }else{
                ErrorSemantico e = new ErrorSemantico(v.getId(), "La variable ya estaba definida",
                    v.getFila(),v.getColumna());
                errores.add(e);
            }
        }
    }
    
    private void registrarResultados(Agente a){
        List<Comando> comandos = a.getComandos();
        
        for(Comando c : comandos){
            List<Termino> terminos = c.getExpresion();

            for(Termino termino : terminos){
                String valor = termino.getValor().trim();

                if (valor.startsWith("\"") && valor.endsWith("\"")) {
                    continue;
                }
                
                if (!valor.isEmpty() && Character.isDigit(valor.charAt(0))) {
                    continue;
                }

                if (!(c.getNombre().equals("GENERAR")&& termino == terminos.getFirst()) &&!tabla.existe(valor)) {
                    ErrorSemantico e = new ErrorSemantico(valor,"No está definido el término anteriormente",
                        termino.getFila(), termino.getColumna());

                    errores.add(e);
                }
            }
            if(!tabla.existe(c.getId())){
                String nombre = c.getId();
                String tipo= "Resultado";
                Agente agente = a;
                int fila = c.getFila();

                Simbolo simbolo = new Simbolo(nombre, tipo, agente.getId(),fila);
                tabla.registrar(simbolo);
            }else{
                ErrorSemantico e = new ErrorSemantico(c.getId(), "La variable ya estaba definida",
                    c.getFila(), c.getColumna());
                errores.add(e);
            }
            
            
        }
    }
    
    private void validarEjecuciones(){
        List<Ejecutar> ejecuciones = programa.getEjecuciones();
        for(Ejecutar ejecucion : ejecuciones){
            String id = ejecucion.getId();
            if(!tabla.existe(id)|| !tabla.buscarSimbolo(id).getTipo().equals("Agente")){
                ErrorSemantico e = new ErrorSemantico(id, "No existe el agente",
                    ejecucion.getFila(),ejecucion.getColumna());
                errores.add(e);
            }
        }
    }
    
    private void validarExportacion(){
        Exportar exportar = programa.getExportar();
        
        for(String id : exportar.getIds()){            
            if(!tabla.existe(id) || !tabla.buscarSimbolo(id).getTipo().equals("Resultado")){
                ErrorSemantico e = new ErrorSemantico(id, "No está definida la variable anteriormente",
                    exportar.getFila(), exportar.getColumna());
                errores.add(e);
            }
        }
    }
    
    public List<ErrorSemantico> getErrores(){
        return errores;
    }
    
    public TablaSimbolo getTabla(){
        return tabla;
    }

}
