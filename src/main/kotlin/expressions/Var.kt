package org.intitis.expressions

data class Var(val name: String) : Expr() {
    override val precedence = 4
    override fun derive() = if(name == "x") Const(1.0) else Const(0.0)

    override fun simplify() = this

    override fun toString() = name
}
