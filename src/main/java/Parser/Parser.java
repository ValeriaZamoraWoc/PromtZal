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
    
    public Programa getPrograma(){
        return parsearPrograma();
    }
    
    public List<ErrorSintactico> getErrores() {
        return errores;
    }
    
    public Programa parsearPrograma(){
        Programa programa = new Programa();
        //consumir directivas
        programa.setDirectivas(parsearDirectivas());
        //consumir agentes
        programa.setAgentes(parsearAgentes());
        //consumir ejecuciones
        programa.setEjecuciones(parsearEjecuciones());
        //consumir exportar
        programa.setExportar(parsearExportar());
        //consumir EOF
        consumir(TipoToken.EOF);
        return programa;
    }
    
    private Exportar parsearExportar(){
        Exportar exportar = new Exportar();
        exportar.setFila(tokenActual.getFila());
        exportar.setColumna(tokenActual.getColumna());
        //solo un exportar con uno o varios id
        //consumir palabra exportar
        consumir(TipoToken.EXPORTAR);
        //consumir id
        Token id =consumir(TipoToken.ID);
        if(id != null){
            exportar.getIds().add(id.getLexema());
        }
        
        while(tokenActual.getTipo() == TipoToken.COMA){
            //consumir coma
            consumir(TipoToken.COMA);
            //consumir mas id's
            Token id2 =consumir(TipoToken.ID);
            if(id2 != null){
                exportar.getIds().add(id2.getLexema());
            }
        }
        return exportar;
    }
    
    private List<Ejecutar> parsearEjecuciones(){
        List<Ejecutar> ejecuciones = new ArrayList<>();
        //varias ejecuciones
        while(tokenActual.getTipo() == TipoToken.EJECUTAR){
            Ejecutar ejecucion = new Ejecutar();
            ejecucion.setFila(tokenActual.getFila());
            ejecucion.setColumna(tokenActual.getColumna());
            //consumir palabra ejecutar
            consumir(TipoToken.EJECUTAR);
            //consumir id
            Token id = consumir(TipoToken.ID);
            if(id != null){
                ejecucion.setId(id.getLexema());
            }
            ejecuciones.add(ejecucion);
        }
        return ejecuciones;
    }
    
    private List<Directiva> parsearDirectivas(){
        List<Directiva> directivas = new ArrayList<>();
        while(tokenActual.getTipo() == TipoToken.DIRECTIVA){
            Directiva directiva = new Directiva();
            directiva.setNombre(tokenActual.getLexema());
            directiva.setFila(tokenActual.getFila());
            directiva.setColumna(tokenActual.getColumna());
            //consumir la directiva
            consumir(TipoToken.DIRECTIVA);
            //consumir la cadena
            Token cadena =consumir(TipoToken.CADENA);
            if(cadena != null){
                directiva.setCadena(cadena.getLexema());
            }
            directivas.add(directiva);
        }
        return directivas;
    }
    
    private List<Agente> parsearAgentes(){
        List<Agente> agentes = new ArrayList();
        
        while(tokenActual.getTipo() == TipoToken.AGENTE){
            Agente agente = new Agente ();
            agente.setFila(tokenActual.getFila());
            agente.setColumna(tokenActual.getColumna());
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
            //consumir variables pueden ser nulas
            List<Variable> variables = parsearVariables();
            agente.setVariables(variables);
            //consumir comandos
            List<Comando> comandos = parsearComandos();
            agente.setComandos(comandos);
            agentes.add(agente);
            
            if (consumir(TipoToken.LLAVE_C) == null) {
                modoPanico(TipoToken.AGENTE,TipoToken.EJECUTAR,TipoToken.EXPORTAR,TipoToken.EOF);
            }
        }
        return agentes;
    }
    
    private List<Comando> parsearComandos() {
        List<Comando> comandos = new ArrayList<>();

        //si no es comando
        if (tokenActual.getTipo() != TipoToken.COMANDO_IA) {
            errores.add(new ErrorSintactico(tokenActual.getLexema(),"Se esperaba al menos un comando dentro del agente",tokenActual.getFila(),
                tokenActual.getColumna()));
            modoPanico(TipoToken.COMANDO_IA,TipoToken.LLAVE_C,TipoToken.AGENTE,TipoToken.EJECUTAR,TipoToken.EXPORTAR,TipoToken.EOF);

            if (tokenActual.getTipo() != TipoToken.COMANDO_IA) {
                return comandos;
            }
        }

        while (tokenActual.getTipo() == TipoToken.COMANDO_IA) {
            Token inicio = tokenActual;
            Comando comando = new Comando();
            comando.setFila(tokenActual.getFila());
            comando.setColumna(tokenActual.getColumna());
            Token nombre = consumir(TipoToken.COMANDO_IA);

            if (nombre == null) {
                modoPanico(TipoToken.COMANDO_IA,TipoToken.LLAVE_C,TipoToken.EOF);
                continue;
            }

            comando.setNombre(nombre.getLexema());

            List<Termino> expresion = parsearExpresion();

            if (expresion.isEmpty()) {
                modoPanico(TipoToken.COMANDO_IA,TipoToken.LLAVE_C,TipoToken.EOF);
                if (tokenActual.getTipo() != TipoToken.COMANDO_IA) {
                    break;
                }
                continue;
            }

            comando.setExpresion(expresion);

            List<Conector> conectores = parsearConector();
            comando.setConectores(conectores);

            if (consumir(TipoToken.FLECHA) == null) {
                modoPanico(TipoToken.COMANDO_IA,TipoToken.LLAVE_C,TipoToken.EOF);

                if (tokenActual.getTipo() != TipoToken.COMANDO_IA) {
                    break;
                }
                continue;
            }

            Token id = consumir(TipoToken.ID);

            if (id == null) {
                modoPanico(TipoToken.COMANDO_IA,TipoToken.LLAVE_C,TipoToken.EOF);

                if (tokenActual.getTipo() != TipoToken.COMANDO_IA) {
                    break;
                }
                continue;
            }

            comando.setId(id.getLexema());
            comandos.add(comando);

            if (tokenActual == inicio) {
                avanzar();
            }
        }

        return comandos;
    }
    
    private List<Conector> parsearConector() {
        List<Conector> conectores = new ArrayList<>();

        while (tokenActual.getTipo() == TipoToken.CONECTOR) 
        {
            Token tokenConector = consumir(TipoToken.CONECTOR);

            List<Termino> expresion = parsearExpresion();

            if (expresion.isEmpty()) {
                modoPanico(TipoToken.CONECTOR,TipoToken.FLECHA,TipoToken.COMANDO_IA,TipoToken.LLAVE_C,TipoToken.EOF);

                if (tokenActual.getTipo() == TipoToken.FLECHA|| tokenActual.getTipo() == TipoToken.COMANDO_IA|| tokenActual.getTipo() == TipoToken.LLAVE_C
                        || tokenActual.getTipo() == TipoToken.EOF) {
                    break;
                }

                continue;
            }

            Conector conector = new Conector();
            conector.setFila(tokenConector.getFila());
            conector.setColumna(tokenConector.getColumna());
            conector.setId(tokenConector.getLexema());
            conector.setExpresion(expresion);
            conectores.add(conector);
        }

        return conectores;
    }

    private List<Variable> parsearVariables() {
        List<Variable> variables = new ArrayList<>();

        while (tokenActual.getTipo() == TipoToken.VARIABLE) {
            consumir(TipoToken.VARIABLE);

            Token id = consumir(TipoToken.ID);

            //si consumir da nulo
            if (id == null) {
                modoPanico(TipoToken.VARIABLE,TipoToken.COMANDO_IA,TipoToken.LLAVE_C,TipoToken.EOF);
                continue;
            }
            //si no es igual
            if (consumir(TipoToken.IGUAL) == null) {
                modoPanico(TipoToken.VARIABLE,TipoToken.COMANDO_IA,TipoToken.LLAVE_C,TipoToken.EOF);
                continue;
            }

            List<Termino> expresion = parsearExpresion();

            //si no hay expresiones
            if (expresion.isEmpty()) {
                modoPanico(TipoToken.VARIABLE,TipoToken.COMANDO_IA,TipoToken.LLAVE_C,TipoToken.EOF);
                continue;
            }

            Variable variable = new Variable();
            variable.setFila(tokenActual.getFila());
            variable.setColumna(tokenActual.getColumna());
            variable.setId(id.getLexema());
            variable.setExpresion(expresion);
            variables.add(variable);
        }

        return variables;
    }
    
    private List<Termino> parsearExpresion(){

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
        contexto.setFila(tokenActual.getFila());
        contexto.setColumna(tokenActual.getColumna());
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
                termino.setFila(tokenActual.getFila());
                termino.setColumna(tokenActual.getColumna());
                Token valor = consumir(tokenActual.getTipo());
                if(valor != null){
                    termino.setValor(valor.getLexema());
                }
                return termino;
            }
            case NUMERO -> {
                Termino termino = new Termino();
                termino.setFila(tokenActual.getFila());
                termino.setColumna(tokenActual.getColumna());
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
        }
        errores.add(new ErrorSintactico(tokenActual.getLexema(),"Término no reconocido o erróneo", tokenActual.getFila(), tokenActual.getColumna()));
        modoPanico(TipoToken.MAS,TipoToken.CONECTOR,TipoToken.FLECHA,TipoToken.COMANDO_IA,TipoToken.VARIABLE,TipoToken.LLAVE_C,
            TipoToken.EOF);
        return null;
    }
    
    private Cargar parsearCargar(){
        Cargar cargar = new Cargar();
        cargar.setFila(tokenActual.getFila());
        cargar.setColumna(tokenActual.getColumna());
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
        if (tokenActual == null || tokenActual.getTipo() == TipoToken.EOF) {
            return;
        }

        try {
            tokenActual = lexer.yylex();
        } catch (IOException ex) {
            System.getLogger(Parser.class.getName())
                .log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }  
    
    private void modoPanico(TipoToken... tokensSincronizacion) {
        while (tokenActual != null&& tokenActual.getTipo() != TipoToken.EOF) {
            for (TipoToken tipo : tokensSincronizacion) {
                if (tokenActual.getTipo() == tipo) {
                    return;
                }
            }

            avanzar();
        }
    }

}
