class Day1 {
    private val data: List<Int> = "day1/input".fromResource().readLines().map(String::toInt)

    fun count(): Int {
        var lastDepth = Int.MAX_VALUE
        var increased = 0
        for (depth in data) {
            if (depth > lastDepth) {
                increased++
            }
            lastDepth = depth
        }
        return increased
    }

    fun count3(): Int {
        var lastDepth = data[0] + data[1] + data[2]
        var increased = 0
        for (i in 3 until data.size) {
            var depth = data[i - 2] + data[i - 1] + data[i]
            if (depth > lastDepth) {
                increased++
            }
            lastDepth = depth
        }
        return increased
    }

}
