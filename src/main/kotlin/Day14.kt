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

    fun parse(): Pair<String, Map<Pair<Char, Char>, Char>> {
        val template = inputData[0]
        val toMap = inputData.filter { it.contains("->") }.map { it.split(" -> ") }
            .associate { it[0] to it[1].toCharArray()[0] }
        val mapKeys = toMap.mapKeys { Pair(it.key.toCharArray()[0], it.key.toCharArray()[1]) }
        return Pair(template, mapKeys)
    }

    data class Polym(val template: String, val map: Map<Pair<Char, Char>, Char>) {
        fun count2(): Long {
            return iteration(25)
        }

        fun count1(): Long {
            return iteration()
        }

        private fun iteration(iteration: Int = 10): Long {
            var ori = template.toCharArray().toList()
            var ori2 = template
            for (i in 1..iteration) {
                ori = it1(ori)
                //ori2 = it2(ori2)
                //println("$i \n$ori\n$ori2")
            }
            val charCounts = ori.groupBy { it }.mapValues { it.value.count().toLong() }.values
            val (min, max) = charCounts.fold(Pair(Long.MAX_VALUE, Long.MIN_VALUE)) { acc, i -> i.minMax(acc) }

            return max - min
        }

        private fun it1(ori: List<Char>): List<Char> {
            val result: ArrayList<Char> = ArrayList(ori.size + (ori.size / 3))
            result.add(ori[0])
            for (i in 1..ori.lastIndex) {
                val firstChar = ori[i]
                val secondChar = ori[i - 1]
                val key = Pair(secondChar, firstChar)
                if (map.containsKey(key)) {
                    result.add(map[key]!!)
                }
                result.add(firstChar)
            }
            return result
        }

        private fun it2(ori: String): String {
            var ori1 = ori
            var temp =
                ori1.windowed(2).map { convert(it) }.mapIndexed { index, s -> if (index != 0) s.substring(1) else s }
                    .joinToString("")

            ori1 = temp
            return ori1
        }

        private fun convert(value: String): String {

            val key = Pair(value[0], value[1])
            return if (map.containsKey(key)) "${value[0]}${map.get(key)!!}${value[1]}"
            else value

        }

    }
}
