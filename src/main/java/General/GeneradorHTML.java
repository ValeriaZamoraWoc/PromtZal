/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package General;

import AST.Agente;
import AST.Comando;
import AST.Directiva;
import AST.Ejecutar;
import AST.Exportar;
import AST.Programa;
import AST.Variable;
import Entidades.ErrorLexico;
import Entidades.ErrorSemantico;
import Entidades.ErrorSintactico;
import Entidades.Simbolo;
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
public class GeneradorHTML {
    
    public void generarHTMLErroresLexicos(List<ErrorLexico> errores) {

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
                /*nombreArchivo + */"reporte_errores_" + fechaHora + ".html";

        boolean guardadoExitoso = false;

        try (FileWriter writer = new FileWriter(nombreArchivoGuardar)) {
            writer.write(html.toString());
            guardadoExitoso = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el reporte: " + nombreArchivoGuardar);
        }

        if (guardadoExitoso) {
            JOptionPane.showMessageDialog(null, "Reporte guardado como: " + nombreArchivoGuardar);
            abrirArchivo(nombreArchivoGuardar);
        }
    }
    
    public void generarHTMLErroresSintacticos(List<ErrorSintactico> errores) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='es'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Reporte de Errores Sintácticos</title>");
        html.append("<style>");
        html.append("* { box-sizing: border-box; margin: 0; padding: 0; }");
        html.append("body { font-family: 'Segoe UI', Arial, sans-serif; background: #322E2B; color: #EEE4DB; padding: 30px; text-align: left; }");
        html.append(".encabezado { margin-bottom: 20px; }");
        html.append(".encabezado h1 { font-size: 20px; font-weight: 600; color: #F0A8A9; margin-bottom: 4px; }");
        html.append("table { width: 100%; border-collapse: collapse; font-size: 13px; text-align: left; }");
        html.append("thead th { color: #F0A8A9; padding: 8px 12px; border-bottom: 1px solid #F0A8A9; font-weight: bold; text-align: left; }");
        html.append("tbody td { padding: 8px 12px; border-bottom: 1px solid #403B37; color: #EEE4DB; text-align: left; }");
        html.append("tbody tr:hover td { background: #3A3431; }");
        html.append(".numero { color: #9D928A; width: 50px; }");
        html.append(".lexema { color: #9AC4D7; font-family: Consolas, monospace; }");
        html.append(".descripcion { color: #EEE4DB; }");
        html.append(".posicion { color: #C8BCB3; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        html.append("<div class='encabezado'>");
        html.append("<h1>Reporte de Errores Sintácticos</h1>");
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
            ErrorSintactico error = errores.get(i);
            html.append("<tr>");
            html.append("<td class='numero'>").append(i).append("</td>");
            html.append("<td class='lexema'>").append(error.getLexema()).append("</td>");
            html.append("<td class='descripcion'>").append(error.getDescripcion()).append("</td>");
            html.append("<td class='posicion'>").append(error.getFila()).append("</td>");
            html.append("<td class='posicion'>").append(error.getColumna()).append("</td>");
            html.append("</tr>");
        }

        html.append("</tbody>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fechaHora = LocalDateTime.now().format(formatter);
        String nombreArchivoGuardar = "reporte_errores_sintacticos_" + fechaHora + ".html";
        boolean guardadoExitoso = false;

        try (FileWriter writer = new FileWriter(nombreArchivoGuardar)) {
            writer.write(html.toString());
            guardadoExitoso = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el reporte: " + nombreArchivoGuardar);
        }

        if (guardadoExitoso) {
            JOptionPane.showMessageDialog(null, "Reporte guardado como: " + nombreArchivoGuardar);
            abrirArchivo(nombreArchivoGuardar);
        }
    }
    
    public void generarHTMLErroresSemanticos(List<ErrorSemantico> errores) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='es'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Reporte de Errores Semánticos</title>");
        html.append("<style>");
        html.append("* { box-sizing: border-box; margin: 0; padding: 0; }");
        html.append("body { font-family: 'Segoe UI', Arial, sans-serif; background: #322E2B; color: #EEE4DB; padding: 30px; text-align: left; }");
        html.append(".encabezado { margin-bottom: 20px; }");
        html.append(".encabezado h1 { font-size: 20px; font-weight: 600; color: #F0A8A9; margin-bottom: 4px; }");
        html.append("table { width: 100%; border-collapse: collapse; font-size: 13px; text-align: left; }");
        html.append("thead th { color: #F0A8A9; padding: 8px 12px; border-bottom: 1px solid #F0A8A9; font-weight: bold; text-align: left; }");
        html.append("tbody td { padding: 8px 12px; border-bottom: 1px solid #403B37; color: #EEE4DB; text-align: left; }");
        html.append("tbody tr:hover td { background: #3A3431; }");
        html.append(".numero { color: #9D928A; width: 50px; }");
        html.append(".lexema { color: #9AC4D7; font-family: Consolas, monospace; }");
        html.append(".descripcion { color: #EEE4DB; }");
        html.append(".posicion { color: #C8BCB3; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        html.append("<div class='encabezado'>");
        html.append("<h1>Reporte de Errores Semánticos</h1>");
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
            ErrorSemantico error = errores.get(i);
            html.append("<tr>");
            html.append("<td class='numero'>").append(i).append("</td>");
            html.append("<td class='lexema'>").append(error.getLexema()).append("</td>");
            html.append("<td class='descripcion'>").append(error.getDescripcion()).append("</td>");
            html.append("<td class='posicion'>").append(error.getFila()).append("</td>");
            html.append("<td class='posicion'>").append(error.getColumna()).append("</td>");
            html.append("</tr>");
        }

        html.append("</tbody>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fechaHora = LocalDateTime.now().format(formatter);
        String nombreArchivoGuardar = "reporte_errores_semanticos_" + fechaHora + ".html";
        boolean guardadoExitoso = false;

        try (FileWriter writer = new FileWriter(nombreArchivoGuardar)) {
            writer.write(html.toString());
            guardadoExitoso = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el reporte: " + nombreArchivoGuardar);
        }

        if (guardadoExitoso) {
            JOptionPane.showMessageDialog(null, "Reporte guardado como: " + nombreArchivoGuardar);
            abrirArchivo(nombreArchivoGuardar);
        }
    }
    
    public void generarHTMLTablaSimbolos(List<Simbolo> simbolos) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='es'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Reporte de Tabla de Símbolos</title>");
        html.append("<style>");
        html.append("* { box-sizing: border-box; margin: 0; padding: 0; }");
        html.append("body { font-family: 'Segoe UI', Arial, sans-serif; background: #322E2B; color: #EEE4DB; padding: 30px; text-align: left; }");
        html.append(".encabezado { margin-bottom: 20px; }");
        html.append(".encabezado h1 { font-size: 20px; font-weight: 600; color: #F0A8A9; margin-bottom: 4px; }");
        html.append("table { width: 100%; border-collapse: collapse; font-size: 13px; text-align: left; }");
        html.append("thead th { color: #F0A8A9; padding: 8px 12px; border-bottom: 1px solid #F0A8A9; font-weight: bold; text-align: left; }");
        html.append("tbody td { padding: 8px 12px; border-bottom: 1px solid #403B37; color: #EEE4DB; text-align: left; }");
        html.append("tbody tr:hover td { background: #3A3431; }");
        html.append(".numero { color: #9D928A; width: 50px; }");
        html.append(".nombre { color: #9AC4D7; font-family: Consolas, monospace; font-weight: bold; }");
        html.append(".tipo { color: #E5C07B; }");
        html.append(".agente { color: #C8BCB3; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        html.append("<div class='encabezado'>");
        html.append("<h1>Reporte de Tabla de Símbolos</h1>");
        html.append("</div>");

        html.append("<table>");
        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>#</th>");
        html.append("<th>Nombre</th>");
        html.append("<th>Tipo</th>");
        html.append("<th>Agente</th>");
        html.append("</tr>");
        html.append("</thead>");
        html.append("<tbody>");

        for (int i = 0; i < simbolos.size(); i++) {
            Simbolo simbolo = simbolos.get(i);
            html.append("<tr>");
            html.append("<td class='numero'>").append(i).append("</td>");
            html.append("<td class='nombre'>").append(simbolo.getNombre()).append("</td>");
            html.append("<td class='tipo'>").append(simbolo.getTipo()).append("</td>");
            html.append("<td class='agente'>").append(simbolo.getAgente()).append("</td>"); // Asegúrate de que Agente tenga un toString() adecuado o usa un getter específico
            html.append("</tr>");
        }

        html.append("</tbody>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fechaHora = LocalDateTime.now().format(formatter);
        String nombreArchivoGuardar = "reporte_simbolos_" + fechaHora + ".html";
        boolean guardadoExitoso = false;

        try (FileWriter writer = new FileWriter(nombreArchivoGuardar)) {
            writer.write(html.toString());
            guardadoExitoso = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el reporte: " + nombreArchivoGuardar);
        }

        if (guardadoExitoso) {
            JOptionPane.showMessageDialog(null, "Reporte guardado como: " + nombreArchivoGuardar);
            abrirArchivo(nombreArchivoGuardar);
        }
    }
    
    public void generarHTMLAST(Programa programa) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='es'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>Reporte AST</title>");
        html.append("<style>");
        html.append("* { box-sizing: border-box; margin: 0; padding: 0; }");
        html.append("body { font-family: 'Segoe UI', Arial, sans-serif; background: #322E2B; color: #EEE4DB; padding: 30px; text-align: left; }");
        html.append(".encabezado { margin-bottom: 20px; }");
        html.append(".encabezado h1 { font-size: 20px; font-weight: 600; color: #F0A8A9; margin-bottom: 4px; }");
        html.append(".ast-tree { font-family: Consolas, monospace; font-size: 13px; line-height: 1.6; white-space: pre; background: #2A2624; padding: 20px; border-radius: 6px; border: 1px solid #403B37; overflow-x: auto; color: #9AC4D7; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        html.append("<div class='encabezado'>");
        html.append("<h1>Reporte de Árbol Sintáctico Abstracto (AST)</h1>");
        html.append("</div>");

        html.append("<div class='ast-tree'>");
        if (programa != null) {
            StringBuilder sbArbol = new StringBuilder();
            generarTextoPrograma(programa, "", true, sbArbol);
            html.append(sbArbol.toString().replace("<", "&lt;").replace(">", "&gt;"));
        } else {
            html.append("El programa está vacío.");
        }
        html.append("</div>");

        html.append("</body>");
        html.append("</html>");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fechaHora = LocalDateTime.now().format(formatter);
        String nombreArchivoGuardar = "reporte_ast_" + fechaHora + ".html";
        boolean guardadoExitoso = false;

        try (FileWriter writer = new FileWriter(nombreArchivoGuardar)) {
            writer.write(html.toString());
            guardadoExitoso = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el reporte AST: " + nombreArchivoGuardar);
        }

        if (guardadoExitoso) {
            JOptionPane.showMessageDialog(null, "Reporte AST guardado como: " + nombreArchivoGuardar);
            abrirArchivo(nombreArchivoGuardar);
        }
    }

    private void generarTextoPrograma(Programa programa, String indent, boolean esUltimo, StringBuilder sb) {
        sb.append(indent);
        if (indent.length() > 0) {
            sb.append(esUltimo ? "└── " : "├── ");
        }
        sb.append("Programa\n");

        String nuevoIndent = indent + (indent.length() == 0 ? "" : (esUltimo ? "    " : "│   "));

        // Directivas
        List<Directiva> directivas = programa.getDirectivas();
        if (directivas != null && !directivas.isEmpty()) {
            for (int i = 0; i < directivas.size(); i++) {
                boolean ultimo = (i == directivas.size() - 1) && (programa.getAgentes() == null || programa.getAgentes().isEmpty()) && (programa.getEjecuciones() == null || programa.getEjecuciones().isEmpty()) && (programa.getExportar() == null || programa.getExportar().getIds().isEmpty());
                Directiva d = directivas.get(i);
                sb.append(nuevoIndent).append(ultimo ? "└── " : "├── ").append("Directiva: ").append(d.getNombre()).append(" = \"").append(d.getCadena()).append("\"\n");
            }
        }

        // Agentes
        List<Agente> agentes = programa.getAgentes();
        if (agentes != null && !agentes.isEmpty()) {
            for (int i = 0; i < agentes.size(); i++) {
                boolean ultimoAgente = (i == agentes.size() - 1) && (programa.getEjecuciones() == null || programa.getEjecuciones().isEmpty()) && (programa.getExportar() == null || programa.getExportar().getIds().isEmpty());
                Agente a = agentes.get(i);
                sb.append(nuevoIndent).append(ultimoAgente ? "└── " : "├── ").append("Agente: ").append(a.getId()).append("\n");

                String agenteIndent = nuevoIndent + (ultimoAgente ? "    " : "│   ");

                // Contexto
                if (a.getContexto() != null) {
                    sb.append(agenteIndent).append("├── Contexto = \"").append(a.getContexto().getCadena()).append("\"\n");
                }

                // Variables
                List<Variable> variables = a.getVariables();
                if (variables != null && !variables.isEmpty()) {
                    sb.append(agenteIndent).append("├── Variables\n");
                    for (int v = 0; v < variables.size(); v++) {
                        Variable var = variables.get(v);
                        boolean ultimaVar = (v == variables.size() - 1) && (a.getComandos() == null || a.getComandos().isEmpty());
                        sb.append(agenteIndent).append(ultimaVar ? "│   └── " : "│   ├── ").append("Variable: ").append(var.getId()).append("\n");
                    }
                }

                // Comandos
                List<Comando> comandos = a.getComandos();
                if (comandos != null && !comandos.isEmpty()) {
                    sb.append(agenteIndent).append("└── Comandos\n");
                    for (int c = 0; c < comandos.size(); c++) {
                        Comando com = comandos.get(c);
                        boolean ultimoCom = (c == comandos.size() - 1);
                        sb.append(agenteIndent).append(ultimoCom ? "    └── " : "    ├── ").append("Comando: ").append(com.getNombre()).append(" -> ").append(com.getId()).append("\n");
                    }
                }
            }
        }

        // Ejecuciones
        List<Ejecutar> ejecuciones = programa.getEjecuciones();
        if (ejecuciones != null && !ejecuciones.isEmpty()) {
            for (int i = 0; i < ejecuciones.size(); i++) {
                boolean ultimo = (i == ejecuciones.size() - 1) && (programa.getExportar() == null || programa.getExportar().getIds().isEmpty());
                Ejecutar e = ejecuciones.get(i);
                sb.append(nuevoIndent).append(ultimo ? "└── " : "├── ").append("Ejecutar: ").append(e.getId()).append("\n");
            }
        }

        // Exportar
        Exportar exportar = programa.getExportar();
        if (exportar != null && exportar.getIds() != null && !exportar.getIds().isEmpty()) {
            sb.append(nuevoIndent).append("└── Exportar: ").append(String.join(", ", exportar.getIds())).append("\n");
        }
    }
    
    private void abrirArchivo(String nombre) {
        try {
            File archivoHtml = new File(nombre); 

            if (archivoHtml.exists()) {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                    Desktop.getDesktop().open(archivoHtml);
                } else {
                    JOptionPane.showMessageDialog(null, "La función de abrir archivos no está soportada en este sistema.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo HTML no existe: " + archivoHtml.getAbsolutePath());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir el archivo HTML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
