import kotlin.math.absoluteValue

class Day7(path: String = "day7/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): Crabs {
        return Crabs(inputData.flatMap { it.split(",") }.map(String::toInt))
    }
}

data class Crabs(val data: List<Int>) {
    fun align() = minValue(this::distanceTo)
    fun alignSum() = minValue(this::distanceToSum)
    fun distanceTo(target: Int) = data.sumOf { it.distance(target) }
    fun distanceToSum(target: Int) = data.sumOf { it.distance(target).intSum() }
    private fun minValue(fn: (Int) -> Int): Int {
        val (min, max) = data.fold(Pair(Int.MAX_VALUE, Int.MIN_VALUE)) { acc, i -> i.minMax(acc) }
        return (min..max).map(fn).minOf { it }
    }

}
