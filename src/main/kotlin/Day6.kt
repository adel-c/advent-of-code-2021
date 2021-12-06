import java.util.*

class Day6(path: String = "day6/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): School {
        return School(inputData.flatMap { it.split(",") }.map(String::toInt))
    }
}

data class School(val fish: List<Int>) {
    fun advance(nbDay: Int): Long {
        /*
        val days= MutableList(9){0L}
        fish.forEach {
            days[it]++
        }
         */
        val eachCount: Map<Int, Long> = fish.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        val days = dayList(eachCount).toMutableList()
        repeat((0 until nbDay).count()) {
            Collections.rotate(days, -1)
            days[6] += days[8]
        }

        val map = (0 until nbDay ).fold(dayList(eachCount)) {acc,_->
            val reproduction = acc[0]
            val rotatedArray = acc.slice(1 until days.size) + reproduction
            val newReproduction = dayList(mapOf(6 to reproduction, 8 to reproduction))
            rotatedArray.zip(newReproduction){ a, b->a+b}
        }
        println(days)
        return days.sumOf { it }
    }

    private fun dayList(eachCount: Map<Int, Long>): List<Long> {
        return (0..8).mapIndexed { i, _ -> eachCount.getOrDefault(i, 0) }.toList()
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
