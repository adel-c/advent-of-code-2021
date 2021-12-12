import java.util.*

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


        return map.fold(mutableMapOf()) { acc, pair ->

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
        val endCave = map["end"]!!
        val paths = mutableListOf<List<Cave>>()
        val stack = Stack<Cave>()
        val deadEnd = mutableSetOf<Cave>()
        stack.push(map["start"]!!)
        while (canMove(stack)) {
            val currentCave = stack.peek()

            var notVisited = currentCave.pathTo
                .filter { !stack.filter(this::multiple).toList().contains(it) }
                .filter { !deadEnd.contains(it) }


            if (notVisited.contains(endCave)) {
                val element = stack.toList()
                paths.add(element + endCave)
            }
            notVisited = notVisited - endCave
            if (notVisited.isNotEmpty()) {
                val toVisit = notVisited[0]
                stack.push(toVisit)
            } else {
                deadEnd.add(currentCave)
                stack.pop()

            }

        }
        return paths.size
    }

    private fun path2(){

    }
    private fun multiple(toVisit: Cave): Boolean {
        return !toVisit.big || toVisit.name == "start" || toVisit.name == "end"
    }

    private fun canMove(stack: Stack<Cave>): Boolean {
        return stack.isNotEmpty()
    }
}
