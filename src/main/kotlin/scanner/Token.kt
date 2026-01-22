package org.intitis.scanner

sealed class Token {
    data class Number(val value: Double) : Token()
    data class Ident(val name: String) : Token()
    object Plus : Token()
    object Minus : Token()
    object Mul : Token()
    object Div : Token()
    object Pow : Token()
    object LParen : Token()
    object RParen : Token()
}