import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {
    val Lines = listOf(
        Line(Point(0, 9), Point(5, 9)),
        Line(Point(8, 0), Point(0, 8)),
        Line(Point(9, 4), Point(3, 4)),
        Line(Point(2, 2), Point(2, 1)),
        Line(Point(7, 0), Point(7, 4)),
        Line(Point(6, 4), Point(2, 0)),
        Line(Point(0, 9), Point(2, 9)),
        Line(Point(3, 4), Point(1, 4)),
        Line(Point(0, 0), Point(8, 8)),
        Line(Point(5, 5), Point(8, 2)),

        )

    @Test
    fun testParser() {
        val actual = Day5("day5/inputTest").parse()
        assertEquals(
            Puzzle(Lines),
            actual
        )
    }


    @Test
    fun line_allPoints_should_return_all_points_horizontally() {
        val line = Line(Point(0, 9), Point(5, 9))
        assertEquals(
            setOf(Point(0, 9), Point(1, 9), Point(2, 9), Point(3, 9), Point(4, 9), Point(5, 9)),
            line.allPoints()
        )
    }

    @Test
    fun line_allPoints_should_return_all_points_vertically() {
        val line = Line(Point(9, 4), Point(3, 4))
        assertEquals(
            setOf(Point(9, 4), Point(8, 4), Point(7, 4), Point(6, 4), Point(5, 4), Point(4, 4), Point(3, 4)),
            line.allPoints()
        )
    }

    @Test
    fun line_allPoints_should_ignore_diagonal_lines_if_asked() {
        val line = Line(Point(8, 0), Point(0, 8))
        assertEquals(
            setOf(),
            line.allPoints()
        )
    }

    @Test
    fun line_allPoints_return_diagonal_lines_if_asked_2() {
        val line = Line(Point(1,1  ), Point(3,3))
        assertEquals(
            setOf(
                Point(1,1),Point( 2,2), Point( 3,3)
            ),
            line.allPoints(false)
        )
    }
    @Test
    fun line_allPoints_return_diagonal_lines_if_asked() {
        val line = Line(Point(8, 0), Point(0, 8))
        assertEquals(
            setOf(Point(8, 0), Point(7, 1), Point(6, 2), Point(5, 3), Point(4, 4), Point(3, 5), Point(2, 6), Point(1, 7), Point(0, 8)),
            line.allPoints(false)
        )
    }
    @Test
    fun test_MostDangerous_should_be_5() {
        val actual = Day5("day5/inputTest").parse()
        assertEquals(5, actual.mostDangerousCount())
    }

    @Test
    fun mostDangerous_diag_should_be_20898() {
        val actual = Day5("day5/input").parse()
        assertEquals(20898, actual.mostDangerousDiagonalCount())
    }

    @Test
    fun test_MostDangerous_diag_should_be_12() {
        val actual = Day5("day5/inputTest").parse()
        assertEquals(12, actual.mostDangerousDiagonalCount())
    }

    @Test
    fun mostDangerous_should_be_7674() {
        val actual = Day5("day5/input").parse()
        assertEquals(7674, actual.mostDangerousCount())
    }
}
