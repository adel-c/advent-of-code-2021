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


    companion object {
        private val uniqueSizeDigit: Map<Int, List<DIGIT>> = values().groupBy { it.representation.length }

        fun isUnique(value: String): Boolean {
            return uniqueSizeDigit.getOrDefault(value.length, listOf()).size == 1
        }

        fun possibilitiesBySize(value: String): List<DIGIT> {
            return uniqueSizeDigit.getOrDefault(value.length, listOf())
        }
    }

}

data class DataLine(val data: List<String>, val output: List<String>) {
    fun numberOfOneFourSevenEight(): Int {
        return output.count(DIGIT::isUnique)
    }

    fun computeNumber(): Int {

        val uniqueDigit: Map<String, List<DIGIT>> = data.associateWith { DIGIT.possibilitiesBySize(it) }
        return -1
    }
}
