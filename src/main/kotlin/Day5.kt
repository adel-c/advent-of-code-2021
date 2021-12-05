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
}
data class Line(val start: Point, val end: Point) {
    companion object {
        fun from(string: String): Line {
            val points = string.split("->")
            return Line(Point.from(points[0]), Point.from(points[1]))
        }
    }
    fun allPoints():List<Point>{
        return listOf()
    }
}
data class Puzzle(val lines: List<Line>)
