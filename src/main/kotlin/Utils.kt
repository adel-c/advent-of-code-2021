import java.io.File
import kotlin.math.absoluteValue

fun String.fromResource(): File {
    return File(getResourcePath(this))
}

fun getResourcePath(path: String): String {
    val resource = object {}.javaClass.getResource(path)
    return if (resource != null) resource.path
    else throw Exception()
}

fun Int.distance(i: Int) = (this - i).absoluteValue
fun Int.intSum() = (this * (this + 1)) / 2
fun Int.min(i: Int) = if (this < i) this else i
fun Int.max(i: Int) = if (this > i) this else i
fun Int.minMax(p: Pair<Int, Int>): Pair<Int, Int> = Pair(p.first.min(this), p.second.max(this))
fun Long.minMax(p: Pair<Long, Long>): Pair<Long, Long> = Pair(p.first.min(this), p.second.max(this))
fun Long.min(i: Long) = if (this < i) this else i
fun Long.max(i: Long) = if (this > i) this else i
