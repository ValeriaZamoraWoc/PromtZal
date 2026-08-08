/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reporte;

import Entidades.ErrorLexico;
import Entidades.Token;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author cacerola
 */
public class GeneradorHTML {
    
    private String nombreArchivo="";

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    
    public void generarHTMLTokens(List<Token> tokens){
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<title>Reporte de Tokens</title>");
        html.append("</head>");
        html.append("<body>");

        html.append("<h1>Reporte de Tokens</h1>");

        //armar tabla
        html.append("<table>");
        html.append("<tr>");
        html.append("<th>#</th>");
        html.append("<th>Lexema</th>");
        html.append("<th>Tipo</th>");
        html.append("<th>Fila</th>");
        html.append("<th>Columna</th>");
        html.append("</tr>");
        
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            html.append("<tr>");
            html.append("<td>").append(i).append("</td>");
            html.append("<td>").append(token.getLexema()).append("</td>");
            html.append("<td>").append(token.getTipo().name()).append("</td>");
            html.append("<td>").append(token.getFila()).append("</td>");
            html.append("<td>").append(token.getColumna()).append("</td>");
            html.append("</tr>");
        }
        
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fechaHora = LocalDateTime.now().format(formatter);

        String nombreArchivoGuardar = nombreArchivo +"reporte_tokens_" + fechaHora + ".html";

        try (FileWriter writer = new FileWriter(nombreArchivoGuardar)) {
            writer.write(html.toString());
            System.out.println("Reporte guardado como: " + nombreArchivoGuardar);
        } catch (IOException e) {
            System.err.println("Error al guardar el reporte: " + e.getMessage());
        }
    }
    
    public void generarHTMLErrores(List<ErrorLexico> errores){
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<title>Reporte de Errores</title>");
        html.append("</head>");
        html.append("<body>");

        html.append("<h1>Reporte de Errores</h1>");

        //armar tabla
        html.append("<table>");
        html.append("<tr>");
        html.append("<th>#</th>");
        html.append("<th>Lexema</th>");
        html.append("<th>Descripción</th>");
        html.append("<th>Fila</th>");
        html.append("<th>Columna</th>");
        html.append("</tr>");
        
        for (int i = 0; i < errores.size(); i++) {
            ErrorLexico error = errores.get(i);

            html.append("<tr>");
            html.append("<td>").append(i).append("</td>");
            html.append("<td>").append(error.getLexema()).append("</td>");
            html.append("<td>").append(error.getDescripcion()).append("</td>");
            html.append("<td>").append(error.getFila()).append("</td>");
            html.append("<td>").append(error.getColumna()).append("</td>");
            html.append("</tr>");
        }
        
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fechaHora = LocalDateTime.now().format(formatter);

        String nombreArchivoGuardar = nombreArchivo+"reporte_errores_" + fechaHora + ".html";

        try (FileWriter writer = new FileWriter(nombreArchivoGuardar)) {
            writer.write(html.toString());
            System.out.println("Reporte guardado como: " + nombreArchivoGuardar);
        } catch (IOException e) {
            System.err.println("Error al guardar el reporte: " + e.getMessage());
        }
    }
}
