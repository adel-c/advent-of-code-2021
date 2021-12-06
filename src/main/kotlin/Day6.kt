class Day6(path: String = "day6/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): School {
        return School(inputData.flatMap { it.split(",") }.map(String::toInt))
    }
}

data class School(val fish: List<Int>) {
    fun advance(nbDay: Int): School {
        val newFish = (0 until nbDay).fold(fish) { acc, _ ->
            acc.flatMap {
                if (it == 0)
                    listOf(6, 8)
                else
                    listOf(it - 1)
            }
        }

        return School(newFish)
    }

    fun count(): Long {
        return fish.size.toLong()
    }
}

