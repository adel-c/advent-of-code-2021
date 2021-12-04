class Day4(path: String = "day3/input") {
    private val inputData: List<String> = path.fromResource().readLines()

    fun parse(): Bingo {
        val draws = inputData[0].split(",").map(String::toInt)

        val boards: List<Board> = parseBoards()
        val bingo = Bingo(draws, boards)
        return bingo
    }

    private fun parseBoards(): List<Board> {
        val avoidFirstRow = inputData.subList(2, inputData.size)
        val boardsStringChunks = avoidFirstRow.chunked(6) { it.filter(String::isNotBlank) }

        return boardsStringChunks
            .map(this::parseLineAsInts)
            .map { Board.fromMatrix(it) }
    }

    private fun parseLineAsInts(rows: List<String>): List<List<Int>> {
        return rows.map { parseBoardRow(it) }

    }

    private fun parseBoardRow(row: String) = row.split(" ").filter(String::isNotBlank).map(String::toInt)
}

data class Bingo(val draws: List<Int>, val boards: List<Board>) {
    fun play(): Int {
        val boardsByWinOrder = boardsByWinOrder()
        return boardsByWinOrder.first().score()
    }

    private fun boardsByWinOrder(): List<Board> {
        var playBoards=boards.toList()
        draws.forEach { draw ->
            playBoards= playBoards.map { it.play(draw) }
        }
        return playBoards.sortedBy { it.draws.size }
    }

    fun lastWin(): Int {
        val boardsByWinOrder = boardsByWinOrder()
        return boardsByWinOrder.last().score()
    }
}

data class Board(
    private val matrix: List<List<Int>>,
    private val rowAndColumns: List<List<Int>>,
    val draws: Set<Int> = setOf(),
    private val winningMove:Int = -1,

    ) {
    companion object {
        fun fromMatrix(matrix: List<List<Int>>): Board {
            return Board(matrix, matrix + computeColumns(matrix))
        }

        private fun computeColumns(matrix: List<List<Int>>) =
            List(matrix.size) { index -> matrix.map { it[index] }.toList() }
    }

    fun play(draw: Int): Board {
        if (!win()) {
            return this.copy(draws=draws+draw, winningMove = draw)
        }
        return this
    }

    fun play(vararg draws: Int): Board {
        var b: Board = this
        draws.forEach {
            b = b.play(it)
        }
        return b
    }

    fun win(): Boolean {
        if (draws.size < matrix.size) {
            return false
        }

        return rowAndColumns.any { draws.containsAll(it) }
    }

    fun winningDraw(): Int {
        return winningMove
    }

    fun unmarked() = (matrix.flatten() - draws).sum()


    fun score(): Int {
        return unmarked() * winningDraw()
    }
}
