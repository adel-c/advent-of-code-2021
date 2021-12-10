import java.util.*

class Day10(path: String = "day10/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val syntaxChecker = SyntaxChecker(inputData.map { it.toCharArray().toList() })
        return syntaxChecker.firstErrors()
    }
}

class SyntaxChecker(val data: List<List<Char>>) {

    val openToClose = mapOf('[' to ']', '{' to '}', '(' to ')', '<' to '>')
    val closeToOpen = openToClose.entries.associateBy({ it.value }) { it.key }
    fun firstErrors(): Int {
        val eachCount: Map<Int, Int> =
            data.map(this::firstErrors).filter { it.isPresent }.map { charValue(it.get()) }.groupingBy { it }
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

    fun firstErrors(line: List<Char>): Optional<Char> {
        var s = Stack<Char>()
        line.forEach { c ->
            if (openToClose.contains(c)) {
                s.push(c)
            }
            if (closeToOpen.contains(c)) {
                val pop = s.pop()
                if (closeToOpen[c] != pop) {
                    return Optional.of(c)
                }
            }
        }
        return Optional.empty()
    }
}
