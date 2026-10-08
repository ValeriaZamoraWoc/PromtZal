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
    
    private void parsearEjecutar(){
        
    }
    
    private void parsearExportar(){
        
    }
    
    private List<Agente> parsearAgentes(){
        List<Agente> agentes = new ArrayList();
        
        while(tokenActual.getTipo() == TipoToken.AGENTE){
            Agente agente = new Agente ();
            //consumir palabra AGENTE
            consumir(TipoToken.AGENTE);
            //consumir id
            Token id = consumir(TipoToken.ID);
            if(id != null){
                agente.setId(id.getLexema());
            }
            //consumir llave
            consumir(TipoToken.LLAVE_A);
            //consumir contexto
            Contexto contexto = parsearContexto();
            if(contexto != null){
                agente.setContexto(contexto);
            }
            //consumir variables
            List<Variable> variables = parsearVariables();
            if(!variables.isEmpty()){
                agente.setVariables(variables);
            }
            //consumir comandos
            List<Comando> comandos = parsearComandos();
            if(!comandos.isEmpty()){
                agente.setComandos(comandos);
            }
            agentes.add(agente);
        }
        return agentes;
    }
    
    private List<Comando> parsearComandos(){
        List<Comando> comandos = new ArrayList<>();
        
        while(tokenActual.getTipo() == TipoToken.COMANDO_IA){
            Comando comando = new Comando();
            //consumir el comando
            Token nombre = consumir(TipoToken.COMANDO_IA);
            if(nombre != null){
                comando.setNombre(nombre.getLexema());
            }
            //consumir expresion
            List<Termino> expresion = parsearExpresion();
            if(expresion != null || !expresion.isEmpty()){
                comando.setExpresion(expresion);
            }
            //consumir conectores
            List<Conector> conectores = parsearConector();
            if(conectores != null || !conectores.isEmpty()){
                comando.setConectores(conectores);
            }
            //consumir flecha
            consumir(TipoToken.FLECHA);
            //consumir id
            Token id =consumir(TipoToken.ID);
            if(id != null){
                comando.setId(id.getLexema());
            }
            comandos.add(comando);
        }
        return comandos;
    }
    
    private List<Conector> parsearConector(){
        List<Conector> conectores= new ArrayList<>();
        
        while(tokenActual.getTipo()== TipoToken.CONECTOR){
           Conector conector = new Conector();
            //consumir el conector (varios)
            consumir(TipoToken.CONECTOR);
            //consumir nombre del conector
            Token id = consumir(TipoToken.ID);
            if(id != null){
                conector.setId(id.getLexema());
            }
            //consumir expresion
            List<Termino> expresion = parsearExpresion();
            if(expresion != null || !expresion.isEmpty()){
                conector.setExpresion(expresion);
            } 
            conectores.add(conector);
        }
        return conectores;
    }
    
    private List<Variable> parsearVariables(){
        List<Variable> variables = new ArrayList<>();
        while(tokenActual.getTipo() == TipoToken.VARIABLE){
           Variable variable = new Variable(); 
           //consumir palabra VARIABLE
           consumir(TipoToken.VARIABLE);
           //consumir el nombre de la variable
           Token id = consumir(TipoToken.ID);
           if(id != null){
               variable.setId(id.getLexema());
           }
           //consumir signo igual
           consumir(TipoToken.IGUAL); 
           //consumir expresion
           List<Termino> expresion= parsearExpresion();
           
           if(expresion != null || !expresion.isEmpty()){
               variable.setExpresion(expresion);
           }
           
           variables.add(variable);
        }
        return variables;
    }
    
    private List<Termino> parsearExpresion() {

        List<Termino> terminos = new ArrayList<>();

        Termino termino = parsearTermino();

        if(termino != null) {
            terminos.add(termino);
        }

        while(tokenActual.getTipo() == TipoToken.MAS) {

            consumir(TipoToken.MAS);

            termino = parsearTermino();

            if(termino != null) {
                terminos.add(termino);
            }
        }

        return terminos;
    }
    
    private Contexto parsearContexto(){
        Contexto contexto = new Contexto();
        consumir(TipoToken.CONTEXTO);
        //
        consumir(TipoToken.IGUAL);
        //
        Token cadena = consumir(TipoToken.CADENA);

        if(cadena != null){
            contexto.setCadena(cadena.getLexema());
        }
        return contexto;
    }
    
    private Termino parsearTermino(){
        if(null != tokenActual.getTipo()) switch (tokenActual.getTipo()) {
            case CARGAR -> {
                return parsearCargar();
            }
            case CADENA, ID -> {
                Termino termino = new Termino();
                Token valor = consumir(tokenActual.getTipo());
                if(valor != null){
                    termino.setValor(valor.getLexema());
                }
                return termino;
            }
            case NUMERO -> {
                Termino termino = new Termino();
                //consumir el numero
                Token numero = consumir(TipoToken.NUMERO);
                if(numero != null){
                    termino.setValor(numero.getLexema());
                }
                //consumir el id si hay
                if(tokenActual.getTipo() == TipoToken.ID){
                    Token id = consumir(TipoToken.ID);
                    termino.setValor(numero.getLexema()+" "+id.getLexema());
                }
                return termino;
            }
            default ->             {
            }
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
        Token cadena = consumir(TipoToken.CADENA);

        if (cadena != null) {
            cargar.setValor(cadena.getLexema());
        }

        consumir(TipoToken.PAR_C);

        return cargar;
    }
    
    //--------------------------------------------------------------------------------------------
    private Token consumir(TipoToken esperado){
        if(tokenActual.getTipo() == esperado){
            Token consumido = tokenActual;
            avanzar();
            return consumido;
        }
        errores.add(new ErrorSintactico(tokenActual.getLexema(),"Falta de token "+ esperado.name()+ " esperado", tokenActual.getFila(), tokenActual.getColumna()));
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
