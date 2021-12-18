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
        return 0
    }
    fun magnitude(v :List<SnailPair>):Long{
        var p = sum(v)
        return 0
    }

    fun sum(v: List<SnailPair>): SnailPair {
        var p = v.first()
        for (s in v.drop(1)) {
            p = SnailPair(p, s)
            var action = false
            do {
              action = p.explode()
              if(!action){
                action=  p.split()
              }
            }while (action)

        }
        return p
    }

    fun String.isNumeric() = this.toIntOrNull() != null

    fun numberParser(number: String, parent: SnailPair? = null): SnailValue {
        if (number.isNumeric()) {
            return SnailNumber(number.toInt(),parent)
        }
        val result = splitValues(number)
        val tmp = SnailNumber(1)
        val snailPair = SnailPair(tmp, tmp, parent)
        snailPair.left = numberParser(result.first, snailPair)
        snailPair.right = numberParser(result.second, snailPair)
        return snailPair
    }

    private fun splitValues(number: String): Pair<String, String> {
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
        val result = Pair(leftValue, rightValue)
        return result
    }

    open class SnailValue( var parent: SnailPair? = null) {
        open fun firstLevel(level: Int): SnailValue? {
            return null
        }

        override fun toString(): String {
            return ""
        }
        fun firstLeftValueDescend(): SnailNumber? {

            return if(this is SnailPair){
                return if(left is SnailNumber){
                    left as SnailNumber
                }else{
                    left.firstLeftValueDescend()
                }

            }else{
                null
            }
        }
        fun firstRightValueDescend(): SnailNumber? {
            return if(this is SnailPair){
                return if(right is SnailNumber){
                    right as SnailNumber
                }else{
                    right.firstRightValueDescend()
                }

            }else{
                null
            }
        }
        fun firstLeftValue(): SnailNumber? {
            return if(parent != null &&  parent?.left != this ){
                return if(parent?.left is SnailNumber) {
                    parent?.left as SnailNumber
                } else{
                    this.parent?.left?.firstRightValueDescend()
                }

            }else{
                this.parent?.firstLeftValue()
            }
        }
        fun firstRightValue(): SnailNumber? {
            return if(parent != null && parent?.right != this ){
                return if(parent?.right is SnailNumber) {
                    parent?.right as SnailNumber
                } else{
                    this.parent?.right?.firstLeftValueDescend()
                }

            }else{
                this.parent?.firstRightValue()
            }
        }
    }

    class SnailNumber(var v: Int, parent_: SnailPair? = null) : SnailValue(parent_){
        override fun toString(): String {
            return "$v"
        }

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
    class SnailPair( var left: SnailValue,var right: SnailValue,  parent_: SnailPair? = null) : SnailValue(parent_) {
        init {
            left.parent = this
            right.parent = this
        }
        companion object {
            fun of(a: Int, b: Int) = SnailPair(SnailNumber(a), SnailNumber(b))
        }

        fun split():Boolean {
            val firstMoreThan10 = firstMoreThan10()
            if(firstMoreThan10 != null){
                val v = firstMoreThan10.v
                val leftV = floor(v/2.0).toInt()
                val rightV=ceil(v/2.0).toInt()
                val of = SnailPair.of(leftV, rightV)
                firstMoreThan10.parent?.replaceBy(firstMoreThan10,of)
                return true
            }
            return false
        }

        fun explode():Boolean {
            val firstLevel4 = this.firstLevel4()
            if(firstLevel4 != null){
                val leftValue = firstLevel4.firstLeftValue()
                val rightValue = firstLevel4.firstRightValue()

                if(leftValue!=null){
                    if(firstLevel4.left.javaClass.name.contains("Pair")){
                        System.err.println("AZOIEIOAZOIEAZOIEAOIZE")
                    }
                    leftValue.v=leftValue.v+(firstLevel4.left as SnailNumber).v
                }
                if(rightValue!=null){
                    rightValue.v=rightValue.v+(firstLevel4.right as SnailNumber).v
                }

                firstLevel4.parent?.replaceBy(firstLevel4,SnailNumber(0,firstLevel4.parent))
                return true
            }
            return false
        }
        fun replaceBy(a:SnailValue,b:SnailValue){
            if(right== a){
                right=b
                b.parent=this
            }
            if(left== a){
                left=b
                b.parent=this
            }
        }


        fun firstMoreThan10(): SnailNumber? {
            if(left is SnailNumber && (left as SnailNumber).v >=10){
                return left as SnailNumber
            }
            if(left is SnailPair){
                val firstMoreThan10 = (left as SnailPair).firstMoreThan10()
                if(firstMoreThan10 != null){
                    return firstMoreThan10
                }
            }
            if(right is SnailNumber && (right as SnailNumber).v >=10){
                return right as SnailNumber
            }
            if (right is SnailPair ){
                val firstMoreThan10 = (right as SnailPair).firstMoreThan10()
                if(firstMoreThan10 != null){
                    return firstMoreThan10
                }
            }

            return null
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
                if (left is SnailPair && (left as SnailPair).onlyLeaf()) {
                    return left
                }
                if (right is SnailPair  && (right as SnailPair).onlyLeaf()) {
                    return right
                }

            } else {
                return  left.firstLevel(level - 1)?: right.firstLevel(level - 1)
            }
            return null
        }
        fun onlyLeaf():Boolean = right is SnailNumber && left is SnailNumber
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



    }
}
