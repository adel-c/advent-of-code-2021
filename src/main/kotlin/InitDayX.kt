import java.io.File

fun main(args: Array<String>) {
    val day = "8"
    val className="Day$day"
    val inputFolder="day$day"
    File("./src/main/kotlin/$className.kt").writeText("""
        class $className(path: String = "$inputFolder/input") {
            private val inputData: List<String> = path.fromResource().readLines()
            fun compute(): Int {
                return 0
            }
        }
    """.trimIndent())

    val data = File("./src/main/resources/$inputFolder")
    data.mkdirs()
    File(data,"input").writeText("")
}
