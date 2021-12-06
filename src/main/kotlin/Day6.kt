class Day6(path: String = "day6/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): School {
        return School(inputData.flatMap { it.split(",") }.map(String::toInt))
    }
}

data class School(val fish: List<Int>) {
    fun advance(nbDay: Int): School {
        return this
    }
    fun count(): Int {
        return fish.count()
    }
}

