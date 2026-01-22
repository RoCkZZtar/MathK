package org.intitis.expressions

data class Pow(val base: Expr, val exp: Double) : Expr() {
    override val precedence = 3
    override fun derive(): Expr =
        Mul(
            Const(exp),
            Pow(base, exp - 1)
        )

    override fun simplify(): Expr {
        if (exp == 0.0) return Const(1.0)
        if (exp == 1.0) return base.simplify()
        return this
    }

    override fun toString() = "$base^$exp"
}
