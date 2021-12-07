import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals


class Day7Test {

    companion object {
        @JvmStatic
        fun testData() = listOf(
            Arguments.of(listOf(5), 0, 5),
            Arguments.of(listOf(5), 1, 4),
            Arguments.of(listOf(2), 5, 3),
            Arguments.of(listOf(5, 6), 3, 5),
            Arguments.of(listOf(1, 2, 3), 5, 9),

            )
    }

    @ParameterizedTest(name = "{index} distance from {1} should be {2}  for {0}")
    @MethodSource("testData")
    fun should_compute_distance(data: List<Int>, to: Int, expected: Int) {
        val s = Crabs(data)
        assertEquals(expected, s.distanceTo(to))
    }

    @Test
    fun test_data_should_be_37() {
        val crabs = Day7("day7/inputTest").parse()
        assertEquals(37, crabs.align())
    }


    @Test
    fun data_should_be_329389() {
        val crabs = Day7("day7/input").parse()
        assertEquals(329389, crabs.align())
    }


    @Test
    fun test_data_should_be_168_for_align2() {
        val crabs = Day7("day7/inputTest").parse()
        assertEquals(168, crabs.align2())
    }


    @Test
    fun data_should_be_329389_for_align2() {
        val crabs = Day7("day7/input").parse()
        assertEquals(329389, crabs.align2())
    }

}
