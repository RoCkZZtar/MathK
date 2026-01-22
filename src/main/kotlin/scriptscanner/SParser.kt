package org.intitis.scriptscanner

class SParser(private val tokens: List<SToken>) {
    private var pos = 0

    private fun consume() = tokens[pos++]
    private fun peek() = tokens.getOrNull(pos)

    fun parse(): List<Stmt> {
        val stmts = mutableListOf<Stmt>()
        while (pos < tokens.size) {
            stmts += parseStmt()
        }
        return stmts
    }

    private fun parseStmt(): Stmt =
        when (peek()) {
            SToken.Var -> parseVar()
            SToken.Print -> parsePrint()
            else -> error("Statement erwartet")
        }

    private fun parseVar(): Stmt {
        consume() // var
        val name = (consume() as SToken.Ident).name
        consume() // =
        val value = (consume() as SToken.StringLit).value
        return VarDecl(name, value)
    }

    private fun parsePrint(): Stmt {
        consume() // print
        val expr = parseExpr()
        return PrintStmt(expr)
    }

    private fun parseExpr(): SExpr {
        val ident = (consume() as SToken.Ident).name

        return if (peek() is SToken.LParen) {
            consume() // (
            val arg = (consume() as SToken.Ident).name
            consume() // )
            Call(ident, arg)
        } else {
            VarRef(ident)
        }
    }
}
