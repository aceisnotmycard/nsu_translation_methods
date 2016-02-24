import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sergey on 15/02/16.
 */
public class LexerTest {

    private static final Map<LexemType, String> lexems = new HashMap<>();
    static {
        lexems.put(LexemType.PLUS, "+");
        lexems.put(LexemType.MINUS, "-");
        lexems.put(LexemType.MULTIPLY, "*");
        lexems.put(LexemType.DIVIDE, "/");
        lexems.put(LexemType.POW, "^");
        lexems.put(LexemType.LEFT_BRACKET, "(");
        lexems.put(LexemType.RIGHT_BRACKET, ")");
        lexems.put(LexemType.NUMBER, "32");
    }

    @Test
    public void testGetNextLexem() throws Exception {
        for (Map.Entry<LexemType, String> entry : lexems.entrySet()) {
            Lexer l = new Lexer(new StringReader(entry.getValue()));
            assertEquals(l.getNextLexem().getType(), entry.getKey());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadInput() throws Exception {
        String expr = "adc";
        Lexer l = new Lexer(new StringReader(expr));
        l.getNextLexem();
    }
}