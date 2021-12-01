class Day1 {
    private val InputData: List<Int> = "day1/input".fromResource().readLines().map(String::toInt)

    fun count(): Int {
        return count(InputData)
    }

    fun count3(): Int {
        return count(InputData.windowed(3, 1) { it.sum() })
    }

    private fun count(values: List<Int>): Int {
        return values.windowed(2, 1) { if (it[1] > it[0]) 1 else 0 }.sum()
    }
/*
    private fun ocount(values: List<Int>): Int {
        var lastDepth = values.first()
        var increased = 0
        for (depth in values) {
            if (depth > lastDepth) {
                increased++
            }
            lastDepth = depth
        }
        return increased
    }
*/
}
