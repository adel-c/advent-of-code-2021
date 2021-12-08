import DIGIT.*

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
    ZERO("abcefg", "0") {
        override fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>): String {
            val allSixChars: List<String> = dataByDigitLength.getOrDefault(6, listOf())
            return (allSixChars - setOf(acc[SIX], acc[NINE]))[0]!!
        }
    },
    ONE("cf", "1") {
        override fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>) =
            dataByDigitLength.firstByDigit(this)
    },

    TWO("acdeg", "2") {
        override fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>): String {
            //must be last
            return (dataByDigitLength.values.flatten() - acc.values.toSet())[0]
        }
    },
    THREE("acdfg", "3") {
        override fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>): String {
            return findInList(acc[SEVEN]!!, dataByDigitLength.getOrDefault(size(), listOf()))
        }
    },
    FOUR("bcdf", "4") {
        override fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>) =
            dataByDigitLength.firstByDigit(this)
    },
    FIVE("abdfg", "5") {
        override fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>): String {
            return valueContains(acc[SIX]!!, dataByDigitLength.getOrDefault(FIVE.size(), listOf()))
        }
    },
    SIX("abdefg", "6") {
        override fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>): String {
            val allSixChars: List<String> = dataByDigitLength.getOrDefault(6, listOf())
            return findNotInList(acc[ONE]!!, allSixChars)
        }
    },
    SEVEN("acf", "7") {
        override fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>) =
            dataByDigitLength.firstByDigit(this)
    },
    EIGHT("abcdefg", "8") {
        override fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>) =
            dataByDigitLength.firstByDigit(this)
    },
    NINE("abcdfg", "9") {
        override fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>): String {
            val allSixChars: List<String> = dataByDigitLength.getOrDefault(6, listOf())
            return findInList(acc[FOUR]!!, allSixChars)
        }
    };

    fun size() = representation.length


    abstract fun findValue(dataByDigitLength: Map<Int, List<String>>, acc: Map<DIGIT, String>): String

    fun Map<Int, List<String>>.firstByDigit(key: DIGIT): String {
        return this[key.size()]!![0]
    }

    protected fun findNotInList(value: String, candidates: List<String>): String {
        val filter1 = candidates.map(String::toSortedSet).filter { !it.containsAll(value.toSortedSet()) }
        return filter1[0].joinToString("")
    }

    protected fun valueContains(value: String, candidates: List<String>): String {
        val filter = candidates.map(String::toSortedSet).filter { value.toSortedSet().containsAll(it) }
        return filter[0].joinToString("")
    }

    protected fun findInList(value: String, candidates: List<String>): String {
        val filter = candidates.map(String::toSortedSet).filter { it.containsAll(value.toSortedSet()) }
        return filter[0].joinToString("")
    }
}

data class DataLine(val dataUnSorted: List<String>, val outputUnSorted: List<String>) {
    private val data: List<String> = dataUnSorted.map { it.toSortedSet().joinToString("") }.toList()
    private val output: List<String> =
        outputUnSorted.map { it.toSortedSet().joinToString("") }.filter { it.isNotBlank() }.toList()

    fun numberOfOneFourSevenEight(): Int {
        val decoder = decoder()
        return output.map { decoder[it]!! }.count { setOf(ONE, FOUR, SEVEN, EIGHT).contains(it) }
    }

    private fun possibilitiesBySize(data: List<String>): Map<Int, List<String>> {
        return data.groupBy { it.length }
    }

    fun computeNumber(): Int {
        val decoder = decoder()
        return output.joinToString("") { decoder[it]!!.value }.toInt()

    }

    private fun decoder(): Map<String, DIGIT> {
        val dataByDigitLength: Map<Int, List<String>> = possibilitiesBySize(data)
        val resolveOrder = listOf(ONE, SEVEN, FOUR, EIGHT, NINE, SIX, ZERO, FIVE, THREE, TWO)
        val fold = resolveOrder.fold(mapOf<DIGIT, String>()) { acc, digit ->
            acc + Pair(digit, digit.findValue(dataByDigitLength, acc))
        }
        return fold.entries.associateBy({ it.value }) { it.key }
    }


}
