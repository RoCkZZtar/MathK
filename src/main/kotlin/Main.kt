package org.intitis

import org.intitis.scriptscanner.SInterpreter
import org.intitis.scriptscanner.SLexer
import org.intitis.scriptscanner.SParser

fun main() {
    val script = """
        var b = "x^2"
        print b
    """.trimIndent()

    val tokens = SLexer(script).tokenize()
    val ast = SParser(tokens).parse()
    SInterpreter().execute(ast)
}

