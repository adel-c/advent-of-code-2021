class Day18(path: String = "day18/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {
        return 0
    }

    fun compute2(): Long {
        return 0
    }

    fun String.isNumeric() = this.toIntOrNull() != null

    fun numberParser(number: String, parent: SnailPair? = null): SnailValue {
        if (number.isNumeric()) {
            return SnailNumber(number.toInt())
        }
        var level = 0
        var list = mutableListOf<Char>()
        var leftValue = ""
        var rightValue = ""
        for (c: Char in number.toCharArray()) {
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
        val tmp = SnailNumber(1)
        val snailPair = SnailPair(tmp, tmp, parent)
        snailPair.left = numberParser(leftValue, snailPair)
        snailPair.right = numberParser(rightValue, snailPair)
        return snailPair
    }

    open class SnailValue( open var parent: SnailPair? = null) {
        open fun firstLevel(level: Int): SnailValue? {
            return null
        }
    }

    data class SnailNumber(val v: Int, override var parent: SnailPair? = null) : SnailValue(parent)
    data class SnailPair( var left: SnailValue,var right: SnailValue, override var parent: SnailPair? = null) : SnailValue(parent) {
        companion object {
            fun of(a: Int, b: Int) = SnailPair(SnailNumber(a), SnailNumber(b))
        }

        fun explode() {

        }

        fun left4(): SnailValue? {
            TODO("Not yet implemented")
        }

        fun firstLevel4(): SnailPair? {
            val firstLevel = firstLevel(4)
            if (firstLevel != null && firstLevel is SnailPair) {
                return firstLevel
            }
            return null
        }

        override fun firstLevel(level: Int): SnailValue? {
            if (level == 1) {
                if (right is SnailPair) {
                    return right
                }
                if (left is SnailPair) {
                    return left
                }
            } else {
                return right.firstLevel(level - 1) ?: left.firstLevel(level - 1)
            }
            return null
        }

    }
}
