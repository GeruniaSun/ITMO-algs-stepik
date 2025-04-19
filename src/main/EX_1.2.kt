fun main() {
    readln()
    println(heightOfTreeFromArray(arr = readln().split(" ").map { it.toInt() }.toTypedArray()))
}

fun heightOfTreeFromArray(arr: Array<Int>): Int {
    val heights: MutableMap<Int, Int> = mutableMapOf()

    fun nodeDeep(node: Int): Int {
        if (node == -1) heights[node] = 0
        else if (heights.contains(arr[node])) heights[node] = heights[arr[node]]!!.plus(1)
        
        return heights[node] ?: (nodeDeep(arr[node]) + 1)
    }

    return arr.indices.maxOf { nodeDeep(it) }
}