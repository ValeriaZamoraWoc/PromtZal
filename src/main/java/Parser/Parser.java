/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parser;
import AST.*;
import AST.Termino.*;
import Entidades.*;
import Lexer.AFD_JFlex;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cacerola
 */
public class Parser {
    private Token tokenActual;
    private AFD_JFlex lexer;
    private List<ErrorSintactico> errores;
    
    public Parser(AFD_JFlex lexer){
        this.lexer= lexer;
        try {
            tokenActual = lexer.yylex();
        } catch (IOException ex) {
            System.getLogger(Parser.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        errores = new ArrayList<>();
    }
    
    private void parsearPrograma(){
        
    }
    
    private void parsearAgente(){
        
    }
    
    private void parsearComando(){
        
    }
    
    private void parsearConector(){
        
    }
    
    private void parsearContexto(){
        
    }
    
    private void parsearVariable(){
        
    }
    
    private void parsearEjecutar(){
        
    }
    
    private void parsearExportar(){
        
    }
    
    private Termino parsearTermino(){
        if(tokenActual.getTipo() == TipoToken.CARGAR){
            Cargar cargar = parsearCargar();
            return cargar;  
        }
        //
        else if(tokenActual.getTipo() == TipoToken.CADENA ||tokenActual.getTipo() == TipoToken.ID||tokenActual.getTipo() == TipoToken.NUMERO){
            Termino termino = new Termino();
            termino.setValor(tokenActual.getLexema());
            consumir(tokenActual.getTipo());
            return termino;
        }
        errores.add(new ErrorSintactico(tokenActual.getLexema(),"Término no reconocido o erróneo", tokenActual.getFila(), tokenActual.getColumna()));
        return null;
    }
    
    private Cargar parsearCargar(){
        Cargar cargar = new Cargar();
        consumir(TipoToken.CARGAR);
        //
        consumir(TipoToken.PAR_A);
        //
        if(tokenActual.getTipo() == TipoToken.CADENA){
            cargar.setValor(tokenActual.getLexema());
        }else{
            errores.add(new ErrorSintactico(tokenActual.getLexema(),"Cadena errónea en Cargar", tokenActual.getFila(), tokenActual.getColumna()));
        }
        consumir(TipoToken.CADENA);
        //
        consumir(TipoToken.PAR_C);
        //
        return cargar;
    }
    
    //--------------------------------------------------------------------------------------------
    private Token consumir(TipoToken esperado){
        if(tokenActual.getTipo() == esperado){
            Token consumido = tokenActual;
            avanzar();
            return consumido;
        }
        errores.add(new ErrorSintactico(tokenActual.getLexema(),"Falta de token esperado", tokenActual.getFila(), tokenActual.getColumna()));
        return null;
    }
    
    private void avanzar(){
       if(!lexer.yyatEOF()){
            try {
                tokenActual = lexer.yylex();
            } catch (IOException ex) {
                System.getLogger(Parser.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } 
    }
}
