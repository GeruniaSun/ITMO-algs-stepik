fun siftDown(arr: IntArray, i: Int, heapSize: Int, swaps: MutableList<Pair<Int, Int>>) {
    var minIndex = i
    val left = 2 * i + 1
    val right = 2 * i + 2

    if (left < heapSize && arr[left] < arr[minIndex]) minIndex = left
    if (right < heapSize && arr[right] < arr[minIndex]) minIndex = right

    if (minIndex != i) {
        swaps.add(i to minIndex)
        arr[i] = arr[minIndex].also { arr[minIndex] = arr[i] }
        siftDown(arr, minIndex, heapSize, swaps)
    }
}

fun buildHeap(arr: IntArray): MutableList<Pair<Int, Int>> {
    val swaps = mutableListOf<Pair<Int, Int>>()
    for (i in arr.size / 2 - 1 downTo 0) {
        siftDown(arr, i, arr.size, swaps)
    }
    return swaps
}

fun main() {
    readln()
    val arr = readln().split(" ").map { it.toInt() }.toIntArray()

    val swaps = buildHeap(arr)

    println(swaps.size)
    for (swap in swaps) {
        println("${swap.first} ${swap.second}")
    }
}