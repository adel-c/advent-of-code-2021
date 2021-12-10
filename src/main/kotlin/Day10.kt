import java.util.*

class Day10(path: String = "day10/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val syntaxChecker = SyntaxChecker(inputData.map { it.toCharArray().toList() })
        return syntaxChecker.firstErrors()
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
}
class SyntaxChecker(val data: List<List<Char>>) {

    val openToClose = mapOf('[' to ']', '{' to '}', '(' to ')', '<' to '>')
    val closeToOpen = openToClose.entries.associateBy({ it.value }) { it.key }
    fun firstErrors(): Int {
        val eachCount: Map<Int, Int> =
            data.map(this::firstErrors)
                .map { it.charValue() }.groupingBy { it }
                .eachCount()
        return eachCount.entries.fold(listOf<Int>()) { acc, e -> acc + listOf(e.key * e.value) }.sum()
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
        var s = Stack<Char>()
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
        return LineFix(null, listOf())
    }
}
