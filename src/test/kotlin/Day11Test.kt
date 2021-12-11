import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day11Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day11("day11/inputTest")
        assertEquals(1656, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day11("day11/input")
        assertEquals(1679, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day11("day11/inputTest")
        assertEquals(195, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day11("day11/input")
        assertEquals(519, day.compute2())
    }
}
