import kotlin.math.pow

infix fun Int.power(exponent: Int): Int = toDouble().pow(exponent).toInt()
class Stats(val totalLines: Int, val oneCounts: List<Int> = listOf()) {
    fun countOnes(line: List<Int>): Stats {

        val newOneCounts: List<Int> =
            line.mapIndexed { index, i -> if (oneCounts.size > index) i + oneCounts[index] else i }
        return Stats(totalLines, newOneCounts);
    }

    fun mostPresent(): List<Int> {
        return oneCounts.map { if (it > totalLines / 2) 1 else 0 }
    }

    fun leastPresent(): List<Int> {
        return mostPresent().map { if (it == 1) 0 else 1 }
    }
}

class Diagnostic(val fullData: List<List<Int>>) {
    fun gamma(): Int {
        val stats = statFor(fullData);
        return binaryListToDecimal(stats.mostPresent())

    }

    fun epsilon(): Int {
        val stats = statFor(fullData);
        return binaryListToDecimal(stats.leastPresent())
    }

    fun oxygen(): Int {

        var data = fullData
        (0..5).forEach { index ->
            val stats = statFor(data)
            val mostPresent = stats.mostPresent()
            val targetValue = mostPresent[index]
            data = data.filter {
                it[index] == targetValue
            }
            if (data.size == 1) {
                return binaryListToDecimal(data.first())
            }
        }
        return 0
    }

    fun binaryListToDecimal(binAray: List<Int>) =
        binAray.reversed().reduceIndexed { index, acc, i -> ((2 power index) * i) + acc }
    //fun binaryListToDecimal(bins: List<Int>) = bins.joinToString("").toInt(2)

    fun statFor(data: List<List<Int>>) = data.fold(Stats(data.size)) { acc, s -> acc.countOnes(s) }
}

class Day3 {
    private val inputData: List<String> = "day3/inputTest".fromResource().readLines()


    fun diag(): Diagnostic {
        return Diagnostic(inputData.map {  line ->line.toCharArray().map { it.digitToInt() } });
    }
}
