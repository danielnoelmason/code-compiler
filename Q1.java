
public class Q1 {

	public static void main(String[] args) {

	}

	//this is the enum for token types used throughout the program
	public enum TokenType {
		OP_MULTIPLY, OP_DIVIDE, OP_MOD, OP_ADD, OP_SUBTRACT, OP_LESS, OP_LESSEQUAL, OP_GREATER, OP_GREATEREQUAL,
		OP_EQUAL, OP_NOTEQUAL, OP_NOT, OP_ASSIGN, OP_AND, OP_OR, OP_DOT,

		LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE, LEFT_BRACKET, RIGHT_BRACKET, SEMICOLON, COLON, COMMA,

		KEYWORD_IF, KEYWORD_ELSE, KEYWORD_WHILE, KEYWORD_RETURN, KEYWORD_MAIN, KEYWORD_INT, KEYWORD_DOUBLE,
		KEYWORD_BOOLEAN, KEYWORD_STRING, KEYWORD_PUBLIC, KEYWORD_CLASS, KEYWORD_VOID, KEYWORD_FOR, KEYWORD_CASE,
		KEYWORD_STATIC, KEYWORD_BREAK, KEYWORD_CONTINUE, KEYWORD_DEFAULT, KEYWORD_SWITCH,

		IDENTIFIER, INTEGER, DOUBLE, STRING, BOOLEAN

	}

	//this takes a char and returns a operator token type or null
	public static TokenType getOP(char ch) {

		TokenType opChar;
		switch (ch) {
		case ('*'):
			opChar = TokenType.OP_MULTIPLY;
			break;
		case ('/'):
			opChar = TokenType.OP_DIVIDE;
			break;
		case ('%'):
			opChar = TokenType.OP_MOD;
			break;
		case ('+'):
			opChar = TokenType.OP_ADD;
			break;
		case ('-'):
			opChar = TokenType.OP_SUBTRACT;
			break;
		case ('<'):
			opChar = TokenType.OP_LESSEQUAL;
			break;
		case ('>'):
			opChar = TokenType.OP_GREATEREQUAL;
			break;
		case ('!'):
			opChar = TokenType.OP_NOT;
			break;
		case ('='):
			opChar = TokenType.OP_ASSIGN;
			break;
		case ('.'):
			opChar = TokenType.OP_DOT;
			break;
		default:
			opChar = null;
			break;
		}
		return opChar;
	}

	//this takes a string and returns a operator token type or null
	public static TokenType getOP(String s) {

		TokenType opString;
		switch (s) {
		case ("<="):
			opString = TokenType.OP_LESSEQUAL;
			break;
		case (">="):
			opString = TokenType.OP_GREATEREQUAL;
			break;
		case ("=="):
			opString = TokenType.OP_EQUAL;
			break;
		case ("!="):
			opString = TokenType.OP_NOTEQUAL;
			break;
		case ("&&"):
			opString = TokenType.OP_AND;
			break;
		case ("||"):
			opString = TokenType.OP_OR;
			break;
		default:
			opString = null;
			break;
		}
		return opString;
	}

	//this takes a char and returns a symbol token type or null
	public static TokenType getSymbol(char ch) {

		TokenType symbol;
		switch (ch) {
		case ('('):
			symbol = TokenType.LEFT_PAREN;
			break;
		case (')'):
			symbol = TokenType.RIGHT_PAREN;
			break;
		case ('{'):
			symbol = TokenType.LEFT_BRACE;
			break;
		case ('}'):
			symbol = TokenType.RIGHT_BRACE;
			break;
		case ('['):
			symbol = TokenType.LEFT_BRACKET;
			break;
		case (']'):
			symbol = TokenType.RIGHT_BRACKET;
			break;
		case (';'):
			symbol = TokenType.SEMICOLON;
			break;
		case (':'):
			symbol = TokenType.COLON;
			break;
		case (','):
			symbol = TokenType.COMMA;
			break;
		default:
			symbol = null;
			break;
		}
		return symbol;
	}

	//this takes a string and returns a key word token type or null
	public static TokenType getKeyword(String s) {

		TokenType getKeyword;
		switch (s) {
		case ("if"):
			getKeyword = TokenType.KEYWORD_IF;
			break;
		case ("else"):
			getKeyword = TokenType.KEYWORD_ELSE;
			break;
		case ("while"):
			getKeyword = TokenType.KEYWORD_WHILE;
			break;
		case ("return"):
			getKeyword = TokenType.KEYWORD_RETURN;
			break;
		case ("main"):
			getKeyword = TokenType.KEYWORD_MAIN;
			break;
		case ("int"):
			getKeyword = TokenType.KEYWORD_INT;
			break;
		case ("double"):
			getKeyword = TokenType.KEYWORD_DOUBLE;
			break;
		case ("boolean"):
			getKeyword = TokenType.KEYWORD_BOOLEAN;
			break;
		case ("String"):
			getKeyword = TokenType.KEYWORD_STRING;
			break;
		case ("public"):
			getKeyword = TokenType.KEYWORD_PUBLIC;
			break;
		case ("class"):
			getKeyword = TokenType.KEYWORD_CLASS;
			break;
		case ("void"):
			getKeyword = TokenType.KEYWORD_VOID;
			break;
		case ("for"):
			getKeyword = TokenType.KEYWORD_FOR;
			break;
		case ("case"):
			getKeyword = TokenType.KEYWORD_CASE;
			break;
		case ("switch"):
			getKeyword = TokenType.KEYWORD_SWITCH;
			break;
		case ("static"):
			getKeyword = TokenType.KEYWORD_STATIC;
			break;
		case ("break"):
			getKeyword = TokenType.KEYWORD_BREAK;
			break;
		case ("continue"):
			getKeyword = TokenType.KEYWORD_CONTINUE;
			break;
		case ("default"):
			getKeyword = TokenType.KEYWORD_DEFAULT;
			break;
		default:
			getKeyword = null;
			break;
		}
		return getKeyword;
	}

	//this takes a char and returns true if its a letter or else false 
	public static boolean isLetter(char ch) {
		if (ch >= 'a' && ch <= 'z')
			return true;
		else if (ch >= 'A' && ch <= 'Z')
			return true;
		else
			return false;
	}

	//this takes a char and returns true if its a digit or else false 
	public static boolean isDigit(char ch) {

		switch (ch) {
		case ('0'):
			return true;
		case ('1'):
			return true;
		case ('2'):
			return true;
		case ('3'):
			return true;
		case ('4'):
			return true;
		case ('5'):
			return true;
		case ('6'):
			return true;
		case ('7'):
			return true;
		case ('8'):
			return true;
		case ('9'):
			return true;
		default:
			return false;
		}

	}

	//this takes a char and returns true if its a whitespace or else false 
	public static boolean isWhiteSpace(char ch) {

		switch (ch) {
		case (' '):
			return true;
		case ('\t'):
			return true;
		case ('\n'):
			return true;
		case ('\r'):
			return true;

		default:
			return false;
		}

	}

}
