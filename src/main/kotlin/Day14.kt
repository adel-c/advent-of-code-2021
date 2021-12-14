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
            return 0
        }

        fun count1(): Int {
            return 0
        }
    }
}
