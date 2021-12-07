import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day7Test {

    @Test
    fun test_data_should_be_37() {
        val crabs = Day7("day7/inputTest").parse()
        assertEquals(37, crabs.align())
    }


    @Test
    fun data_should_be_363101_after_80() {
        val crabs = Day7("day7/input").parse()
        assertEquals(363101, crabs.align())
    }

}
