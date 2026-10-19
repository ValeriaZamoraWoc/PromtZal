package Lexer;
import Entidades.Token;
import Entidades.ErrorLexico;
import Entidades.TipoToken;
import java.util.ArrayList;
import java.util.List;

%%
%public 
%class AFD_JFlex
%line
%column
%debug
%type Token
%{
    private List<ErrorLexico> errores = new ArrayList<>();
    public List<ErrorLexico> getErrores() {
        return errores;
    }
%}

ESPACIO = [ \t\n\r]+
DIRECTIVA = "@modelo" | "@rol" | "@formato"
AGENTE = "AGENTE"
CONTEXTO = "contexto"
VARIABLE = "variable"
EJECUTAR = "EJECUTAR"
EXPORTAR = "EXPORTAR"
COMANDO_IA = "PREGUNTAR" |"GENERAR" |"RESUMIR" |"ANALIZAR" |"TRADUCIR" |"CLASIFICAR" |"EXTRAER"
CARGAR = "CARGAR"
CONECTOR = "SOBRE" |"DESDE" |"EN" |"COMO" 
FLECHA = "->"
IGUAL = "="
MAS = "+"
LLAVE_A = "{"
LLAVE_C = "}"
PAR_A = "("
PAR_C = ")"
COMA = ","
NUMERO = [0-9]+(\.[0-9]+)?
LETRA = [a-zA-Z]
ID = ({LETRA}|_)({LETRA}|[0-9]|_)*
CADENA = \"[^\"]*\"
COMENTARIO_LINEA = "//"[^\r\n]*
COMENTARIO_BLOQUE = "/*"([^*]|\*+[^*/])*\*+"/"

%%

{ESPACIO} {}
{COMENTARIO_LINEA} {}
{COMENTARIO_BLOQUE} {}
{DIRECTIVA} {
	return new Token(yytext(), TipoToken.DIRECTIVA, yyline+1, yycolumn +1);
}
{AGENTE} {
	return new Token(yytext(), TipoToken.AGENTE, yyline+1, yycolumn +1);
}
{CONTEXTO} {
	return new Token(yytext(), TipoToken.CONTEXTO, yyline+1, yycolumn +1);
}
{VARIABLE} {
	return new Token(yytext(), TipoToken.VARIABLE, yyline+1, yycolumn +1);
}
{EJECUTAR} {
	return new Token(yytext(), TipoToken.EJECUTAR, yyline+1, yycolumn +1);
}
{EXPORTAR} {
	return new Token(yytext(), TipoToken.EXPORTAR, yyline+1, yycolumn +1);
}
{COMANDO_IA} {
	return new Token(yytext(), TipoToken.COMANDO_IA, yyline+1, yycolumn +1);
}
{CARGAR} {
	return new Token(yytext(), TipoToken.CARGAR, yyline+1, yycolumn +1);
}
{CONECTOR} {
	return new Token(yytext(), TipoToken.CONECTOR, yyline+1, yycolumn +1);
}
{FLECHA} {
	return new Token(yytext(), TipoToken.FLECHA, yyline+1, yycolumn +1);
}
{IGUAL} {
	return new Token(yytext(), TipoToken.IGUAL, yyline+1, yycolumn +1);
}
{MAS} {
	return new Token(yytext(), TipoToken.MAS, yyline+1, yycolumn +1);
}
{LLAVE_A} {
	return new Token(yytext(), TipoToken.LLAVE_A, yyline+1, yycolumn +1);
}
{LLAVE_C} {
	return new Token(yytext(), TipoToken.LLAVE_C, yyline+1, yycolumn +1);
}
{PAR_A} {
	return new Token(yytext(), TipoToken.PAR_A, yyline+1, yycolumn +1);
}
{PAR_C} {
	return new Token(yytext(), TipoToken.PAR_C, yyline+1, yycolumn +1);
}
{COMA} {
	return new Token(yytext(), TipoToken.COMA, yyline+1, yycolumn +1);
}
{NUMERO} {
	return new Token(yytext(), TipoToken.NUMERO, yyline+1, yycolumn +1);
}
{ID} {
	return new Token(yytext(), TipoToken.ID, yyline+1, yycolumn +1);
}
{CADENA} {
	return new Token(yytext(), TipoToken.CADENA, yyline+1, yycolumn +1);
}
. { errores.add(new ErrorLexico(yytext(),"Carácter no reconocido", yyline+1, yycolumn+1)); }
<<EOF>> {
	return new Token("EOF", TipoToken.EOF, yyline+1, yycolumn+1);
}

