/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AnalizadorLexico;

import Entidades.ErrorLexico;
import Entidades.Token;
import Enum.ComandoIA;
import Enum.Conector;
import Enum.Directiva;
import Enum.Operador;
import Enum.PalabraReservada;
import Enum.TipoToken;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cacerola
 */
public class AnalizadorLexico {
    private int indice = 0;
    private String entrada;
    private int fila, columna;
    private List<Token> tokens;
    private List<ErrorLexico> erroresLexicos;
    
    public void analizarTexto(String texto){
        indice=0;
        tokens= new ArrayList<>();
        erroresLexicos= new ArrayList<>();
        entrada = texto;
        fila = 1;
        columna = 1;
        int c;
        
        while(indice != entrada.length()){
            char actual = entrada.charAt(indice);
            
            switch(actual){
                //espacio en blanco
                case ' ' -> {
                    indice++; 
                    columna++;
                }
                //salto de linea
                case '\n' -> {
                    indice++; 
                    fila++;
                    columna= 1;
                }
                //inicio cadena
                case '"' -> {
                    Character a = actual;
                    agregarToken(TipoToken.DELIMITADOR,a.toString(), fila, columna);
                    
                    indice++; 
                    columna++;
                    c= columna;
                    leerCadena(c);
                }
                //inicio comentario
                case '/' -> {
                    indice++; 
                    c=columna;
                    columna++;
                    leerComentario(c);
                }
                //operadores
                case'+' -> {
                    //agregar token, luego avanzar
                    agregarToken(TipoToken.OPERADOR, "+", fila,columna);
                    indice++;
                    columna++;
                }
                case '=' -> {
                    agregarToken(TipoToken.OPERADOR, "=", fila,columna);
                    indice++; 
                    columna++;
                }
                case'{' ->{
                    agregarToken(TipoToken.DELIMITADOR, "{", fila,columna);
                    indice++;
                    columna++;
                }
                case'}' ->{
                    agregarToken(TipoToken.DELIMITADOR, "}", fila,columna);
                    indice++;
                    columna++;
                }
                case'(' ->{
                    agregarToken(TipoToken.DELIMITADOR, "(", fila,columna);
                    indice++;
                    columna++;
                }
                case')' ->{
                    agregarToken(TipoToken.DELIMITADOR, ")", fila,columna);
                    indice++;
                    columna++;
                }
                case ',' ->{
                    agregarToken(TipoToken.DELIMITADOR, ",", fila, columna);
                    indice++;
                    columna++;
                }
                default -> {
                    c= columna;
                    if(Character.isDigit(actual)){
                        leerNumero(c);
                    }else{
                        leerIdentificador(c); 
                    }
                }
                    
            }          
        }
    }

    private boolean esSeparador(char c){
        return Character.isWhitespace(c)
            || c=='{'
            || c=='}'
            || c=='('
            || c==')'
            || c==','
            || c=='='
            || c=='+'
            || c=='"'
            || c=='/';
        
    }
    
    private void leerIdentificador(int c){
        String identificador = "";
        String comando;
        //obtiene la palabra, verifica que no sea un separador, para si encuentra un delimitador
        while(indice < entrada.length() && !esSeparador(entrada.charAt(indice))){
            identificador+= entrada.charAt(indice); 
            indice++;
            columna++;
        }
        
        //mira el comando y si no, error
        if(ComandoIA.esComandoIA(identificador)){
            comando = ComandoIA.getComandoIALexema(identificador);
            agregarToken(TipoToken.COMANDO_IA, comando, fila,c);
        }
        else if(Conector.esConector(identificador)){
            comando = Conector.getConectorLexema(identificador);
            agregarToken(TipoToken.CONECTOR, comando, fila,c);
        }
        else if(Directiva.esDirectiva(identificador)){
            comando = Directiva.getDirectivaLexema(identificador);
            agregarToken(TipoToken.DIRECTIVA, comando, fila,c);
        }
        else if(PalabraReservada.esPalabraReservada(identificador)){
            comando = PalabraReservada.getPalabraReservadaLexema(identificador);
            agregarToken(TipoToken.PALABRA_RESERVADA, comando, fila,c);
        }
        else if(esIdentificadorValido(identificador)){
            agregarToken(TipoToken.IDENTIFICADOR, identificador,fila,c);
        }
        else{
            agregarError(identificador, fila, c);
        }
        //sale del método y vuelve al while del analizador
    }
    
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
    }
    
    private void leerComentario(int c){
        String comentario = "";
        int filaInicio = fila;
        
        if(indice == entrada.length()){
            return;
        }
        
        //comentario no válido
        if(entrada.charAt(indice)!= '/' || entrada.charAt(indice)!= '*'){
            while(indice < entrada.length() && !esSeparador(entrada.charAt(indice))){
                comentario+= entrada.charAt(indice); 
                if(entrada.charAt(indice)== '\n'){
                    fila++;
                    columna=1;
                }else{
                    columna++;
                }
                indice++;
            }
            agregarError(comentario, fila, c);
            return;
        }
        
        //comentario 1 linea
        if(entrada.charAt(indice)== '/'){
            //salto de linea que acaba el comentario
            while(indice < entrada.length()&&entrada.charAt(indice)!= '\n'){
                comentario += entrada.charAt(indice);
                indice++;
                columna++;
            }
        }
        //comentario largo
        else if(entrada.charAt(indice)=='*'){ 
            boolean cerrado = false;
            //barra que indica el final del comentario largo
            while (indice < entrada.length()) {

                if (entrada.charAt(indice) == '*' && (indice + 1) < entrada.length() && entrada.charAt(indice + 1) == '/') {
                    comentario += "*/";
                    indice += 2; // Consumimos el '*' y el '/'
                    columna += 2;
                    cerrado = true;
                    break; // Cerramos con éxito
                }

                // Si no es el cierre, acumulamos el carácter
                comentario += entrada.charAt(indice);

                if (entrada.charAt(indice) == '\n') {
                    fila++;
                    columna = 1;
                } else {
                    columna++;
                }
                indice++;
            }

            // 🔴 PARCHE: Si llegó al final del texto y NUNCA se cerró el comentario
            if (!cerrado) {
                agregarError("Comentario multilínea no cerrado al final del archivo", filaInicio, c);
                return;
            }
        }
        //agregarToken(TipoToken.COMENTARIO, comentario, filaInicio,c);
    }
    
    private void leerNumero(int c){
        int puntos=0;
        String identificador = "";
        boolean b= false;
        //obtiene el coso ese
        while(indice < entrada.length() && !esSeparador(entrada.charAt(indice))){
            identificador+= entrada.charAt(indice); 
            if(!Character.isDigit(entrada.charAt(indice)) && entrada.charAt(indice)!='.'){
                b=true;
            }if(entrada.charAt(indice)== '.'){
                puntos++;
            }
            indice++;
            columna++;
        }

        if(b || puntos >1){
            agregarError(identificador,fila,c);
            return;
        }
        if(identificador.contains(".")){
            if((identificador.startsWith(".") || identificador.endsWith(".")) || (identificador.startsWith(".") && identificador.endsWith("."))){
                agregarError(identificador, fila, c);
                return;
            }
            
            agregarToken(TipoToken.DECIMAL, identificador, fila, c);
        }else{
            agregarToken(TipoToken.ENTERO, identificador, fila, c);
        }
        
    }
    
    private void leerCadena(int c){
        String cadena= "";
        while(indice < entrada.length() && entrada.charAt(indice) != '"'){
            cadena += entrada.charAt(indice);
            indice++;
            columna++;
            if(indice == entrada.length()){
                agregarError(cadena, fila, c);
                return;
            }
            if(entrada.charAt(indice)== '\n'){
                fila++;
                columna =1;
            }
            
        }
        //agregar la cadena
        agregarToken(TipoToken.CADENA, cadena, fila,c);
        
        //agregar el delimitador de la comilla
        Character a = entrada.charAt(indice);
        agregarToken(TipoToken.DELIMITADOR, a.toString(),fila,columna);
        
        //avanzar
        indice++;
        columna++;
    }
    
    private void agregarError(String lexema, int fila, int columna){
        ErrorLexico error = new ErrorLexico(lexema, fila, columna);
        
        erroresLexicos.add(error);
    }
    
    private void agregarToken(TipoToken tipo, String lexema, int fila, int columna){
        Token token = new Token();
        token.setTipo(tipo);
        token.setLexema(lexema);
        token.setFila(fila);
        token.setColumna(columna);
        
        tokens.add(token);
    }
}
