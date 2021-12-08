import java.util.*

class Day8(path: String = "day8/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    private val data = inputData.map { it.split("|") }.map { DataLine(it[0].split(" "), it[1].split(" ")) }
    fun compute(): Int {

        println(data)
        return data.map(DataLine::numberOfOneFourSevenEight).sum()
    }

    fun compute2(): Int {
        return data.map(DataLine::computeNumber).sum()
    }
}

enum class DIGIT(val representation: String) {

    ZERO("abcefg"),
    ONE("cf"),
    TWO("acdeg"),
    THREE("acdfg"),
    FOUR("bcdf"),
    FIVE("abdfg"),
    SIX("abdefg"),
    SEVEN("acf"),
    EIGHT("abcdefg"),
    NINE("abcdfg");

    fun size() = representation.length

    companion object {
        private val uniqueSizeDigit: Map<Int, List<DIGIT>> = values().groupBy { it.representation.length }

        fun isUnique(value: SortedSet<Char>): Boolean {
            return uniqueSizeDigit.getOrDefault(value.size, listOf()).size == 1
        }

        fun possibilitiesBySize(value: SortedSet<Char>): List<DIGIT> {
            return uniqueSizeDigit.getOrDefault(value.size, listOf())
        }

    }

}

data class DataLine(val dataUnSorted: List<String>, val outputUnSorted: List<String>) {
    val data: List<SortedSet<Char>> = dataUnSorted.map { it.toSortedSet() }.toList()
    val output: List<SortedSet<Char>> = outputUnSorted.map { it.toSortedSet() }.toList()
    fun numberOfOneFourSevenEight(): Int {
        return output.count { DIGIT.isUnique(it) }
    }

    fun possibilitiesBySize(data: List<SortedSet<Char>>): Map<Int, List<SortedSet<Char>>> {
        return data.groupBy { it.size }
    }

    fun computeNumber(): Int {
        val possibilitiesBySize: Map<Int, List<SortedSet<Char>>> = possibilitiesBySize(data)
        var one = possibilitiesBySize.getOrDefault(DIGIT.ONE.size(), listOf())[0]
        var seven = possibilitiesBySize.getOrDefault(DIGIT.SEVEN.size(), listOf())[0]
        var four: SortedSet<Char> = possibilitiesBySize.getOrDefault(DIGIT.FOUR.size(), listOf())[0]
        var eight = possibilitiesBySize.getOrDefault(DIGIT.EIGHT.size(), listOf())[0]
        println(one)
        println(seven)
        println(four)
        println(eight)

        var nine = findNine(four, possibilitiesBySize.getOrDefault(6, listOf()))
        println(nine)
        // val uniqueDigit: Map<String, List<DIGIT>> = data.associateWith { DIGIT.possibilitiesBySize(it) }
        return -1
    }

    private fun findNine(four: SortedSet<Char>, candidates: List<SortedSet<Char>>): SortedSet<Char> {
        val filter1 = candidates.filter { it.containsAll(four) }
        val filter = filter1.filter { it.size == four.size + 2}
        return filter[0]
    }
}
