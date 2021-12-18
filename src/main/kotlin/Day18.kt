import kotlin.math.ceil
import kotlin.math.floor

class Day18(path: String = "day18/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {
        val map = parsedData()
        return magnitude(map)
    }

    fun parsedData(): List<SnailPair> {
        val map = inputData.filter { it.isNotBlank() }.map { numberParser(it) as SnailPair }
        return map
    }

    fun compute2(): Long {
        val map = inputData.filter { it.isNotBlank() }.map { numberParser(it) as SnailPair }

        return permutations(map, length = 2)
            .maxOf {
                magnitude(it)
            }
    }

    fun magnitude(v: List<SnailPair>): Long {
        var p = sum(v)
        return p.magnetude()
    }

    fun sum(v: List<SnailPair>): SnailPair {
        var p = v.first()
        for (s in v.drop(1)) {
            p = SnailPair(p, s)
            var action = false
            do {
                val ora = p.toString()

                val firstLevel4 = p.firstLevel4()
                p = p.explode()
                val ore = p.toString()
                action = ora != ore
                if (!action) {
                    p = p.split()
                    action = ora != p.toString()
                }
            } while (action)

        }
        return p
    }

    fun String.isNumeric() = this.toIntOrNull() != null

    fun numberParser(number: String): SnailValue {
        if (number.isNumeric()) {
            return SnailNumber(number.toInt())
        }
        val result = splitValues(number)
        return SnailPair(numberParser(result.first), numberParser(result.second))
    }

    private fun splitValues(number: String): Pair<String, String> {
        var level = 0
        var list = mutableListOf<Char>()
        var leftValue = ""
        var rightValue = ""
        for (i in number.indices) {
            val c: Char = number[i]
            when (c) {
                '[' -> {
                    level++
                    if (level != 1) {
                        list.add(c)
                    }
                }
                ']' -> {
                    if (level == 1) {
                        rightValue = list.joinToString("")
                    } else {
                        list.add(c)
                    }

                    level--
                }
                ',' -> {
                    if (level == 1) {
                        leftValue = list.joinToString("")
                        list.clear()
                    } else {
                        list.add(c)
                    }
                }
                else -> list.add(c)

            }
        }
        val result = Pair(leftValue, rightValue)
        return result
    }

    open class SnailValue {
        open fun magnetude() = 0L
    }

    class SnailNumber(var v: Int) : SnailValue() {
        override fun toString(): String {
            return "$v"
        }

        override fun magnetude() = v.toLong()
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as SnailNumber

            if (v != other.v) return false

            return true
        }

        override fun hashCode(): Int {
            return v
        }

    }

    class SnailPair(var left: SnailValue, var right: SnailValue) : SnailValue() {


        companion object {
            fun of(a: Int, b: Int) = SnailPair(SnailNumber(a), SnailNumber(b))
        }

        fun split(): SnailPair {
            val day18 = Day18()

            val pair = this.toString()
            val toRegex = "(\\d\\d+)".toRegex()
            val matches = toRegex.find(pair)
            if (matches != null && matches.groups.size == 2) {
                val matchGroup = matches.groups[1]!!
                val range = matchGroup.range
                val v = matchGroup.value.toInt()
                val leftV = floor(v / 2.0).toInt()
                val rightV = ceil(v / 2.0).toInt()
                val replace = "[$leftV,$rightV]"

                val left = pair.substring(0, range.first)
                val right = pair.substring(range.last + 1)
                val newValue = left + replace + right
                return day18.numberParser(newValue) as SnailPair
            }

            return day18.numberParser(pair) as SnailPair
        }

        private fun level4Range(pair: String): IntRange {
         return levelRange(pair,5)
        }
        private fun levelRange(pair: String,targetLevel:Int): IntRange {
            var level = 0
            var list = mutableListOf<Char>()
            var leftValue = ""
            var rightValue = ""
            var startIndex = -1
            var endIndex = -1
            for (i in pair.indices) {
                val c: Char = pair[i]
                when (c) {
                    '[' -> {
                        level++
                        if (level == targetLevel) {
                            startIndex = i
                            list.clear()

                        }
                        list.add(c)
                    }
                    ']' -> {
                        list.add(c)
                        if (level == targetLevel) {
                            val inside = list.drop(1).dropLast(1)

                            val hasSubElement = inside.any { it == '[' || it == ']' } || list.isNotEmpty()
                            list.clear()
                            if (!inside.contains('[') && inside.isNotEmpty()) {
                                endIndex = i
                                break;
                            }
                        }

                        level--
                    }

                    else -> list.add(c)

                }
            }
            return startIndex..endIndex
        }
        fun IntRange.isValid()=start > -1 && last > -1
        fun explode(): SnailPair {
            val day18 = Day18()

            val pair = this.toString()
            var range = level4Range(pair)

            if (range.isValid()) {

                val snailPair = explodeInRange(range, pair)
                return snailPair

            }
            return day18.numberParser(pair) as SnailPair
        }
    private fun leftDigits(s:String):IntRange{
        var startIndex=-1
        var endIndex=-1
        for(i in s.length-1 downTo  0){
            val c =s[i]
            if(c.isDigit() && endIndex==-1){
                endIndex=i
            }
            if(!c.isDigit() && endIndex!=-1){
                startIndex=i+1
                break
            }
        }
        return startIndex .. endIndex
    }
        private fun explodeInRange(level4Range: IntRange, pair: String): SnailPair {
            val day18 = Day18()
            val range = level4Range
            val v = pair.substring(level4Range)
            val toExplode = day18.numberParser(v) as SnailPair
            val leftAdd = (toExplode.left as SnailNumber).v
            val rightAdd = (toExplode.right as SnailNumber).v
            var leftSide = pair.substring(0, range.first)
            val leftDigits1 = leftDigits(leftSide)
            if (leftDigits1.isValid()) {
                val replaceBy = leftSide.substring(leftDigits1).toInt() + leftAdd
                leftSide = leftSide.substring(0, leftDigits1.first) + replaceBy + leftSide.substring(
                    leftDigits1.last + 1
                )
            }
            var rightSide = pair.substring(range.last)
            val rightFirstDigitsRegExp = "(\\d+)".toRegex()
            val rightDigits = rightFirstDigitsRegExp.find(rightSide)
            if (rightDigits != null && rightDigits.groups.isNotEmpty()) {
                val digitToReplace = rightDigits.groups[1]!!
                val replaceBy = digitToReplace.value.toInt() + rightAdd
                rightSide = rightSide.substring(1, digitToReplace.range.first) + replaceBy + rightSide.substring(
                    digitToReplace.range.last + 1
                )
            }

            val result = leftSide + "0" + rightSide
            val snailPair = day18.numberParser(result) as SnailPair
            return snailPair
        }


        fun firstLevel4(): SnailPair? {
            val day18 = Day18()
            val toString = this.toString()
            val level4Range = level4Range(toString)
            if (level4Range.start > -1 && level4Range.last > -1) {
                val v = toString.substring(level4Range)
                val toExplode = day18.numberParser(v) as SnailPair
                return toExplode
            }

            return null
        }


        override fun toString(): String {
            return "[$left,$right]"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as SnailPair

            if (left != other.left) return false
            if (right != other.right) return false

            return true
        }

        override fun hashCode(): Int {
            var result = left.hashCode()
            result = 31 * result + right.hashCode()
            return result
        }

        override fun magnetude() = 3 * left.magnetude() + 2 * right.magnetude()
    }
}
