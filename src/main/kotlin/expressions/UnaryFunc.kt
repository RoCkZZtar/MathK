package org.intitis.expressions

sealed class UnaryFunc(val arg: Expr) : Expr() {
    override val precedence = 3
}