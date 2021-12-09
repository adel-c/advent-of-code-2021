import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day9Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day9("day9/inputTest")
        assertEquals(15, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day9("day9/input")
        assertEquals(478, day.compute())
    }
}
