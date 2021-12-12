import java.util.*

class Day12(path: String = "day12/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        return Paths(parse()).path1()
    }

    fun compute2(): Int {
        return Paths(parse()).path2()
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
            acc[pair.second.name]!!.pathTo.add(acc[pair.first.name]!!)
            acc
        }
    }
}

data class Cave(val name: String) {
    val big = name.uppercase() == name
    val pathTo = mutableSetOf<Cave>()
}

class Paths(val map: Map<String, Cave>) {
    val endCave = map["end"]!!
    val startCave = map["start"]!!


    fun path1(): Int {
        val path2 = pathRec(listOf(map["start"]!!),this::canVisit1)

        val filter = path2.filter { it.last() == map["end"]!! }
        return filter.size
    }
    fun path2(): Int {
        val path2 = pathRec(listOf(map["start"]!!),this::canVisit2)

        val filter = path2.filter { it.last() == map["end"]!! }
        return filter.size
    }
    private fun pathRec(currentPath: List<Cave>,visitPredicate:( List<Cave>,Cave)->Boolean): List<List<Cave>> {
        val lastElement = currentPath.last()

        val filter = lastElement.pathTo.filter {
            visitPredicate(currentPath, it)
        }
        val nextPaths = filter.map { currentPath + it }
        val map1: List<List<Cave>> = nextPaths.flatMap { pathRec(it,visitPredicate) }
        val toMutableList = map1.toMutableList()
        toMutableList.add(currentPath)
        return toMutableList.toList()
    }


    private fun canVisit1(currentPath: List<Cave>, toVisit: Cave): Boolean {
        val currentPath = !currentPath.contains(toVisit)
        val big = toVisit.big
        return currentPath || big
    }

    private fun canVisit2(currentPath: List<Cave>, toVisit: Cave): Boolean {
        if (toVisit.big) {
            return true
        }
        if (toVisit == startCave) {
            return false
        }
        if (currentPath.contains(endCave)) {
            return false
        }
        if(!currentPath.contains(toVisit)){
            return true
        }
        val groupingBy =
            currentPath.filter { !it.big }.groupingBy { it }.eachCount()

        val duplicatedSmall =
            groupingBy
                .filter { it.value > 1 }

        return duplicatedSmall.isEmpty()
    }

    private fun canMove(stack: Stack<Cave>): Boolean {
        return stack.isNotEmpty()
    }
}
