class Day21(path: String = "day21/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun compute(): Long {
        val playerOneStart = inputData[0].split(":")[1].trim().toInt()
        val playerTwoStart = inputData[1].split(":")[1].trim().toInt()
        println("$playerOneStart $playerTwoStart")
        return Game(playerOneStart, playerTwoStart).play()
    }

    fun compute2(): Long {
        val playerOneStart = inputData[0].split(":")[1].trim().toInt()
        val playerTwoStart = inputData[1].split(":")[1].trim().toInt()
        println("$playerOneStart $playerTwoStart")
        return 0
    }

    data class Player(val position: Int, val score: Int, val winningScore: Int = 1000, val caseCount: Int = 10) {

        fun play(move: Int): Player {
            val trueMove = move % caseCount
            var newPos = position + trueMove
            if (newPos > 10) newPos -= 10
            val newScore = score + newPos
            return Player(newPos, newScore)
        }

        fun hasWon() = score >= winningScore
    }


    data class Game(val playerOneStart: Int, val playerTwoStart: Int) {
        private val dice = Dice(100)

        fun play(): Long {
            var player1 = Player(playerOneStart, 0)
            var player2 = Player(playerTwoStart, 0)
            do {
                player1 = player1.play(dice.nextThree())
                if (player1.hasWon()) break
                player2 = player2.play(dice.nextThree())
                if (player2.hasWon()) break
            } while (true)
            val winnerScore = if (player1.hasWon()) player2.score else player1.score

            println(dice.totalThrows)
            return winnerScore * dice.totalThrows
        }


    }

    data class Dice(val sideCount: Int = 100) {

        private var currentValue = 0
        var totalThrows: Long = 0
        fun nextValue(): Sequence<Int> = sequence {
            while (true) {
                totalThrows++
                currentValue += 1
                if (currentValue > sideCount) currentValue -= sideCount
                yield(currentValue)

            }
        }

        fun nextThree(): Int {
            return nextValue().take(3).sum()
        }
    }
}
