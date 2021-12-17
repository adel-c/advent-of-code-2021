class Day17(path: String = "day17/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {
        return TrickShot(parse()).high().toLong()
    }

    fun compute2(): Long {
        return 0
    }

    fun parse(): TargetArea {
        //target area: x=137..171, y=-98..-73
        val flatMap = inputData[0].replace("target area: x=", "")
            .replace("y=", "")
            .split(",")
            .flatMap { it.split("..") }
            .map { it.trim() }
            .map { it.toInt() }

        return TargetArea(flatMap[0], flatMap[1], flatMap[2], flatMap[3])
    }

    data class TargetArea(val x1: Int, val x2: Int, val y1: Int, val y2: Int)

    data class TrickShot(val targetArea: TargetArea) {
        fun high(): Int {
            println(targetArea)
            return 0
        }
    }
}
