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
        return output.count{DIGIT.isUnique(it)}
    }

    fun possibilitiesBySize(data: List<SortedSet<Char>>): Map<Int, List<SortedSet<Char>>> {
        return data.groupBy { it.size }
    }

    fun computeNumber(): Int {
        val possibilitiesBySize = possibilitiesBySize(data)
        var one = possibilitiesBySize.getOrDefault(DIGIT.ONE.size(),"")
        var seven = possibilitiesBySize.getOrDefault(DIGIT.SEVEN.size(),"")
        var four = possibilitiesBySize.getOrDefault(DIGIT.FOUR.size(),"")
        var eight = possibilitiesBySize.getOrDefault(DIGIT.EIGHT.size(),"")
       // val uniqueDigit: Map<String, List<DIGIT>> = data.associateWith { DIGIT.possibilitiesBySize(it) }
        return -1
    }
}
