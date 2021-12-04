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
            .map { Board(it) }
    }

    private fun parseLineAsInts(rows: List<String>): List<List<Int>> {
        return rows.map { parseBoardRow(it) }

    }

    private fun parseBoardRow(row: String) = row.split(" ").filter(String::isNotBlank).map(String::toInt)
}

data class Bingo(val draws: List<Int>, val boards: List<Board>) {
    fun play(): Int {
        val boardsByWinOrder= boardsByWinOrder()
        return boardsByWinOrder.first().score()
    }

    private fun boardsByWinOrder(): List<Board> {
        draws.forEach { draw ->
            boards.forEach { it.play(draw) }
        }
        return boards.sortedBy { it.draws.size }
    }

    fun lastWin(): Int {
        val boardsByWinOrder= boardsByWinOrder()
        return boardsByWinOrder.last().score()
    }
}

data class Board(val matrix: List<List<Int>>) {
    private val columns = computeColumns()
    private var winningMove = -1

    private fun computeColumns() = List(matrix.size) { index -> matrix.map { it[index] }.toList() }
    val draws: Set<Int>
        get() = played.toSet()
    private val played: MutableSet<Int> = mutableSetOf()


    fun play(draw: Int) {
        if (!win()) {
            played.add(draw)
            winningMove = draw
        }

    }

    fun play(vararg draws: Int) {
        draws.forEach(this::play)
    }

    fun win(): Boolean {
        if (played.size < matrix.size) {
            return false
        }

        return (matrix + columns).any { played.containsAll(it) }
    }

    fun winningDraw(): Int {
        return winningMove
    }

    fun unmarked() = (matrix.flatten() - played).sum()


    fun score():Int{
        return unmarked() * winningDraw()
    }
}
