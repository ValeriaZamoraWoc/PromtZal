/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import AnalizadorLexico.AnalizadorLexico;
import AnalizadorLexico.AnalizadorLexicoAFD;
import AnalizadorLexico.LectorArchivo;
import Reporte.GeneradorConsola;
import Reporte.GeneradorHTML;
import javax.swing.JOptionPane;

/**
 *
 * @author cacerola
 */
public class Ventana {
    private AnalizadorLexicoAFD lexer;
    private LectorArchivo lector;
    private GeneradorConsola gc;
    private GeneradorHTML ghtml;
    
    public Ventana(){
        this.lexer = new AnalizadorLexicoAFD();
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
            JOptionPane.showMessageDialog(null, "No se ha procesado ningún archivo");
        }
        else if(lexer.getTokens().isEmpty()){
            System.out.println("No hay datos a procesar");
                        JOptionPane.showMessageDialog(null, "No se ha procesado ningún archivo");

        }else{
            ghtml.generarHTMLTokens(lexer.getTokens());
            JOptionPane.showMessageDialog(null, "Se ha generado un archivo HTML de Tokens");
        }
        ghtml.setNombreArchivo("");
    }
    
    public void generarHTMLError(){
        ghtml.setNombreArchivo(lector.getNombreArchivo());
        if(lexer.getErrores() == null){
            System.out.println("Error, errores es nula");
                        JOptionPane.showMessageDialog(null, "No se ha procesado ningún archivo");

        }
        else if(lexer.getErrores().isEmpty()){
            System.out.println("No hay datos a procesar");
                        JOptionPane.showMessageDialog(null, "No se ha procesado ningún archivo");

        }else{
            ghtml.generarHTMLErrores(lexer.getErrores());       
            JOptionPane.showMessageDialog(null, "Se ha procesado un archivo HTML de Errores");
        }
        ghtml.setNombreArchivo("");
    }
    
}
