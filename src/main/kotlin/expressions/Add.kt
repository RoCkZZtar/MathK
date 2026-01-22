package org.intitis.expressions

data class Add(val left: Expr, val right: Expr) : Expr() {

    override val precedence = 1

    override fun derive() =
        Add(left.derive(), right.derive())

    override fun simplify(): Expr {
        val l = left.simplify()
        val r = right.simplify()

        // 0 + x = x
        if (l is Const && l.value == 0.0) return r

        // x + 0 = x
        if (r is Const && r.value == 0.0) return l

        // Konstanten zusammenfassen
        if (l is Const && r is Const)
            return Const(l.value + r.value)

        return Add(l, r)
    }

    override fun toString() =
        "${wrap(left)}+${wrap(right)}"
}
