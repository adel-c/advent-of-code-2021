class Day11(path: String = "day11/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        return OctoGrid(inputData.map {line-> line.split("").filter { it.isNotBlank() }.map { it.toInt() }.toMutableList() }).countFlashes()
    }

    fun compute2(): Int {
        return 0
    }
}

data class OctoGrid(val grid: List<MutableList<Int>>) {
    private val matrix= Matrix(grid)
    fun countFlashes(): Int {
        var countFlashes=0
        (1..100).forEach { _ ->
            countFlashes+= doFlashIteration()
        }

        return countFlashes
    }

    private fun doFlashIteration():Int {
        var flashesCount = 0
        val flashed = mutableSetOf<Point>()
        var flashedSize: Int
        matrix.map { it.value + 1 }

        do {
            flashedSize = flashed.size
            val flashedInIteration =
                matrix.eachData().filter { it.value > 9 }.map { it.point() }.filter { !flashed.contains(it) }.toList()

            flashed.addAll(flashedInIteration)
            flashedInIteration.forEach {
                matrix.incAll(matrix.around(it).map(DataPoint::point))
            }

        } while (flashedSize != flashed.size)
        flashesCount += flashed.size
        flashed.forEach {
            matrix.set(it, 0)
        }
        return flashesCount
    }
}
