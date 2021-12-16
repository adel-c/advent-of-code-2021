import java.util.*

class Day15(path: String = "day15/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        return ShortestPath(parse()).shortPath1()
    }

    fun compute2(): Int {
        return ShortestPath(parse()).shortPath2()
    }

    fun parse(): Matrix {
        return Matrix(inputData.filter { it.isNotBlank() }.map { it.split("").filter { it.isNotBlank() } }
            .map { it.map { v -> v.toInt() } })
    }

    data class PathHead(val dataPoint: DataPoint, val totalValue: Int) : Comparable<PathHead> {
        override fun compareTo(other: PathHead): Int = this.totalValue - other.totalValue

    }

    data class ShortestPath(val matrix: Matrix) {
        fun shortPath1(): Int {
            return shortPath(matrix)
        }

        private fun shortPath(map: Matrix): Int {
            val targetPoint = map.last()
            val start = map.get(0, 0)
            val path = PriorityQueue<PathHead>().apply { add(PathHead(start, 0)) }
            val visited = mutableSetOf<DataPoint>()
            while (path.isNotEmpty()) {
                val poll = path.poll()
                if (poll.dataPoint == targetPoint) {
                    return poll.totalValue
                }
                if (poll.dataPoint !in visited) {
                    visited.add(poll.dataPoint)
                    val aroundNoDiag = map.aroundNoDiag(poll.dataPoint.point())
                    aroundNoDiag.map { PathHead(it, poll.totalValue + it.value) }.forEach(path::offer)
                }


            }

            TODO("should not happen")
        }


        fun shortPath2(): Int {
            return 0
        }

    }
}
