package org.intitis.scanner

class Lexer(private val input: String) {
    private var pos = 0

    fun tokenize(): List<Token> {
        val tokens = mutableListOf<Token>()

        while (pos < input.length) {
            when (val c = input[pos]) {
                ' ', '\t', '\n' -> pos++

                '+' -> {
                    tokens += Token.Plus; pos++
                }

                '-' -> {
                    tokens += Token.Minus; pos++
                }

                '*' -> {
                    tokens += Token.Mul; pos++
                }

                '/' -> {
                    tokens += Token.Div; pos++
                }

                '^' -> {
                    tokens += Token.Pow; pos++
                }

                '(' -> {
                    tokens += Token.LParen; pos++
                }

                ')' -> {
                    tokens += Token.RParen; pos++
                }

                in '0'..'9', '.' -> tokens += readNumber()
                in 'a'..'z', in 'A'..'Z' -> tokens += readIdent()

                else -> error("Unbekanntes Zeichen: '$c'")
            }
        }
        return tokens
    }

    private fun readNumber(): Token.Number {
        val start = pos
        while (pos < input.length &&
            (input[pos].isDigit() || input[pos] == '.')
        ) {
            pos++
        }
        return Token.Number(input.substring(start, pos).toDouble())
    }

    private fun readIdent(): Token.Ident {
        val start = pos
        while (pos < input.length && input[pos].isLetter()) {
            pos++
        }
        return Token.Ident(input.substring(start, pos))
    }
}