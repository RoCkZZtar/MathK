package org.intitis.scanner

import org.intitis.expressions.*

class Parser(private val tokens: List<Token>) {
    private var pos = 0

    private fun peek(): Token? =
        tokens.getOrNull(pos)

    private fun consume(): Token =
        tokens[pos++]

    fun parse(): Expr =
        parseExpression()

    private fun parseExpression(): Expr {
        var expr = parseTerm()
        while (true) {
            expr = when (peek()) {
                is Token.Plus -> {
                    consume()
                    Add(expr, parseTerm())
                }

                is Token.Minus -> {
                    consume()
                    Add(expr, Mul(Const(-1.0), parseTerm()))
                }

                else -> return expr
            }
        }
    }

    private fun parseTerm(): Expr {
        var expr = parsePower()
        while (true) {
            expr = when (peek()) {
                is Token.Mul -> {
                    consume()
                    Mul(expr, parsePower())
                }

                is Token.Div -> {
                    consume()
                    Mul(expr, Pow(parsePower(), -1.0))
                }

                else -> return expr
            }
        }
    }

    private fun parsePower(): Expr {
        val base = parseFactor()
        return if (peek() is Token.Pow) {
            consume()
            Pow(base, (consume() as Token.Number).value)
        } else base
    }

    private fun parseFactor(): Expr =
        when (val t = consume()) {

            is Token.Number -> Const(t.value)

            is Token.Ident -> when (t.name) {
                "sin" -> Sin(parseParenExpr())
                "cos" -> Cos(parseParenExpr())
                "tan" -> Tan(parseParenExpr())
                else -> Var(t.name)
            }

            is Token.LParen -> {
                val e = parseExpression()
                expect<Token.RParen>("')' erwartet")
                e
            }

            else -> error("Unerwartetes Token: $t")
        }

    private fun parseParenExpr(): Expr {
        expect<Token.LParen>("'(' erwartet")
        val e = parseExpression()
        expect<Token.RParen>("')' erwartet")
        return e
    }

    private inline fun <reified T : Token> expect(msg: String) {
        if (consume() !is T) error(msg)
    }

}
