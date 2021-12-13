class Day13(path: String = "day13/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val (matrix,instructions) = parse()
        return 0
    }

    fun compute2(): Int {
        return 0
    }

    fun parse(): Pair<Matrix, List<FoldInst>> {
        val matPoint = inputData.filter { !it.startsWith("fold") && it.isNotBlank() }.map { it.split(",") }
            .map { Point(it[0].toInt(), it[1].toInt()) }.toSet()
        val instruction =
            inputData.filter { it.startsWith("fold") }.map { it.replace("fold along ", "") }.map { it.split("=") }
                .map { FoldInst(it[0], it[1].toInt()) }
        val fold = matPoint.fold(Pair(0, 0)) { acc, point -> Pair(acc.first.max(point.i), acc.second.max(point.j)) }
        val matrix = List(fold.first) {x-> List(fold.second) {y-> if(matPoint.contains(Point(x,y))) 1 else 0 } }
        return Pair(Matrix(matrix), instruction)
    }

    class FoldInst(val axe: String, val value: Int) {

    }
}
