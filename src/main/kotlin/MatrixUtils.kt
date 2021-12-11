import java.util.*


data class DataPoint(val i: Int, val j: Int, val value: Int) {
    fun point() = Point(i, j)
}

private infix fun Int.progressTo(b: Int) = if (this < b) this..b else this downTo b
data class Point(val i: Int, val j: Int) {

    fun isDiagonal(p: Point): Boolean = (i != p.i) && (j != p.j)//((x - p.x).absoluteValue == (y - p.y).absoluteValue)
    fun progressionX(p: Point) = i progressTo p.i
    fun progressionY(p: Point) = j progressTo p.j
}

data class Matrix(private val oData: List<List<Int>>) {
    val data: List<MutableList<Int>> = oData.map { it.toMutableList() }
    fun up(p: DataPoint) = oPoint(p.i - 1, p.j)


    fun down(p: DataPoint) = oPoint(p.i + 1, p.j)


    fun left(p: DataPoint) = oPoint(p.i, p.j - 1)


    fun right(p: DataPoint) = oPoint(p.i, p.j + 1)


    fun upLeft(p: DataPoint) = oPoint(p.i - 1, p.j - 1)
    fun upRight(p: DataPoint) = oPoint(p.i - 1, p.j + 1)
    fun downLeft(p: DataPoint) = oPoint(p.i + 1, p.j - 1)
    fun downRight(p: DataPoint) = oPoint(p.i + 1, p.j + 1)
    fun aroundNoDiag(p: DataPoint) =
        listOf(up(p), down(p), left(p), right(p)).filter { it.isPresent }.map { it.get() }

    fun around(p: DataPoint) =
        listOf(
            up(p),
            down(p),
            left(p),
            right(p),
            upLeft(p),
            upRight(p),
            downLeft(p),
            downRight(p)
        ).filter { it.isPresent }.map { it.get() }

    private fun oPoint(i: Int, j: Int) = if (i >= 0 && i < data.size && j >= 0 && j < data[i].size) Optional.of(
        DataPoint(
            i,
            j,
            data[i][j]
        )
    ) else Optional.empty()

    fun eachData() = sequence {
        for (i in data.indices) {
            for (j in data[i].indices) {
                val current = data[i][j]
                yield(DataPoint(i, j, current))
            }
        }
    }

    fun map(fn: (DataPoint) -> Int) {
        eachData().forEach {
            data[it.i][it.j] = fn(it)
        }
    }

    fun set(p: Point, value: Int) {
        data[p.i][p.j] = value
    }
}
