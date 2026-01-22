package org.intitis.scriptscanner

import org.intitis.scanner.Lexer
import org.intitis.scanner.Parser

class SInterpreter {

    private val variables = mutableMapOf<String, String>()

    fun execute(stmts: List<Stmt>) {
        for (stmt in stmts) {
            when (stmt) {
                is VarDecl -> variables[stmt.name] = stmt.value

                is PrintStmt -> {
                    val result = eval(stmt.expr)
                    println(result)
                }
            }
        }
    }

    private fun eval(expr: SExpr): String =
        when (expr) {
            is VarRef -> variables[expr.name]
                ?: error("Unbekannte Variable ${expr.name}")

            is Call -> when (expr.func) {
                "derivative" -> {
                    val src = variables[expr.arg]
                        ?: error("Unbekannte Variable ${expr.arg}")

                    val ast = Parser(Lexer(src).tokenize()).parse()
                    ast.derive().simplify().toString()
                }

                else -> error("Unbekannte Funktion ${expr.func}")
            }
        }
}
