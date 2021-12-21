import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day21Test {

    @Test
    fun test_compute_should_be_X() {
        val day = Day21("day21/inputTest")
        assertEquals(739785, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day21("day21/input")
        assertEquals(918081, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day21("day21/inputTest")
        assertEquals(0, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day21("day21/input")
        assertEquals(0, day.compute2())
    }
}
