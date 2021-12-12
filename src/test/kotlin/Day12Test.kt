import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day12Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day12("day12/inputTest")
        assertEquals(10, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day12("day12/input")
        assertEquals(0, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day12("day12/inputTest")
        assertEquals(0, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day12("day12/input")
        assertEquals(0, day.compute2())
    }
}
