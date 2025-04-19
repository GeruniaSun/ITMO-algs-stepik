fun main() {
    val (size, n) = readln().split(" ").map { it.toInt() }
    val buffer = Buffer(size)
    repeat(n) {
        val (arrival, duration) = readln().split(" ").map { it.toInt() }
        buffer.addPackage(arrival, duration)
    }
    println(buffer.log.joinToString("\n"))
}

class Buffer(private val size: Int) {
    val log = mutableListOf<Int>()
    private val buffer = ArrayDeque<Int>()
    private var proc: Int = 0

    fun addPackage(time: Int, duration: Int) {
        if (check(time)) push(time, duration)
        else log.add(-1)
        }

    private fun check(time: Int): Boolean {
        var maxEnd = proc
        while (buffer.isNotEmpty() && buffer.first() <= time) {
            val end = buffer.removeFirst()
            if (end > maxEnd) maxEnd = end
        }
        proc = maxEnd
        return buffer.size < size
    }

    private fun push(time: Int, duration: Int) {
        val startTime = if (buffer.isEmpty()) maxOf(time, proc) else maxOf(time, buffer.last())
        val endTime = startTime + duration
        buffer.add(endTime)
        log.add(startTime)
        if (buffer.size == 1) proc = endTime
    }
}