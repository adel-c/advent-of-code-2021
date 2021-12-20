class Day20(path: String = "day20/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    val dataLine = inputData[0].map { if (it == '#') 1 else 0 }

    fun parseMatrix(): Matrix {
        return Matrix(inputData.drop(1).filter(String::isNotBlank).map { l -> l.map { if (it == '#') 1 else 0 } })
    }

    fun compute(): Long {
        println(dataLine)
        var matrix = parseMatrix()
        (0..2).forEach {
            var temp = Matrix(matrix.data.map { it.toList() })
            temp.map { dataPoint ->
                val binValue =
                    dataPoint.point().aroundMatrix().joinToString("") { matrix.getOrDefault(it, 0).value.toString() }
                val index = binValue.toInt(2)
                val newValue = dataLine[index]
                newValue
            }
            matrix = temp
        }
        matrix.print()
        return matrix.eachData().filter { it.value ==1 }.count().toLong()
    }

    fun compute2(): Long {
        return 0
    }
    fun Matrix.print(){
        val print = this.data.joinToString("\n") { line ->
            line.map { if (it == 1) '#' else '.' }.joinToString("")
        }
        println(print)
    }
    fun Point.aroundMatrix(): List<Point> =
        listOf(
            this.copy(i = i - 1, j = j - 1),
            this.copy(i = i - 1),
            this.copy(i = i - 1, j = j + 1),
            this.copy(j = j - 1),
            this,
            this.copy(j = j + 1),
            this.copy(i = i + 1, j = j - 1),
            this.copy(i = i + 1),
            this.copy(i = i + 1, j = j + 1),
        )
}
