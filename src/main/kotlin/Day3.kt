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

    fun invert() = BinaryValue(data.map { if (it == 1) 0 else 1 })
}

class Stats(val totalLines: Int, val oneCounts: List<Int> = listOf()) {
    fun countOnes(line: BinaryValue): Stats {

        val newOneCounts: List<Int> =
            line.data.mapIndexed { index, i -> if (oneCounts.size > index) i + oneCounts[index] else i }
        return Stats(totalLines, newOneCounts);
    }

    fun mostPresent(): BinaryValue {
        return BinaryValue(oneCounts.map { if (it >= totalLines - it) 1 else 0 })
    }

    fun leastPresent(): BinaryValue {
        return mostPresent().invert()
    }
}

class Diagnostic(val fullData: List<BinaryValue>) {
    fun gamma(): Int {
        val stats = statFor(fullData);
        return stats.mostPresent().toDecimal()

    }

    fun epsilon(): Int {
        val stats = statFor(fullData);
        return stats.leastPresent().toDecimal()
    }

    fun oxygen(): Int {

        var data = fullData
        (0..data.first().data.size).forEach { index ->
            val stats = statFor(data)
            val mostPresent = stats.mostPresent()
            val targetValue = mostPresent[index]
            data = data.filter {
                it[index] == targetValue
            }
            if (data.size == 1) {
                return data.first().toDecimal()
            }
        }
        return 0
    }
    fun co2(): Int {

        var data = fullData
        (0..data.first().data.size).forEach { index ->
            val stats = statFor(data)
            val mostPresent = stats.leastPresent()
            val targetValue = mostPresent[index]
            data = data.filter {
                it[index] == targetValue
            }
            if (data.size == 1) {
                return data.first().toDecimal()
            }
        }
        return 0
    }


    //fun binaryListToDecimal(bins: List<Int>) = bins.joinToString("").toInt(2)

    fun statFor(data: List<BinaryValue>) = data.fold(Stats(data.size)) { acc, s -> acc.countOnes(s) }
}

class Day3 {
    private val inputData: List<String> = "day3/input".fromResource().readLines()


    fun diag(): Diagnostic {
        return Diagnostic(inputData.map { line -> BinaryValue.fromString(line) });
    }
}
