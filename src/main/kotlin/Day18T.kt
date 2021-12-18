import kotlin.math.ceil
import kotlin.math.floor

class Day18T(path: String = "day18/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {
        val map = parsedData()
        return magnitude(map)
    }

    fun parsedData(): List<NodePair> {
        val map = inputData.filter { it.isNotBlank() }.map { numberParser(it) as NodePair }
        return map
    }

    fun compute2(): Long {
        val map = inputData.filter { it.isNotBlank() }.map { numberParser(it) as NodePair }

        return permutations(map, length = 2)
            .maxOf {
                magnitude(it)
            }
    }
    fun magnitude(v :List<NodePair>):Long{

        var p = sum(v.map { numberParser(it.toString()) as NodePair})
        return p.magnitude()
    }

    fun sum(v: List<NodePair>): NodePair {
        var p = v.first()
        for (s in v.drop(1)) {
            p = NodePair(p, s)
            var action = false
            var i =0
            do {
                i++
                //println("$i -> $p")
                action = p.explode()
                if(!action){
                    action=  p.split()
                }
            }while (action)

        }
        return p
    }

    fun String.isNumeric() = this.toIntOrNull() != null

    fun numberParser(number: String, parent: NodePair? = null): Node {
        if (number.isNumeric()) {
            return Leaf(number.toInt(),parent)
        }
        val result = splitValues(number)
        val tmp = Leaf(1)
        val nodePair = NodePair(tmp, tmp, parent)
        nodePair.left = numberParser(result.first, nodePair)
        nodePair.right = numberParser(result.second, nodePair)
        return nodePair
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

    sealed class Node(var parent: NodePair? = null) {
        open fun magnitude():Long {
            return when(this){
                is Leaf -> v.toLong()
                is NodePair -> left.magnitude() * 3 + right.magnitude() *2
            }
        }
        open fun firstLevel(level: Int): Node? {
            return null
        }

        override fun toString(): String {
            return ""
        }
        fun firstLeftValueDescend(): Leaf? {

            return if(this is NodePair){
                return if(left is Leaf){
                    left as Leaf
                }else{
                    left.firstLeftValueDescend()
                }

            }else{
                null
            }
        }
        fun firstRightValueDescend(): Leaf? {
            return if(this is NodePair){
                return if(right is Leaf){
                    right as Leaf
                }else{
                    right.firstRightValueDescend()
                }

            }else{
                null
            }
        }
        fun firstLeftValue(): Leaf? {
            return if(parent != null &&  parent?.left !== this ){
                return if(parent?.left is Leaf) {
                    parent?.left as Leaf
                } else{
                    this.parent?.left?.firstRightValueDescend()
                }

            }else{
                this.parent?.firstLeftValue()
            }
        }
        fun firstRightValue(): Leaf? {
            return if(parent != null && parent?.right !== this ){
                return if(parent?.right is Leaf) {
                    parent?.right as Leaf
                } else{
                    this.parent?.right?.firstLeftValueDescend()
                }

            }else{
                this.parent?.firstRightValue()
            }
        }
    }

    class Leaf(var v: Int, parent_: NodePair? = null) : Node(parent_){
        override fun toString(): String {
            return "$v"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Leaf

            if (v != other.v) return false

            return true
        }

        override fun hashCode(): Int {
            return v
        }

    }
    class NodePair(var left: Node, var right: Node, parent_: NodePair? = null) : Node(parent_) {
        init {
            left.parent = this
            right.parent = this
        }
        companion object {
            fun of(a: Int, b: Int) = NodePair(Leaf(a), Leaf(b))
        }

        fun split():Boolean {
            val firstMoreThan10 = firstMoreThan10()
            if(firstMoreThan10 != null){
                val v = firstMoreThan10.v
                val leftV = floor(v/2.0).toInt()
                val rightV=ceil(v/2.0).toInt()
                val of = NodePair.of(leftV, rightV)
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
                    leftValue.v=leftValue.v+(firstLevel4.left as Leaf).v
                }
                if(rightValue!=null){
                    rightValue.v=rightValue.v+(firstLevel4.right as Leaf).v
                }

                firstLevel4.parent?.replaceBy(firstLevel4,Leaf(0,firstLevel4.parent))
                return true
            }
            return false
        }
        fun replaceBy(a:Node, b:Node){
            if(right=== a){
                right=b
                b.parent=this
            }
            if(left=== a){
                left=b
                b.parent=this
            }
        }


        fun firstMoreThan10(): Leaf? {
            if(left is Leaf && (left as Leaf).v >=10){
                return left as Leaf
            }
            if(left is NodePair){
                val firstMoreThan10 = (left as NodePair).firstMoreThan10()
                if(firstMoreThan10 != null){
                    return firstMoreThan10
                }
            }
            if(right is Leaf && (right as Leaf).v >=10){
                return right as Leaf
            }
            if (right is NodePair ){
                val firstMoreThan10 = (right as NodePair).firstMoreThan10()
                if(firstMoreThan10 != null){
                    return firstMoreThan10
                }
            }

            return null
        }


        fun firstLevel4(): NodePair? {
            val firstLevel = firstLevel(4)
            if (firstLevel != null && firstLevel is NodePair) {
                return firstLevel
            }
            return null
        }

        override fun firstLevel(level: Int): Node? {
            if (level == 1) {
                if (left is NodePair && (left as NodePair).onlyLeaf()) {
                    return left
                }
                if (right is NodePair  && (right as NodePair).onlyLeaf()) {
                    return right
                }

            } else {
                return  left.firstLevel(level - 1)?: right.firstLevel(level - 1)
            }
            return null
        }
        fun onlyLeaf():Boolean = right is Leaf && left is Leaf
        override fun toString(): String {
            return "[$left,$right]"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as NodePair

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

