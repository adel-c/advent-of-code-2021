import java.lang.Integer.max
import kotlin.math.absoluteValue

class Day17(path: String = "day17/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {
        return TrickShot(parse()).high().toLong()
    }

    fun compute2(): Long {
        return TrickShot(parse()).distinct().toLong()
    }

    fun parse(): Area {
        val flatMap = inputData[0].replace("target area: x=", "")
            .replace("y=", "").split(",").flatMap { it.split("..") }.map { it.trim().toInt() }

        return Area(flatMap[0], flatMap[1], flatMap[2], flatMap[3])
    }

    data class Speed(val x: Int, val y: Int) {
        private fun Int.stepToZero() = if (this == 0) 0 else if (this < 0) this + 1 else this - 1
        fun step() = Speed(x.stepToZero(), y - 1)
    }

    data class Probe(val speed: Speed, val maxPosition: Int = 0, val position: Point = Point(0, 0)) {
        private fun step(): Probe {
            val newJ = position.j + speed.y
            return Probe(
                speed.step(),
                maxPosition = max(maxPosition, newJ),
                Point(position.i + speed.x, newJ)
            )
        }

        private fun overShot(area: Area): Boolean {
            return position.i > area.x2 || position.j < area.y1
        }

        fun shootTo(area: Area): Probe {
            var lastProb = this
            var previous: Probe
            do {
                previous = lastProb
                lastProb = lastProb.step()
            } while (!lastProb.overShot(area) || lastProb.position.inArea(area))
            return previous
        }
    }


    data class TrickShot(val targetArea: Area) {
        fun distinct(): Int {
            return tryAll().map { it.first }.toSet().size
        }

        fun high(): Int {
            return tryAll().map { it.second }.maxOf { it.maxPosition }
        }

        private fun tryAll(): Sequence<Pair<Speed, Probe>> = sequence {
            (0..targetArea.x2).forEach { x ->
                (targetArea.y1..targetArea.y1.absoluteValue).forEach { y ->
                    val shootTo = Probe(Speed(x, y)).shootTo(targetArea)
                    if (shootTo.position.inArea(targetArea)) {
                        yield(Pair(Speed(x, y), shootTo))
                    }
                }
            }
        }

    }

}
