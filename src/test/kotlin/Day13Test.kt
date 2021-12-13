import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day13Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day13("day13/inputTest")
        assertEquals(0, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day13("day13/input")
        assertEquals(0, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day13("day13/inputTest")
        assertEquals(0, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day13("day13/input")
        assertEquals(0, day.compute2())
    }
}