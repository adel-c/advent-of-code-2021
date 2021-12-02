enum class Movement { FORWARD, UP, DOWN }
enum class MovementStrategy {
    WITHOUT_AIM {
        override fun computePosition(currentPosition: Position, movement: Movement, value: Long): Position {
            return when (movement) {
                Movement.FORWARD -> currentPosition.copy(horizontal = currentPosition.horizontal + value)
                Movement.UP -> currentPosition.copy(depth = currentPosition.depth - value)
                Movement.DOWN -> currentPosition.copy(depth = currentPosition.depth + value)
            }
        }
    },
    WITH_AIM {
        override fun computePosition(currentPosition: Position, movement: Movement, value: Long): Position {
            return when (movement) {
                Movement.FORWARD -> currentPosition.copy(
                    horizontal = currentPosition.horizontal + value,
                    depth = currentPosition.depth + (value * currentPosition.aim)
                )
                Movement.UP -> currentPosition.copy(aim = currentPosition.aim - value)
                Movement.DOWN -> currentPosition.copy(aim = currentPosition.aim + value)
            }
        }
    };

    abstract fun computePosition(currentPosition: Position, movement: Movement, value: Long): Position
}

data class Position(
    val movementStrategy: MovementStrategy,
    val horizontal: Long = 0,
    val aim: Long = 0,
    val depth: Long = 0
) {

    fun updatePosition(order: String): Position {
        val (movementString, value) = order.split(" ")
        val movement = Movement.valueOf(movementString.uppercase())
        return movementStrategy.computePosition(this, movement, value.toLong())
    }

    fun factor() = horizontal * depth
}

class Day2 {
    private val inputData: List<String> = "day2/input".fromResource().readLines()

    fun position(): Position = positionWithStrategy(MovementStrategy.WITHOUT_AIM)

    fun position2() = positionWithStrategy(MovementStrategy.WITH_AIM)

    private fun positionWithStrategy(movementStrategy: MovementStrategy) =
        inputData.fold(Position(movementStrategy)) { acc, s -> acc.updatePosition(s) }
}
