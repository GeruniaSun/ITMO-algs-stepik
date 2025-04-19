import main.heightOfTreeFromArray
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Tester {

    private fun uniTester(filename: String, testFunction: (String) -> String) {
        // Чтение файла из ресурсов
        // в файле ввод и ожидаемый результат разделены ' | '
        val file = this::class.java.classLoader.getResource(filename)?.file
        if (file == null) {
            println("Файл не найден!")
            return
        }

        File(file).readLines().forEach { line ->
            val (input, expected) = line.split(" | ", limit = 2)
            val result = testFunction(input)
            assertEquals(expected, result, "Ошибка для входной строки: '$input'")
        }
    }

    @Test
    fun testEX_1_1() {
        uniTester("tests_1.1", ::isValidSequence)
    }

    @Test
    fun testEX_1_2() {
        fun adapter(input: String): String {
            val array = input.split(" ").map { it.toInt() }.toTypedArray()
            return  heightOfTreeFromArray(array).toString()
        }

        uniTester("tests_1.2", ::adapter)
    }

}