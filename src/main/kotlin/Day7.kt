import kotlin.math.absoluteValue

class Day7(path: String = "day7/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): Crabs {
        return Crabs(inputData.flatMap { it.split(",") }.map(String::toInt))
    }
}

data class Crabs(val data: List<Int>) {
    fun align():Int{
        val max = data.maxOf { it }
        val min = data.minOf { it }
        return (min..max).map (this::distanceTo).minOf { it }
    }
    fun distanceTo(target: Int) = data.sumOf { (it - target).absoluteValue }
    fun align2(): Int {
        TODO("Not yet implemented")
    }
}
