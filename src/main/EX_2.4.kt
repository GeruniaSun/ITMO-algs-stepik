import kotlin.math.max

class Disjoint(size: Int, private val sizes: IntArray) {
    private val parent = IntArray(size) { it }
    private val rank = IntArray(size) { 0 }
    private var max: Int = sizes.maxOrNull() ?: 0

    fun find(i: Int): Int {
        if (i != parent[i]) parent[i] = find(parent[i])
        return parent[i]
    }

    fun union(i: Int, j: Int): Int {
        val iId = find(i)
        val jId = find(j)
        if (iId == jId) return max

        if (rank[iId] > rank[jId]) {
            parent[jId] = iId
            sizes[iId] += sizes[jId]
            max = max(max, sizes[iId])
        } else {
            parent[iId] = jId
            sizes[jId] += sizes[iId]
            max = max(max, sizes[jId])
        }
        if (rank[iId] == rank[jId]) rank[jId]++

        return max
    }
}

fun solve(n: Int, eqs: List<Pair<Int, Int>>, uneqs: List<Pair<Int, Int>>): Boolean {
    val set = Disjoint(n, (1..n).toList().toIntArray())
    eqs.forEach { eq -> set.union(eq.first - 1, eq.second - 1) }
    return uneqs.any{ eq -> set.find(eq.first - 1) == set.find(eq.second - 1)}
}

fun main() {
    val (n, e, d) = readln().split(" ").map { it.toInt() }
    val equations = List(e) { readln().split(" ").take(2).map { it.toInt() }.let { (a, b) -> a to b } }
    val unequations = List(d) { readln().split(" ").take(2).map { it.toInt() }.let { (a, b) -> a to b } }

    println(if (solve(n, equations, unequations)) 0 else 1)
}