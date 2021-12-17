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

            return position.i > area.x2 || position.j < area.y1
        }

        fun shootTo(area: Area): Probe {
            var lastProb = this
            var previous = this
            do {
                previous = lastProb
                lastProb = lastProb.step()
            } while (!lastProb.overShot(area) || lastProb.position.inArea(area))
            return previous
        }
    }


    data class TrickShot(val targetArea: Area) {
        fun high(): Int {
            println(targetArea)

            var ProbeOk = mutableListOf<Speed>()
            /*
            var probe = Probe(Speed(7, 2))
            (1..10).forEach {
                       val inArea = probe.position.inArea(targetArea)
                       if (inArea) {
                           println("$it $probe in area $inArea")

                       }
                       println("$it $probe in area $inArea overShot ${probe.overShot(targetArea)}")
                       probe = probe.step()

                   }
       */
            var highestY = 0
            (0..targetArea.x2.absoluteValue).forEach { x ->
                (targetArea.y1..targetArea.y1.absoluteValue).forEach { y ->
                    val shootTo = Probe(Speed(x, y)).shootTo(targetArea)
                    val maxPosition = shootTo.maxPosition
                    val inArea = shootTo.position.inArea(targetArea)
                    if (inArea) {
                        println(" $shootTo in area while x=$x,y=$y and max $maxPosition ")
                        highestY = max(highestY, maxPosition)
                    }


                }
            }
            println(highestY)
            return highestY
        }


    }

}
