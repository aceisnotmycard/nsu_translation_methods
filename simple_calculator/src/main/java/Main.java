import java.io.Reader;
import java.io.StringReader;

/**
 * Created by sergey on 09/02/16.
 */
public class Main {
    public static void main(String[] args) {
        Reader r = new StringReader("(10 + 12 - 2432 * (15-6)^2/14)");
        Lexer l = new Lexer(r);
        Parser parser = new Parser();
        System.out.println(parser.parse(l));
    }
}
