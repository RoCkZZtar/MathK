package org.intitis.expressions

data class Cos(val x: Expr) : UnaryFunc(x) {

    override fun derive() = Mul(Const(-1.0), Mul(Sin(x), x.derive()))

    override fun simplify() = Cos(x.simplify())

    override fun toString() = "cos(${x})"
}
