import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day10Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day10("day10/inputTest")
        assertEquals(0, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day10("day10/input")
        assertEquals(0, day.compute())
    }
}