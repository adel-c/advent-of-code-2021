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
        assertEquals(739, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day15("day15/inputTest")
        assertEquals(315, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day15("day15/input")
        assertEquals(3040, day.compute2())
    }
}
