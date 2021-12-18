import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day18Test {
    @Test
    fun parseSimplePair() {
        val day = Day18()
        assertEquals(Day18.SnailPair.of(1,2), day.numberParser("[1,2]"))
    }
    @Test
    fun parseSimplePair2() {
        val day = Day18()
        assertEquals(Day18.SnailPair(Day18.SnailPair.of(1,2), Day18.SnailNumber(3)), day.numberParser("[[1,2],3]"))
    }
    @Test
    fun parseSimplePair3() {
        val day = Day18()
        assertEquals(Day18.SnailPair(Day18.SnailPair.of(1,9), Day18.SnailPair.of(8,5)), day.numberParser("[[1,9],[8,5]]"))
    }

    @Test
    fun parseSimplePair4() {
        val day = Day18()
        assertEquals(Day18.SnailPair(Day18.SnailPair(
            Day18.SnailPair(Day18.SnailPair.of(1,2), Day18.SnailPair.of(3,4)),
            Day18.SnailPair(Day18.SnailPair.of(5,6), Day18.SnailPair.of(7,8)),
        ), Day18.SnailNumber(9)), day.numberParser("[[[[1,2],[3,4]],[[5,6],[7,8]]],9]"))
    }

    @Test
    fun test_compute_should_be_X() {
        val day = Day18("day18/inputTest")
        assertEquals(0, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day18("day18/input")
        assertEquals(0, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day18("day18/inputTest")
        assertEquals(0, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day18("day18/input")
        assertEquals(0, day.compute2())
    }
}
