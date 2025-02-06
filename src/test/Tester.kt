import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Tester {
    @Test
    fun testFromFile() {
        // Чтение файла из ресурсов
        // в файле ввод и ожидаемый результат разделены ' | '
        val filename = "tests_1.1" // можно поменять типо
        val file = this::class.java.classLoader.getResource(filename)?.file
        if (file == null) {
            println("Файл не найден!")
            return
        }

        // Прогон тестов
        File(file).readLines().forEach { line ->
            val (input, expected) = line.split(" | ", limit = 2)
            val result = isValidSequence(input)
            assertEquals(expected, result, "Ошибка для входной строки: '$input'")
        }
    }
}