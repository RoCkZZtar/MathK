package org.intitis.expressions

data class Const(val value: Double) : Expr() {
    override val precedence = 4
    override fun derive() = Const(0.0)
    override fun simplify() = this
    override fun toString(): String =
        if (value % 1.0 == 0.0)
            value.toInt().toString()
        else
            value.toString()
}
