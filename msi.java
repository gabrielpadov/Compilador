import lexical.Lexeme;
import lexical.Lexical;
import lexical.TokenType;
import lexical.LexicalException;


public class msi {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java msi [Mini Shell Script File]");
            return;
        }

        try (Lexical l = new Lexical(args[0])) {
            // Syntatical s = new Syntatical(l);
            // s.start();
            
            Lexeme lex;
            while (checkType((lex = l.nextToken()).type)) {
                System.out.printf("%02d: \t <\"%s\", %s>\n", l.line(), lex.token, lex.type);
            }
           
            switch (lex.type) {
                case INVALID_TOKEN:
                    System.out.printf("%02d: Lexema inválido [%s]\n", l.line(), lex.token);
                    break;
                case UNEXPECTED_EOF:
                    System.out.printf("%02d: Fim de arquivo inesperado\n", l.line());
                    break;
                default:
                    System.out.printf("<\"%s\", %s>\n", lex.token, lex.type);
                    break;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static boolean checkType(TokenType type) {
        return !(type == TokenType.END_OF_FILE ||
                 type == TokenType.INVALID_TOKEN ||
                 type == TokenType.UNEXPECTED_EOF);
    }
}

