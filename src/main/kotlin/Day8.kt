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
        private val uniqueSizeDigit = values().groupingBy { it.representation.length }.eachCount()

        fun isUnique(value: String): Boolean {
            return uniqueSizeDigit.getOrDefault(value.length, -1) == 1
        }
    }

}

data class DataLine(val data: List<String>, val output: List<String>) {
    fun numberOfOneFourSevenEight(): Int {
        return output.count(DIGIT::isUnique)
    }

    fun computeNumber(): Int {

        data.filter(DIGIT::isUnique)
        return -1
    }
}
