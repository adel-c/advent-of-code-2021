class Day21(path: String = "day21/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {
        val dice = Dice(100)
        val take = dice.nextValue().take(5)
        println(take.toList())
        println(dice.nextValue().take(3).toList())
        return 0
    }

    fun compute2(): Long {
        return 0
    }

    data class Dice(val sideCount: Int = 100) {
        private var currentValue = 0
        fun nextValue(): Sequence<Int> = sequence {
            while(true){
                yield(currentValue++ % sideCount)

            }

        }
    }
}
