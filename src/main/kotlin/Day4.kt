class Day4(path: String = "day3/input") {
    private val inputData: List<String> = path.fromResource().readLines()

    fun parse(): Bingo {
        val draws = inputData.get(0).split(",").map(String::toInt)

        val boards: List<Board> = parseBoards()
        val bingo = Bingo(draws, boards)
        println(bingo)
        return bingo
    }

    private fun parseBoards(): List<Board> {
        val avoidFirstRow = inputData.subList(2, inputData.size)
        val boardsStringChunks = avoidFirstRow.chunked(6) { it.filter(String::isNotBlank) }

        return boardsStringChunks
            .map(this::parseLineAsInts)
            .map { Board(it) }
    }

    private fun parseLineAsInts(rows:List<String>) :List<List<Int>>{
        return rows.map { parBoardRow(it) }

    }

    private fun parBoardRow(row: String) = row.split(" ").filter(String::isNotBlank).map(String::toInt)
}

data class Bingo(val draws: List<Int>, val boards: List<Board>) {

    override fun toString(): String {
        return "draws=$draws \n\n${boards.joinToString ("\n\n")}"
    }
}

data class Board(val matrix: List<List<Int>>) {
    override fun toString(): String {

        return matrix.joinToString("\n") { row -> row.joinToString(" ") { it.toString().padStart(2, '0') } } +"\n"
    }
}
