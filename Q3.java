import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Q3 {

	public static void main(String[] args) {

		String Q3Example = readFile("src/Q3Example.java");
		scan(Q3Example);

	}

	public static void scan(String text) {

		int textLength = text.length(); // number of characters in the scanned text
		int index = 0; // a count for which character of text is being scanned
		int line = 1; // a count for which line of text is being scanned

		while (index < textLength) {

			char currentCh = text.charAt(index);// current character
			TokenType isOP = getOP(currentCh);// is the current character an operator
			TokenType isSym = getSymbol(currentCh);// is the current character a symbol
			boolean isWhiteSpace = isWhiteSpace(currentCh);// is the current character a space
			boolean isLetter = isLetter(currentCh);// is the current character a letter
			boolean isDigit = isDigit(currentCh);// is the current character a digit

			// if there is a new line, line counter adds 1
			if (currentCh == '\n') {
				line++;
			}

			// White Space
			if (isWhiteSpace) {
				index++;
				continue;
			}
			
			/*Q3 
			 * Comment Checker
			 * This is the statement i have added for Q3 so that comments are removed
			 */
			else if (currentCh == '/') {
				index++;
				char nextCh = text.charAt(index);
				if(nextCh== '/') {
					index = text.indexOf('\n', index);
					continue;
				}
				else if (nextCh=='*') {
					index++;
					index = text.indexOf('*', index);
					line++;
					index = text.indexOf('\n', index);
					index = text.indexOf('*', index);
					index ++;
					line++;
					char chPlusTwo = text.charAt(index);
					if (chPlusTwo == '/') {
						index = text.indexOf('\n', index);
						continue;
					}		
				}
				else {
					System.out.println(line + ", " + isOP + ", " + currentCh);
				}
			}
			
			

			// Operations
			else if (isOP != null || currentCh == '&' || currentCh == '|') {
				char firstOp = currentCh;
				index++;
				currentCh = text.charAt(index);
				if (getOP(currentCh) != null || currentCh == '&' || currentCh == '|') {
					String doubleOPString = Character.toString(firstOp) + Character.toString(currentCh);
					TokenType doubleOP = getOP(doubleOPString);
					System.out.println(line + ", " + doubleOP + ", " + doubleOPString);
					index++;
				} else {
					System.out.println(line + ", " + isOP + ", " + firstOp);
				}
				continue;
			}

			// Symbols
			else if (isSym != null) {
				System.out.println(line + ", " + isSym + ", " + currentCh);
				index++;
				continue;
			}
			// Integers and Doubles
			else if (isDigit) {
				String number = "";
				number += currentCh;
				index++;
				while (index < textLength) {
					currentCh = text.charAt(index);
					if (isLetter(currentCh) || isDigit(currentCh) || currentCh == '_') {
						number += currentCh;
						index++;
						continue;
					} else if (currentCh == '.') {
						number += currentCh;
						index++;
						continue;
					} else if (isLetter(currentCh)) {
						number += currentCh;
						index++;
						continue;
					} else {
						break;
					}
				}
				if (number.length() >= 1) {

					if (number.contains(".")) {
						System.out.println(line + ", " + TokenType.DOUBLE + ", " + number);
					} else if (containsLetter(number)) {
						System.out.println(line + ", " + TokenType.IDENTIFIER + ", " + number);

					} else if (number.contains("_")) {
						System.out.println(line + ", " + TokenType.IDENTIFIER + ", " + number);

					} else {
						System.out.println(line + ", " + TokenType.INTEGER + ", " + number);
					}
				}
				continue;
			}
			

			// Letters (Key Words, Identifiers and Strings)
			else if (isLetter) {
				String word = "";
				word = word + currentCh;
				index++;
				while (index < textLength) { // this will collect all letters in a word
					currentCh = text.charAt(index);
					if (isLetter(currentCh) || isDigit(currentCh)) {
						word = word + currentCh;
						index++;
						continue;
					} else {
						break;
					}

				}
				if (getKeyword(word) != null) {
					System.out.println(line + ", " + getKeyword(word) + ", " + word);
				} else if (word.contains("true") || word.contains("false")) {
					System.out.println(line + ", " + TokenType.BOOLEAN + ", " + word);
				}  else {
					System.out.println(line + ", IDENTIFIER, " + word);
				}
			} else if (currentCh == '"') {
				String str = "";
				str = str + currentCh;
				index++;
				while (index < textLength) {
					currentCh = text.charAt(index);
					if (currentCh != '"' && currentCh != '\\') {
						str = str + currentCh;
						index++;
						continue;
					} else {
						// End of string
						str = str + currentCh;
						index++;
						break;
					}
				}
				System.out.println(line + ", STRING , " + str);
			}

			else {
				System.out.println("You have given me something I do not know: " + currentCh);
				index++;
				continue;
			}
		}

	}

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
			opChar = TokenType.OP_LESS;
			break;
		case ('>'):
			opChar = TokenType.OP_GREATER;
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

	public static boolean isLetter(char ch) {
		if (ch >= 'a' && ch <= 'z')
			return true;
		else if (ch >= 'A' && ch <= 'Z')
			return true;
		else
			return false;
	}

	public static boolean containsLetter(String str) {
		char[] numArray = str.toCharArray();
		boolean containsLetterStr = false;
		for (char i : numArray) {
			if (isLetter(i)) {
				containsLetterStr = true;
			}
		}
		return containsLetterStr;

	}

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

	public enum TokenType {
		OP_MULTIPLY, OP_DIVIDE, OP_MOD, OP_ADD, OP_SUBTRACT, OP_LESS, OP_LESSEQUAL, OP_GREATER, OP_GREATEREQUAL,
		OP_EQUAL, OP_NOTEQUAL, OP_NOT, OP_ASSIGN, OP_AND, OP_OR, OP_DOT,

		LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE, LEFT_BRACKET, RIGHT_BRACKET, SEMICOLON, COLON, COMMA,

		KEYWORD_IF, KEYWORD_ELSE, KEYWORD_WHILE, KEYWORD_RETURN, KEYWORD_MAIN, KEYWORD_INT, KEYWORD_DOUBLE,
		KEYWORD_BOOLEAN, KEYWORD_STRING, KEYWORD_PUBLIC, KEYWORD_CLASS, KEYWORD_VOID, KEYWORD_FOR, KEYWORD_CASE,
		KEYWORD_STATIC, KEYWORD_BREAK, KEYWORD_CONTINUE, KEYWORD_DEFAULT,

		IDENTIFIER, INTEGER, DOUBLE, STRING, BOOLEAN

	}

	/**
	 * To read the content of a file into a string
	 * 
	 * @param fname the file name
	 * @return the string representing the file content
	 */
	public static String readFile(String fname) {
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get(fname)));
		} catch (IOException e) {
			System.out.println("Fail to read a file");
		}

		return content;
	}
}
