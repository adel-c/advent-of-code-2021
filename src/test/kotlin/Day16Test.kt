import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day16Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day16("day16/inputTest")
        assertEquals(16, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day16("day16/input")
        assertEquals(0, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day16("day16/inputTest")
        assertEquals(0, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day16("day16/input")
        assertEquals(0, day.compute2())
    }
}
