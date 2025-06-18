import java.util.*

class HashTable(private val size: Int) {
    private val p = 1000000007L
    private val x = 263L
    private val table = Array(size) { LinkedList<String>() }

    fun add(str: String) {
        val bucket = table[hash(str)]
        if (!bucket.contains(str)) bucket.addFirst(str)
    }

    fun del(str: String) { table[hash(str)].remove(str) }

    fun find(str: String) { println(if (table[hash(str)].contains(str)) "yes" else "no") }

    fun check(index: Int) { println(table.getOrNull(index)?.joinToString(" ") ?: "") }

    private fun hash(str: String): Int {
        val (hash, _) = str.fold(0L to 1L) { (hash, power), c ->
            (hash + c.code * power) % p to (power * x) % p
        }
        return (hash % size).toInt()
    }
}

fun main() {
    val tableSize = readln().toInt()
    val table = HashTable(tableSize)

    repeat(readln().toInt()) {
        val (command, value) = readln().split(' ')
        when (command) {
            "add" -> table.add(value)
            "del" -> table.del(value)
            "find" -> table.find(value)
            "check" -> table.check(value.toInt())
        }
    }
}