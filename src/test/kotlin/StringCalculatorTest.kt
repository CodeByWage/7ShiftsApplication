import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StringCalculatorTest {
    @Test
    fun add_noInputs() {
        assertEquals(0, StringCalculator.add(""))
    }

    @Test
    fun add_oneNumber() {
        assertEquals(5, StringCalculator.add("5"))
    }

    @Test
    fun add_twoNumbers() {
        assertEquals(12, StringCalculator.add("10,2"))
    }

    @Test
    fun add_threeNumbers() {
        assertEquals(23, StringCalculator.add("7,15,1"))
    }

    @Test
    fun add_newLineAfterFirstNumber() {
        assertEquals(250, StringCalculator.add("120\n,130"))
    }

    @Test
    fun add_newLineAfterFirstComma() {
        assertEquals(650, StringCalculator.add("200,\n300,150"))
    }

    @Test
    fun add_newLineBetweenEachNumberAndComma() {
        assertEquals(1000, StringCalculator.add(
            "\n100\n,\n200\n,\n300\n,\n400\n"))
    }

    @Test
    fun add_customDelimiter() {
        assertEquals(100, StringCalculator.add("//:50:50"))
    }

    @Test
    fun add_throwExceptionForNegative() {
        val exception = assertThrows(Exception::class.java) { StringCalculator.add("-1,2,-3,4,-5") }
        assert(exception.message!!.contains("Negatives not allowed"))
    }

    @Test
    fun add_IgnoreNumbersLargerThan1000() {
        assertEquals(2, StringCalculator.add("2,1001"))
    }

    @Test
    fun add_multiCharacterDelimiter() {
        assertEquals(6, StringCalculator.add("//***\n1***2***3"))
    }

    @Test
    fun add_multipleDifferentDelimiters() {
        assertEquals(6, StringCalculator.add("//$,@\n1$2@3"))
    }

    @Test
    fun add_multipleDifferentDelimitersOfDifferentLengths() {
        assertEquals(10, StringCalculator.add("//$,@\n1$@,2@$$$@3,,,,,4"))
    }



}

