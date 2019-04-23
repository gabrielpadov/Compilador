package lexical;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.PushbackInputStream;

public class Lexical implements AutoCloseable {

    private int line;
    private PushbackInputStream input;
	private SymbolTable table;
	public int c_aux = 0;
    public Lexical(String filename) throws LexicalException {
        try {
            input = new PushbackInputStream(new FileInputStream(filename));
        } catch (Exception e) {
            throw new LexicalException("Unable to open file");
        }

        line = 1;
        table = new SymbolTable();
    }

    public void close() {
        try {
            input.close();
        } catch (Exception e) {
            // ignore
        }
    }

    public int line() {
        return this.line;
    }

    public Lexeme nextToken() throws IOException {

		Lexeme lex = new Lexeme("", TokenType.END_OF_FILE);
		int estado = 1;

		while(estado != 9){
        
        int c = input.read();
    	// System.out.println("[" + estado + ", " + ((char) c) + "]");
        
        switch(estado){
       
            case 1:
             	if(c == -1){
            		return lex;
             	}
             	else if(c == ' ' || c == '\t' || c == '\b' || c == '\r'){
                	estado = 1;
             	}
             	else if(c == '\n'){
					estado = 1;
	             	line++;
             	}
				else if(c == ',' || c == ';' || c == '.' || c == '(' || c == ')' || c == '-' || c == '+' || c == '/' || c == '*' || c == '='){
					 lex.token += (char) c;
             		estado = 9;
             	}
             	else if(c == '<' || c == '>' || c == '!' || c == ':'){
					c_aux = c;
					// System.out.println("[" + estado + ", " + ((char) c_aux) + "]");
             		estado = 4;
             	}
             	else if(c == '_'){
             		lex.token += (char) c;
             		estado = 3;
             	}
             	else if(c == '%'){
             		// lex.token += (char) c;
             		estado = 7;
             	}
             	else if(c == '|'){
             		lex.token += (char) c;
             		estado = 5;
             	}
             	else if(c == '&'){
             		lex.token += (char) c;
             		estado = 6;
             	}
             	else if(Character.isDigit(c)){	
             		lex.token += (char) c;
             		estado = 2;
             	}
             	else if(Character.isLetter(c)){
					 // System.out.println("[" + estado + ", " + ((char) c_aux) + "]");
             			lex.token += (char) c;
             			estado = 3;
             	}
             	else if(c == '{'){
						lex.token += (char) c;
             			estado = 8;
				}
				else{
             			lex.type = TokenType.INVALID_TOKEN;
						lex.token += (char) c;
						line++;
             			return lex;
				}
             	
       			break;

			 case 2:
			 	if(Character.isDigit(c)){
			 	    lex.token += (char) c;
			 		estado = 2;
			 	} else{
			 	 	if (c != -1)
						input.unread(c);

						if(Character.isLetter(c) || c == '_'){
							lex.token += (char) c;
							estado = 3;
							break;
						}

						if (!table.contains(lex.token.toLowerCase())) {
					    	lex.type = TokenType.VAR;
							return lex;
						}					
					
				     estado = 9;
			 	 }
			 
			 	break;

			 case 3:
			 	if(Character.isLetter(c) || Character.isDigit(c) || c == '_'){
				 	lex.token += (char) c;
			 		estado = 3;
			 	} else{
			 	 	if (c != -1)
						input.unread(c);


					if (!table.contains(lex.token.toLowerCase())) {
						if( lex.token.length() == 1 || lex.token.startsWith("_")){
					    	lex.type = TokenType.ID;
							return lex;
						}else{
							// System.out.println("[" + estado + ", " + ((char) c) + "]");
							lex.token += (char) c;
							lex.type = TokenType.INVALID_TOKEN;
							return lex;
						}
					 }
					 
				     estado = 9;
			 	 }
			 
			 	break;

			 case 4:
             	if(c == '='){
					
					switch(c_aux){
						case ':':
							lex.token += (char) c_aux;
							lex.token += (char) c; 													
						break;

						case '!':
							lex.token = "--ne";
						break;

						case '>':
							lex.token = "--le";
						break;

						case '<':
							lex.token = "--ge";
						break;

						default:
						break;
					}

					c_aux = 0;
             		estado = 9;
             	} else{
             		if (c != -1)
						input.unread(c);

					switch(c_aux){
						
						case '>':
							lex.token = "--lt";
						break;

						case '<':
							lex.token = "--gt";
						break;

						default:
						  lex.token += (char) c_aux;
						break;
					}

					c_aux = 0;	
					estado = 9;
             	}
             	
             	break;

             case 5:
			 	 if(c == '|'){
				 	 lex.token += (char) c;
			 	 	estado = 9;
			 	 } else{
			 	 	 if (c != -1)
						input.unread(c);
						
				     estado = 9;
			 	 } 
			 	 
				break;

			 case 6:
			 	if(c == '&'){
			 	    lex.token += (char) c;
			 		estado = 9;
			 	} else{
			 	 	 if (c != -1)
						input.unread(c);
						
				     estado = 9;
			 	 }
			 
			 	break;

			 case 7:
			 	if(c != '\n'){
					// System.out.println("[" + estado + ", " + ((char) c) + "]");
			 		estado = 7;
			 	} else {
					 if (c != -1)
						input.unread(c);

			 		if (c == '\n'){
						estado = 1;
						line++;
				 	}
				}

			 	break;

			 case 8:
			 	if(c != '\n'){
					 if(c == '}'){
						lex.token += (char) c; 
						lex.type = TokenType.STRING; 
						return lex;
					}

					lex.token += (char) c;
					estado = 8;
					
				 }else{
					if(c != -1)
						input.unread(c);

					lex.token += (char) c;
					lex.type = TokenType.INVALID_TOKEN;
             		return lex;
				 }
			 	break;

			 case 9:
			 default:
			 	break;            	
        }
    }

		lex.type = table.find(lex.token.toLowerCase());
		return lex;
    }
}
