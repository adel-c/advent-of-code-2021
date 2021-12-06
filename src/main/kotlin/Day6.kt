class Day6(path: String = "day6/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): School {
        return School(inputData.flatMap { it.split(",") }.map(String::toInt))
    }
}

data class School(val fish: List<Int>) {
    fun advance(nbDay: Int): Long {

        return fish.sumOf { populationAfter(it, nbDay) }
    }

    fun populationAfter(initAge: Int, days: Int): Long {
        return if (initAge >= days) {
            1
        } else populationAfter(6, days - initAge - 1) + populationAfter(8, days - initAge - 1)
    }
}
