import java.util.*

fun solve(processors: Int, tasks: List<Long>): List<Pair<Int, Long>> {
    val queue = PriorityQueue<Pair<Long, Int>>(compareBy({ it.first }, { it.second }))
    val log = mutableListOf<Pair<Int, Long>>()

    repeat(processors) { queue.add(0L to it) }
    tasks.forEach { task ->
        val (time, proc) = queue.poll()
        log.add(proc to time)
        queue.add(time + task to proc)
    }

    return log
}

fun main() {
    val (n) = readln().split(' ').map(String::toInt)
    val tasks = readln().split(" ").map { it.toLong() }
    solve(n, tasks).forEach { (processor, startTime) ->
        println("$processor $startTime")
    }
}