class Day9(path: String = "day9/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val heightmap =
            Heightmap(inputData.filter(String::isNotEmpty).map { it.toCharArray().map { c -> c.toString().toInt() } })
        return heightmap.lowest()
    }
}

fun List<List<Int>>.up(i: Int, j: Int) = if (i > 0) this[i - 1][j] else -1
fun List<List<Int>>.down(i: Int, j: Int) = if (i < this.size - 1) this[i + 1][j] else -1
fun List<List<Int>>.left(i: Int, j: Int) = if (j > 0) this[i][j - 1] else -1
fun List<List<Int>>.right(i: Int, j: Int) = if (j < this[i].size - 1) this[i][j + 1] else -1
fun List<List<Int>>.allLoc(i: Int, j: Int) =
    listOf(this.up(i, j), this.down(i, j), this.left(i, j), this.right(i, j)).filter { it != -1 }


fun List<List<Int>>.seq(): Sequence<DataPoint> {
    val d = this
    return sequence {
        for (i in d.indices) {
            for (j in d[i].indices) {
                val current = d[i][j]
                yield(DataPoint(i, j, current))
            }
        }
    }

}

data class DataPoint(val i: Int, val j: Int, val value: Int)
data class Heightmap(val data: List<List<Int>>) {


    fun lowest(): Int {

        val lowPoints = data.seq().filter { point ->
            val minOf = data.allLoc(point.i, point.j).minOf { it }
            point.value < minOf
        }

        return lowPoints.sumOf { it.value + 1 }
    }
}

