/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AFD;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cacerola
 */
public class AFD {
    private List<Estado> estados;
    private List<Transicion> transiciones;
    private Estado estadoInicial;
    
    public AFD(){
        this.estados= new ArrayList<>();
        this.transiciones= new ArrayList<>();
    }
    
    public void agregarEstado(Estado estado){
        this.estados.add(estado);
    }
    
    public void agregarTransicion(Transicion transicion){
        this.transiciones.add(transicion);
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }
    
    public void construirAFD(){
        //estados de aceptacion
        Estado qID = new Estado("qID", true);
        Estado qINT = new Estado("qINT", true);
        Estado qDOB = new Estado("qDOB", true);
        Estado qDIRECTIVA= new Estado("qDIRECTIVA", true);
        Estado qFINCOMENTARIO = new Estado("qFINCOMENTARIO", true);
        Estado qFINCADENA = new Estado("qFINCADENA", true);
        Estado qFLECHA = new Estado("qFLECHA", true);

        //estados normales
        Estado q0 = new Estado("q0", false);
        Estado qPUNTO= new Estado("qPUNTO", false);
        Estado qCADENA = new Estado("qCADENA", false);
        Estado qBARRA = new Estado("qBARRA", false);
        Estado qASTERISCO= new Estado("qASTERISCO", false);
        Estado qCOMCORTO= new Estado("qCOMCORTO", false);
        Estado qCOMLARGO= new Estado("qCOMLARGO", false);
        Estado qGUION = new Estado("qGUION", false);
        
        //agregar estados
        estados.add(q0);
        estados.add(qID);
        estados.add(qINT);
        estados.add(qDOB);
        estados.add(qPUNTO);
        estados.add(qDIRECTIVA);
        estados.add(qCADENA);
        estados.add(qBARRA);
        estados.add(qASTERISCO);
        estados.add(qCOMCORTO);
        estados.add(qCOMLARGO);
        estados.add(qFINCOMENTARIO);
        estados.add(qFINCADENA);
        estados.add(qGUION);
        estados.add(qFLECHA);
        
        //transiciones q0
        transiciones.add(new Transicion(q0, q0, "+"));
        transiciones.add(new Transicion(q0, q0, "="));
        transiciones.add(new Transicion(q0, q0, "{"));
        transiciones.add(new Transicion(q0, q0, "}"));
        transiciones.add(new Transicion(q0, q0, "("));
        transiciones.add(new Transicion(q0, q0, ")"));
        
        //transiciones qFLECHA
        transiciones.add(new Transicion(q0, qGUION, "-"));
        transiciones.add(new Transicion(qGUION, qFLECHA, ">"));
        
        //transiciones qID
        transiciones.add(new Transicion(q0, qID, "LETRA"));
        transiciones.add(new Transicion(q0, qID, "_"));
        transiciones.add(new Transicion(qID, qID, "NUMERO"));
        transiciones.add(new Transicion(qID, qID, "_"));
        transiciones.add(new Transicion(qID, qID, "LETRA"));

        
        //transiciones qDIRECTIVA
        transiciones.add(new Transicion(q0, qDIRECTIVA, "@"));
        transiciones.add(new Transicion(qDIRECTIVA, qDIRECTIVA, "LETRA"));
        
        //'' CADENA
        char comilla = '"';
        transiciones.add(new Transicion(q0, qCADENA, String.valueOf(comilla)));
        transiciones.add(new Transicion(qCADENA, qCADENA, "ANY_CADENA"));
        transiciones.add(new Transicion(qCADENA, qFINCADENA, String.valueOf(comilla)));
        
        // '' NUMERO
        transiciones.add(new Transicion(q0, qINT, "NUMERO"));
        transiciones.add(new Transicion(qINT, qPUNTO, "."));
        transiciones.add(new Transicion(qPUNTO, qDOB, "NUMERO"));
        transiciones.add(new Transicion(qINT, qINT, "NUMERO"));
        transiciones.add(new Transicion(qDOB, qDOB, "NUMERO"));
        
        //'' COMENTARIO
        // Inicio comentario
        transiciones.add(new Transicion(q0, qBARRA, "/"));

        // Comentario de bloque
        transiciones.add(new Transicion(qBARRA, qASTERISCO, "*"));

        // Comentario de línea
        transiciones.add(new Transicion(qBARRA, qCOMCORTO, "/"));

        // Comentario corto
        transiciones.add(new Transicion(qCOMCORTO, qFINCOMENTARIO, "\n"));
        transiciones.add(new Transicion(qCOMCORTO, qCOMCORTO, "ANY"));

        // Comentario largo
        transiciones.add(new Transicion(qASTERISCO, qFINCOMENTARIO, "/"));
        transiciones.add(new Transicion(qASTERISCO, qASTERISCO, "*"));
        transiciones.add(new Transicion(qASTERISCO, qCOMLARGO, "ANY"));

        transiciones.add(new Transicion(qCOMLARGO, qASTERISCO, "*"));
        transiciones.add(new Transicion(qCOMLARGO, qCOMLARGO, "ANY"));
        
        estadoInicial= q0;
    }
    
    /*el estado actual es el estado en el que se encuentran en este momento. El char es el
    simbolo en el que se encuentra el lexer actualmente*/
    public Transicion buscarTransicion(Estado estadoActual, char c){
        
        //por cada transicion, tomar el origen, y ver si es el mismo que el estado en el que se está
        for (Transicion t : transiciones) {
            /*Si el origen es igual al estado actual, tomara el simbolo de la transicion y lo evaluara
            para ver si el char es valido*/
            if(t.getOrigen() == estadoActual && !t.getValor().equals("ANY") && caracterValido(t.getValor(), c)){
                return t;
            }
            
        }
        
        for (Transicion t : transiciones) {
            if (t.getOrigen() == estadoActual && t.getValor().equals("ANY")) {
                return t;
            }
        }
        return null;
    }
    
    public boolean caracterValido(String simbolo, char c){
        //verificar si el char actual es válido para el simbolo de cualquier transicion
        switch(simbolo){
            case"LETRA"->{
                return Character.isLetter(c);
            }
            case"NUMERO"->{
                return Character.isDigit(c);
            }
            case"\"" -> {
                return c == '"';
            }
            case"_"->{
                return c== '_';
            }
            case"-"->{
                return c== '-';
            }
            case","->{
                return c== ',';
            }
            case">"->{
                return c== '>';
            }
            case"+"->{
                return c== '+';
            }
            case"." -> {
                return c == '.';
            }
            case"="->{
                return c== '=';
            }
            case"/"->{
                return c== '/';
            }
            case"*"->{
                return c== '*';
            }
            case"@"->{
               return c== '@';
            }
            case"\n"->{
                return c== '\n';
            }
            case"{"->{
                return c== '{';
            }
            case"}"->{
                return c== '}';
            }
            case"("->{
                return c== '(';
            }
            case")"->{
                return c== ')';
            }
            case"ANY"->{
                return true;
            }
            case"ANY_CADENA"->{
                return c != '\n' && c != '"';
            }
            default->{
                return false;
            }
        }
    }
}
