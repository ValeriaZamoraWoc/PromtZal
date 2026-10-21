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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author cacerola
 */
public class IDE {
    GeneradorHTML html;
    AFD_JFlex lexer;
    Parser parser;
    AnalizadorSemantico as;
    GeneradorPromtZal promtzal;
    private File archivoActual;  
    
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
        
        try (Reader reader = new FileReader(texto)) {
            //analisis del lexer
            lexer = new AFD_JFlex(reader);
            List<ErrorLexico> eLex = lexer.getErrores();
            
            if(eLex == null || eLex.isEmpty()){
                s.append("Análisis léxico exitoso --- No errores detectados\n");
                parser = new Parser(lexer);
                Programa programaAnalizado = parser.getPrograma();
                List<ErrorSintactico> eSin = parser.getErrores();
                
                if(eSin== null || eSin.isEmpty()){
                    s.append("Analisis sintáctico exitoso --- No errores detectados\n");
                    as = new AnalizadorSemantico(programaAnalizado);
                    List<ErrorSemantico> eSem = as.getErrores();
                    
                    if(eSem == null || eSem.isEmpty()){
                        s.append("Análisis semántico exitoso --- No errores detectados\n");
                        promtzal = new GeneradorPromtZal(programaAnalizado);
                        s.append("COMPILADO COMPLETADO SIN ERROES\n");
                        return s.toString();
                    }else{
                        s.append(transcribirErroresSemanticos(eSem));
                        return s.toString();
                    }
                }else{
                    s.append(transcribirErroresSintacticos(eSin));
                    return s.toString();
                }
                
            }else{
                s.append(transcribirErroresLexicos(eLex));
                return s.toString();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Error al compilar";
    }
    
    private String transcribirErroresLexicos(List<ErrorLexico> e){
        String s="";
        for (int i = 0; i < e.size(); i++) {
            s+=e.get(i).getError()+"\n";
        }
        return s;
    }
    
    private String transcribirErroresSintacticos(List<ErrorSintactico> e){
        String s="";
        for (int i = 0; i < e.size(); i++) {
            s+=e.get(i).getError()+"\n";
        }
        return s;
    }
    
    private String transcribirErroresSemanticos(List<ErrorSemantico> e){
        String s="";
        for (int i = 0; i < e.size(); i++) {
            s+=e.get(i).getError()+"\n";
        }
        return s;
    }
    
    public void generarHTMLLexicos(){
        
    }
    
    public void generarHTMLSintacticos(){
        
    }
    
    public void generarHTMLSemanticos(){
        
    }
    
    public void generarHTMLTablaSimbolos(){
        
    }
    
    public void generarHTMLAST(){
        
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
