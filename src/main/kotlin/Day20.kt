class Day20(path: String = "day20/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    val dataLine: List<Int> = inputData[0].map { if (it == '#') 1 else 0 }

    fun parseMatrix(): Set<Point> {
        return inputData.drop(1).filter(String::isNotBlank)
            .flatMapIndexed { i, r ->
                r.mapIndexed { j, c -> if (c == '#') Point(i, j) else Point(-1, -1) }.filter { it != Point(-1, -1) }
            }.toSet()
    }

    fun compute(): Long {

        return enhance(2)
    }

    private fun enhance(iterations: Int): Long {
        var set = parseMatrix()
        var outsideValue = "0"
        repeat(iterations) {
            set = step(set, dataLine, outsideValue)

            outsideValue = if (outsideValue == "0") {
                dataLine[0].toString()
            } else {
                dataLine.last().toString()
            }

        }
        return set.count().toLong()
    }

    fun step(points: Set<Point>, dataLine: List<Int>, outsideValue:String): Set<Point> {
        val (minI, maxI) = points.map { it.i }.fold((Int.MAX_VALUE to Int.MIN_VALUE)) { acc, v -> v.minMax(acc) }
        val (minJ, maxJ) = points.map { it.j }.fold((Int.MAX_VALUE to Int.MIN_VALUE)) { acc, v -> v.minMax(acc) }
        val newSet = points.toMutableSet()
        for (i in minI - 1..maxI +1) {
            for (j in minJ - 1..maxJ +1) {
                val currentPoint = Point(i, j)
                val aroundMatrix = currentPoint.aroundMatrix()
                val binValue =
                    aroundMatrix.joinToString("") { p ->
                        pixelValue(p, minI.. maxI, minJ .. maxJ, outsideValue, points)
                    }
                val dataIndex = binValue.toInt(2)
                if (dataLine[dataIndex] == 1) {
                    newSet.add(currentPoint)
                }else{
                    newSet.remove(currentPoint)
                }

            }
        }
        return newSet
    }

    private fun pixelValue(
        p: Point,
        iRange: IntRange,
        jRange: IntRange,
        defaultValue: String,
        points: Set<Point>
    ): String {
        return if (p.i in iRange && p.j in jRange ) {
            if (points.contains(p))  "1" else  "0"
        } else defaultValue
    }

    fun compute2(): Long {
        return enhance(50)
    }

    fun print(points:Set<Point>) {
        val (minI, maxI) = points.map { it.i }.fold((Int.MAX_VALUE to Int.MIN_VALUE)) { acc, v -> v.minMax(acc) }
        val (minJ, MaxJ) = points.map { it.j }.fold((Int.MAX_VALUE to Int.MIN_VALUE)) { acc, v -> v.minMax(acc) }
        for (i in minI - 2..maxI + 2) {
            for (j in minJ - 2..MaxJ + 2) {
                if(points.contains(Point(i,j))){
                    print("#")
                }else{
                    print(".")
                }

            }
            println()
        }
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
