import java.io.File

fun String.fromResource(): File {
    return File(getResourcePath(this))
}

fun getResourcePath(path: String): String {
    val resource = object {}.javaClass.getResource(path)
    return if (resource != null) resource.path
    else throw Exception()
}
