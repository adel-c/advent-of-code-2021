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
fun <E> permutations(list: List<E>, length: Int? = null): Sequence<List<E>> = sequence {
    val n = list.size
    val r = length ?: list.size

    val indices = list.indices.toMutableList()
    val cycles = (n downTo (n - r)).toMutableList()
    yield(indices.take(r).map { list[it] })

    while (true) {
        var broke = false
        for (i in (r - 1) downTo 0) {
            cycles[i]--
            if (cycles[i] == 0) {
                val end = indices[i]
                for (j in i until indices.size - 1) {
                    indices[j] = indices[j + 1]
                }
                indices[indices.size - 1] = end
                cycles[i] = n - i
            } else {
                val j = cycles[i]
                val tmp = indices[i]
                indices[i] = indices[-j + indices.size]
                indices[-j + indices.size] = tmp
                yield(indices.take(r).map { list[it] })
                broke = true
                break
            }
        }
        if (!broke) {
            break
        }
    }
}
