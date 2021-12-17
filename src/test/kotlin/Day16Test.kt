import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day16Test {

    @Test
    fun parseLitteral() {

        val day = Day16.PacketParser(Day16.hexToBin("D2FE28"))
        assertEquals(Day16.LiteralPacket(6,4,24,"2021"), day.parse())
    }
    @Test
    fun parseOperator() {

        val day = Day16.PacketParser(Day16.hexToBin("38006F45291200"))
        assertEquals(Day16.OperatorPacket(1,6, 0,listOf(
            Day16.LiteralPacket(6,4,11,"10"),
            Day16.LiteralPacket(2,4,16,"20")
        )), day.parse())
    }

    @Test
    fun test_compute_should_be_X() {
        val day = Day16("day16/inputTest")
        assertEquals(16, day.compute())
    }

    @Test
    fun compute_should_be_X() {
        val day = Day16("day16/input")
        assertEquals(0, day.compute())
    }
    @Test
    fun test2_compute_should_be_X() {
        val day = Day16("day16/inputTest")
        assertEquals(0, day.compute2())
    }

    @Test
    fun compute2_should_be_X() {
        val day = Day16("day16/input")
        assertEquals(0, day.compute2())
    }
}
