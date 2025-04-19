import java.util.*
import kotlin.NoSuchElementException

fun main() {
    val n = readln().toInt()
    val stack = IntStackWithMAx()
    repeat(n) {
        val parts = readln().split(" ", limit = 2)
        val operation = parts[0]
        val arg = parts.getOrNull(1) ?: ""

        when (operation) {
            "push" -> stack.push(arg.toInt())
            "pop" -> stack.pop()
            "max" -> println(stack.max())
        }
    }
}

class IntStackWithMAx {
    private val elements = mutableListOf<Int>()
    private val maxStack = Stack<Int>()

    fun push(item: Int) {
        elements.add(item)
        if (maxStack.isEmpty() || item >= maxStack.peek()) maxStack.push(item)
    }

    fun pop() {
        if (isEmpty()) throw NoSuchElementException("пуста)")
        val removed = elements.removeAt(elements.lastIndex)
        if (removed == maxStack.peek()) maxStack.pop()
    }

    fun max(): Int {
        return if (maxStack.isNotEmpty()) maxStack.peek() else 0
    }

    private fun isEmpty() = elements.isEmpty()

    val size get() = elements.size
}