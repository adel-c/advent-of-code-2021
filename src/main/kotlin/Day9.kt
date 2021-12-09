class Day9(path: String = "day9/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val heightmap =
            Heightmap(inputData.filter(String::isNotEmpty).map { it.toCharArray().map { c -> c.toString().toInt() } })
        return heightmap.lowest()
    }
}


data class DataPoint(val i: Int, val j: Int, val value: Int)
data class Heightmap(val data: List<List<Int>>) {


    fun lowest(): Int {

        val lowPoints = lowPoints()

        return lowPoints.sumOf { it.value + 1 }
    }

    private fun lowPoints(): Sequence<DataPoint> = eachData().filter { point ->
        val minOf = allLoc(point.i, point.j).minOf { it }
        point.value < minOf
    }


    private fun up(i: Int, j: Int) = if (i > 0) data[i - 1][j] else -1
    private fun down(i: Int, j: Int) = if (i < data.size - 1) data[i + 1][j] else -1
    private fun left(i: Int, j: Int) = if (j > 0) data[i][j - 1] else -1
    private fun right(i: Int, j: Int) = if (j < data[i].size - 1) data[i][j + 1] else -1
    private fun allLoc(i: Int, j: Int) =
        listOf(up(i, j), down(i, j), left(i, j), right(i, j)).filter { it != -1 }


    private fun eachData() = sequence {
        for (i in data.indices) {
            for (j in data[i].indices) {
                val current = data[i][j]
                yield(DataPoint(i, j, current))
            }
        }
    }
}

