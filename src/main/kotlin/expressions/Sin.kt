package org.intitis.expressions

data class Sin(val x: Expr) : UnaryFunc(x) {

    override fun derive() =  Mul(Cos(x), x.derive())

    override fun simplify(): Expr = Sin(x.simplify())

    override fun toString(): String = "sin(${x})"
}
