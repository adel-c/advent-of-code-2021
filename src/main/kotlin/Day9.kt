import java.util.*

class Day9(path: String = "day9/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val heightmap =
            Heightmap(inputData.filter(String::isNotEmpty).map { it.toCharArray().map { c -> c.toString().toInt() } })
        return heightmap.lowest()
    }

    fun compute2(): Int {
        val heightmap =
            Heightmap(inputData.filter(String::isNotEmpty).map { it.toCharArray().map { c -> c.toString().toInt() } })
        return heightmap.largestBasins()
    }
}


fun DataPoint.isNotNine() = value != 9
data class Basin(val points: Set<DataPoint>)
data class Heightmap(val data: List<List<Int>>) {
    private val matrix = Matrix(data)
    fun lowest(): Int {
        return lowPoints().sumOf { it.value + 1 }
    }

    fun largestBasins(): Int {
        return lowPoints()
            .map { basinFromPoint(it) }
            .sortedByDescending { it.points.size }
            .take(3)
            .map { it.points.size }
            .reduce { acc, i -> acc * i }
    }


    private fun basinFromPoint(p: DataPoint): Basin {
        return Basin(bassinPoint(p))
    }

    private fun bassinPoint(p: DataPoint): Set<DataPoint> {

        val s: Stack<DataPoint> = Stack<DataPoint>()
        s.push(p)
        val all = mutableSetOf<DataPoint>()
        while (s.isNotEmpty()) {
            val pop = s.pop()
            all.add(pop)
            val allLoc = matrix.aroundNoDiag(pop)
            allLoc.filter { !all.contains(it) && it.isNotNine() }.forEach { s.push(it) }
        }
        return all.toSet()
    }

    private fun lowPoints(): Sequence<DataPoint> = matrix.eachData().filter { point ->
        val allLocNoDiag = matrix.aroundNoDiag(point)
        val minOf = allLocNoDiag.minOf { it.value }
        point.value < minOf
    }


}

