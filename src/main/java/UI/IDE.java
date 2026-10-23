/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import AST.Programa;
import Entidades.ErrorLexico;
import Entidades.ErrorSemantico;
import Entidades.ErrorSintactico;
import General.GeneradorHTML;
import General.GeneradorPromtZal;
import Lexer.AFD_JFlex;
import Parser.Parser;
import Semantica.AnalizadorSemantico;
import Semantica.TablaSimbolo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author cacerola
 */
public class IDE {
    private GeneradorHTML html;
    private AFD_JFlex lexer;
    private Parser parser;
    private AnalizadorSemantico as;
    private GeneradorPromtZal promtzal;
    private File archivoActual;  
    private List<ErrorLexico> eLex;
    private List<ErrorSintactico> eSin;
    private List<ErrorSemantico> eSem;
    private TablaSimbolo tabla;
    private Programa programa;
    
    public IDE(){
        html = new GeneradorHTML();
    }
    
    public File cargarArchivo() {
        JFileChooser buscador = new JFileChooser();

        FileNameExtensionFilter filtro =
            new FileNameExtensionFilter("Archivos de texto (*.pz)", "pz");

        buscador.setFileFilter(filtro);

        int resultado = buscador.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            return buscador.getSelectedFile();
        }

        return null;
    }
    
    private void limpiar(){
        eLex = null;
        eSin = null;
        eSem= null;
        promtzal = null;
        tabla = null;
        programa = null;
    }
    
    public String leerArchivo(File archivo) {
        StringBuilder texto = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                texto.append(linea).append('\n');
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al leer el archivo: " + e.getMessage());
        }

        return texto.toString();
    }
    
    public void guardarArchivo(String contenido) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo .pz");

        FileNameExtensionFilter filter =new FileNameExtensionFilter("Archivos PZ (*.pz)", "pz");

        fileChooser.setFileFilter(filter);

        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            File archivoAGuardar = fileChooser.getSelectedFile();
            String ruta = archivoAGuardar.getAbsolutePath();

            // Si el usuario no escribió la extensión .pz, se agrega automáticamente
            if (!ruta.toLowerCase().endsWith(".pz")) {
                archivoAGuardar = new File(ruta + ".pz");
            }

            try (FileWriter writer = new FileWriter(archivoAGuardar)) {

                writer.write(contenido);

                JOptionPane.showMessageDialog(null,"Archivo guardado exitosamente en:\n"+ archivoAGuardar.getAbsolutePath());

            } catch (IOException e) {

                JOptionPane.showMessageDialog(null,"Error al guardar el archivo: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void guardarArchivo(File archivo, String contenido) {

        try (FileWriter writer = new FileWriter(archivo)) {

            writer.write(contenido);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null,
                    "Error al guardar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String compilar(String texto){
        StringBuilder s = new StringBuilder();
        limpiar();
        
        try (Reader reader = new StringReader(texto)) {
            //analisis del lexer
            lexer = new AFD_JFlex(reader);
            eLex = lexer.getErrores();
            
            if(eLex.isEmpty()){
                s.append("Análisis léxico exitoso --- No errores detectados\n");
                parser = new Parser(lexer);
                programa= parser.getPrograma();
                eSin = parser.getErrores();
                
                if(eSin.isEmpty()){
                    s.append("Analisis sintáctico exitoso --- No errores detectados\n");
                    as = new AnalizadorSemantico(programa);
                    as.analizar();
                    tabla = as.getTabla();
                    eSem = as.getErrores();
                    
                    if(eSem.isEmpty()){
                        s.append("Análisis semántico exitoso --- No errores detectados\n");
                        promtzal = new GeneradorPromtZal(programa);
                        s.append("COMPILADO COMPLETADO SIN ERROES\n");
                        return s.toString();
                    }else{
                        s.append(transcribirErroresSemanticos());
                        return s.toString();
                    }
                }else{
                    s.append(transcribirErroresSintacticos());
                    return s.toString();
                }
                
            }else{
                s.append(transcribirErroresLexicos());
                return s.toString();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Error al compilar";
    }
    
    private String transcribirErroresLexicos(){
        String s="";
        for (int i = 0; i < eLex.size(); i++) {
            s+=eLex.get(i).getError()+"\n";
        }
        return s;
    }
    
    private String transcribirErroresSintacticos(){
        String s="";
        for (int i = 0; i < eSin.size(); i++) {
            s+=eSin.get(i).getError()+"\n";
        }
        return s;
    }
    
    private String transcribirErroresSemanticos(){
        String s="";
        for (int i = 0; i < eSem.size(); i++) {
            s+=eSem.get(i).getError()+"\n";
        }
        return s;
    }
    
    public void generarHTMLLexicos(){
        if(!eLex.isEmpty()){
            html.generarHTMLErroresLexicos(eLex);
        }
        else{
            JOptionPane.showMessageDialog(null, "No hay errores léxicos");
        }
    }
    
    public void generarHTMLSintacticos(){
        if(!eSin.isEmpty()){
            html.generarHTMLErroresSintacticos(eSin);
        }else{
            JOptionPane.showMessageDialog(null, "No hay errores sintácticos");
        }
    }
    
    public void generarHTMLSemanticos(){
        if(!eSem.isEmpty()){
            html.generarHTMLErroresSemanticos(eSem);
        }else{
            JOptionPane.showMessageDialog(null, "No hay errores semánticos");
        }
    }
    
    public void generarHTMLTablaSimbolos(){
        if(tabla != null){
            html.generarHTMLTablaSimbolos(tabla.getSimbolos());
        }else{
            JOptionPane.showMessageDialog(null, "No hay tabla de símbolos");
        }
    }
    
    public void generarHTMLAST(){
        if(programa != null){
            html.generarHTMLAST(programa);
        }else{
            JOptionPane.showMessageDialog(null, "No hay AST");
        }
    }
    
    public void generarPromtZal(String t){
        if(promtzal != null){
            promtzal.archivoPromtzal(t);
        }else{
            JOptionPane.showMessageDialog(null,"No hay promtZal para generar");
        }
    }
    
/*---------------------------------------------------------------------------------------*/
    
    public File getArchivoActual() {
        return archivoActual;
    }

    public void setArchivoActual(File archivoActual) {
        this.archivoActual = archivoActual;
    }

    public GeneradorPromtZal getPromtzal() {
        return promtzal;
    }
    
}
