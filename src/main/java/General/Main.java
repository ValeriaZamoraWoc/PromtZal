/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
<<<<<<< HEAD
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package General;

import AST.Programa;
import Entidades.TipoToken;
import Entidades.Token;
import Lexer.AFD_JFlex;
import Parser.Parser;
import Semantica.AnalizadorSemantico;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author cacerola
 */
public class Main {
    
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de entrada");

        fileChooser.setFileFilter(
            new FileNameExtensionFilter("Archivos de texto (*.pz)", "pz")
        );

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();

            System.out.println("Archivo seleccionado: "
                    + archivoSeleccionado.getAbsolutePath());

            try (BufferedReader br = new BufferedReader(
                    new FileReader(archivoSeleccionado))) {

                //abre archivos
                AFD_JFlex lexer = new AFD_JFlex(br);

                //genera programa
                Parser parser = new Parser(lexer);
                Programa programaAnalizado = parser.getPrograma();

                //hace análisis semantico
                AnalizadorSemantico semantico = new AnalizadorSemantico(programaAnalizado);
                semantico.analizar();

                //genera promtzal
                GeneradorPromtZal generador = new GeneradorPromtZal(programaAnalizado);
                System.out.println(generador.getPromtzal());
                
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Operacion cancelada por el usuario.");
        }
    }

}
