class Day5(path: String = "day5/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): Puzzle {
        val lines = inputData.map(Line::from)
        return Puzzle(lines)
    }

}

data class Point(val x: Int, val y: Int) {
    companion object {
        fun from(string: String): Point {
            val coord = string.split(",")
            return Point(coord[0].trim().toInt(), coord[1].trim().toInt())
        }
    }

    fun isDiagonal(p: Point): Boolean = (x != p.x) && (y != p.y)
    fun rangeX(p: Point) = range(x, p.x)
    fun rangeY(p: Point) = range(y, p.y)

    private fun range(a: Int, b: Int) = if (a < b) a..b else a downTo b
}

data class Line(val start: Point, val end: Point) {
    companion object {
        fun from(string: String): Line {
            val points = string.split("->")
            return Line(Point.from(points[0]), Point.from(points[1]))
        }
    }

    fun allPoints(ignoreDiagonals: Boolean = true): Set<Point> {
        if (ignoreDiagonals && start.isDiagonal(end)) return setOf()

        return pairGenerator(start.rangeX(end), start.rangeY(end))
            .map { Point(it.first, it.second) }.toSet()
    }

    private fun pairGenerator(rangeX: IntProgression, rangeY: IntProgression) =
        if (start.isDiagonal(end))
            rangeX.zip(rangeY)
        else
            rangeX.flatMap { x -> rangeY.map { y -> Pair(x, y) } }
}

data class Puzzle(val lines: List<Line>) {
    fun mostDangerousCount(): Int {
        return count()
    }

    fun mostDangerousDiagonalCount(): Int {
        return count(false)
    }

    private fun count(ignoreDiagonals: Boolean = true): Int {
        return lines.flatMap { it.allPoints(ignoreDiagonals) }
            .groupingBy { it }
            .eachCount()
            .filterValues { it >= 2 }
            .count()
    }
}
