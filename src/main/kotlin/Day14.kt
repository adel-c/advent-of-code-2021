class Day14(path: String = "day14/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        val (template, map) = parse()
        return Polym(template, map).count1()
    }

    fun compute2(): Int {
        val (template, map) = parse()
        return Polym(template, map).count2()
    }

    fun parse(): Pair<String, Map<String, String>> {
        val template = inputData[0]
        val toMap = inputData.filter { it.contains("->") }.map { it.split(" -> ") }.associate { it[0] to it[1] }
        return Pair(template, toMap)
    }

    data class Polym(val template: String, val map: Map<String, String>) {
        fun count2(): Int {
            return iteration(40)
        }

        fun count1(): Int {
            return iteration()
        }

        private fun iteration(iteration: Int = 10): Int {
            var ori = template
            for (i in 1..iteration) {
                ori = it1(ori)
            }
            val charCounts = ori.toCharArray().groupBy { it }.mapValues { it.value.count() }.values
            val (min, max) = charCounts.fold(Pair(Int.MAX_VALUE, Int.MIN_VALUE)) { acc, i -> i.minMax(acc) }


            return max - min
        }

        private fun it1(ori: String): String {
            var ori1 = ori
            var temp =
                ori1.windowed(2).map { convert(it) }.mapIndexed { index, s -> if (index != 0) s.substring(1) else s }
                    .joinToString("")

            ori1 = temp
            return ori1
        }

        private fun convert(value: String): String {

            return if (map.containsKey(value)) value[0] + map.getOrDefault(value, "") + value[1]
            else value

        }
    }
}
