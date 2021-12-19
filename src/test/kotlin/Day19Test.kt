import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day19Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day19("day19/inputTest")
        assertEquals(0, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day19("day19/input")
        assertEquals(0, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day19("day19/inputTest")
        assertEquals(0, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day19("day19/input")
        assertEquals(0, day.compute2())
    }
}