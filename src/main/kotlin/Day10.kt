import java.util.*

class Day10(path: String = "day10/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val syntaxChecker = SyntaxChecker(inputData.map { it.toCharArray().toList() })
        return syntaxChecker.firstErrors()
    }

    fun compute2(): Long {
        val syntaxChecker = SyntaxChecker(inputData.map { it.toCharArray().toList() })
        return syntaxChecker.fixLine()
    }
}

data class LineFix(val wrongChar: Char?, val missingChar: List<Char>) {

    fun charValue(): Int {
        return when (wrongChar) {
            '[', ']' -> 57
            '{', '}' -> 1197
            '(', ')' -> 3
            '<', '>' -> 25137
            else -> 0
        }
    }

    fun fixCharValue(c: Char): Int {
        return when (c) {
            '[', ']' -> 2
            '{', '}' -> 3
            '(', ')' -> 1
            '<', '>' -> 4
            else -> 0
        }
    }

    fun lineScore(): Long {
        val map = missingChar
            .map(this::fixCharValue)
        val reduce = map
            .fold(0L) { acc, i -> (acc * 5) + i }
        return reduce
    }
}
class SyntaxChecker(val data: List<List<Char>>) {

    val openToClose = mapOf('[' to ']', '{' to '}', '(' to ')', '<' to '>')
    val closeToOpen = openToClose.entries.associateBy({ it.value }) { it.key }
    fun firstErrors(): Int {
        return data.map(this::firstErrors)
            .map { it.charValue() }.groupingBy { it }
            .eachCount().entries.fold(listOf<Int>()) { acc, e -> acc + listOf(e.key * e.value) }.sum()
    }

    fun fixLine(): Long {
        val values = data.map(this::firstErrors)
            .filter { it.missingChar.isNotEmpty() }
            .map { it.lineScore() }
            .sortedDescending()

        return values[values.size / 2]
    }

    fun charValue(c: Char): Int {
        return when (c) {
            '[', ']' -> 57
            '{', '}' -> 1197
            '(', ')' -> 3
            '<', '>' -> 25137
            else -> 0
        }
    }

    fun firstErrors(line: List<Char>): LineFix {
        val s = Stack<Char>()
        line.forEach { c ->
            if (openToClose.contains(c)) {
                s.push(c)
            }
            if (closeToOpen.contains(c)) {
                val pop = s.pop()
                if (closeToOpen[c] != pop) {
                    return LineFix(c, listOf())
                }
            }
        }

        val missingChar = s.map { openToClose[it]!! }.toList().reversed()
        return LineFix(null, missingChar)
    }
}
