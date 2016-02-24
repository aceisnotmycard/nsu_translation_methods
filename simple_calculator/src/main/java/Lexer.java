import java.io.IOException;
import java.io.Reader;

/**
 * Created by sergey on 09/02/16.
 */
public class Lexer {
    Reader reader;
    char currentSymbol;
    boolean usePrevious;

    public Lexer(Reader reader) {
        this.reader = reader;
        usePrevious = false;
    }

    public Lexem getNextLexem() {
        Lexem l = new Lexem("EOF", LexemType.EOF);
        try {
            if (!usePrevious) {
                currentSymbol = (char) reader.read();
            } else {
                usePrevious = false;
            }
            switch (currentSymbol) {
                case '+':
                    l = new Lexem("+", LexemType.PLUS);
                    break;
                case '-':
                    l = new Lexem("-", LexemType.MINUS);
                    break;
                case '*':
                    l = new Lexem("*", LexemType.MULTIPLY);
                    break;
                case '/':
                    l = new Lexem("/", LexemType.DIVIDE);
                    break;
                case '^':
                    l =  new Lexem("^", LexemType.POW);
                    break;
                case '(':
                    l = new Lexem("(", LexemType.LEFT_BRACKET);
                    break;
                case ')':
                    l = new Lexem(")", LexemType.RIGHT_BRACKET);
                    break;
                case ' ':
                    l = getNextLexem();
                    break;
                case '\n':
                    l = new Lexem("EOF", LexemType.EOF);
                    break;
                default:
                    if (Character.isDigit(currentSymbol)) {
                        StringBuilder builder = new StringBuilder();
                        while (Character.isDigit(currentSymbol)) {
                            builder.append(currentSymbol);
                            currentSymbol = (char) reader.read();
                        }
                        l = new Lexem(builder.toString(), LexemType.NUMBER);
                        usePrevious = true;
                    }
                    if (Character.isAlphabetic(currentSymbol)) {
                        throw new IllegalArgumentException("Only +, -, *, /, (, ), ^, and 0-9 are supported");
                    }
            }
        } catch (IOException e) {
            // TODO: Replace
            e.printStackTrace();
        }
        //System.out.println("Next lexem is " + l.getValue());
        return l;
    }
}
