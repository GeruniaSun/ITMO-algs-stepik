import java.util.Stack

fun main() {
    println(isValidSequence(readln()))
}

fun isValidSequence(seq: String): String {
    val openings = listOf('(', '{', '[')
    val closings = listOf(')', '}', ']')
    val openingStack: Stack<Int> = Stack()

    for (i in seq.indices) {
        if ((seq[i] !in openings) and (seq[i] !in closings)) continue

        if (seq[i] in openings) openingStack.push(i)
        else if (openingStack.empty() || openings.indexOf(seq[openingStack.pop()]) != closings.indexOf(seq[i]))
                return (i + 1).toString()
    }

    return if(openingStack.empty()) "Success" else (openingStack.pop() + 1).toString()
}