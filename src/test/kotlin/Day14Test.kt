import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day14Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day14("day14/inputTest")
        assertEquals(1588, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day14("day14/input")
        assertEquals(2967, day.compute())
    }

    @Test
    fun test2_compute_should_be_X() {
        val day = Day14("day14/inputTest")
        assertEquals(2188189693529, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day14("day14/input")
        assertEquals(3692219987038, day.compute2())
    }
}
