/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import AnalizadorLexico.AnalizadorLexicoAFD;
import AnalizadorLexico.LectorArchivo;
import Entidades.ErrorLexico;
import Entidades.Token;
import Reporte.GeneradorDOT;
import Reporte.GeneradorHTML;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private GeneradorDOT gdot;
    
    public Ventana2(){
        this.lexer = new AnalizadorLexicoAFD();
        this.lector = new LectorArchivo();
        this.ghtml = new GeneradorHTML();
        this.gdot = new GeneradorDOT();
    }
    
    public String obtenerArchivoCargado(){
        String archivoLeido = lector.leerArchivo();
        return archivoLeido;
    }
    
    public boolean procesarArchivo(String archivoLeido){     
        lexer.analizarTexto(archivoLeido);
        
        if(lexer.getTokens() == null|| lexer.getErrores()== null){
            return false;
        }
        return true;
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
    
    public void generarGrafica(){
        String grafica = lexer.obtenerDot();
        
        if(grafica == null||grafica.isEmpty() || grafica.isBlank()){
            System.out.println("Error, dot es nulo");
                        JOptionPane.showMessageDialog(null, "No existen datos");
        }else{
            try {
                gdot.generarArchivoDOT(grafica,nombreNuevo());
            } catch (InterruptedException ex) {
                System.getLogger(Ventana2.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    private String nombreNuevo(){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        String fechaHora = LocalDateTime.now().format(formatter);

        String nombreArchivoGuardar = "archivo_" + fechaHora;
        return nombreArchivoGuardar;
    }
}
