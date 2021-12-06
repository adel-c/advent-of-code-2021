import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals


class Day6Test {
    private val initialData = listOf(
        3, 4, 3, 1, 2
    )

    companion object {
        @JvmStatic
        fun testData() = listOf(
            Arguments.of(1, listOf(2, 3, 2, 0, 1)),
            Arguments.of(2, listOf(1, 2, 1, 6, 0, 8)),
            Arguments.of(3, listOf(0, 1, 0, 5, 6, 7, 8)),
            Arguments.of(4, listOf(6, 0, 6, 4, 5, 6, 7, 8, 8)),
            Arguments.of(5, listOf(5, 6, 5, 3, 4, 5, 6, 7, 7, 8)),
            Arguments.of(6, listOf(4, 5, 4, 2, 3, 4, 5, 6, 6, 7)),
            Arguments.of(7, listOf(3, 4, 3, 1, 2, 3, 4, 5, 5, 6)),
            Arguments.of(8, listOf(2, 3, 2, 0, 1, 2, 3, 4, 4, 5)),
            Arguments.of(9, listOf(1, 2, 1, 6, 0, 1, 2, 3, 3, 4, 8)),
            Arguments.of(10, listOf(0, 1, 0, 5, 6, 0, 1, 2, 2, 3, 7, 8)),
            Arguments.of(11, listOf(6, 0, 6, 4, 5, 6, 0, 1, 1, 2, 6, 7, 8, 8, 8)),
            Arguments.of(12, listOf(5, 6, 5, 3, 4, 5, 6, 0, 0, 1, 5, 6, 7, 7, 7, 8, 8)),
            Arguments.of(13, listOf(4, 5, 4, 2, 3, 4, 5, 6, 6, 0, 4, 5, 6, 6, 6, 7, 7, 8, 8)),
            Arguments.of(14, listOf(3, 4, 3, 1, 2, 3, 4, 5, 5, 6, 3, 4, 5, 5, 5, 6, 6, 7, 7, 8)),
            Arguments.of(15, listOf(2, 3, 2, 0, 1, 2, 3, 4, 4, 5, 2, 3, 4, 4, 4, 5, 5, 6, 6, 7)),
            Arguments.of(16, listOf(1, 2, 1, 6, 0, 1, 2, 3, 3, 4, 1, 2, 3, 3, 3, 4, 4, 5, 5, 6, 8)),
            Arguments.of(17, listOf(0, 1, 0, 5, 6, 0, 1, 2, 2, 3, 0, 1, 2, 2, 2, 3, 3, 4, 4, 5, 7, 8)),
            Arguments.of(18, listOf(6, 0, 6, 4, 5, 6, 0, 1, 1, 2, 6, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 7, 8, 8, 8, 8)),
        )

        @JvmStatic
        fun fishData() = listOf(
            Arguments.of(3, 1, 1),
            Arguments.of(3, 3, 1),
            Arguments.of(3, 4, 2),
            Arguments.of(3, 11, 3),
            Arguments.of(3, 13, 4),
        )
    }

    @Test
    fun testParser() {
        val actual = Day6("day6/inputTest").parse()
        assertEquals(
            School(initialData),
            actual
        )
    }


    @ParameterizedTest(name = "{index} after {0} days, it should be {1}")
    @MethodSource("testData")
    fun shouldContainsExpectedList(nbDay: Int, expected: List<Int>) {
        val s = School(initialData)
        assertEquals(expected.size.toLong(), s.advance(nbDay))
    }


    @ParameterizedTest(name = "{index} for a fish {0} after {1} days, it should be {2}")
    @MethodSource("fishData")
    fun fishGrowth(fishDays: Int, nbDay: Int, count: Long) {
        val s = School(listOf(fishDays))
        assertEquals(count, s.advance(nbDay))
    }

    @Test
    fun test_data_should_be_26_after_18() {
        val s = School(initialData)
        assertEquals(26, s.advance(18))
    }

    @Test
    fun test_data_should_be_5934_after_80() {
        val s = School(initialData)
        assertEquals(5934, s.advance(80))
    }

    @Test
    fun test_data_should_be_26984457539_after_256() {
        val s = School(initialData)
        //  assertEquals(26984457539, s.advance(256))
    }


    @Test
    fun data_should_be_363101_after_80() {
        val initialSchool = Day6("day6/input").parse()
       // assertEquals(363101, initialSchool.advance(80))
    }


    fun schoolOf(vararg v: Int): School {
        return School(v.toList())
    }
}
