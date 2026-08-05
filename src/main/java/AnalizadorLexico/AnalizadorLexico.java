/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AnalizadorLexico;

import Entidades.ErrorLexico;
import Entidades.Token;
import Enum.TipoToken;
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
        
        entrada = texto;
        fila = 1;
        columna = 1;
        
        while(indice != entrada.length()){
            char actual = entrada.charAt(indice);
            
            switch(actual){
                //espacio
                case ' ':
                    indice++; 
                    columna++;
                    break;
                //salto de linea
                case '\n':
                    indice++; 
                    fila++;
                    columna= 1;
                    break;
                //cadena
                case '"':
                    indice++; 
                    columna++;
                    leerCadena();
                    break;
                //comentario
                case '/':
                    indice++; 
                    columna++;
                    leerComentario();
                    break;
                //operadores
                case'+':
                    indice++; 
                    columna++;
                    break;
                case '=':
                    indice++; 
                    columna++;
                    break;
                //conector flecha
                case '-':
                    indice++; 
                    columna++;
                    break;
                default:

            }
           
        }
    }
    
    private void leerIdentificador(){
        
    }
    
    //arreglar
    private void leerComentario(){
        String comentario = "";
        //comentario 1 linea
        if(entrada.charAt(indice)== '/'){
            //salto de linea que acaba el comentario
            while(entrada.charAt(indice)!= '\n'){
                comentario += entrada.charAt(indice);
                indice++;
                columna++;
            }
        }else{
            
            //barra que indica el final del comentario largo
            while(entrada.charAt(indice)!= '/'){
                comentario += entrada.charAt(indice);
                if(entrada.charAt(indice)== '\n'){
                    fila++;
                    columna=1;
                }else{
                    columna++;
                }
                indice++;
            }
        }
        agregarToken(TipoToken.COMENTARIO, comentario, fila);
    }
    
    private void leerNumero(){
        
    }
    
    private void leerCadena(){
        String cadena= "";
        while(entrada.charAt(indice) != '"'){
            cadena += entrada.charAt(indice);
            indice++;
            columna++;
        }
        indice++;
        columna++;
        agregarToken(TipoToken.CADENA, cadena, fila);
    }
    
    private void agregarError(){
        
    }
    
    private void agregarToken(TipoToken tipo, String lexema, int fila){
        Token token = new Token();
        token.setTipo(tipo);
        token.setLexema(lexema);
        token.setFila(fila);
        
        tokens.add(token);
    }
}
