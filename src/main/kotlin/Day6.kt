class Day6(path: String = "day6/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): School {
        return School(inputData.flatMap { it.split(",") }.map(String::toInt))
    }
}

data class School(val fish: List<Int>) {
    fun advance(nbDay: Int): Long {

        return fish.map { Fish(it) }.sumOf { it.populationAfter(nbDay) }
    }

}

data class Fish(val initAge: Int) {
    fun populationAfter(days: Int): Long {
        return if (initAge >= days) {
            1
        } else Fish(7).populationAfter(days - initAge) + Fish(9).populationAfter(days - initAge)
    }


}

