/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reporte;

import Entidades.ErrorLexico;
import Entidades.Token;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author cacerola
 */
public class GeneradorDOT {
    
    public void generarArchivoDOT(String texto, String nombreArchivo) throws InterruptedException{

        String nombreArchivoGuardar =nombreArchivo+".dot";
        try{
            try (FileWriter writer = new FileWriter(nombreArchivoGuardar)) {
                writer.write(texto);
            }
            
            ProcessBuilder pb = new ProcessBuilder("dot","-Tpng",nombreArchivoGuardar,"-o",nombreArchivo+".png");

            Process proceso = pb.start();

            int resultado = proceso.waitFor();

            if (resultado == 0) {
                JOptionPane.showMessageDialog(null,"Imagen generada correctamente");
                File imagen = new File(nombreArchivo + ".png");

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(imagen);
                }
                
            } else {
                JOptionPane.showMessageDialog(null,"Error en la generación de la imagen");
            }
            
        }catch (IOException e) {

            System.err.println(
                    "Error al guardar el reporte: "
                    + e.getMessage()
            );
        }
    }
}
