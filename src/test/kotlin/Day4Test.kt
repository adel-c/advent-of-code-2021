import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day4Test {
    val aBoard = Board.fromMatrix(
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
                Board.fromMatrix(
                    listOf(
                        listOf(22, 13, 17, 11, 0),
                        listOf(8, 2, 23, 4, 24),
                        listOf(21, 9, 14, 16, 7),
                        listOf(6, 10, 3, 18, 5),
                        listOf(1, 12, 20, 15, 19),
                    )
                ),

                Board.fromMatrix(
                    listOf(
                        listOf(3, 15, 0, 2, 22),
                        listOf(9, 18, 13, 17, 5),
                        listOf(19, 8, 7, 25, 23),
                        listOf(20, 11, 10, 24, 4),
                        listOf(14, 21, 16, 12, 6),
                    )
                ),
                Board.fromMatrix(
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
        val result = aBoard.play(7, 4)
        assertEquals(setOf(7, 4), result.draws)
    }

    @Test
    fun board_should_not_play_if_won() {
        val result = aBoard.play(11, 18, 8, 23, 26, 20, 99, 88)
        assertTrue(result.computeWin())
        assertEquals(20, result.winningDraw())
        assertEquals(setOf(11, 18, 8, 23, 26, 20), result.draws)
    }

    @Test
    fun board_score_should_return_unmarked_times_winningDraw() {
        val result = aBoard.play(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 30, 20, 3)
        assertEquals(4512, result.score())
    }

    @Test
    fun isWin_should_be_true_if_line_is_all_played() {
        val result = aBoard.play(12, 10, 16, 15, 9, 19)
        assertTrue(result.computeWin())
    }

    @Test
    fun unmarkedSum_should_return_the_sum_of_all_unmarked_board() {
        val result = aBoard.play(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24)

        assertEquals(188, result.unmarked())
    }


    @Test
    fun isWin_should_be_true_if_column_is_all_played() {
        val result = aBoard.play(21, 117, 16, 8, 11, 0)
        assertTrue(result.computeWin())
    }

    @Test
    fun isWin_should_be_false_if_less_than_5_play() {
        val result = aBoard.play(10, 16, 19)
        assertFalse(result.computeWin())
    }

    @Test
    fun isWin_should_be_false_if_not_line() {
        val result = aBoard.play(10, 2, 5, 9, 19)

        assertFalse(result.computeWin())
    }

    @Test
    fun test_play_result_should_be_4512() {
        val actual = Day4("day4/inputTest").parse()

        assertEquals(4512, actual.play())
    }

    @Test
    fun test_lastWin_result_should_be_4512() {
        val actual = Day4("day4/inputTest").parse()

        assertEquals(1924, actual.lastWin())
    }

    @Test
    fun play_result_should_be_89001() {
        val actual = Day4("day4/input").parse()
        assertEquals(89001, actual.play())
    }

    @Test
    fun lastWin_result_should_be_7296() {
        val actual = Day4("day4/input").parse()
        assertEquals(7296, actual.lastWin())
    }

    private fun assertValues(draws: List<Int>, boards: List<Board>, actual: Bingo) {
        assertEquals(draws, actual.draws, "draws")
        assertEquals(boards, actual.boards, "boards")
    }
}
