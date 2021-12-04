import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day4Test {
    val aBoard = Board(
        listOf(
            listOf(14, 21, 17, 24, 4),
            listOf(10, 16, 15, 9, 19),
            listOf(18, 8, 23, 26, 20),
            listOf(22, 11, 13, 6, 5),
            listOf(2, 0, 12, 3, 7),
        )
    )

    @Test
    fun testParser() {
        val actual = Day4("day4/inputTest").parse()
        assertValues(
            listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1),
            listOf(
                Board(
                    listOf(
                        listOf(22, 13, 17, 11, 0),
                        listOf(8, 2, 23, 4, 24),
                        listOf(21, 9, 14, 16, 7),
                        listOf(6, 10, 3, 18, 5),
                        listOf(1, 12, 20, 15, 19),
                    )
                ),

                Board(
                    listOf(
                        listOf(3, 15, 0, 2, 22),
                        listOf(9, 18, 13, 17, 5),
                        listOf(19, 8, 7, 25, 23),
                        listOf(20, 11, 10, 24, 4),
                        listOf(14, 21, 16, 12, 6),
                    )
                ),
                Board(
                    listOf(
                        listOf(14, 21, 17, 24, 4),
                        listOf(10, 16, 15, 9, 19),
                        listOf(18, 8, 23, 26, 20),
                        listOf(22, 11, 13, 6, 5),
                        listOf(2, 0, 12, 3, 7),
                    )
                )
            ), actual
        )
    }


    @Test
    fun board_should_remember_played() {
        val board = aBoard.copy()
        board.play(7)
        board.play(4)
        assertEquals(setOf(7, 4), board.draws)
    }

    @Test
    fun isWin_should_be_true_if_line_is_all_played() {
        val board = aBoard.copy()
        board.play(12,10,16,15,9,19)
        assertTrue(board.win())
    }

    @Test
    fun unmarkedSum_should_return_the_sum_of_all_unmarked_board() {
        val board = aBoard.copy()

        board.play(7, 4, 9, 5, 11,17, 23, 2, 0, 14, 21,24)

        assertEquals(188,board.unmarked())
    }

    @Test
    fun last_played_should_return_the_last_draw() {
        val board = aBoard.copy()
        board.play(7, 4, 9, 5, 11,17, 23)
    }

    @Test
    fun isWin_should_be_true_if_column_is_all_played() {
        val board = aBoard.copy()
        board.play(21)
        board.play(117)
        board.play(16)
        board.play(8)
        board.play(11)
        board.play(0)
        assertTrue(board.win())
    }
    @Test
    fun isWin_should_be_false_if_less_than_5_play() {
        val board = aBoard.copy()
        board.play(10)
        board.play(16)
        board.play(19)
        assertFalse(board.win())
    }
    @Test
    fun isWin_should_be_false_if_not_line() {
        val board = aBoard.copy()
        board.play(10)
        board.play(2)
        board.play(5)
        board.play(9)
        board.play(19)
        assertFalse(board.win())
    }

    @Test
    fun test_play_result_should_be_4512() {
        val actual = Day4("day4/inputTest").parse()

        assertEquals(4512,actual.play())
    }

    @Test
    fun test_lastWin_result_should_be_4512() {
        val actual = Day4("day4/inputTest").parse()

        assertEquals(1924,actual.lastWin())
    }
    @Test
    fun play_result_should_be_89001() {
        val actual = Day4("day4/input").parse()

        assertEquals(89001,actual.play())
    }

    @Test
    fun lastWin_result_should_be_7296() {
        val actual = Day4("day4/input").parse()

        assertEquals(7296,actual.lastWin())
    }
    private fun assertValues(draws: List<Int>, boards: List<Board>, actual: Bingo) {
        assertEquals(draws, actual.draws, "draws")
        assertEquals(boards, actual.boards, "boards")
    }
}
