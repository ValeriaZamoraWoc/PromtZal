/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.LectorArchivo;
import Reporte.GeneradorConsola;
import Reporte.GeneradorHTML;

/**
 *
 * @author cacerola
 */
public class Ventana {
    private AnalizadorLexico lexer;
    private LectorArchivo lector;
    private GeneradorConsola gc;
    private GeneradorHTML ghtml;
    
    public Ventana(){
        this.lexer = new AnalizadorLexico();
        this.lector = new LectorArchivo();
        this.gc = new GeneradorConsola();
        this.ghtml = new GeneradorHTML();
    }
    
    
    public void procesarArchivo(){
        String archivoLeido = lector.leerArchivo();
        lexer.analizarTexto(archivoLeido);
        
        if(lexer.getTokens() == null){
            System.out.println("Error, tokens es nula");
        }else if(lexer.getErrores() == null){
            System.out.println("Error, errores es nula");
        }
        else if(lexer.getTokens().isEmpty() && lexer.getErrores().isEmpty()){
            System.out.println("No hay datos a procesar");
        }else{
            gc.imprimirTokensConsola(lexer.getTokens());
            gc.imprimirErroresConsola(lexer.getErrores());
        }
    }
    
    public void generarHTMLToken(){
        ghtml.setNombreArchivo(lector.getNombreArchivo());
        if(lexer.getTokens() == null){
            System.out.println("Error, tokens es nula");
        }
        else if(lexer.getTokens().isEmpty()){
            System.out.println("No hay datos a procesar");
        }else{
            ghtml.generarHTMLTokens(lexer.getTokens());
        }
        ghtml.setNombreArchivo("");
    }
    
    public void generarHTMLError(){
        ghtml.setNombreArchivo(lector.getNombreArchivo());
        if(lexer.getErrores() == null){
            System.out.println("Error, errores es nula");
        }
        else if(lexer.getErrores().isEmpty()){
            System.out.println("No hay datos a procesar");
        }else{
            ghtml.generarHTMLErrores(lexer.getErrores());            
        }
        ghtml.setNombreArchivo("");
    }
    
}
