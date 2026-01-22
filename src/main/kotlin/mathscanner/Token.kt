package org.intitis.scanner

sealed class Token {
    data class Number(val value: Double) : Token()
    data class Ident(val name: String) : Token()
    data object Plus : Token()
    data object Minus : Token()
    data object Mul : Token()
    data object Div : Token()
    data object Pow : Token()
    data object LParen : Token()
    data object RParen : Token()
}