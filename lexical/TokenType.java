package lexical;

public enum TokenType {

    // special tokens
    INVALID_TOKEN,
    UNEXPECTED_EOF,
    END_OF_FILE,
    
    // keywords
    APP,
    START,
    STOP,
    IF,       // if
    THEN,     // then
    ELSE,     // else
    END,
    REPEAT,
    UNTIL,
    WHILE,    // while
    DO,       // do
    READ,     // read
    WRITE,    // write

   // operators
    PLUS,            // +
    MINUS,           // -
    MUL,             // *
    DIV,             // /
    AND,             // &&
    OR,              // ||
    NOT,             // !
    EQUAL,           // =
    ASSIGN,          // :=
    NOT_EQUAL,       // --ne !=
    LOWER_THAN,      // --lt >
    LOWER_EQUAL,     // --le >=
    GREATER_EQUAL,   // --ge <=
    GREATER_THAN,    // --gt <

    // symbols
    DOT,           // .
    COMMA,         // ,
    DOT_COMMA,     // ;
    OPEN_PAREN,    // ( PARENTHESES
    CLOSE_PAREN,   // )
    OPEN_BRACKET,  // [ BRACKETS
    CLOSE_BRACKET, // ]

    // others
    INTEGER,    // integer
    REAL,   // real
    ID,
    VAR,
    STRING

/*
    // keywords
    EXIT,     // exit
    READ,     // read
    PRINT,    // print
    PRINTLN,  // println
    SET,      // set
    EXEC,     // exec
    IF,       // if
    THEN,     // then
    ELIF,     // elif
    ELSE,     // else
    FI,       // fi
    WHILE,    // while
    DO,       // do
    DONE,     // done
    FOR,      // for
    IN,       // in

    // operators
    CONCAT,          // .
    PLUS,            // +
    MINUS,           // -
    MUL,             // *
    DIV,             // /
    MOD,             // %
    POWER,           // **
    AND,             // &&
    OR,              // ||
    NOT,             // !
    EMPTY,           // --z
    NOT_EMPTY,       // --n
    EQUAL,           // --eq
    NOT_EQUAL,       // --ne
    LOWER_THAN,      // --lt
    LOWER_EQUAL,     // --le
    GREATER_EQUAL,   // --ge
    GREATER_THAN,    // --gt

    // symbols
    DOT_COMMA,     // ;
    ASSIGN,        // =
    OPEN_ARITH,    // $((
    CLOSE_ARITH,   // ))
    OPEN_PARAM,    // ${
    CLOSE_PARAM,   // }
    OPEN_CMD,      // $(
    OPEN_PAREN,    // (
    CLOSE_PAREN,   // )
    OPEN_BRACKET,  // [
    CLOSE_BRACKET, // ]

    // others
    VAR,          // variable
    STRING,       // string
    NUMBER,       // number
*/

}
