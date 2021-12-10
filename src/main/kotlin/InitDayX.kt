import java.io.File

fun main() {

    FolderStruct("99").generate()
}

class FolderStruct(day: String) {
    private val className = "Day$day"
    private val inputFolder = "day$day"
    fun generate() {

        File("./src/main/kotlin/$className.kt").writeText(
            """
        class $className(path: String = "$inputFolder/input") {
            private val inputData: List<String> = path.fromResource().readLines()
            fun compute(): Int {
                return 0
            }
        }
    """.trimIndent()
        )

        blancInput(inputFolder)


        File("./src/test/kotlin/${className}Test.kt").writeText(
            """
        import org.junit.jupiter.api.Test
        import kotlin.test.assertEquals


        class ${className}Test {

            @Test
            fun test_compute_should_be_X() {
                val day = $className("$inputFolder/inputTest")
                assertEquals(0, day.compute())
            }

            @Test
            fun compute_should_be_X() {
                val day = $className("$inputFolder/input")
                assertEquals(0, day.compute())
            }
        }
    """.trimIndent()
        )
    }

    private fun blancInput(inputFolder: String) {
        val data = File("./src/main/resources/$inputFolder")
        data.mkdirs()
        File(data, "input").writeText("")

        val dataTest = File("./src/test/resources/$inputFolder")
        dataTest.mkdirs()
        File(dataTest, "inputTest").writeText("")
    }

}
