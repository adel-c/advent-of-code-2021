class Day11(path: String = "day11/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        return OctoGrid(inputData.map {line-> line.split("").map { it.toInt() }.toMutableList() }).countFlashes()
    }

    fun compute2(): Int {
        return 0
    }
}
data class Pos(val i:Int,val j:Int)
data class OctoGrid(val grid: List<MutableList<Int>>) {

    fun countFlashes(): Int {
        val flashed = mutableSetOf <Pos>()
        return 0
    }
}
