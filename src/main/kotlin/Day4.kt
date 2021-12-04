class Day4(path: String = "day3/input") {
    private val inputData: List<String> = path.fromResource().readLines()
    fun parse(): Bingo {
        val draws = inputData[0].split(",").map(String::toInt)
        val boards: List<Board> = parseBoards(inputData.subList(2, inputData.size))
        return Bingo(draws, boards)
    }

    private fun parseBoards(matrixRows: List<String>): List<Board> {
        return matrixRows.chunked(6) { it.filter(String::isNotBlank) }.map(this::parseLineAsInts)
            .map { Board.fromMatrix(it) }
    }

    private fun parseLineAsInts(rows: List<String>): List<List<Int>> {
        return rows.map { it.split(" ").filter(String::isNotBlank).map(String::toInt) }
    }
}

data class Bingo(val draws: List<Int>, val boards: List<Board>) {
    fun play(): Int {
        return boardsByWinOrder().first().score()
    }

    private fun boardsByWinOrder(): List<Board> {
        val drawsAsArray = draws.toIntArray()
        return boards.map { it.play(*drawsAsArray) }.sortedBy { it.draws.size }
    }

    fun lastWin(): Int {
        return boardsByWinOrder().last().score()
    }
}

data class Board(
    private val matrix: List<List<Int>>,
    private val rowAndColumns: List<List<Int>>,
    val draws: Set<Int> = setOf(),
    private val lastDraw: Int = -1,
) {
    companion object {
        fun fromMatrix(matrix: List<List<Int>>): Board {
            return Board(matrix, matrix + computeColumns(matrix))
        }

        private fun computeColumns(matrix: List<List<Int>>) =
            List(matrix.size) { index -> matrix.map { it[index] }.toList() }
    }

    private val win = computeWin()

    fun computeWin(): Boolean {
        if (draws.size < matrix.size) {
            return false
        }
        return rowAndColumns.any { draws.containsAll(it) }
    }

    private fun play(draw: Int): Board {
        if (!win) {
            return this.copy(draws = draws + draw, lastDraw = draw)
        }
        return this
    }

    fun play(vararg draws: Int): Board {
        return draws.fold(this) { acc, draw ->
            acc.play(draw)
        }
    }

    fun winningDraw(): Int {
        return lastDraw
    }

    fun unmarked() = (matrix.flatten() - draws).sum()

    fun score(): Int {
        return unmarked() * winningDraw()
    }
}
