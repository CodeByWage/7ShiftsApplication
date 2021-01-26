import java.lang.RuntimeException

/**
 * String and Delimiter
 *
 * This is a convenience data class for holding a string and the delimiters in the string
 */
data class StringAndDelimiters(val string: String, val delimiter: String)

/**
 * A calculator of string contents
 **/
object StringCalculator {
    /**
     * Parse a string at an optionally custom delimiter and return the sum of the values in the string that are less
     * than 1000. The input can be structured in one of two ways:
     *
     * No custom Delimiter: "1,2,3"
     * If no custom delimiter is provided, a comma is expected to separate the numbers
     *
     * Custom Delimiter: "//@1@2@3"
     * A custom delimiter can be provided by including it at the beginning of the string, after the sequence "//"
     *
     * A custom delimiter can be provided as a sequence of characters. If this is done, any of those characters will be
     * treated as a delimiter. Any combination of the delimiters is also acceptable when separating numbers as well as
     * repeated delimiters.
     *
     * @param: numbers: A string containing a sequence of numbers
     * @return: The sum of the numbers in the input
     * @throws: RuntimeException if any of the numbers in the input are negative
     */
    fun add(numbers: String): Int = addNumbersWthDelimiter(getDelimiter(numbers))

    private fun addNumbersWthDelimiter(numbersAndDelimiters: StringAndDelimiters): Int =
        when {
            numbersAndDelimiters.string.isNotEmpty() ->{
                val numbers = removeLargeNumbers(parseNumbers(numbersAndDelimiters))
                checkForNegatives(numbers)
                numbers.sum()
            }
            else -> 0
        }

    private fun getDelimiter(numbers: String): StringAndDelimiters =
        when {
            numbers.startsWith("//") -> {
                val firstNumberIndex = numbers.indexOfFirst { it.isDigit() }
                val numberString = numbers.substring(firstNumberIndex)
                val delimiter = numbers.substring(2, firstNumberIndex)
                StringAndDelimiters(numberString, delimiter)
            }
            else ->
                StringAndDelimiters(numbers, ",")
    }

    private fun parseNumbers(numbersAndDelimiters: StringAndDelimiters): List<Int> =
        numbersAndDelimiters.string
            .replace("\n", "")
            .split(buildRegex(numbersAndDelimiters.delimiter))
            .map {it.toInt()}

    private fun buildRegex(delimiter: String) : Regex = Regex("[" + getUniqueCharacters(delimiter) + "]+")

    private fun getUniqueCharacters(characters: String) : String = characters.toCharArray().joinToString("")

    private fun removeLargeNumbers(parseNumbers: List<Int>): List<Int> = parseNumbers.filter { it < 1000 }

    private fun checkForNegatives(numbers: List<Int>) {
        if (numbers.any { it < 0 }) {
            throw RuntimeException("Negatives not allowed")
        }
    }

}