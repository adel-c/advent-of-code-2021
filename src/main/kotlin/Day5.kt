import kotlin.math.absoluteValue

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
    fun rangeX(p: Point): IntRange{
        return if(x<p.x){
            x..p.x
        }else{
            p.x..x
        }
    }
    fun rangeY(p: Point): IntRange{
        return if(y<p.y){
            y..p.y
        }else{
            p.y..y
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

    fun allPoints(): Set<Point> {
        if(start.isDiagonal(end)){
            return setOf()
        }
        val rangeX = start.rangeX(end)
        val rangeY = start.rangeY(end)

        return  rangeX.flatMap {x-> rangeY.map {y-> Point(x,y) } }.toSet()
    }
}

data class Puzzle(val lines: List<Line>)
