class Day6(path: String = "day6/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): School {
        return School(inputData.flatMap { it.split(",") }.map(String::toInt).toSet())
    }
}

data class School(val fish: Set<Int>) {
    fun advance(nbDay: Int): School {
        val newFish = (0 until nbDay).fold(fish) { acc, _ ->
            acc.flatMap {
                if (it == 0)
                    listOf(6, 8)
                else
                    listOf(it - 1)
            }.toSet()
        }

        return School(newFish)
    }

    fun count(): Int {
        return fish.size
    }
}

