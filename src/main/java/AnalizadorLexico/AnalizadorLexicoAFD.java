/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AnalizadorLexico;

import AFD.AFD;
import AFD.Estado;
import AFD.Transicion;
import Entidades.ErrorLexico;
import Entidades.Token;
import Enum.ComandoIA;
import Enum.Conector;
import Enum.Directiva;
import Enum.PalabraReservada;
import Enum.TipoToken;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cacerola
 */
public class AnalizadorLexicoAFD {
    private int indice;
    private String entrada;
    private int fila, columna;
    private List<Token> tokens;
    private List<ErrorLexico> errores;
    private AFD afd;
    private StringBuilder recorridoDot;
    
    
    public AnalizadorLexicoAFD(){
        afd = new AFD();
        afd.construirAFD();
    }
    
    public void analizarTexto(String texto){
        recorridoDot = new StringBuilder();
        indice =0;
        entrada = texto;
        fila= 1;
        columna=1;
        tokens = new ArrayList<>();
        errores = new ArrayList<>();
        String lexema = "";
        int colTemporal=0;
        
        //el estado actual empezará con q0
        Estado estadoActual= afd.getEstadoInicial();
        
        //mientras el índice vaya dentro de la entrada
        while(indice < entrada.length()){
            char c = entrada.charAt(indice);
            
            if(lexema.isEmpty()){
                colTemporal = columna;
            }
            //se verifica si hay una transicion con el char como valor valido
            Transicion transicion = afd.buscarTransicion(estadoActual, c);
            
            if(transicion != null){
                //si no es nulo, se toma el nuevo estado como el actual y se acumula el lexema
                agregarRecorrido(transicion);
                
                estadoActual = transicion.getDestino();
                lexema += c;
                
                indice ++;
                
                if(c == '\n'){
                    fila++;
                    columna = 1;
                }else{
                    columna++;
                }
                
            }else{
                //si es nulo (no existe transicion) verifica si el estado actual es de aceptacion
                if(estadoActual.esAceptacion()){
                    //mira si no es un número mal escrito
                    if((estadoActual.getNombre().equals("qINT")|| estadoActual.getNombre().equals("qDOB"))&& !esSeparador(c)){
                        String error = obtenerLexemaInvalido(lexema,c);
                        agregarError(error,"Número mal escrito",fila,colTemporal);
                    }else if(estadoActual.getNombre().equals("qID")&& !esSeparador(c)){
                        String error = obtenerLexemaInvalido(lexema,c);
                        agregarError(error,"Identificador no valido",fila,colTemporal);
                    }else{
                        determinarToken(estadoActual.getNombre(),lexema,colTemporal);
                    }
                }
                //si vuelve a q0, busca si es + o =
                else if(estadoActual == afd.getEstadoInicial()){
                    if(lexema.equals("")){
                        // Caracteres que se pueden ignorar
                        if(Character.isWhitespace(c)){
                            indice++;
                            if(c == '\n'){
                                fila++;
                                columna = 1;
                            }else{
                                columna++;
                            }
                            continue;
                        }
                        // Caracteres válidos que no generan token
                        else if(c == '{' || c == '}' || c == '(' || c == ')'){
                            indice++;
                            columna++;
                            continue;
                        }
                        // Cualquier otro carácter es un error
                        else{
                            String error = obtenerLexemaInvalido(lexema,c);
                            agregarError(error,"Carácter no reconocido",fila,columna);
                            continue;
                        }
                        
                    }else if(lexema.equals("+")){
                        agregarToken(TipoToken.OPERADOR, "+", fila,columna);
                    }else if(lexema.equals("=")){
                        agregarToken(TipoToken.OPERADOR, "=", fila,columna);
                    }else if(!(lexema.equals("{")||lexema.equals("}")||lexema.equals("(")||lexema.equals(")")||lexema.equals(","))){
                        agregarError(lexema,"Carácter no reconocido",fila,colTemporal);
                    }
                }
                //manda error
                else{
                    determinarError(estadoActual.getNombre(), lexema, colTemporal);
                }
                lexema="";
                estadoActual = afd.getEstadoInicial();
            }
            //evita que, al estar al lado de otra transicion correcta, lo absorba
            if(c == '{' || c == '}' || c == '(' || c == ')'){
                lexema ="";
                estadoActual = afd.getEstadoInicial();
            }
        }
        
        //último lexema
        if (!lexema.isEmpty()) {

            if (estadoActual.esAceptacion()) {
                determinarToken(estadoActual.getNombre(),lexema,colTemporal);
            } else {
                determinarError(estadoActual.getNombre(),lexema,colTemporal);
            }
        }
    }
    
    private boolean esSeparador(char c){
        return Character.isWhitespace(c)
                || c == '{'
                || c == '}'
                || c == '('
                || c == ')'
                || c == '+'
                || c == '='
                || c == '"'
                || c == '/'
                || c == ';'
                || c == ',';
    }
    
    private void determinarToken(String nombreEstado, String lexema, int columna){
        switch(nombreEstado){
            case"qID"->{
                leerIdentificador(lexema, columna);
            }
            case"qINT"->{
                agregarToken(TipoToken.ENTERO, lexema, fila, columna);
            }
            case"qDOB"->{
                agregarToken(TipoToken.DECIMAL, lexema, fila, columna);
            }
            case"qDIRECTIVA"->{
                if(Directiva.esDirectiva(lexema)){
                    String comando = Directiva.getDirectivaLexema(lexema);
                    agregarToken(TipoToken.DIRECTIVA, comando, fila,columna);
                }else{
                    agregarError(lexema, "Directiva no válida", fila, columna);
                }
            }
            case"qFINCADENA"->{
                agregarToken(TipoToken.CADENA, lexema, fila,columna);
            }
            case"qFINCOMENTARIO"->{
                //comentarios no generan token
            }
        }
    }
    
    private void determinarError(String nombreEstado, String lexema, int columna){
        // punto, barra, asterisco, comlargo, comcorto
        switch(nombreEstado){
            case "qPUNTO"->{
                agregarError(lexema, "Número decimal incompleto, se esperaba un dígito después del punto", fila, columna);
            }
            case "qBARRA"->{
                agregarError(lexema, "Inicio de comentario incompleto", fila, columna);
            }
            case "qASTERISCO"->{
                agregarError(lexema, "Comentario largo no delimitado correctamente", fila, columna);
            }
            case "qCOMLARGO"->{
                agregarError(lexema, "Comentario largo no finalizado", fila, columna);
            }
            case "qCOMCORTO"->{
                agregarError(lexema, "Comentario corto no finalizado", fila, columna);
            }case "qCADENA"->{
                agregarError(lexema, "Cadena no cerrada correctamente", fila, columna);
            }
        }
    }
    
    private void leerIdentificador(String identificador, int columna){
        String comando;
        if(ComandoIA.esComandoIA(identificador)){
            comando = ComandoIA.getComandoIALexema(identificador);
            agregarToken(TipoToken.COMANDO_IA, comando, fila,columna);
        }
        else if(Conector.esConector(identificador)){
            comando = Conector.getConectorLexema(identificador);
            agregarToken(TipoToken.CONECTOR, comando, fila,columna);
        }
        else if(PalabraReservada.esPalabraReservada(identificador)){
            comando = PalabraReservada.getPalabraReservadaLexema(identificador);
            agregarToken(TipoToken.PALABRA_RESERVADA, comando, fila,columna);
        }
        else if(esIdentificadorValido(identificador)){
            agregarToken(TipoToken.IDENTIFICADOR, identificador,fila,columna);
        }
        else{
            agregarError(identificador,"Identificador no válido, uso de caracteres inválidos", fila, columna);
        }
        //sale del método y vuelve al while del analizador
    }//ya
    
    private boolean esIdentificadorValido(String a){
        
        if(a.isEmpty()){
            return false;
        }

        char primero = a.charAt(0);

        if(!Character.isLetter(primero) && primero != '_'){
            return false;
        }

        for(int i = 1; i < a.length(); i++){
            char actual = a.charAt(i);

            if(!Character.isLetterOrDigit(actual) && actual != '_'){
                return false;
            }
        }

        return true;
    }//ya

    private String obtenerLexemaInvalido(String lexemaInicial, char c){
        String invalido = lexemaInicial+c;
        indice++;
        columna++;
        
        while(indice < entrada.length()){
            char e = entrada.charAt(indice);
            if(esSeparador(e)){
               break; 
            }
            invalido += e;
            indice ++;
            columna++;
        }
        
        return invalido;
    }
    
    private void agregarRecorrido(Transicion t){
        if(t!= null && !(t.getOrigen() == t.getDestino())){
            String valor = t.getValor();
            valor = valor.replace("\\", "\\\\");
            valor = valor.replace("\"", "\\\"");
            String d = t.getOrigen().getNombre() +" -> "+ t.getDestino().getNombre()+" [label=\""+ valor+"\"];\n";
            recorridoDot.append(d);
        }
    }
    
    /*
    *
    *
    *
    *
    *
    *
    *
    *
    */
    private void agregarError(String lexema,String descripcion, int fila, int columna){
        ErrorLexico error = new ErrorLexico(lexema, descripcion, fila, columna);
        
        errores.add(error);
    }
    
    private void agregarToken(TipoToken tipo, String lexema, int fila, int columna){
        Token token = new Token();
        token.setTipo(tipo);
        token.setLexema(lexema);
        token.setFila(fila);
        token.setColumna(columna);
        
        tokens.add(token);
    }
    
    //getters de tokens y errores
    public List<Token> getTokens(){
        return tokens;
    }
    
    public List<ErrorLexico> getErrores(){
        return errores;
    }
 
    public String obtenerDot(){
        StringBuilder dot = new StringBuilder();
        dot.append("digraph AFD {\n");
        dot.append("rankdir=LR;\n");
        dot.append("node [shape=circle];\n");
        dot.append("inicio [shape=point];\n");
        dot.append("inicio -> q0;\n");

        // Estados de aceptación
        dot.append("qID [shape=doublecircle];\n");
        dot.append("qINT [shape=doublecircle];\n");
        dot.append("qDOB [shape=doublecircle];\n");
        dot.append("qDIRECTIVA [shape=doublecircle];\n");
        dot.append("qFINCADENA [shape=doublecircle];\n");
        dot.append("qFINCOMENTARIO [shape=doublecircle];\n");
        
        dot.append(recorridoDot);
        
        dot.append("}");
        System.out.println(dot);
        
        return dot.toString();
    }
}
