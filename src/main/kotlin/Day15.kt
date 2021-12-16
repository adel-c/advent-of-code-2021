class Day15(path: String = "day15/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        return ShortestPath(parse()).shortPath1()
    }

    fun compute2(): Int {
        return ShortestPath(parse()).shortPath2()
    }

    fun parse(): Matrix {
        return Matrix(inputData.filter { it.isNotBlank() }.map { it.split("").filter { it.isNotBlank() } }.map { it.map { v -> v.toInt() } })
    }


    data class ShortestPath(val matrix: Matrix) {
        fun shortPath1(): Int {
            val targetPoint = matrix.last()
            val get = matrix.get(0, 0)
            val path = mutableListOf(get)
            val visited = mutableSetOf(get)
            while (path.last() != targetPoint) {
                val aroundNoDiag = matrix.aroundNoDiag(path.last().point())
                val candidate = aroundNoDiag - path.toSet() - visited
                val closest = candidate.minByOrNull { it.value }
                if (closest != null) {
                    path.add(closest)
                } else {
                    visited.add(path.removeLast())
                }

            }

            return path.sumOf { it.value } - get.value
        }

        fun shortPath2(): Int {
            return 0
        }

    }
}
