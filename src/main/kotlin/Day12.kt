class Day12(path: String = "day12/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        return Paths(parse()).path1()
    }

    fun compute2(): Int {
        return 0
    }

    fun parse(): Map<String, Cave> {
        val map = inputData.asSequence().filter { it.isNotBlank() }
            .map { it.split("-") }
            .map { Cave(it[0]) to Cave(it[1]) }


    return map.fold(mutableMapOf()){
            acc, pair ->

        if (!acc.containsKey(pair.first.name)) {
            acc[pair.first.name] = pair.first
        }
        if (!acc.containsKey(pair.second.name)) {
            acc[pair.second.name] = pair.second
        }

        acc[pair.first.name]!!.pathTo.add(acc[pair.second.name]!!)
        acc
    }
    }
}

data class Cave(val name: String) {
    val big = name.uppercase() == name
    val pathTo = mutableSetOf<Cave>()
}

class Paths(val map: Map<String, Cave>) {
    fun path1(): Int {
        return 0
    }
}
