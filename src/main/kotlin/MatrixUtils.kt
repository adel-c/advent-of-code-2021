import java.util.*


data class DataPoint(val i: Int, val j: Int, val value: Int) {

}

data class Matrix(val data: List<List<Int>>) {
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

    private fun oPoint(i: Int, j: Int) = if (i >= 0 && i < data.size  && j >= 0 && j < data[i].size ) Optional.of(
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
}
