class Day13(path: String = "day13/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val (matrix, instructions) = parse()
        return MatFolder(matrix, instructions).fold1()
    }

    fun compute2(): Int {
        return 0
    }

    fun parse(): Pair<Set<Point>, List<FoldInst>> {
        val matPoint = inputData.filter { !it.startsWith("fold") && it.isNotBlank() }.map { it.split(",") }
            .map { Point(it[0].toInt(), it[1].toInt()) }.toSet()
        val instruction =
            inputData.filter { it.startsWith("fold") }.map { it.replace("fold along ", "") }.map { it.split("=") }
                .map { FoldInst(it[0], it[1].toInt()) }
        //val fold = matPoint.fold(Pair(0, 0)) { acc, point -> Pair(acc.first.max(point.i), acc.second.max(point.j)) }
        //val matrix = List(fold.first) { x -> List(fold.second) { y -> if (matPoint.contains(Point(x, y))) 1 else 0 } }
        return Pair(matPoint, instruction)
    }

    class FoldInst(val axe: String, val value: Int) {

    }

    data class MatFolder(val points: Set<Point>, val inst: List<FoldInst>) {
        fun fold1(): Int {
            val inst = inst.get(0)
            val a = when (inst.axe) {
                "x" -> splitX(points, inst.value)
                "y" -> splitY(points, inst.value)
                else -> setOf()
            }
            val splitY = splitY(points, 7)
            val splitX = splitX(splitY, 5)
            val sortedBy = splitX.sortedWith(compareBy({ it.i }, { it.j }))
            return a.size
        }

        fun splitY(source: Set<Point>, y: Int): Set<Point> {
            val toFold = source.filter { it.j > y }
            val converted = toFold.map { Point(it.i, y - (it.j - y)) }
            val keep = source.filter { it.j <= y }.toMutableSet()
            return keep + converted
        }

        fun splitX(source: Set<Point>, x: Int): Set<Point> {
            val toFold = source.filter { it.i > x }
            val converted = toFold.map { Point(x - (it.i - x), it.j) }
            val keep = source.filter { it.i <= x }.toMutableSet()
            return keep + converted
        }
    }
}
