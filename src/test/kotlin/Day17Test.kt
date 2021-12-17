import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day17Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day17("day17/inputTest")
        assertEquals(45, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day17("day17/input")
        assertEquals(4753, day.compute())
    }

    @Test
    fun test2_compute_should_be_X() {
        val day = Day17("day17/inputTest")
        assertEquals(112, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day17("day17/input")
        assertEquals(1546, day.compute2())
    }
}
