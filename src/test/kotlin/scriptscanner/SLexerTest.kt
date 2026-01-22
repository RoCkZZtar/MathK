package scriptscanner

import org.intitis.scriptscanner.SLexer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SLexerTest {

    @Test
    fun testSimpleFunction() {
        val tokens = SLexer("var a = \"0\"").tokenize()
        assertEquals(4,tokens.size)
    }

    @Test
    fun testEmptyFunction() {
        val tokens = SLexer("").tokenize()
        assertEquals(0,tokens.size)
    }

    @Test
    fun testPrintFunction() {
        val tokens = SLexer("""var a = "0"
            |print a
        """.trimMargin()).tokenize()
        assertEquals(6,tokens.size)
    }

    @Test
    fun testError() {
        org.junit.jupiter.api.assertThrows<IllegalStateException> {
            SLexer("var a = 2").tokenize()
        }
    }
}