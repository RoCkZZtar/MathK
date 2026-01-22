package org.intitis.scriptscanner

class SLexer(private val input: String) {
    private var pos = 0

    fun tokenize(): List<SToken> {
        val tokens = mutableListOf<SToken>()

        while (pos < input.length) {
            when {
                input[pos].isWhitespace() -> pos++

                input.startsWith("var", pos) -> {
                    tokens += SToken.Var
                    pos += 3
                }

                input.startsWith("print", pos) -> {
                    tokens += SToken.Print
                    pos += 5
                }

                input[pos] == '=' -> {
                    tokens += SToken.Equals
                    pos++
                }

                input[pos] == '(' -> {
                    tokens += SToken.LParen
                    pos++
                }

                input[pos] == ')' -> {
                    tokens += SToken.RParen
                    pos++
                }

                input[pos] == '"' -> {
                    tokens += readString()
                }

                input[pos].isLetter() -> {
                    tokens += readIdent()
                }

                else -> error("Unbekanntes Zeichen: ${input[pos]}")
            }
        }
        return tokens
    }

    private fun readIdent(): SToken.Ident {
        val start = pos
        while (pos < input.length && input[pos].isLetter()) pos++
        return SToken.Ident(input.substring(start, pos))
    }

    private fun readString(): SToken.StringLit {
        pos++ // "
        val start = pos
        while (input[pos] != '"') pos++
        val value = input.substring(start, pos)
        pos++ // "
        return SToken.StringLit(value)
    }
}
