import kotlin.math.pow
infix fun Int.power(exponent: Int): Int = toDouble().pow(exponent).toInt()
class Stats(val totalLines: Int, val oneCounts: List<Int> = listOf()) {
    fun countOnes(line: String): Stats {
        val map = line.toCharArray().map { it.digitToInt() }
        val newOneCounts: List<Int> =
            map.mapIndexed { index, i -> if (oneCounts.size > index) i + oneCounts[index] else i }
        return Stats(totalLines, newOneCounts);
    }

    fun mostPresent(): List<Int> {
        return oneCounts.map { if (it > totalLines / 2) 1 else 0 }
    }

    fun leastPresent(): List<Int> {
        return mostPresent().map { if (it == 1) 0 else 1 }
    }
}

class Diagnostic(val fullData: List<String>) {
    fun gamma(): Int {
        val stats =statFor(fullData);
        return binaryListToDecimal(stats.mostPresent())

    }

    fun epsilon(): Int {
        val stats =statFor(fullData);
        return binaryListToDecimal(stats.leastPresent())
    }

   fun binaryListToDecimal(binAray: List<Int>) = binAray.reversed().reduceIndexed { index, acc, i -> ((2 power index)*i)+acc }
    //fun binaryListToDecimal(bins: List<Int>) = bins.joinToString("").toInt(2)

    fun statFor(data:List<String>)=  data.fold(Stats(data.size)) { acc, s -> acc.countOnes(s) }
}

class Day3 {
    private val inputData: List<String> = "day3/input".fromResource().readLines()


    fun diag(): Diagnostic {
        return Diagnostic(inputData);
    }
}
