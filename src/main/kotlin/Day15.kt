class Day15(path: String = "day15/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        return ShortestPath(parse()).shortPath1()
    }

    fun compute2(): Int {
        return ShortestPath(parse()).shortPath2()
    }

    fun parse(): Matrix {
        return Matrix(inputData.filter { it.isNotBlank() }.map { it.split("") }.map { it.map { v -> v.toInt() } })
    }


    data class ShortestPath(val matrix:Matrix){
        fun shortPath1(): Int {
           return 0
        }

        fun shortPath2(): Int {
          return 0
        }

    }
}
