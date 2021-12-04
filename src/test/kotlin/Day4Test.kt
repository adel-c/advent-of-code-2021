import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day4Test {



    @Test
    fun diag_test() {
        val actual = Day4("day4/inputTest").parse()
        assertValues(listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1), listOf(), actual)
    }

    private fun assertValues(draws: List<Int>, boards:List<Board>, actual: Bingo) {
        assertEquals(draws, actual.draws, "draws")
        assertEquals(boards, actual.boards, "boards")
    }
}
