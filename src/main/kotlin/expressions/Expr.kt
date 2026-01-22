package org.intitis.expressions

sealed class Expr {
    abstract fun derive(): Expr
    abstract fun simplify(): Expr
    abstract val precedence: Int

    protected fun wrap(child: Expr): String =
        if (child.precedence < this.precedence)
            "($child)"
        else
            child.toString()
}
