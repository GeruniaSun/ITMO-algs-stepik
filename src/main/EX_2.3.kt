import kotlin.math.max

class DisjointSet(size: Int, private val sizes: IntArray) {
    private val parent = IntArray(size) { it }
    private val rank = IntArray(size) { 0 }
    private var max: Int = sizes.maxOrNull() ?: 0

    private fun find(i: Int): Int {
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

    override fun toString(): String {
        return "DisjointSet(parent=${parent.contentToString()}, rank=${rank.contentToString()}, max=$max)"
    }


}
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val r = readln().split(" ").map { it.toInt() }.toIntArray()
    val reqs = List(m) { readln().split(" ").take(2).map { it.toInt() }.let { (a, b) -> a to b } }

    val disjointSet = DisjointSet(n, r)
    reqs.forEach {req -> println(disjointSet.union(req.first - 1, req.second - 1))}
}