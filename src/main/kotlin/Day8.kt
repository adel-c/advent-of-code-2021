class Day8(path: String = "day8/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val data = inputData.map { it.split("|") }.map { DataLine(it[0].split(" "), it[1].split(" ")) }
        println(data)
        return data.map(DataLine::numberOfOneFourSevenEight).sum()
    }
}

enum class DIGIT(val size: Int) {
    ONE(2),
    FOUR(4),
    SEVEN(3),
    EIGHT(7);

    companion object {
        val uniqueSizeDigit = values().groupingBy { it.size }.eachCount()
        fun hasUniqueSize(i: Int): Boolean {

            return uniqueSizeDigit.getOrDefault(i, -1) == 1
        }
    }

}

data class DataLine(val data: List<String>, val output: List<String>) {
    fun numberOfOneFourSevenEight(): Int {

        return output.map { it.length }.filter(DIGIT::hasUniqueSize).sum()
    }
}
