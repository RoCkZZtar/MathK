package org.intitis.expressions

data class Mul(val left: Expr, val right: Expr) : Expr() {
    override val precedence = 2

    override fun derive() = Add(Mul(left.derive(), right), Mul(left, right.derive()))

    override fun simplify(): Expr {
        val l = left.simplify()
        val r = right.simplify()

        if (l is Const && r is Const)
            return Const(l.value * r.value)

        if (l is Const && l.value == 0.0) return Const(0.0)
        if (r is Const && r.value == 0.0) return Const(0.0)
        if (l is Const && l.value == 1.0) return r
        if (r is Const && r.value == 1.0) return l

        return Mul(l, r)
    }

    override fun toString(): String {
        val l = wrap(left)
        val r = wrap(right)

        return if (orderByVar(left, right))
            "$l$r"
        else
            "$l*$r"
    }

    private fun orderByVar(l: Expr, r: Expr): Boolean =
        (l is Const && r is Var) ||
                (l is Var && r is Const) ||
                (l is Const && r is Pow) ||
                (l is Pow && r is Const)
}