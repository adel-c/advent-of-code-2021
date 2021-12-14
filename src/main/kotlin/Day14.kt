class Day14(path: String = "day14/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {
        val (template, map) = parse()
        return Polym(template, map).count1()
    }

    fun compute2(): Long {
        val (template, map) = parse()
        return Polym(template, map).count2()
    }

    fun parse(): Pair<String, Map<String, Char>> {
        val template = inputData[0]
        val toMap = inputData.filter { it.contains("->") }.map { it.split(" -> ") }
            .associate { it[0] to it[1].toCharArray()[0] }
        val mapKeys = toMap.mapKeys { Pair(it.key.toCharArray()[0], it.key.toCharArray()[1]) }
        return Pair(template, toMap)
    }

    data class Polym(val template: String, val R: Map<String, Char>) {
        fun count2(): Long {
            return iterations(40)
        }

        fun count1(): Long {
            return iterations()
        }

        private fun iterations(nbIteration: Int = 10): Long {
            val pairCounter = template.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }
            val result = (1..nbIteration).fold(pairCounter) { acc, _ -> iteration(acc) }

            val charCounter = mutableMapOf<Char, Long>()
            result.forEach {
                val key = it.key[0]
                charCounter[key] = (charCounter[key] ?: 0L) + it.value
            }
            charCounter[template.last()] = charCounter[template.last()]!! + 1L
            val (min, max) = charCounter.values.fold(Pair(Long.MAX_VALUE, Long.MIN_VALUE)) { acc, i -> i.minMax(acc) }

            return max - min
        }

        private fun iteration(PairCounter: Map<String, Long>): Map<String, Long> {
            val newPairCounter = mutableMapOf<String, Long>()
            PairCounter.forEach { entry ->
                val newChar = R.getOrDefault(entry.key, "")
                val key1 = "${entry.key[0]}$newChar"
                val key2 = "$newChar${entry.key[1]}"
                newPairCounter.put(key1, (newPairCounter[key1] ?: 0L) + entry.value)
                newPairCounter.put(key2, (newPairCounter[key2] ?: 0L) + entry.value)

            }

            return newPairCounter
        }


    }
}
