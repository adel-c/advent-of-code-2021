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

    data class PathHead(val dataPoint: Point, val totalValue: Int) : Comparable<PathHead> {
        override fun compareTo(other: PathHead): Int = this.totalValue - other.totalValue


    }

    data class ShortestPath(val matrix: Matrix) {
        fun shortPath1(): Int {
            val targetPoint = matrix.last()
            return shortPath(matrix, targetPoint.point())
        }

        private fun shortPath(map: Matrix, targetPoint: Point): Int {

            val start = map.get(0, 0)
            val path = PriorityQueue<PathHead>().apply { add(PathHead(start.point(), 0)) }
            val visited = mutableSetOf<Point>()
            while (path.isNotEmpty()) {
                val poll = path.poll()
                if (poll.dataPoint == targetPoint) {
                    return poll.totalValue
                }
                if (poll.dataPoint !in visited) {
                    visited.add(poll.dataPoint)
                    val aroundNoDiag = poll.dataPoint.aroundNoDiag(maxI = map.sizeX(), maxJ = map.sizeY())

                    aroundNoDiag.map { PathHead(it, poll.totalValue + pointRisk(it, map)) }.forEach(path::offer)
                }


            }

            TODO("should not happen")
        }

        fun pointRisk(p: Point, map: Matrix): Int {
          /*  val di: Int = p.i / map.sizeX()
            val dj: Int = p.j / map.sizeY()
            val originalValue: Int = map.get(p.i%map.sizeX(),p.j%map.sizeY()).value
            val newRisk = (originalValue + di + dj)
            return newRisk.takeIf { it < 10 } ?: (newRisk - 9)*/

            return map.get(p.i,p.j).value
        }

        fun shortPath2(): Int {
            val newData = ArrayList<ArrayList<Int>>()

            return 0
        }

    }
}
