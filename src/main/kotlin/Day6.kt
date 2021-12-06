import java.util.*

class Day6(path: String = "day6/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): School {
        return School(inputData.flatMap { it.split(",") }.map(String::toInt))
    }
}

data class School(val fish: List<Int>) {
    fun advanceMutable(nbDay: Int): Long {
        val days = MutableList(9) { 0L }
        fish.forEach {
            days[it]++
        }
        repeat((0 until nbDay).count()) {
            Collections.rotate(days, -1)
            days[6] += days[8]
        }
        return days.sum()
    }

    fun advance(nbDay: Int): Long {
        val fishCount: Map<Int, Long> = fish.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        val initialPopulation = dayList(fishCount)
        val population = (0 until nbDay).fold(initialPopulation) { acc, _ ->
            val reproduction = acc[0]
            List(acc.size) { index ->
                when (index) {
                    acc.size - 1 -> reproduction
                    6 -> acc[7] + reproduction
                    else -> acc[index + 1]
                }
            }
        }
        println(population)
        return population.sum()
    }

    private fun dayList(eachCount: Map<Int, Long>): List<Long> {
        return initArray(9, 0, eachCount)
    }

    private fun <T> initArray(size: Int, defaultValue: T, valuesAT: Map<Int, T> = emptyMap()): List<T> {
        return (0 until size).mapIndexed { i, _ -> valuesAT.getOrDefault(i, defaultValue) }.toList()
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
