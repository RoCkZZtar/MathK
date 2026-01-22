package org.intitis.expressions

data class Tan(val x: Expr) : UnaryFunc(x) {

    override fun derive(): Expr = Div(x.derive(), Pow(Cos(x), 2.0))

    override fun simplify() = Tan(x.simplify())

    override fun toString() = "tan(${x})"
}
