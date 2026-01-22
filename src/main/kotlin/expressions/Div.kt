package org.intitis.expressions

data class Div(val numerator: Expr, val denominator: Expr) : Expr() {

    override val precedence = 2  // gleiche wie Mul

    override fun derive(): Expr =
        Div(
            Add(
                Mul(numerator.derive(), denominator),
                Mul(Const(-1.0), Mul(numerator, denominator.derive()))
            ),
            Pow(denominator, 2.0)
        )

    override fun simplify(): Expr {
        val num = numerator.simplify()
        val denom = denominator.simplify()

        // 0 / x = 0
        if (num is Const && num.value == 0.0) return Const(0.0)

        // x / 1 = x
        if (denom is Const && denom.value == 1.0) return num

        // Konstanten dividieren
        if (num is Const && denom is Const) return Const(num.value / denom.value)

        return Div(num, denom)
    }

    override fun toString(): String {
        val num = if (numerator.precedence < precedence) "($numerator)" else numerator.toString()
        val denom = if (denominator.precedence < precedence) "($denominator)" else denominator.toString()
        return "$num/$denom"
    }
}
