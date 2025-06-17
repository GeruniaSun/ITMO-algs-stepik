fun proceedRequest(numbers: MutableMap<Int, String>, req: List<String>) =
    with(req + listOf("")) {
        when (get(0)) {
            "add" -> numbers[get(1).toInt()] = get(2)
            "del" -> numbers.remove(get(1).toInt())
            else -> println(numbers[get(1).toInt()] ?: "not found")
        }
    }

fun main() {
    val n = readln().toInt()
    val numbers = mutableMapOf<Int, String>()
    repeat(n) { proceedRequest(numbers, readln().split(" ")) }
}