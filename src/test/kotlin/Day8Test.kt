import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals


class Day8Test {


    companion object {
        @JvmStatic
        fun testData() = listOf(
            Arguments.of(2,listOf("be", "cfbegad", "cbdgef", "fgaecd", "cgeb", "fdcge", "agebfd", "fecdb", "fabcd", "edb"), listOf("fdgacbe", "cefdb", "cefbgd", "gcbe")),
            Arguments.of(3,listOf("edbfga", "begcd", "cbg", "gc", "gcadebf", "fbgde", "acbgfd", "abcde", "gfcbed", "gfec"), listOf("fcgedb", "cgb", "dgebacf", "gc")),
            Arguments.of(3,listOf("fgaebd", "cg", "bdaec", "gdafb", "agbcfd", "gdcbef", "bgcad", "gfac", "gcb", "cdgabef"), listOf("cg", "cg", "fdcagb", "cbg")),
            Arguments.of(1,listOf("fbegcd", "cbd", "adcefb", "dageb", "afcb", "bc", "aefdc", "ecdab", "fgdeca", "fcdbega"), listOf("efabcd", "cedba", "gadfec", "cb")),
            Arguments.of(3,listOf("aecbfdg", "fbg", "gf", "bafeg", "dbefa", "fcge", "gcbea", "fcaegb", "dgceab", "fcbdga"), listOf("gecf", "egdcabf", "bgf", "bfgea")),
            Arguments.of(4,listOf("fgeab", "ca", "afcebg", "bdacfeg", "cfaedg", "gcfdb", "baec", "bfadeg", "bafgc", "acf"), listOf("gebdcfa", "ecba", "ca", "fadegcb")),
            Arguments.of(3,listOf("dbcfg", "fgd", "bdegcaf", "fgec", "aegbdf", "ecdfab", "fbedc", "dacgb", "gdcebf", "gf"), listOf("cefg", "dcbef", "fcge", "gbcadfe")),
            Arguments.of(1,listOf("bdfegc", "cbegaf", "gecbf", "dfcage", "bdacg", "ed", "bedf", "ced", "adcbefg", "gebcd"), listOf("ed", "bcgafe", "cdgba", "cbgef")),
            Arguments.of(4,listOf("egadfb", "cdbfeg", "cegd", "fecab", "cgb", "gbdefca", "cg", "fgcdab", "egfdb", "bfceg"), listOf("gbdfcae", "bgc", "cg", "cgb")),
            Arguments.of(2,listOf("gcafb", "gcf", "dcaebfg", "ecagb", "gf", "abcdeg", "gaef", "cafbge", "fdbac", "fegbdc"), listOf("fgae", "cfgab", "fg", "bagce")),

            )

    }

    @ParameterizedTest(name = "{index} expect {0} from output {2}")
    @MethodSource("testData")
    fun test_line_by_line(expected: Int,data: List<String>, output:List<String> ) {
        val s = DataLine(data,output)
        assertEquals(expected, s.numberOfOneFourSevenEight())
    }

  @Test
    fun test_line_by_line2()  {
        val s = DataLine(listOf("be", "cfbegad", "cbdgef", "fgaecd", "cgeb", "fdcge", "agebfd", "fecdb", "fabcd", "edb"), listOf("fdgacbe", "cefdb", "cefbgd", "gcbe"))
        assertEquals(8394, s.computeNumber())
    }

    @Test
    fun test_line_by_line3()  {
        val s = DataLine(listOf("bdfegc", "cbegaf", "gecbf", "dfcage", "bdacg", "ed", "bedf", "ced", "adcbefg", "gebcd") , listOf("ed", "bcgafe", "cdgba", "cbgef"))
        //assertEquals(1625, s.computeNumber2())
        assertEquals(1625, s.computeNumber())
    }
    @Test
    fun test_compute_should_be_X() {
        val day = Day8("day8/inputTest")
        assertEquals(26, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day8("day8/input")
        assertEquals(409, day.compute())
    }


    @Test
    fun test2_compute_should_be_X() {
        val day = Day8("day8/inputTest")
        assertEquals(61229, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day8("day8/input")
        assertEquals(1024649, day.compute2())
    }
}
