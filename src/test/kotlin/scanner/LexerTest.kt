package scanner

import org.intitis.scanner.Lexer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LexerTest {

    @Test
    fun simpleTest() {
        val result = Lexer("2*x").tokenize()
        assertEquals(result.size,3)
    }

    @Test
    fun longerTest() {
        val result = Lexer("212*(38+5)*x^23-3/2*x-44").tokenize()
        assertEquals(result.size,19)
    }

    @Test
    fun testFunction() {
        val result = Lexer("2*sin(x)").tokenize()
        assertEquals(result.size,5)
    }

    @Test
    fun testError() {
        assertThrows<IllegalStateException> {
            Lexer("}}").tokenize()
        }

    }
}