import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun diag() {
        val actual = Day3().diag()
        assertEquals(3529, actual.gamma())
        assertEquals(566, actual.epsilon())
        assertEquals(3573, actual.oxygen())
        assertEquals(289, actual.co2())
    }


}
