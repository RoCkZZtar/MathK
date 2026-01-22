package org.intitis.scriptscanner

sealed class Stmt

data class VarDecl(val name: String, val value: String) : Stmt()
data class PrintStmt(val expr: SExpr) : Stmt()

sealed class SExpr
data class VarRef(val name: String) : SExpr()
data class Call(val func: String, val arg: String) : SExpr()
