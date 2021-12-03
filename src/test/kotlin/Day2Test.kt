import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun postion() {
        val actual = Day2().position()
        assertEquals(1895, actual.horizontal)
        assertEquals(894, actual.depth)
        assertEquals(0, actual.aim)
        assertEquals(1694130, actual.factor())
    }

    @Test
    fun postion2() {
        val actual = Day2().position2()
        assertEquals(1895, actual.horizontal)
        assertEquals(896491, actual.depth)
        assertEquals(894, actual.aim)
        assertEquals(1698850445, actual.factor())
    }
}
