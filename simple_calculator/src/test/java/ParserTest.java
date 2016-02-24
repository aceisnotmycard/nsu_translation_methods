import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sergey on 15/02/16.
 */
public class ParserTest {

    private static final Map<String, Integer> expressions = new HashMap<>();
    static {
        expressions.put("5", 5);
        expressions.put("5+5", 10);
        expressions.put("2^4", 16);
        expressions.put("-5", -5);
        expressions.put("1^-1", 1);
        expressions.put("-2^2+4*4", 20);
        expressions.put("-2^3", -8);
        expressions.put("-2^2-4", 0);
        expressions.put("-(2)+1", -1);
        expressions.put("-(3^3)*(2^2)", -108);
        expressions.put("-(-1)", 1);
    }

    @Test
    public void testParseExpression() throws Exception {
        Parser p = new Parser();
        for (Map.Entry<String, Integer> pair : expressions.entrySet()) {
            Lexer l = new Lexer(new StringReader(pair.getKey()));
            assertEquals(pair.getValue(), Integer.valueOf(p.parse(l)));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroDivider() throws Exception {
        Parser p = new Parser();
        p.parse(new Lexer(new StringReader("1/0")));
    }
}