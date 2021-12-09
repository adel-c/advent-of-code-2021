class Day9(path: String = "day9/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val heightmap =
            Heightmap(inputData.filter(String::isNotEmpty).map { it.toCharArray().map { c -> c.toString().toInt() } })
        return heightmap.lowest()
    }
}

fun List<List<Int>>.up(i: Int, j: Int) = if (i > 0) this[i - 1][j] else -1
fun List<List<Int>>.down(i: Int, j: Int) = if (i < this.size-1) this[i + 1][j] else -1
fun List<List<Int>>.left(i: Int, j: Int) = if (j > 0) this[i][j - 1] else -1
fun List<List<Int>>.right(i: Int, j: Int) = if (j < this[i].size-1) this[i][j + 1] else -1
fun List<List<Int>>.allLoc(i: Int, j: Int) =
    listOf(this.up(i, j), this.down(i, j), this.left(i, j), this.right(i, j)).filter { it != -1 }

fun List<List<Int>>.iterate(fn: (i: Int, j: Int, element: Int) -> Unit) {
    for (i in this.indices) {
        for (j in this[i].indices) {
            val current = this[i][j]
            fn(i, j, current)
        }
    }
}

data class Heightmap(val data: List<List<Int>>) {


    fun lowest(): Int {

        val mins= mutableListOf<Int>()
        data.iterate { i, j, element ->
            val minOf = data.allLoc(i, j).minOf { it }
            if (element < minOf) {
                mins.add(element)
            }
        }
        return mins.sumOf { it + 1 }
    }
}

