class Day1 {
    private val inputData: List<Int> = "day1/input".fromResource().readLines().map(String::toInt)

    fun count()= count(inputData)

    fun count3()=count(inputData.windowed(3, 1) { it.sum() })

    private fun count(values: List<Int>)= values.windowed(2, 1) { if (it[1] > it[0]) 1 else 0 }.sum()

}
