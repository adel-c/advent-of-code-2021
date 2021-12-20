import java.util.*


data class DataPoint(val i: Int, val j: Int, val value: Int) {
    fun point() = Point(i, j)
}

data class Area(val x1: Int, val x2: Int, val y1: Int, val y2: Int)

private infix fun Int.progressTo(b: Int) = if (this < b) this..b else this downTo b
data class Point(val i: Int, val j: Int) {

    fun isDiagonal(p: Point): Boolean = (i != p.i) && (j != p.j)//((x - p.x).absoluteValue == (y - p.y).absoluteValue)
    fun progressionX(p: Point) = i progressTo p.i
    fun progressionY(p: Point) = j progressTo p.j

    fun aroundNoDiag(maxI: Int = Int.MAX_VALUE, maxJ: Int = Int.MAX_VALUE): List<Point> = listOf(
        this.copy(i = i + 1),
        this.copy(i = i - 1),
        this.copy(j = j + 1),
        this.copy(j = j - 1)
    ).filter { it.i in 0 until maxI && it.j in 0 until maxJ }

    fun inArea(area: Area): Boolean {
        return i >= area.x1 && i <= area.x2 && j >= area.y1 && j <= area.y2
    }
}

data class Matrix(private val oData: List<List<Int>>) {
    val data: List<MutableList<Int>> = oData.map { it.toMutableList() }
    fun up(p: Point) = oPoint(p.i - 1, p.j)


    fun down(p: Point) = oPoint(p.i + 1, p.j)


    fun left(p: Point) = oPoint(p.i, p.j - 1)


    fun right(p: Point) = oPoint(p.i, p.j + 1)


    fun upLeft(p: Point) = oPoint(p.i - 1, p.j - 1)
    fun upRight(p: Point) = oPoint(p.i - 1, p.j + 1)
    fun downLeft(p: Point) = oPoint(p.i + 1, p.j - 1)
    fun downRight(p: Point) = oPoint(p.i + 1, p.j + 1)
    fun aroundNoDiag(p: Point) =
        listOf(up(p), down(p), left(p), right(p)).filter { it.isPresent }.map { it.get() }

    fun around(p: Point) =
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

    fun get(i: Int, j: Int): DataPoint {
        return DataPoint(i, j, data[i][j])
    }

    fun getOrDefault(p:Point, defaultValue:Int=Int.MIN_VALUE): DataPoint {
        return if(sizeX()>p.i && sizeY()>p.j && p.i>=0 && p.j>=0){
            DataPoint(p.i,p.j, data[p.i][p.j])
        }else{
            DataPoint(p.i,p.j, defaultValue)
        }

    }

    fun last(): DataPoint {
        return get(data.lastIndex, data[0].lastIndex)
    }

    fun incAll(points: List<Point>) {
        points.forEach {
            set(it, data[it.i][it.j] + 1)
        }
    }

    fun splitY(y: Int): Pair<Matrix, Matrix> {
        return Pair(
            Matrix(data.subList(0, y)),
            Matrix(data.subList(y, data.size))
        )
    }

    fun sizeX(): Int = data.size
    fun sizeY(): Int = data[0].size
}
