import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day19Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day19("day19/inputTest")
        assertEquals(79, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day19("day19/input")
        assertEquals(303, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day19("day19/inputTest")
        assertEquals(3621, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day19("day19/input")
        assertEquals(9621, day.compute2())
    }
}
