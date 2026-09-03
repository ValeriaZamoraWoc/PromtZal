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
import javax.swing.JOptionPane;

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
        html.append("* { box-sizing: border-box; margin: 0; padding: 0; }");
        html.append("body { font-family: 'Segoe UI', Arial, sans-serif; background: #322E2B; color: #EEE4DB; padding: 30px; text-align: left; }");
        html.append(".encabezado { margin-bottom: 20px; }");
        html.append(".encabezado h1 { font-size: 20px; font-weight: 600; color: #9AC4D7; margin-bottom: 4px; }");
        html.append(".encabezado p { font-size: 13px; color: #9D928A; }");
        html.append("table { width: 100%; border-collapse: collapse; font-size: 13px; text-align: left; }");
        html.append("thead th { color: #9AC4D7; padding: 8px 12px; border-bottom: 1px solid #9AC4D7; font-weight: bold; text-align: left; }");
        html.append("tbody td { padding: 8px 12px; border-bottom: 1px solid #403B37; color: #EEE4DB; text-align: left; }");
        html.append("tbody tr:hover td { background: #3A3431; }");
        html.append(".numero { color: #9D928A; width: 50px; }");
        html.append(".lexema { color: #F0A8A9; font-family: Consolas, monospace; }");
        html.append(".tipo { color: #9AC4D7; }");
        html.append(".posicion { color: #C8BCB3; }");
        html.append(".pie { margin-top: 20px; color: #9D928A; font-size: 12px; text-align: left; }");
        html.append("</style>");
        html.append("</head>");

        html.append("<body>");

        html.append("<div class='encabezado'>");
        html.append("<h1>Reporte de Tokens</h1>");
        html.append("</div>");

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

    public void generarHTMLEstadisticas(Integer[] contadores, int cantTokens, int cantLineas, int cantErrores) {

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='es'>");

        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Reporte de Estadísticas</title>");

        html.append("<style>");

        html.append("* { box-sizing: border-box; margin: 0; padding: 0; }");

        html.append("body { "+ "font-family: 'Segoe UI', Arial, sans-serif; "+ "background: #322E2B; "+ "color: #EEE4DB; "
                + "padding: 30px; "+ "text-align: left; "+ "}");

        html.append(".encabezado { margin-bottom: 25px; }");

        html.append(".encabezado h1 { "+ "font-size: 22px; "+ "font-weight: 600; "+ "color: #C8A2C8; "+ "margin-bottom: 4px; "
                + "}");

        html.append(".encabezado p { "+ "font-size: 13px; "+ "color: #9D928A; "+ "}");

        html.append(".estadisticas { "+ "margin-top: 25px; "+ "}");

        html.append(".estadisticas h2 { "+ "font-size: 17px; "+ "font-weight: 600; "+ "color: #C8A2C8; "
                + "margin-bottom: 12px; "+ "}");

        html.append(".tabla-estadisticas { "+ "width: 100%; "+ "border-collapse: collapse; "+ "font-size: 13px; "+ "}");

        html.append(".tabla-estadisticas thead th { "+ "color: #C8A2C8; "+ "padding: 9px 12px; "+ "border-bottom: 1px solid #C8A2C8; "
                + "font-weight: bold; "+ "text-align: left; "+ "}");

        html.append(".tabla-estadisticas tbody td { "+ "padding: 9px 12px; "+ "border-bottom: 1px solid #403B37; "+ "color: #EEE4DB; "
                + "}");

        html.append(".tabla-estadisticas tbody tr:hover td { "+ "background: #3A3431; "+ "}");

        html.append(".estadistica-tipo { "+ "color: #C8A2C8; "+ "font-weight: 500; "+ "}");

        html.append(".estadistica-cantidad { "+ "color: #C8A2C8; "+ "font-weight: bold; "+ "text-align: center; "
                + "width: 150px; "+ "}");

        html.append(".tabla-resumen { "+ "margin-top: 10px; "+ "}");

        html.append(".resumen-concepto { "+ "color: #EEE4DB; "+ "font-weight: 500; "+ "}");

        html.append(".resumen-cantidad { "+"color: #C8A2C8; "+ "font-weight: bold; " + "text-align: center; "+ "width: 150px; "+ "}");

        html.append(".pie { "+"margin-top: 25px; "+"color: #9D928A; "+"font-size: 12px; "+"text-align: left; "+ "}");

        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        html.append("<div class='encabezado'>");
        html.append("<h1>Reporte de Estadísticas</h1>");
        html.append("<p>Resumen del análisis léxico del documento</p>");
        html.append("</div>");
        html.append("<div class='estadisticas'>");

        html.append("<h2>Tokens encontrados</h2>");

        html.append("<table class='tabla-estadisticas'>");

        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>Tipo de Token</th>");
        html.append("<th class='estadistica-cantidad'>Cantidad</th>");
        html.append("</tr>");
        html.append("</thead>");

        html.append("<tbody>");

        String[] tipos = {
            "DIRECTIVAS",
            "PALABRA RESERVADA",
            "COMANDO DE IA",
            "CONECTORES",
            "ENTERO",
            "DECIMAL",
            "OPERADOR",
            "IDENTIFICADOR",
            "CADENA"
        };

        for (int i = 0; i < tipos.length; i++) {

            html.append("<tr>");

            html.append("<td class='estadistica-tipo'>").append(tipos[i]).append("</td>");

            html.append("<td class='estadistica-cantidad'>").append(contadores[i]).append("</td>");

            html.append("</tr>");
        }

        html.append("</tbody>");
        html.append("</table>");

        html.append("</div>");

        html.append("<div class='estadisticas'>");

        html.append("<h2>Resumen del documento</h2>");

        html.append("<table class='tabla-estadisticas tabla-resumen'>");

        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>Descripción</th>");
        html.append("<th class='resumen-cantidad'>Cantidad</th>");
        html.append("</tr>");
        html.append("</thead>");

        html.append("<tbody>");

        html.append("<tr>");
        html.append("<td class='resumen-concepto'>Cantidad de Tokens</td>");
        html.append("<td class='resumen-cantidad'>").append(cantTokens).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td class='resumen-concepto'>Cantidad de Líneas</td>");
        html.append("<td class='resumen-cantidad'>").append(cantLineas).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td class='resumen-concepto'>Cantidad de Errores</td>");
        html.append("<td class='resumen-cantidad'>").append(cantErrores).append("</td>");
        html.append("</tr>");

        html.append("</tbody>");
        html.append("</table>");

        html.append("</div>");

        html.append("</body>");
        html.append("</html>");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        String fechaHora =
                LocalDateTime.now().format(formatter);

        String nombreArchivoGuardar =
                nombreArchivo + "reporte_estadisticas_" + fechaHora + ".html";

        try (FileWriter writer =
                new FileWriter(nombreArchivoGuardar)) {

            writer.write(html.toString());

            JOptionPane.showMessageDialog(null, "Reporte guardado como: "+ nombreArchivoGuardar);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,  "Error al guardar el reporte: "+ nombreArchivoGuardar);

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
        html.append("* { box-sizing: border-box; margin: 0; padding: 0; }");
        html.append("body { font-family: 'Segoe UI', Arial, sans-serif; background: #322E2B; color: #EEE4DB; padding: 30px; text-align: left; }");
        html.append(".encabezado { margin-bottom: 20px; }");
        html.append(".encabezado h1 { font-size: 20px; font-weight: 600; color: #F0A8A9; margin-bottom: 4px; }");
        html.append(".encabezado p { font-size: 13px; color: #9D928A; }");
        html.append("table { width: 100%; border-collapse: collapse; font-size: 13px; text-align: left; }");
        html.append("thead th { color: #F0A8A9; padding: 8px 12px; border-bottom: 1px solid #F0A8A9; font-weight: bold; text-align: left; }");
        html.append("tbody td { padding: 8px 12px; border-bottom: 1px solid #403B37; color: #EEE4DB; text-align: left; }");
        html.append("tbody tr:hover td { background: #3A3431; }");
        html.append(".numero { color: #9D928A; width: 50px; }");
        html.append(".lexema { color: #9AC4D7; font-family: Consolas, monospace; }");
        html.append(".descripcion { color: #EEE4DB; }");
        html.append(".posicion { color: #C8BCB3; }");
        html.append(".pie { margin-top: 20px; color: #9D928A; font-size: 12px; text-align: left; }");
        html.append("</style>");
        html.append("</head>");

        html.append("<body>");

        html.append("<div class='encabezado'>");
        html.append("<h1>Reporte de Errores</h1>");
        html.append("</div>");

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
