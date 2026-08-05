/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AnalizadorLexico;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author cacerola
 */
public class LectorArchivo {

    public String leerArchivo() {
        StringBuilder texto = new StringBuilder();
        JFileChooser buscador = new JFileChooser();
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.pz)", "pz");
        buscador.setFileFilter(filtro);

        int resultado = buscador.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            
            File archivoSeleccionado = buscador.getSelectedFile();

            try (FileReader fr = new FileReader(archivoSeleccionado);
                 BufferedReader br = new BufferedReader(fr)) {

                String linea;
                
                //que hacer con la linea
                while ((linea = br.readLine()) != null) {
                    texto.append(linea).append('\n');
                }

            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }

        } else {
            System.out.println("El usuario canceló la selección de archivo.");
        }
        return texto.toString();
    }
}
