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

    
    public void generarHTMLTokens(List<Token> tokens) {

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='es'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Reporte de Tokens</title>");

        html.append("<style>");

        html.append("* {");
        html.append("    box-sizing: border-box;");
        html.append("    margin: 0;");
        html.append("    padding: 0;");
        html.append("}");

        html.append("body {");
        html.append("    font-family: 'Segoe UI', Arial, sans-serif;");
        html.append("    background: #322E2B;");
        html.append("    color: #EEE4DB;");
        html.append("    min-height: 100vh;");
        html.append("    padding: 40px;");
        html.append("}");

        html.append(".contenedor {");
        html.append("    max-width: 1200px;");
        html.append("    margin: auto;");
        html.append("}");

        html.append(".encabezado {");
        html.append("    background: #3A3431;");
        html.append("    border: 1px solid #9AC4D7;");
        html.append("    border-radius: 24px;");
        html.append("    padding: 30px 35px;");
        html.append("    margin-bottom: 25px;");
        html.append("    box-shadow: 0 8px 25px rgba(0,0,0,0.25);");
        html.append("}");

        html.append(".encabezado h1 {");
        html.append("    color: #9AC4D7;");
        html.append("    font-size: 30px;");
        html.append("    margin-bottom: 8px;");
        html.append("}");

        html.append(".encabezado p {");
        html.append("    color: #C8BCB3;");
        html.append("    font-size: 15px;");
        html.append("}");

        html.append(".icono {");
        html.append("    font-size: 28px;");
        html.append("    margin-right: 8px;");
        html.append("}");

        html.append(".tabla-contenedor {");
        html.append("    background: #3A3431;");
        html.append("    border: 1px solid #9AC4D7;");
        html.append("    border-radius: 24px;");
        html.append("    padding: 20px;");
        html.append("    overflow-x: auto;");
        html.append("    box-shadow: 0 8px 25px rgba(0,0,0,0.25);");
        html.append("}");

        html.append("table {");
        html.append("    width: 100%;");
        html.append("    border-collapse: separate;");
        html.append("    border-spacing: 0;");
        html.append("    overflow: hidden;");
        html.append("}");

        html.append("thead th {");
        html.append("    background: #9AC4D7;");
        html.append("    color: #322E2B;");
        html.append("    font-size: 14px;");
        html.append("    font-weight: 700;");
        html.append("    padding: 15px 18px;");
        html.append("    text-align: left;");
        html.append("}");

        html.append("thead th:first-child {");
        html.append("    border-radius: 14px 0 0 0;");
        html.append("}");

        html.append("thead th:last-child {");
        html.append("    border-radius: 0 14px 0 0;");
        html.append("}");

        html.append("tbody td {");
        html.append("    padding: 14px 18px;");
        html.append("    color: #EEE4DB;");
        html.append("    background: #403B37;");
        html.append("    border-bottom: 1px solid #504943;");
        html.append("    font-size: 14px;");
        html.append("}");

        html.append("tbody tr:hover td {");
        html.append("    background: #4A4541;");
        html.append("}");

        html.append("tbody tr:last-child td {");
        html.append("    border-bottom: none;");
        html.append("}");

        html.append(".numero {");
        html.append("    color: #9AC4D7;");
        html.append("    font-weight: 700;");
        html.append("    width: 60px;");
        html.append("}");

        html.append(".lexema {");
        html.append("    color: #F0A8A9;");
        html.append("    font-family: Consolas, monospace;");
        html.append("}");

        html.append(".tipo {");
        html.append("    color: #9AC4D7;");
        html.append("    font-weight: 600;");
        html.append("}");

        html.append(".posicion {");
        html.append("    color: #C8BCB3;");
        html.append("}");

        html.append(".pie {");
        html.append("    text-align: center;");
        html.append("    margin-top: 25px;");
        html.append("    color: #9D928A;");
        html.append("    font-size: 13px;");
        html.append("}");

        html.append("</style>");
        html.append("</head>");

        html.append("<body>");

        html.append("<div class='contenedor'>");

        html.append("<div class='encabezado'>");
        html.append("<h1><span class='icono'>★</span>Reporte de Tokens</h1>");
        html.append("<p>Listado de tokens identificados durante el análisis léxico.</p>");
        html.append("</div>");

        html.append("<div class='tabla-contenedor'>");

        html.append("<table>");

        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>#</th>");
        html.append("<th>Lexema</th>");
        html.append("<th>Tipo</th>");
        html.append("<th>Fila</th>");
        html.append("<th>Columna</th>");
        html.append("</tr>");
        html.append("</thead>");

        html.append("<tbody>");

        for (int i = 0; i < tokens.size(); i++) {

            Token token = tokens.get(i);

            html.append("<tr>");

            html.append("<td class='numero'>")
                    .append(i)
                    .append("</td>");

            html.append("<td class='lexema'>")
                    .append(token.getLexema())
                    .append("</td>");

            html.append("<td class='tipo'>")
                    .append(token.getTipo().name())
                    .append("</td>");

            html.append("<td class='posicion'>")
                    .append(token.getFila())
                    .append("</td>");

            html.append("<td class='posicion'>")
                    .append(token.getColumna())
                    .append("</td>");

            html.append("</tr>");
        }

        html.append("</tbody>");
        html.append("</table>");

        html.append("</div>");

        html.append("<div class='pie'>");
        html.append("Reporte generado automáticamente por el analizador léxico.");
        html.append("</div>");

        html.append("</div>");

        html.append("</body>");
        html.append("</html>");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        String fechaHora =
                LocalDateTime.now().format(formatter);

        String nombreArchivoGuardar =
                nombreArchivo + "reporte_tokens_" + fechaHora + ".html";

        try (FileWriter writer =
                new FileWriter(nombreArchivoGuardar)) {

            writer.write(html.toString());

            System.out.println(
                    "Reporte guardado como: "
                    + nombreArchivoGuardar
            );

        } catch (IOException e) {

            System.err.println(
                    "Error al guardar el reporte: "
                    + e.getMessage()
            );
        }
    }

    public void generarHTMLErrores(List<ErrorLexico> errores) {

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='es'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Reporte de Errores</title>");

        html.append("<style>");

        html.append("* {");
        html.append("    box-sizing: border-box;");
        html.append("    margin: 0;");
        html.append("    padding: 0;");
        html.append("}");

        html.append("body {");
        html.append("    font-family: 'Segoe UI', Arial, sans-serif;");
        html.append("    background: #322E2B;");
        html.append("    color: #EEE4DB;");
        html.append("    min-height: 100vh;");
        html.append("    padding: 40px;");
        html.append("}");

        html.append(".contenedor {");
        html.append("    max-width: 1200px;");
        html.append("    margin: auto;");
        html.append("}");

        html.append(".encabezado {");
        html.append("    background: #3A3431;");
        html.append("    border: 1px solid #F0A8A9;");
        html.append("    border-radius: 24px;");
        html.append("    padding: 30px 35px;");
        html.append("    margin-bottom: 25px;");
        html.append("    box-shadow: 0 8px 25px rgba(0,0,0,0.25);");
        html.append("}");

        html.append(".encabezado h1 {");
        html.append("    color: #F0A8A9;");
        html.append("    font-size: 30px;");
        html.append("    margin-bottom: 8px;");
        html.append("}");

        html.append(".encabezado p {");
        html.append("    color: #C8BCB3;");
        html.append("    font-size: 15px;");
        html.append("}");

        html.append(".icono {");
        html.append("    font-size: 28px;");
        html.append("    margin-right: 8px;");
        html.append("}");

        html.append(".tabla-contenedor {");
        html.append("    background: #3A3431;");
        html.append("    border: 1px solid #F0A8A9;");
        html.append("    border-radius: 24px;");
        html.append("    padding: 20px;");
        html.append("    overflow-x: auto;");
        html.append("    box-shadow: 0 8px 25px rgba(0,0,0,0.25);");
        html.append("}");

        html.append("table {");
        html.append("    width: 100%;");
        html.append("    border-collapse: separate;");
        html.append("    border-spacing: 0;");
        html.append("}");

        html.append("thead th {");
        html.append("    background: #F0A8A9;");
        html.append("    color: #322E2B;");
        html.append("    font-size: 14px;");
        html.append("    font-weight: 700;");
        html.append("    padding: 15px 18px;");
        html.append("    text-align: left;");
        html.append("}");

        html.append("thead th:first-child {");
        html.append("    border-radius: 14px 0 0 0;");
        html.append("}");

        html.append("thead th:last-child {");
        html.append("    border-radius: 0 14px 0 0;");
        html.append("}");

        html.append("tbody td {");
        html.append("    padding: 14px 18px;");
        html.append("    color: #EEE4DB;");
        html.append("    background: #403B37;");
        html.append("    border-bottom: 1px solid #504943;");
        html.append("    font-size: 14px;");
        html.append("}");

        html.append("tbody tr:hover td {");
        html.append("    background: #4A4541;");
        html.append("}");

        html.append("tbody tr:last-child td {");
        html.append("    border-bottom: none;");
        html.append("}");

        html.append(".numero {");
        html.append("    color: #F0A8A9;");
        html.append("    font-weight: 700;");
        html.append("    width: 60px;");
        html.append("}");

        html.append(".lexema {");
        html.append("    color: #9AC4D7;");
        html.append("    font-family: Consolas, monospace;");
        html.append("}");

        html.append(".descripcion {");
        html.append("    color: #EEE4DB;");
        html.append("}");

        html.append(".posicion {");
        html.append("    color: #C8BCB3;");
        html.append("}");

        html.append(".pie {");
        html.append("    text-align: center;");
        html.append("    margin-top: 25px;");
        html.append("    color: #9D928A;");
        html.append("    font-size: 13px;");
        html.append("}");

        html.append("</style>");
        html.append("</head>");

        html.append("<body>");

        html.append("<div class='contenedor'>");

        html.append("<div class='encabezado'>");
        html.append("<h1><span class='icono'>✖</span>Reporte de Errores</h1>");
        html.append("<p>Errores léxicos encontrados durante el análisis del archivo.</p>");
        html.append("</div>");

        html.append("<div class='tabla-contenedor'>");

        html.append("<table>");

        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>#</th>");
        html.append("<th>Lexema</th>");
        html.append("<th>Descripción</th>");
        html.append("<th>Fila</th>");
        html.append("<th>Columna</th>");
        html.append("</tr>");
        html.append("</thead>");

        html.append("<tbody>");

        for (int i = 0; i < errores.size(); i++) {

            ErrorLexico error = errores.get(i);

            html.append("<tr>");

            html.append("<td class='numero'>")
                    .append(i)
                    .append("</td>");

            html.append("<td class='lexema'>")
                    .append(error.getLexema())
                    .append("</td>");

            html.append("<td class='descripcion'>")
                    .append(error.getDescripcion())
                    .append("</td>");

            html.append("<td class='posicion'>")
                    .append(error.getFila())
                    .append("</td>");

            html.append("<td class='posicion'>")
                    .append(error.getColumna())
                    .append("</td>");

            html.append("</tr>");
        }

        html.append("</tbody>");
        html.append("</table>");

        html.append("</div>");

        html.append("<div class='pie'>");
        html.append("Reporte generado automáticamente por el analizador léxico.");
        html.append("</div>");

        html.append("</div>");

        html.append("</body>");
        html.append("</html>");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        String fechaHora =
                LocalDateTime.now().format(formatter);

        String nombreArchivoGuardar =
                nombreArchivo + "reporte_errores_" + fechaHora + ".html";

        try (FileWriter writer =
                new FileWriter(nombreArchivoGuardar)) {

            writer.write(html.toString());

            System.out.println(
                    "Reporte guardado como: "
                    + nombreArchivoGuardar
            );

        } catch (IOException e) {

            System.err.println(
                    "Error al guardar el reporte: "
                    + e.getMessage()
            );
        }
    }


}
