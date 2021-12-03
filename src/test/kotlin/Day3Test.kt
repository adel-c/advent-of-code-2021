import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun diag() {
        val actual = Day3().diag()
        assertValues(3529, 566, 3573, 289, actual)
    }

    @Test
    fun diag_test() {
        val actual = Day3("day3/inputTest").diag()
        assertValues(22, 9, 23, 10, actual)
    }

    private fun assertValues(gamma: Int, epsilon: Int, oxygen: Int, co2: Int, actual: Diagnostic) {
        assertEquals(gamma, actual.gamma(), "gamma")
        assertEquals(epsilon, actual.epsilon(), "epsilon")
        assertEquals(oxygen, actual.oxygen(), "oxygen")
        assertEquals(co2, actual.co2(), "co2")
    }
}
