import java.util.*

class Day8(path: String = "day8/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    private val data = inputData.map { it.split("|") }.map { DataLine(it[0].split(" "), it[1].split(" ")) }
    fun compute(): Int {

        println(data)
        return data.map(DataLine::numberOfOneFourSevenEight).sum()
    }

    fun compute2(): Int {
        return data.map(DataLine::computeNumber).sum()
    }
}

enum class DIGIT(val representation: String, val value: String) {
    ZERO("abcefg", "0"),
    ONE("cf", "1"),
    TWO("acdeg", "2"),
    THREE("acdfg", "3"),
    FOUR("bcdf", "4"),
    FIVE("abdfg", "5"),
    SIX("abdefg", "6"),
    SEVEN("acf", "7"),
    EIGHT("abcdefg", "8"),
    NINE("abcdfg", "9");

    fun size() = representation.length

    companion object {
        private val uniqueSizeDigit: Map<Int, List<DIGIT>> = values().groupBy { it.representation.length }
        fun isUnique(value: SortedSet<Char>): Boolean {
            return uniqueSizeDigit.getOrDefault(value.size, listOf()).size == 1
        }
    }

}

data class DataLine(val dataUnSorted: List<String>, val outputUnSorted: List<String>) {
    val data: List<String> = dataUnSorted.map { it.toSortedSet().joinToString("") }.toList()
    val output: List<String> = outputUnSorted.map { it.toSortedSet().joinToString("") }.toList()
    fun numberOfOneFourSevenEight(): Int {
        return output.count { DIGIT.isUnique(it.toSortedSet()) }
    }

    fun possibilitiesBySize(data: List<String>): Map<Int, List<String>> {
        return data.groupBy { it.length }
    }
/*
    fun computeNumber2(): Int {
        val possibilitiesBySize: Map<Int, List<SortedSet<Char>>> = possibilitiesBySize(data)
        var one = possibilitiesBySize.getOrDefault(DIGIT.ONE.size(), listOf())[0]
        var seven = possibilitiesBySize.getOrDefault(DIGIT.SEVEN.size(), listOf())[0]
        var four: SortedSet<Char> = possibilitiesBySize.getOrDefault(DIGIT.FOUR.size(), listOf())[0]
        var eight = possibilitiesBySize.getOrDefault(DIGIT.EIGHT.size(), listOf())[0]

        val allSixChars: List<SortedSet<Char>> = possibilitiesBySize.getOrDefault(6, listOf())
        var nine = findInList(four, allSixChars)
        var six = findNotInList(one, allSixChars)

        var zero = (allSixChars - setOf(six, nine))[0]
        var five = valueContains(six, possibilitiesBySize.getOrDefault(DIGIT.FIVE.size(), listOf()))
        var three = findInList(seven, possibilitiesBySize.getOrDefault(DIGIT.THREE.size(), listOf()))
        var two = (data - setOf(zero, one, three, four, five, six, seven, eight, nine))[0]

        val valuesMap = listOf(zero, one, two, three, four, five, six, seven, eight, nine)
        valuesMap.forEachIndexed { index, chars ->
            println("${index} -> ${chars.joinToString("")}")
        }
        println()
        println()
        println()
        return output.map {
            when (it) {
                zero -> 0
                one -> 1
                two -> 2
                three -> 3
                four -> 4
                five -> 5
                six -> 6
                seven -> 7
                eight -> 8
                nine -> 9
                else -> 0
            }
        }.map { it.toString() }.joinToString("").toInt()
    }
*/
    fun computeNumber(): Int {
        val possibilitiesBySize: Map<Int, List<String>> = possibilitiesBySize(data)

        val one = Pair(possibilitiesBySize.getOrDefault(DIGIT.ONE.size(), listOf())[0], DIGIT.ONE)
        val seven = Pair(possibilitiesBySize.getOrDefault(DIGIT.SEVEN.size(), listOf())[0], DIGIT.SEVEN)
        val four = Pair(possibilitiesBySize.getOrDefault(DIGIT.FOUR.size(), listOf())[0], DIGIT.FOUR)
        val eight = Pair(possibilitiesBySize.getOrDefault(DIGIT.EIGHT.size(), listOf())[0], DIGIT.EIGHT)

        val allSixChars: List<String> = possibilitiesBySize.getOrDefault(6, listOf())
        val nine = Pair(findInList(four.first, allSixChars), DIGIT.NINE)
        val six = Pair(findNotInList(one.first, allSixChars), DIGIT.SIX)
        val zero: Pair<String, DIGIT> =
            Pair((allSixChars - setOf(six.first, nine.first))[0], DIGIT.ZERO)

        val five =
            Pair(valueContains(six.first, possibilitiesBySize.getOrDefault(DIGIT.FIVE.size(), listOf())), DIGIT.FIVE)
        val three=
            Pair(findInList(seven.first, possibilitiesBySize.getOrDefault(DIGIT.THREE.size(), listOf())), DIGIT.THREE)
        val first = (data - setOf(zero, one, three, four, five, six, seven, eight, nine).map { it.first }.toSet())[0]
        val two= Pair(first, DIGIT.TWO)
        val valuesMap = mapOf(zero, one, two, three, four, five, six, seven, eight, nine)

        return output.joinToString("") { s ->
            if (s.isNotEmpty()) {
                valuesMap.get(s)!!.value
            } else ""

        }.toInt()
    }

    private fun findNotInList(value: String, candidates: List<String>): String {
        val filter1 = candidates.map (String::toSortedSet).filter { !it.containsAll(value.toSortedSet()) }
        return filter1[0].joinToString("")
    }

    private fun valueContains(value: String, candidates: List<String>): String {
        val filter = candidates.map (String::toSortedSet).filter { value.toSortedSet().containsAll(it) }
        return filter[0].joinToString("")
    }

    private fun findInList(value: String, candidates: List<String>): String {
        val filter = candidates.map (String::toSortedSet).filter { it.containsAll(value.toSortedSet()) }
        return filter[0].joinToString("")
    }
}
