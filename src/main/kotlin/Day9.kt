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


data class DataPoint(val i: Int, val j: Int, val value: Int) {
    fun isNotNine() = value != 9
}

data class Basin(val points: Set<DataPoint>)
data class Heightmap(val data: List<List<Int>>) {


    fun lowest(): Int {
        val lowPoints = lowPoints()
        return lowPoints.sumOf { it.value + 1 }
    }

    fun largestBasins(): Int {
        val lowPoints = lowPoints().toList()

        val map =
            lowPoints.map { basinFromPoint(it) }.sortedByDescending { it.points.size }.take(3).map { it.points.size }
        return  map.reduce{ acc, i -> acc*i }
    }


    fun basinFromPoint(p: DataPoint): Basin {

        // allLoc(p.i,p.j).
        return Basin(all(p))
    }

    fun all(p: DataPoint): Set<DataPoint> {

        val s: Stack<DataPoint> = Stack<DataPoint>()
        s.push(p)
        val all = mutableSetOf<DataPoint>()

        while (s.isNotEmpty()) {
            val pop = s.pop()
            all.add(pop)
            val allLoc = allLoc(pop)
            allLoc.filter { !all.contains(it) && it.isNotNine()}.forEach { s.push(it) }
        }
        return all.toSet()// allLoc(p.i,p.j).filter { it }.flatMap { all(p) }
    }

    private fun lowPoints(): Sequence<DataPoint> = eachData().filter { point ->
        val minOf = allLoc(point).minOf { it.value }
        point.value < minOf
    }





    private fun up(p: DataPoint) =
        if (p.i > 0) oPoint(p.i - 1, p.j) else Optional.empty<DataPoint>()

    private fun down(p: DataPoint) =
        if (p.i < data.size - 1) oPoint(p.i + 1, p.j) else Optional.empty()

    private fun left(p: DataPoint) =
        if (p.j > 0) oPoint(p.i, p.j - 1) else Optional.empty()

    private fun right(p: DataPoint) =
        if (p.j < data[p.i].size - 1) oPoint(p.i, p.j + 1) else Optional.empty()

    private fun allLoc(p: DataPoint) =
        listOf(up(p), down(p), left(p), right(p)).filter { it.isPresent }.map { it.get() }

    private fun oPoint(i: Int, j: Int) = Optional.of(DataPoint(i, j, data[i][j]))


    private fun eachData() = sequence {
        for (i in data.indices) {
            for (j in data[i].indices) {
                val current = data[i][j]
                yield(DataPoint(i, j, current))
            }
        }
    }
}

