/**
 * Created by sergey on 09/02/16.
 */
public class Lexem {
    String value;
    LexemType type;

    public Lexem(String value, LexemType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public LexemType getType() {
        return type;
    }
}
