import kotlin.math.pow
import kotlin.math.roundToInt

class Day19(path: String = "day19/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {

        val parse = parse()

        println(parse)
        val scanner1 = parse[0]
        val scanner2 = parse[1]
        val doubles = scanner1.relativeDistances() - scanner2.relativeDistances()
        val size = doubles.size
        println(size)
        println(size > (12 * 13) / 2)
        val listP = mutableListOf<Pair<Point3d, Point3d>>()
        for (p1 in scanner1.points) {
            val distances1 = (scanner1.points - p1).map { p1.distanceTo(it) }

            for (p2 in scanner2.points) {

                val distances2 = (scanner2.points - p2).map { p2.distanceTo(it) }
                val doubles1 = distances1.toSet() - distances2.toSet()
                //println("$p1 and $p2 have relate distance ${doubles1.size}")
                if (doubles1.size < 15) {
                    println("$p1 and $p2 have relate distance ${p1.distanceTo(p2)}")
                    listP.add(p1 to p2)
                }

            }
        }

        listP.forEach {
            val p1 = it.first
            val p2 = it.second
            println("$p1 -> $p2 | ${p1 - p2} | ${p1 - p2.rotateX()} | ${p1 - p2.rotate()} |  ${p1 - p2.flip()}")
        }
        println()
        return 0
    }

    fun compute2(): Long {
        return 0
    }

    fun parse(): List<Scanner> {
        val scans = mutableListOf<Scanner>()
        val currentListPoint = mutableListOf<Point3d>()
        inputData.filter { it.isNotBlank() }.drop(1).forEach { l ->
            if (l.startsWith("---")) {
                scans.add(Scanner(currentListPoint.toList()))
                currentListPoint.clear()
            } else {
                val (x, y, z) = l.split(",").map { it.toInt() }
                currentListPoint.add(Point3d(x, y, z))
                // currentListPoint.add(Point3d(x, z, y))
                // currentListPoint.add(Point3d(-y, z, -x))
            }

        }
        scans.add(Scanner(currentListPoint.toList()))
        return scans
    }

    data class Scanner(val points: List<Point3d>) {
        /*
        n(n−1)/2
         */
        fun relativeDistances(): Set<Int> {
            return permutations(points, 2).map { it[0].distanceTo(it[1]) }.toSet()
        }
    }

    data class Point3d(val x: Int, val y: Int, val z: Int) {
        fun distanceTo(p: Point3d): Int {
            val d = (p.x - x).toDouble().pow(2) + (p.y - y).toDouble().pow(2) + (p.z - z).toDouble().pow(2)
            val pow = d.pow(1.0 / 2)
            return pow.roundToInt()

        }
        // currentListPoint.add(Point3d(x, z, y))
        // currentListPoint.add(Point3d(-y, z, -x))
        operator fun minus(p: Point3d): Point3d {
            return Point3d(x - p.x, y - p.y, z - p.z)
        }
        fun rotate(): Point3d {
            return Point3d(x, z , y )
        }
        fun flip(): Point3d {
            return Point3d(-y, z , -x )
        }
        fun rotateX(): Point3d {
            return Point3d(-x, y , z)
        }
        override fun toString(): String {
            return "($x,$y,$z)"
        }


    }
}
/*
--- scanner 0 ---
-809,-750,623
-853,-746,517
-136,-29,-84
 */
