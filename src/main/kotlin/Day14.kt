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
            return iteration(40)
        }

        fun count1(): Long {
            return iteration()
        }

        private fun iteration(iteration: Int = 10): Long {
            var PairCount = mutableMapOf<String, Long>()
            template.windowed(2).forEach { PairCount.put(it, PairCount.getOrDefault(it, 0L) + 1) }
            val sortedMap = PairCount.toList().sortedByDescending { (k, v) -> v }.toMap()
            println(sortedMap)
            for (i in 1..iteration) {
                PairCount = it1(PairCount)
            }


            val CharCounter = mutableMapOf<Char, Long>()

            PairCount.forEach {
                val key = it.key[0]
                CharCounter[key] = CharCounter.getOrDefault(key, 0) + it.value
            }
            CharCounter[template.last()] = CharCounter[template.last()]!! + 1L
            val (min, max) = CharCounter.values.fold(Pair(Long.MAX_VALUE, Long.MIN_VALUE)) { acc, i -> i.minMax(acc) }

            return max - min
        }

        private fun it1(PairCounter: MutableMap<String, Long>): MutableMap<String, Long> {
            val newPairCounter = mutableMapOf<String, Long>()
            PairCounter.forEach { entry ->

                val newChar = R.getOrDefault(entry.key, "")

                val key1 = "${entry.key[0]}$newChar"
                val key2 = "$newChar${entry.key[1]}"
                newPairCounter.put(key1, newPairCounter.getOrDefault(key1, 0) + entry.value)
                newPairCounter.put(key2, newPairCounter.getOrDefault(key2, 0) + entry.value)

            }

            return newPairCounter
        }


    }
}
