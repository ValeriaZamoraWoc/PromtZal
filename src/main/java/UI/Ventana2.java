/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import AnalizadorLexico.AnalizadorLexicoAFD;
import AnalizadorLexico.LectorArchivo;
import Entidades.ErrorLexico;
import Entidades.Token;
import Reporte.GeneradorAnalisis;
import Reporte.GeneradorConsola;
import Reporte.GeneradorHTML;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author cacerola
 */
public class Ventana2 {
    private AnalizadorLexicoAFD lexer;
    private LectorArchivo lector;
    private GeneradorHTML ghtml;
    
    public Ventana2(){
        this.lexer = new AnalizadorLexicoAFD();
        this.lector = new LectorArchivo();
        this.ghtml = new GeneradorHTML();
    }
    
    public String obtenerArchivoCargado(){
        String archivoLeido = lector.leerArchivo();
        return archivoLeido;
    }
    
    public boolean procesarArchivo(String archivoLeido){     
        lexer.analizarTexto(archivoLeido);
        
        if(lexer.getTokens() == null){
            return false;
        }else if(lexer.getErrores() == null){
            return false;
        }
        else if(lexer.getTokens().isEmpty() && lexer.getErrores().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public List<Token> obtenerTokens(){
        return this.lexer.getTokens();
    }
    
    public List<ErrorLexico> obtenerErrores(){
        return this.lexer.getErrores();
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
