import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day15Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day15("day15/inputTest")
        assertEquals(40, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day15("day15/input")
        assertEquals(0, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day15("day15/inputTest")
        assertEquals(0, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day15("day15/input")
        assertEquals(0, day.compute2())
    }
}
