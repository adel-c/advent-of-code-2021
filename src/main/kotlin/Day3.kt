import kotlin.math.pow

infix fun Int.power(exponent: Int): Int = toDouble().pow(exponent).toInt()
data class BinaryValue(val data: List<Int>) {
    companion object {
        fun fromString(binaryString: String) = BinaryValue(binaryString.toCharArray().map { it.digitToInt() })
    }

    operator fun get(index: Int): Int {
        return data[index]
    }

    override fun toString(): String {
        return data.joinToString("")
    }

    fun toDecimal() =
        data.reversed().reduceIndexed { index, acc, i -> ((2 power index) * i) + acc }
    //fun toDecimal() = data.joinToString("").toInt(2)

    fun invert() = BinaryValue(data.map { if (it == 1) 0 else 1 })

    fun length() = data.size
}

class Stats(val totalLines: Int, val oneCounts: List<Int> = listOf()) {
    companion object {
        fun fold(data: List<BinaryValue>) = data.fold(Stats(data.size)) { acc, s -> acc.countOnes(s) }
        fun mostCommon(data: List<BinaryValue>) = fold(data).mostCommonBinary()
        fun leastCommon(data: List<BinaryValue>) =fold(data).leastCommonBinary()
    }
    fun countOnes(line: BinaryValue): Stats {
        val newOneCounts: List<Int> =
            line.data.mapIndexed { index, i -> if (oneCounts.size > index) i + oneCounts[index] else i }
        return Stats(totalLines, newOneCounts);
    }

    fun mostCommonBinary(): BinaryValue {
        return BinaryValue(oneCounts.map { if (it >= totalLines - it) 1 else 0 })
    }

    fun leastCommonBinary(): BinaryValue {
        return mostCommonBinary().invert()
    }
}

class Diagnostic(val fullData: List<BinaryValue>) {
    fun gamma(): Int {
        return Stats.mostCommon(fullData).toDecimal()

    }

    fun epsilon(): Int {
        return Stats.leastCommon(fullData).toDecimal()
    }

    fun oxygen(): Int {
        return recursiveFilter(fullData, 0, Stats::mostCommon)
    }

    fun co2(): Int {
        return recursiveFilter(fullData, 0, Stats::leastCommon)
    }

    private fun recursiveFilter(data: List<BinaryValue>, index: Int, reducer: (List<BinaryValue>) -> BinaryValue): Int {
        if (index > data.first().length()) {
            TODO("should not happen")
        }

        val reduced = reducer(data)
        val targetValue = reduced[index]
        val filteredData = data.filter {
            it[index] == targetValue
        }
        if (filteredData.size == 1) {
            return filteredData.first().toDecimal()
        }
        return recursiveFilter(filteredData, index + 1, reducer)


    }




}

class Day3 {
    private val inputData: List<String> = "day3/input".fromResource().readLines()


    fun diag(): Diagnostic {
        return Diagnostic(inputData.map { line -> BinaryValue.fromString(line) });
    }
}
