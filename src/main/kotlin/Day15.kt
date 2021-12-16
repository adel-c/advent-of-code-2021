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


    data class ShortestPath(val matrix: Matrix) {
        fun shortPath1(): Int {
            val targetPoint = matrix.last()
            val get = matrix.get(0, 0)
            val paths = mutableListOf<List<DataPoint>>()
            val path = mutableListOf(get)
            val visited = mutableSetOf(get)
            while (path.isNotEmpty()) {

                val aroundNoDiag = matrix.aroundNoDiag(path.last().point())
                val candidate = aroundNoDiag - path.toSet() - visited
                val closest = candidate.minByOrNull { it.value }
                if (candidate.contains(targetPoint)) {
                    paths.add(path.toList() + targetPoint)
                    path.removeLast()
                } else if (closest != null) {
                    visited.add(closest)
                    path.add(closest)
                } else {
                    path.removeLast()
                }
            }

        return paths.map{ it.sumOf { it.value } - get.value }.minOrNull() ?: 0
    }

    fun shortRec(path: List<DataPoint>):List<List<DataPoint>>{
        val targetPoint = matrix.last()
        val start = matrix.get(0, 0)
        val aroundNoDiag = matrix.aroundNoDiag(path.last().point())
        val candidates = aroundNoDiag - path.toSet()
        if (candidates.contains(targetPoint)){
            return listOf(path+targetPoint)
        }else{
            return candidates.flatMap { shortRec(path+it) }
        }

    }
    fun shortPath2(): Int {
        return 0
    }

}
}
