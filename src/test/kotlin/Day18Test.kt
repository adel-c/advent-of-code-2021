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
        val actual = day.numberParser("[[1,2],3]")
        val expected = Day18.SnailPair(Day18.SnailPair.of(1, 2), Day18.SnailNumber(3))
        assertEquals(expected, actual)
    }
    @Test
    fun parseSimplePair3() {
        val day = Day18()
        assertEquals(Day18.SnailPair(Day18.SnailPair.of(1,9), Day18.SnailPair.of(8,5)), day.numberParser("[[1,9],[8,5]]"))
    }
    @Test
    fun testHas4Level() {
        val day = Day18()
        val init = day.numberParser("[[[[[9,8],1],2],3],4]") as Day18.SnailPair

        val expected = day.numberParser("[9,8]")
        assertEquals(expected, init.firstLevel4()!!)
    }

    @Test
    fun left() {
        val day = Day18()
        val init = day.numberParser("[[[[[9,8],1],2],3],4]") as Day18.SnailPair

        val expected = day.numberParser("1")
        assertEquals(expected, init.left4()!!)
    }

    @Test
    fun testExplode() {
        val day = Day18()
        val init = day.numberParser("[[[[[9,8],1],2],3],4]") as Day18.SnailPair
        init.explode()
        val expected = day.numberParser("[[[[0,9],2],3],4]")
        assertEquals(expected, init)
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
