/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package General;

import AST.Agente;
import AST.Comando;
import AST.Conector;
import AST.Directiva;
import AST.Programa;
import AST.Termino.Termino;
import AST.Variable;
import java.util.List;

/**
 *
 * @author cacerola
 */
public class GeneradorPromtZal {
    StringBuilder promtzal;
    Programa programa;
    String formato;
    
    public GeneradorPromtZal(Programa programa){
        this.programa = programa;
        promtzal = new StringBuilder();
        
        promtzal.append("Prompt generado con PromtZal\n");
        promtzal.append(generarEncabezado());
        promtzal.append(generarAgentes());
        promtzal.append(generarFinal());
    }
    
    private String generarEncabezado(){
        //modelo, rol, formato respuesta
        String s="";
        List<Directiva> directivas = programa.getDirectivas();
        for(Directiva d : directivas){
            
            switch(d.getNombre()){
                case "@modelo"->{
                    s += "Modelo -> "+d.getCadena()+"\n";
                }
                case "@formato"->{
                    this.formato=d.getCadena();
                }
                case "@rol"->{
                    s += "Tomas el rol -> "+d.getCadena()+"\n";
                }
            }

            
        }
        return s;
    }
    
    private String generarAgentes(){
        /*contexto general
        variables (si es cargar "archivo" (archivo adjunto))
        Instrucciones: comandos ia*/
        String s="";
        
        List<Agente> agentes = programa.getAgentes();
        for(Agente a : agentes){
            s += "\n\nEste agente es "+a.getId()+"\n"
                    +" Su contexto es "+a.getContexto().getCadena()+"\n";
            s+=generarVariables(a.getVariables());
            s+=generarInstrucciones(a.getComandos());
        }
        return s;
    }
    
    private String generarFinal(){
        String s= "En resumen dame la respuesta en el formato: "+ formato;
        return s;
    }
    
    private String generarVariables(List<Variable> variables){
        String s;
        s = " Variables: \n";
        for(Variable v: variables){
            s+="  "+v.getId();
            for(Termino t : v.getExpresion()){
                if(t.getValor().contains("[\"")&& t.getValor().contains("\"]")){
                    s+= " es el valor de este archivo adjunto: "+t.getValor();
                    continue;
                }
                s+= " es "+t.getValor();
            }
            s+= "\n";
        }
        return s;
    }
    
    private String generarInstrucciones(List<Comando> comandos) {

        String s = " Instrucciones:\n";

        for (Comando c : comandos) {

            switch (c.getNombre()) {

                case "PREGUNTAR" -> {
                    s += "  Basándote en "+ generarTerminos(c.getExpresion())+ ", responde"+generarConectores(c.getConectores())
                    +". Guarda la respuesta como "+ c.getId() + ".\n";
                }

                case "GENERAR" -> {
                    s += "  Genera "+ generarTerminos(c.getExpresion())+generarConectores(c.getConectores())
                    +". Guarda el resultado como "+ c.getId() + ".\n";
                }

                case "RESUMIR" -> {
                    s += "  Resume "+ generarTerminos(c.getExpresion())+generarConectores(c.getConectores())
                    +". Guarda el resultado como "+ c.getId() + ".\n";
                }

                case "ANALIZAR" -> {
                    s += "  Analiza "+ generarTerminos(c.getExpresion())+generarConectores(c.getConectores())
                    +". Guarda el resultado como "+ c.getId() + ".\n";
                }

                case "TRADUCIR" -> {
                    s += "  Traduce "+ generarTerminos(c.getExpresion())+generarConectores(c.getConectores())
                    + ". Guarda el resultado como "+ c.getId() + ".\n";
                }

                case "CLASIFICAR" -> {
                    s += "  Clasifica "+ generarTerminos(c.getExpresion())+generarConectores(c.getConectores())
                    +". Guarda el resultado como "+ c.getId() + ".\n";
                }

                case "EXTRAER" -> {
                    s += "  Extrae "+ generarTerminos(c.getExpresion())+generarConectores(c.getConectores())
                    +". Guarda el resultado como "+ c.getId() + ".\n";
                }
            }
        }

        return s;
    }
    
    private String generarTerminos(List<Termino> terminos) {
        String s = "";
        for (int i = 0; i < terminos.size(); i++) {
            s += terminos.get(i).getValor();
            if (i < terminos.size() - 1) {
                s += " y ";
            }
        }

        return s;
    }
    
    private String generarConectores(List<Conector> conectores) {
        String s = "";

        for (Conector c : conectores) {
            String valor = generarTerminos(c.getExpresion());

            switch (c.getId()) {
                case "DESDE" -> {
                    s += " a partir de: " + valor;
                }
                case "SOBRE" -> {
                    s += " considerando: " + valor;
                }
                case "EN" -> {
                    s += " en " + valor;
                }
                case "COMO" -> {
                    s += ". Entrega el resultado en formato " + valor;
                }
            }
        }

        return s;
    }
    
    public String getPromtzal(){
        return promtzal.toString();
    }
}
