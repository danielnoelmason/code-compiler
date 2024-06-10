import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Q4 {

	public static void main(String[] args) {

		String Q4Example1 = readFile("src/Q4Example1.java");// variable name incorrect WORKS
		String Q4Example2 = readFile("src/Q4Example2.java");// missing a return type WORKS
		String Q4Example3 = readFile("src/Q4Example3.java");// Method Identifier expected WORKS
		String Q4Example4 = readFile("src/Q4Example4.java");// repeated keyword WORKS
		String Q4Example5 = readFile("src/Q4Example5.java");// variable not declared
		String Q4Example6 = readFile("src/Q4Example6.java");// unclosed Bracket WORKS
		String Q4Example7 = readFile("src/Q4Example7.java");// unclosed Parenthesis WORKS
		String Q4Example8 = readFile("src/Q4Example8.java");// unclosed Brace WORKZ
		String Q4Example9 = readFile("src/Q4Example9.java");// string not Declared Properly WORKS
		String Q4Example10 = readFile("src/Q4Example10.java");// integer not Declared Properly WORKS

		System.out.println("\nEXAMPLE ONE ---- INCORRECT VARIABLE NAMES");
		scan(Q4Example1);
		System.out.println("\nEXAMPLE TWO ---- RETURN TYPE FOR METHOD MISSING");
		scan(Q4Example2);
		System.out.println("\nEXAMPLE THREE ---- METHOD HAS NOT BEEN NAMED");
		scan(Q4Example3);
		System.out.println("\nEXAMPLE FOUR ---- THERE HAS BEEN KEY WORDS REPEATED");
		scan(Q4Example4);
		System.out.println("\nEXAMPLE FIVE ---- VARIABLES HAVE NOT BEEN DECLARED");
		scan(Q4Example5);
		System.out.println("\nEXAMPLE SIX ---- UNCLOSED BRACKET");
		scan(Q4Example6);
		System.out.println("\nEXAMPLE SEVEN ---- UNCLOSED PARENTHESIS");
		scan(Q4Example7);
		System.out.println("\nEXAMPLE EIGHT ---- UNCLOSED BRACE");
		scan(Q4Example8);
		System.out.println("\nEXAMPLE NINE ---- STRING NOT DECLARED CORRECTLY");
		scan(Q4Example9);
		System.out.println("\nEXAMPLE TEN ---- INTEGER NOT DECLARED CORRECTLY");
		scan(Q4Example10);

		/*
		 * if you wish to view the output from previous questions with the errors
		 * uncomment //printArrays(tokenName, tokenType, lineNumber); at the bottom of
		 * scan
		 */

	}

	// 4B AND 4C This checks if the variable names are valid and is applied through
	// the scan
	// 4B This is my solution to 4B, it can checks if a variable name is valid
	// this is my first error detection and is implemented throughout scan
	public static boolean variableNameCheck(String varName) {

		boolean isValid = true;

		if (varName.charAt(0) != '_' && !isLetter(varName.charAt(0))) {
			isValid = false;
		}
		if (varName.charAt(0) == '_' && varName.length() == 1) {
			isValid = false;
		}
		if (isDigit(varName.charAt(0)) && varName.length() == 1) {
			isValid = false;
		}

		for (int i = 1; i < varName.length(); i++) {

			if (isLetter(varName.charAt(i)) || isDigit(varName.charAt(i)) || varName.charAt(i) == '_') {

			} else {
				isValid = false;
				break;
			}

		}
		return isValid;
	}

	// 4D This checks that a method has a return type
	public static void returnTypeMissing(String[] tokenName, String[] tokenType, int[] lineNumber) {

		for (int i = 0; i < tokenType.length; i++) {
			if (tokenType[i] == TokenType.KEYWORD_PUBLIC.toString()) {
				i++;
				if (tokenType[i] == TokenType.KEYWORD_STATIC.toString()) {
					i++;
					if (!tokenType[i].equals(TokenType.KEYWORD_VOID.toString())
							&& tokenType[i] != TokenType.KEYWORD_INT.toString()
							&& tokenType[i] != TokenType.KEYWORD_DOUBLE.toString()
							&& tokenType[i] != TokenType.KEYWORD_BOOLEAN.toString()) {

						System.out.println("Line: " + lineNumber[i] + ": Missing a return type " + "\""
								+ tokenName[i - 2] + " " + tokenName[i - 1] + " (return type required)");
					} else {
						continue;
					}
				}
			}
		}
	}

	// 4D This checks if a method has been given a identifier name
	public static void methodNameMissing(String[] tokenName, String[] tokenType, int[] lineNumber) {

		for (int i = 0; i < tokenType.length; i++) {
			if (tokenType[i] == TokenType.KEYWORD_PUBLIC.toString()) {
				i++;
				if (tokenType[i] == TokenType.KEYWORD_STATIC.toString()) {
					i++;
					if (tokenType[i].equals(TokenType.KEYWORD_VOID.toString())
							|| tokenType[i] == TokenType.KEYWORD_INT.toString()
							|| tokenType[i] == TokenType.KEYWORD_DOUBLE.toString()
							|| tokenType[i] == TokenType.KEYWORD_BOOLEAN.toString()) {
						i++;
						if (tokenType[i].equals(TokenType.LEFT_PAREN.toString())) {
							System.out.println("Line: " + lineNumber[i] + ": Syntax error on token " + tokenType[i - 1]
									+ ", Identifier expected after this token ");
						} else {
							continue;
						}
					}
				}
			}
		}
	}

	// 4D This checks that when a identifier is declared after a keyword
	// 4D This checks that if a variable keyword has been added, it has also been
	// declared with a identifier
	public static void variableDeclaredCheck(String[] tokenName, String[] tokenType, int[] lineNumber) {
		for (int i = 0; i < tokenType.length; i++) {
			if (tokenType[i] == TokenType.KEYWORD_INT.toString() || tokenType[i] == TokenType.KEYWORD_DOUBLE.toString()
					|| tokenType[i] == TokenType.KEYWORD_BOOLEAN.toString()
					|| tokenType[i] == TokenType.KEYWORD_STRING.toString()) {
				i++;
				if (tokenType[i] != TokenType.IDENTIFIER.toString()
						&& !(tokenType[i] == TokenType.LEFT_BRACKET.toString()
								&& tokenType[i + 1] == TokenType.RIGHT_BRACKET.toString()
								&& tokenType[i + 2] == TokenType.IDENTIFIER.toString())) {

					System.out.println("Line: " + lineNumber[i]
							+ ": Syntax error, insert \"VariableDeclarators\" to complete LocalVariableDeclaration after "
							+ tokenType[i - 1]);
				} else {
					continue;
				}
			}
		}
	}

	// 4D This checks if a keyword is repeated
	// 4D This checks that if a
	public static boolean repeatedKeywordCheck(String[] tokenName, String[] tokenType, int[] lineNumber) {
		boolean isValid = true;
		// Compilation error check for repeated keywords eg. IF IF or int int
		for (int i = 0; i < tokenName.length; i++) {
			if (tokenName[i] == null) {
				break;
			}
			if (tokenName[i + 1] != null) {
				if (getKeyword(tokenName[i]) != null) {
					if (lineNumber[i] == (lineNumber[i + 1])) {
						if (tokenName[i].contentEquals(tokenName[i + 1])) {
							System.out.println(
									"Line: " + lineNumber[i] + ": Repeated the Keyword " + "\"" + tokenName[i] + "\"");
						}
					}
				}
			}
		}

		return isValid;
	}

//4D This checks if a bracket has been closed 

	public static void unclosedBracketCheck(String[] tokenName, String[] tokenType, int[] lineNumber) {
		String search1 = "[";
		int count1 = 0;
		for (int i = 0; i < tokenName.length; i++) {
			if (tokenName[i] != null) {
				if (tokenName[i].equals(search1)) {
					count1++;
				}
			}
		}
		String search2 = "]";
		int count2 = 0;
		for (int i = 0; i < tokenName.length; i++) {
			if (tokenName[i] != null) {
				if (tokenName[i].equals(search2)) {
					count2++;
				}
			}
		}
		if (count1 != count2) {
			System.out.println("ERROR: \"]\" Bracket has not been closed");
		}

	}

	// 4D This checks if a parenthesis has been closed

	public static void unclosedParenthesisCheck(String[] tokenName, String[] tokenType, int[] lineNumber) {
		String search1 = "(";
		int count1 = 0;
		for (int i = 0; i < tokenName.length; i++) {
			if (tokenName[i] != null) {
				if (tokenName[i].equals(search1)) {
					count1++;
				}
			}
		}
		String search2 = ")";
		int count2 = 0;
		for (int i = 0; i < tokenName.length; i++) {
			if (tokenName[i] != null) {
				if (tokenName[i].equals(search2)) {
					count2++;
				}
			}
		}
		if (count1 != count2) {
			System.out.println("ERROR: \")\" Parenthesis has not been closed");
		}
	}

	// 4D This checks if a brace has been closed

	public static void unclosedBraceCheck(String[] tokenName, String[] tokenType, int[] lineNumber) {
		String search1 = "{";
		int count1 = 0;
		for (int i = 0; i < tokenName.length; i++) {
			if (tokenName[i] != null) {
				if (tokenName[i].equals(search1)) {
					count1++;
				}
			}
		}
		String search2 = "}";
		int count2 = 0;
		for (int i = 0; i < tokenName.length; i++) {
			if (tokenName[i] != null) {
				if (tokenName[i].equals(search2)) {
					count2++;
				}
			}
		}
		if (count1 != count2) {
			System.out.println("ERROR: \"}\" Brace has not been closed");
		}
	}

	// 4D This checks if a string has been declared in the right data type

	public static void stringDeclaredProperly(String[] tokenName, String[] tokenType, int[] lineNumber) {
		for (int i = 0; i < tokenType.length; i++) {
			if (tokenType[i] == TokenType.KEYWORD_STRING.toString()) {
				i++;
				if (tokenType[i] == TokenType.IDENTIFIER.toString()) {
					i++;
					if (tokenType[i] == TokenType.OP_ASSIGN.toString()) {
						i++;
						if (!tokenType[i].equals(TokenType.STRING.toString())) {

							System.out.println("Line: " + lineNumber[i] + ": Type mismatch: cannot convert from "
									+ tokenType[i] + " to String ");
						} else {
							continue;
						}
					}
				}
			}
		}
	}

	// 4D This checks if a integer has been declared in the correct data type
	public static void integerDeclaredProperly(String[] tokenName, String[] tokenType, int[] lineNumber) {
		for (int i = 0; i < tokenType.length; i++) {
			if (tokenType[i] == TokenType.KEYWORD_INT.toString()) {
				i++;
				if (tokenType[i] == TokenType.IDENTIFIER.toString()) {
					i++;
					if (tokenType[i] == TokenType.OP_ASSIGN.toString()) {
						i++;
						if (!tokenType[i].equals(TokenType.INTEGER.toString())
								&& !tokenType[i].equals(TokenType.IDENTIFIER.toString())) {

							System.out.println("Line: " + lineNumber[i] + ": Type mismatch: cannot convert from "
									+ tokenType[i] + " to int ");
						} else {
							continue;
						}
					}
				}
			}
		}
	}

	// UPDATED SCAN FOR Q4
	public static void scan(String text) {

		int textLength = text.length(); // number of characters in the scanned text
		int index = 0; // a count for which character of text is being scanned
		int line = 1;

		// Arrays to help with Q4 and Q5
		int[] lineNumber = new int[text.length()]; // a count for which line of text is being scanned
		String[] tokenType = new String[text.length()];
		String[] tokenName = new String[text.length()];
		int arrayTokenNumber = 0;

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

			// Comment Checker
			else if (currentCh == '/') {
				index++;
				char nextCh = text.charAt(index);
				if (nextCh == '/') {
					index = text.indexOf('\n', index);
					continue;
				} else if (nextCh == '*') {
					index++;
					index = text.indexOf('*', index);
					line++;
					index = text.indexOf('\n', index);
					index = text.indexOf('*', index);
					index++;
					line++;
					char chSecond = text.charAt(index);
					if (chSecond == '/') {
						index = text.indexOf('\n', index);
						continue;
					}
				} else {
					// In Q4 I have implemented arrays for my line number, token type and token name
					lineNumber[arrayTokenNumber] = line;
					tokenType[arrayTokenNumber] = isOP.toString();
					tokenName[arrayTokenNumber] = String.valueOf(currentCh);
					arrayTokenNumber += 1;
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
					index++;
					
					// In Q4 I have implemented arrays for my line number, token type and token name
					lineNumber[arrayTokenNumber] = line;
					tokenType[arrayTokenNumber] = doubleOP.toString();
					tokenName[arrayTokenNumber] = doubleOPString;
					arrayTokenNumber += 1;

				} else {

					// In Q4 I have implemented arrays for my line number, token type and token name
					lineNumber[arrayTokenNumber] = line;
					tokenType[arrayTokenNumber] = isOP.toString();
					tokenName[arrayTokenNumber] = String.valueOf(firstOp);
					arrayTokenNumber += 1;

				}
				continue;
			}

			// Symbols
			else if (isSym != null) {

				// In Q4 I have implemented arrays for my line number, token type and token name
				index++;
				lineNumber[arrayTokenNumber] = line;
				tokenType[arrayTokenNumber] = isSym.toString();
				tokenName[arrayTokenNumber] = String.valueOf(currentCh);
				arrayTokenNumber += 1;

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
						// In Q4 I have implemented arrays for my line number, token type and token name
						lineNumber[arrayTokenNumber] = line;
						tokenType[arrayTokenNumber] = TokenType.DOUBLE.toString();
						tokenName[arrayTokenNumber] = number;
						arrayTokenNumber += 1;

						// The following two else if statements allow for identifiers that start with
						// digit to be recognized
					} else if (containsLetter(number)) {
						lineNumber[arrayTokenNumber] = line;
						tokenType[arrayTokenNumber] = TokenType.IDENTIFIER.toString();
						tokenName[arrayTokenNumber] = number;
						arrayTokenNumber += 1;
						if (!variableNameCheck(number)) {
							/*4C This is my 4B being implemented in the scan
							 * I do this every time a identifier is identified in the text
							 */
							System.out.println("Line: " + line + ": Invalid identifier name " + "\"" + number + "\"");
						}

					} else if (number.contains("_")) {
						lineNumber[arrayTokenNumber] = line;
						tokenType[arrayTokenNumber] = TokenType.IDENTIFIER.toString();
						tokenName[arrayTokenNumber] = number;
						arrayTokenNumber += 1;
						if (!variableNameCheck(number)) {
							System.out.println("Line: " + line + ": Invalid identifier name " + "\"" + number + "\"");
						}
					} else {
						lineNumber[arrayTokenNumber] = line;
						tokenType[arrayTokenNumber] = TokenType.INTEGER.toString();
						tokenName[arrayTokenNumber] = number;
						arrayTokenNumber += 1;

					}
				}
				continue;
			}

			// Underscore identifiers (recognises identifiers starting with '_'
			else if (currentCh == '_') {
				String underscoreIden = "";
				underscoreIden = underscoreIden + currentCh;
				index++;
				while (index < textLength) {
					currentCh = text.charAt(index);
					if (isDigit(currentCh)) {
						underscoreIden += currentCh;
						index++;
						continue;
					} else if (currentCh == '_') {
						underscoreIden += currentCh;
						index++;
						continue;
					} else if (isLetter(currentCh)) {
						underscoreIden += currentCh;
						index++;
						continue;
					} else {
						break;
					}
				}
				// In Q4 I have implemented arrays for my line number, token type and token name
				lineNumber[arrayTokenNumber] = line;
				tokenType[arrayTokenNumber] = TokenType.IDENTIFIER.toString();
				tokenName[arrayTokenNumber] = underscoreIden;
				arrayTokenNumber += 1;
				
				/*4C This is my 4B being implemented in the scan
				 * I do this every time a identifier is identified in the text
				 */
				if (!variableNameCheck(underscoreIden)) {
					System.out.println("Line: " + line + ": Invalid identifier name " + "\"" + underscoreIden + "\"");
				}

			}
			// Letters (Key Words, Identifiers and Strings)
			else if (isLetter) {
				String word = "";
				word = word + currentCh;
				index++;
				while (index < textLength) { // this will collect all letters in a word
					currentCh = text.charAt(index);
					if (isLetter(currentCh) || isDigit(currentCh) || currentCh == '_') {
						word = word + currentCh;
						index++;
						continue;
					} else {
						break;
					}

				}
				if (getKeyword(word) != null) {
					lineNumber[arrayTokenNumber] = line;
					tokenType[arrayTokenNumber] = getKeyword(word).toString();
					tokenName[arrayTokenNumber] = word;
					arrayTokenNumber += 1;

				} else if (word.contains("true") || word.contains("false")) {
					lineNumber[arrayTokenNumber] = line;
					tokenType[arrayTokenNumber] = TokenType.BOOLEAN.toString();
					tokenName[arrayTokenNumber] = word;
					arrayTokenNumber += 1;
				} else {
					lineNumber[arrayTokenNumber] = line;
					tokenType[arrayTokenNumber] = TokenType.IDENTIFIER.toString();
					tokenName[arrayTokenNumber] = word;
					arrayTokenNumber += 1;
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
				lineNumber[arrayTokenNumber] = line;
				tokenType[arrayTokenNumber] = TokenType.STRING.toString();
				tokenName[arrayTokenNumber] = str;
				arrayTokenNumber += 1;
			}

			else {
				System.out.println("You have given me something I do not know: " + currentCh);
				index++;

				lineNumber[arrayTokenNumber] = line;
				tokenType[arrayTokenNumber] = "You have given me something I do not know";
				tokenName[arrayTokenNumber] = String.valueOf(currentCh);
				arrayTokenNumber += 1;

				continue;
			}

		}

		// printArrays(tokenName, tokenType, lineNumber);

		/*
		 * 4D The following nine methods are the sanity checker for 4D, the tenth error
		 * is the boolean variableNameCheck throughout my scan
		 */
		returnTypeMissing(tokenName, tokenType, lineNumber);
		methodNameMissing(tokenName, tokenType, lineNumber);
		repeatedKeywordCheck(tokenName, tokenType, lineNumber);
		unclosedBraceCheck(tokenName, tokenType, lineNumber);
		unclosedParenthesisCheck(tokenName, tokenType, lineNumber);
		unclosedBracketCheck(tokenName, tokenType, lineNumber);
		stringDeclaredProperly(tokenName, tokenType, lineNumber);
		integerDeclaredProperly(tokenName, tokenType, lineNumber);
		variableDeclaredCheck(tokenName, tokenType, lineNumber);

	}

	// METHODS FROM Q1-3
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

	// Reads the example files
	public static String readFile(String fname) {
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get(fname)));
		} catch (IOException e) {
			System.out.println("Fail to read a file");
		}

		return content;
	}

	// added for use in Q4
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

	public static void printArrays(String[] tokenName, String[] tokenType, int[] lineNumber) {
		// This is a for loop to print the arrays that i have stored my scan in
		for (int i = 0; i < tokenType.length; i++) {
			if (tokenName[i] != null) {
				System.out.println(lineNumber[i] + ", " + tokenType[i] + ", " + tokenName[i]);
			}
		}
	}

}
