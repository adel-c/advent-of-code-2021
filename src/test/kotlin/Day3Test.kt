import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun diag() {
        val actual = Day3().diag()
        assertEquals(3529, actual.gamma())
        assertEquals(566, actual.epsilon())
        assertEquals(3573, actual.oxygen())
        assertEquals(289, actual.co2())
    }

    @Test
    fun diag_test() {
        val actual = Day3("day3/inputTest").diag()
        assertEquals(22, actual.gamma())
        assertEquals(9, actual.epsilon())
        assertEquals(23, actual.oxygen())
        assertEquals(10, actual.co2())
    }
}
