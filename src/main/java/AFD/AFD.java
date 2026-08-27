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
    
    public void construirAFD(){
        //estados de aceptacion
        Estado q0 = new Estado("q0", true);
        Estado qID = new Estado("qID", true);
        Estado qINT = new Estado("qINT", true);
        Estado qDOB = new Estado("qDOB", true);
        
        //estados normales
        Estado qPUNTO= new Estado("qPUNTO", false);
        Estado qDIRECTIVA= new Estado("qDIREC", false);
        Estado qCADENA = new Estado("qCADENA", false);
        Estado qBARRA = new Estado("qBARRA", false);
        Estado qASTERISCO= new Estado("qASTERISCO", false);
        Estado qCOMCORTO= new Estado("qCOMCORTO", false);
        Estado qCOMLARGO= new Estado("qCOMLARGO", false);
        
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
        
        
        //transiciones q0
        transiciones.add(new Transicion(q0, q0, "+"));
        transiciones.add(new Transicion(q0, q0, "="));
        transiciones.add(new Transicion(q0, q0, "{"));
        transiciones.add(new Transicion(q0, q0, "}"));
        transiciones.add(new Transicion(q0, q0, "("));
        transiciones.add(new Transicion(q0, q0, ")"));
        
        //transiciones qID
        transiciones.add(new Transicion(q0, qID, "LETRA"));
        transiciones.add(new Transicion(qID, qID, "NUMERO"));
        transiciones.add(new Transicion(qID, qID, "_"));
        
        //transiciones qDIRECTIVA
        transiciones.add(new Transicion(q0, qDIRECTIVA, "@"));
        transiciones.add(new Transicion(qDIRECTIVA, q0, "\n"));
        transiciones.add(new Transicion(qDIRECTIVA, qDIRECTIVA, "LETRA"));
        
        //'' CADENA
        char comilla = '"';
        transiciones.add(new Transicion(q0, qCADENA, String.valueOf(comilla)));
        transiciones.add(new Transicion(qCADENA, qCADENA, "ANY"));
        transiciones.add(new Transicion(qCADENA, q0, String.valueOf(comilla)));
        
        // '' NUMERO
        transiciones.add(new Transicion(q0, qINT, "NUMERO"));
        transiciones.add(new Transicion(qINT, qPUNTO, "."));
        transiciones.add(new Transicion(qPUNTO, qDOB, "NUMERO"));
        transiciones.add(new Transicion(qINT, qINT, "NUMERO"));
        transiciones.add(new Transicion(qDOB, qDOB, "NUMERO"));
        
        //'' COMENTARIO
        transiciones.add(new Transicion(q0, qBARRA, "/"));
        transiciones.add(new Transicion(qBARRA, qASTERISCO, "*"));
        transiciones.add(new Transicion(qASTERISCO, qASTERISCO, "*"));
        transiciones.add(new Transicion(qBARRA, qCOMCORTO, "/"));
        transiciones.add(new Transicion(qCOMCORTO, qCOMCORTO, "ANY"));
        transiciones.add(new Transicion(qCOMCORTO, q0, "\n"));
        transiciones.add(new Transicion(qASTERISCO, qCOMLARGO, "ANY"));
        transiciones.add(new Transicion(qCOMLARGO, qASTERISCO, "*"));
        transiciones.add(new Transicion(qASTERISCO, q0, "/"));
        
        estadoInicial= q0;
    }
}
