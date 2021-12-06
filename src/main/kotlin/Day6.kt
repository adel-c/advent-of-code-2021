class Day6(path: String = "day6/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): School {
        return School(inputData.flatMap { it.split(",") }.map(String::toInt))
    }
}

data class School(val fish: List<Int>) {
    private val cache = mutableMapOf<Pair<Int, Int>, Long>()
    fun advance(nbDay: Int): Long {
        return fish.sumOf { populationAfter(it, nbDay) }
    }
    private fun populationAfter(initAge: Int, days: Int): Long {
        val key = Pair(initAge, days)
        if (!cache.containsKey(key)) {
            val v = if (initAge >= days) {
                1
            } else populationAfter(6, days - initAge - 1) + populationAfter(8, days - initAge - 1)
            cache[key] = v
        }

        return cache[key]!!
    }
}
