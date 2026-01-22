package org.intitis.scriptscanner

sealed class SToken {
    object Var : SToken()
    object Print : SToken()
    object Equals : SToken()
    object LParen : SToken()
    object RParen : SToken()
    data class Ident(val name: String) : SToken()
    data class StringLit(val value: String) : SToken()
}
