import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day8Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day8("day8/inputTest")
        assertEquals(26, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day8("day8/input")
        assertEquals(0, day.compute())
    }
}
