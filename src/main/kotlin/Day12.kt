class Day12(path: String = "day12/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Int {
        return Paths(parse()).path1()
    }
    fun compute2(): Int {
        return 0
    }
    fun parse(): Map<String,Cave> {
        return inputData.asSequence().filter { it.isNotBlank() }
            .map { it.split("-") }
            .map { Cave(it[0]) to Cave(it[1]) }
            .map { it.first.pathTo.add(it.second)
            it.first}
            .map { it.name to it }
            .toMap()

    }
}
data class Cave(val name:String){
    val big= name.uppercase() == name
    val pathTo= mutableSetOf<Cave>()
}
class Paths(val map:Map<String,Cave>){
    fun path1():Int{
        return 0
    }
}
