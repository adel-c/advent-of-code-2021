data class Position(val horizontal: Long, val depth: Long) {

    fun updatePosition(order: String): Position {
        val (movement, value) = order.split(" ")
        return computePosition(movement, value.toLong())
    }

    private fun computePosition(movement: String, value: Long) = when (movement.trim()) {
        "forward" -> this.copy(horizontal = this.horizontal + value)
        "up" -> this.copy(depth = this.depth - value)
        "down" -> this.copy(depth = this.depth + value)
        else -> TODO("WTF")
    }

    fun factor()=horizontal*depth
}

data class Position2(val horizontal: Long,val aim:Long, val depth: Long)  {

    fun updatePosition(order: String): Position2 {
        val (movement, value) = order.split(" ")
        return computePosition(movement, value.toLong())
    }

    private fun computePosition(movement: String, value: Long) = when (movement.trim()) {
        "forward" -> this.copy(horizontal = this.horizontal + value,depth=this.depth+(value*this.aim))
        "up" -> this.copy(aim = this.aim - value)
        "down" -> this.copy(aim = this.aim + value)
        else -> TODO("WTF")
    }

    fun factor()=horizontal*depth
}

class Day2 {
    private val inputData: List<String> = "day2/input".fromResource().readLines()

    fun position(): Position {
        var position = Position(0, 0)
        inputData.forEach {
            position = position.updatePosition(it)
        }
        return position
    }

    fun position2(): Position2 {
        var position2 = Position2(0,0, 0)
        inputData.forEach {
            position2 = position2.updatePosition(it)
        }
        return position2
    }

}
