import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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
            listOf(Point(0, 9),Point(1, 9),Point(2, 9),Point(3, 9),Point(4, 9), Point(5, 9)),
            line.allPoints()
        )
    }

    @Test
    fun line_allPoints_should_return_all_points_vertically() {
        val line = Line(Point(9, 4), Point(3, 4))
        assertEquals(
            listOf(Point(9, 4), Point(8, 4),Point(7, 4),Point(6, 4),Point(5, 4),Point(4, 4),Point(3, 4)),
            line.allPoints()
        )
    }
    @Test
    fun line_allPoints_should_ignore_diagonal_lines() {
        val line = Line(Point(8, 0), Point(0, 8))
        assertEquals(
            listOf(),
            line.allPoints()
        )
    }
}
