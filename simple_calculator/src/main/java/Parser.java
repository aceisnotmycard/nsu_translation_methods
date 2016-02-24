public class Parser {
    Lexer lexer;
    Lexem currentLexem;

    public Parser() {
    }

    public int parse(Lexer lexer) {
        this.lexer = lexer;
        currentLexem = lexer.getNextLexem();
        return parseExpression();
    }

    private int parseExpression() {
        int result = parseTerm();
        while (true) {
            if (currentLexem.getType() == LexemType.PLUS) {
                currentLexem = lexer.getNextLexem();
                result += parseTerm();
            } else if (currentLexem.getType() == LexemType.MINUS) {
                currentLexem = lexer.getNextLexem();
                result -= parseTerm();
            } else {
                return result;
            }
        }
    }

    private int parseTerm() {
        int result = parseFactor();
        while (true) {
            if (currentLexem.getType() == LexemType.DIVIDE) {
                currentLexem = lexer.getNextLexem();
                int divisor = parseFactor();
                if (divisor == 0) {
                    throw new IllegalArgumentException("Division by zero!");
                }
                result /= divisor;
            } if (currentLexem.getType() == LexemType.MULTIPLY) {
                currentLexem = lexer.getNextLexem();
                result *= parseFactor();
            } else {
                return result;
            }
        }
    }

    private int parseFactor() {
        int result = parsePower();
        if (currentLexem.getType() == LexemType.POW) {
            currentLexem = lexer.getNextLexem();
            result = (int) Math.pow(result, parsePower());
        }
        return result;
    }

    private int parsePower() {
        int coef = 1;
        if (currentLexem.getType() == LexemType.MINUS) {
            currentLexem = lexer.getNextLexem();
            coef = -1;
        }
        return coef * parseAtom();
    }

    private int parseAtom() {
        int val = 0;
        if (currentLexem.getType() == LexemType.LEFT_BRACKET) {
            currentLexem = lexer.getNextLexem();
            val = parseExpression();
        } else if (currentLexem.getType() == LexemType.RIGHT_BRACKET) {
            return val;
        } else if (currentLexem.getType() == LexemType.NUMBER) {
            val = Integer.valueOf(currentLexem.getValue());
        }
        currentLexem = lexer.getNextLexem();
        return val;
    }
}
