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

    data class Probe(val speed: Speed, val position: Point = Point(0, 0)) {
        fun step() = Probe(speed.step(), position.copy(i = position.i + speed.x, j = position.j + speed.y))
    }


    data class TrickShot(val targetArea: Area) {
        fun high(): Int {
            println(targetArea)
            var probe = Probe(Speed(7, 2))
            (1..10).forEach {
                val inArea = probe.position.inArea(targetArea)
                if (inArea) {
                    println("$it $probe in area $inArea")

                }
                println("$it $probe in area $inArea")
                probe = probe.step()

            }
            return 0
        }
    }

}
