class Day5(path: String = "day5/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): Vents {
        val draws = inputData[0].split(",").map(String::toInt)
        return Vents(0)
    }

}

data class Vents(val i: Int)
