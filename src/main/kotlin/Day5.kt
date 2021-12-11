class Day5(path: String = "day5/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): Puzzle {
        val lines = inputData.map(this::parseLine)
        return Puzzle(lines)
    }

    private fun parsePoint(string: String): Point {
        val coord = string.split(",")
        return Point(coord[0].trim().toInt(), coord[1].trim().toInt())
    }

    private fun parseLine(string: String): Line {
        val points = string.split("->")
        return Line(parsePoint(points[0]), parsePoint(points[1]))
    }
}



data class Line(val start: Point, val end: Point) {

    fun allPoints(ignoreDiagonals: Boolean = true): Set<Point> {
        if (ignoreDiagonals && start.isDiagonal(end)) return setOf()

        return pairGenerator(start.progressionX(end), start.progressionY(end))
            .map { Point(it.first, it.second) }.toSet()
    }

    private fun pairGenerator(rangeX: IntProgression, rangeY: IntProgression) =
        if (start.isDiagonal(end))
            rangeX.zip(rangeY)
        else
            rangeX.flatMap { x -> rangeY.map { y -> Pair(x, y) } }
}

data class Puzzle(val lines: List<Line>) {
    fun mostDangerousCount(): Int = count()

    fun mostDangerousDiagonalCount() = count(false)

    private fun count(ignoreDiagonals: Boolean = true): Int {
        return lines.flatMap { it.allPoints(ignoreDiagonals) }
            .groupingBy { it }
            .eachCount()
            .filterValues { it >= 2 }
            .count()
    }
}
