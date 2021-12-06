import java.util.*

class Day6(path: String = "day6/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): School {
        return School(inputData.flatMap { it.split(",") }.map(String::toInt))
    }
}
data class School(val fish: List<Int>) {
    fun advance(nbDay: Int): Long {
        val days= MutableList(9){0L}
        fish.forEach {
            days[it]++
        }

        repeat((0 until nbDay).count()) {
            val newSix = days[0]
            (1..8).forEach { i ->
                days[i - 1] = days[i]
            }
            days[8] = newSix
            days[6] += newSix
        }
        println(days)
        return days.sumOf { it }
    }

}

/*
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
*/
