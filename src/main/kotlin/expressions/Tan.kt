package org.intitis.expressions

data class Tan(val x: Expr) : UnaryFunc(x) {

    override fun derive() = Mul(Pow(Cos(x), -2.0),x.derive())

    override fun simplify() = Tan(x.simplify())

    override fun toString() = "tan(${x})"
}
