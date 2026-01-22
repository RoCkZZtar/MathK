package org.intitis

import org.intitis.scanner.Lexer
import org.intitis.scanner.Parser

fun main() {
    // Beispielausdrücke
    val examples = listOf(
        "sin(x)/cos(x)"
    )

    for (input in examples) {
        println("Input: $input")

        try {
            // Tokenisieren
            val tokens = Lexer(input).tokenize()

            // Parser → AST
            val ast = Parser(tokens).parse()

            // Ausgabe original
            println("AST: $ast")

            // Ableitung + vereinfachen
            val derivative = ast.derive().simplify()
            println("Ableitung: $derivative")
        } catch (e: Exception) {
            println("Fehler beim Parsen: ${e.message}")
        }

        println("------------------------------------------------")
    }
}

