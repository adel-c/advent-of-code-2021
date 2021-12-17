import java.lang.Integer.max
import kotlin.math.absoluteValue

class Day17(path: String = "day17/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {
        return TrickShot(parse()).high().toLong()
    }

    fun compute2(): Long {
        return 0
    }


    fun parse(): Area {
        //target area: x=137..171, y=-98..-73
        val flatMap = inputData[0].replace("target area: x=", "")
            .replace("y=", "")
            .split(",")
            .flatMap { it.split("..") }
            .map { it.trim() }
            .map { it.toInt() }

        return Area(flatMap[0], flatMap[1], flatMap[2], flatMap[3])
    }

    data class Speed(val x: Int, val y: Int) {
        private fun Int.stepToZero() = if (this < 0) this + 1 else this - 1
        fun step() = Speed(x.stepToZero(), y - 1)
    }

    data class Probe(val speed: Speed, val maxPosition: Int = 0, val position: Point = Point(0, 0)) {
        fun step(): Probe {
            val newJ = position.j + speed.y
            return Probe(
                speed.step(),
                maxPosition = max(maxPosition, newJ),
                position.copy(i = position.i + speed.x, j = newJ)
            )
        }

        fun overShot(area: Area): Boolean {
            return position.i.absoluteValue > max(
                area.x1.absoluteValue,
                area.x2.absoluteValue
            ) || position.j.absoluteValue > max(area.y1.absoluteValue, area.y2.absoluteValue)
        }
    }


    data class TrickShot(val targetArea: Area) {
        fun high(): Int {
            println(targetArea)
            var probe = Probe(Speed(7, 2))
            var ProbeOk = mutableListOf<Speed>()
            (1..10).forEach {
                val inArea = probe.position.inArea(targetArea)
                if (inArea) {
                    println("$it $probe in area $inArea")

                }
                println("$it $probe in area $inArea overShot ${probe.overShot(targetArea)}")
                probe = probe.step()

            }


            (0..targetArea.x2.absoluteValue).forEach { x ->
                (targetArea.y1..targetArea.y1.absoluteValue).forEach { y ->

                }
            }

            return 0
        }
    }

}
