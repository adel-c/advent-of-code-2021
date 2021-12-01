class Day1 {
    private val inputData: List<Int> = "day1/input".fromResource().readLines().map(String::toInt)

    fun count1()= countIncreased(inputData)

    fun count3()= countIncreased(inputData.windowed(3, 1) { it.sum() })

    private fun countIncreased(values: List<Int>)= values.windowed(2, 1) { if (it[1] > it[0]) 1 else 0 }.sum()

}
