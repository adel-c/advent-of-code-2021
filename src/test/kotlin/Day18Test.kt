import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals


class Day18Test {

    companion object {
        @JvmStatic
        fun sumTestData() = listOf(
            Arguments.of(
                "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
                "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
                "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"
            ),
            Arguments.of(
                "[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]",
                "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
                "[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]"
            ),
            Arguments.of(
                "[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]",
                "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
                "[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]"
            ),
            Arguments.of(
                "[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]",
                "[7,[5,[[3,8],[1,4]]]]",
                "[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]"
            ),
            Arguments.of(
                "[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]",
                "[[2,[2,2]],[8,[8,1]]]",
                "[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]"
            ),
            Arguments.of(
                "[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]",
                "[2,9]",
                "[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]"
            ),
            Arguments.of(
                "[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]",
                "[1,[[[9,3],9],[[9,0],[0,7]]]]",
                "[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]"
            ),
            Arguments.of(
                "[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]",
                "[[[5,[7,4]],7],1]",
                "[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]"
            ),
            Arguments.of(
                "[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]",
                "[[[[4,2],2],6],[8,7]]",
                "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"
            ),
        )

        @Test
        fun testExplode4() {
            val day = Day18()
            val init = day.numberParser("") as Day18.SnailPair
            val result = init.explode()
            val expected = day.numberParser("")
            assertEquals(expected, result)
        }

        @JvmStatic
        fun explodeTestData() = listOf(
            Arguments.of(
                "[[[[[9,8],1],2],3],4]",
                "[[[[0,9],2],3],4]"
            ),
            Arguments.of(
                "[7,[6,[5,[4,[3,2]]]]]",
                "[7,[6,[5,[7,0]]]]"
            ),
            Arguments.of(
                "[[6,[5,[4,[3,2]]]],1]",
                "[[6,[5,[7,0]]],3]"
            ),
            Arguments.of(
                "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]",
                "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"
            ),
            Arguments.of(
                "[[[[0,7],4],[15,[0,13]]],[1,1]]",
                "[[[[0,7],4],[15,[0,13]]],[1,1]]"
            ),
        )

        @JvmStatic
        fun level4TestData() = listOf(
            Arguments.of(
                "[[[[[9,8],1],2],3],4]",
                "[9,8]"
            ),

            Arguments.of(
                "[[6,[5,[4,[3,2]]]],1]",
                "[3,2]"
            ),

            Arguments.of(
                "[7,[6,[5,[4,[3,2]]]]]",
                "[3,2]"
            ),
            Arguments.of(
                "[[6,[5,[4,[3,2]]]],1]",
                "[3,2]"
            ),
            Arguments.of(
                "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]",
                "[7,3]"
            ),

        )

    }

    @ParameterizedTest(name = "{index} {0}  {1}  =  {2}")
    @MethodSource("sumTestData")
    fun testSum(v1: String, v2: String, expected: String) {
        val day = Day18()
        val n1 = day.numberParser(v1) as Day18.SnailPair
        val n2 = day.numberParser(v2) as Day18.SnailPair
        val expected = day.numberParser(expected)

        val sum = day.sum(listOf(n1, n2))
        assertEquals(expected, sum)
    }
    @ParameterizedTest(name = "{index} {0}   =  {1}")
    @MethodSource("explodeTestData")
    fun testExplode(v1: String, expected: String) {
        val day = Day18()
        val init = day.numberParser(v1) as Day18.SnailPair
        val result = init.explode()
        val expected = day.numberParser(expected)
        assertEquals(expected, result)
    }


    @ParameterizedTest(name = "{index} {0}   =  {1}")
    @MethodSource("level4TestData")
    fun testLevel4(v1: String, expected: String) {
        val day = Day18()
        val init = day.numberParser(v1) as Day18.SnailPair
        val result = init.firstLevel4()
        val expected = day.numberParser(expected) as Day18.SnailPair
        assertEquals(expected, result)
    }


    @Test
    fun testSplit() {
        val day = Day18()
        val init = day.numberParser("[[[[0,7],4],[15,[0,13]]],[1,1]]") as Day18.SnailPair
        val result = init.split()
        val expected = day.numberParser("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]")
        assertEquals(expected, result)
    }

    @Test
    fun testSplit2() {
        val day = Day18()
        val init = day.numberParser("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]") as Day18.SnailPair
        val result = init.split()
        val expected = day.numberParser("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]")
        assertEquals(expected, result)
    }





    @Test
    fun test_compute_should_be_X() {
        val day = Day18("day18/inputTest")
        assertEquals(4140, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day18("day18/input")
        assertEquals(4641, day.compute())
    }

    @Test
    fun test2_compute_should_be_X() {
        val day = Day18("day18/inputTest")
        assertEquals(3993, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day18("day18/input")
        assertEquals(4624, day.compute2())
    }


    @Test
    fun parseSimplePair() {
        val day = Day18()
        assertEquals(Day18.SnailPair.of(1, 2), day.numberParser("[1,2]"))
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
        assertEquals(
            Day18.SnailPair(Day18.SnailPair.of(1, 9), Day18.SnailPair.of(8, 5)),
            day.numberParser("[[1,9],[8,5]]")
        )
    }

    @Test
    fun parseSimplePair4() {
        val day = Day18()
        assertEquals(
            Day18.SnailPair(
                Day18.SnailPair(
                    Day18.SnailPair(Day18.SnailPair.of(1, 2), Day18.SnailPair.of(3, 4)),
                    Day18.SnailPair(Day18.SnailPair.of(5, 6), Day18.SnailPair.of(7, 8)),
                ), Day18.SnailNumber(9)
            ), day.numberParser("[[[[1,2],[3,4]],[[5,6],[7,8]]],9]")
        )
    }
}
