fun main() {
    while (true) {
        println("\n=== МЕНЮ ДЛЯ ЗАДАЧ ===")
        println("1 - Задача с символами")
        println("2 - Задача с символами")
        println("3 - Перевод из 10-чной в 2-чную систему")
        println("4 - числа с операциями")
        println("5 - Целочисленный показатель")
        println("6 - ")
        println("0 - Выход")
        print("Выбери цифру: ")

        when (readLine()!!.toIntOrNull()) {
            1 -> task1()
            2 -> task2()
            3 -> task3()
            4 -> task4()
            5 -> task5()
            6 -> task6()
            0 -> {
                println("ВЫХОД ИЗ ПРОГРАММЫ")
                return
            }
            else -> println("ВЫБЕРИТЕ ЦИФРУ ОТ 0 ДО 6")
        }
    }
}

fun task1() {
    println("Введите последовательность символов:")
    val input = readLine()?.toUpperCase() ?: ""

    if (input.isEmpty()) {
        println("Ничего не введено.")
        return
    }

    val result = StringBuilder()
    var currentChar = input[0]
    var count = 1

    for (i in 1 until input.length) {
        val char = input[i]
        val any = if (char == currentChar) {
            count++
        } else {
            result.append(if (count > 1) "$currentChar$count" else currentChar)
            currentChar = char
            count = 1
        }
    }
    result.append(if (count > 1) "$currentChar$count" else currentChar)

    println("Результат: ${result.toString()}")
}

fun task2() {
        println("Введите строку для анализа:")
        val input = readLine() ?: ""

        if (input.isEmpty()) {
            println("Ничего не введено.")
            return
        }

        // подсчет символов
        val charCount = mutableMapOf<Char, Int>()

        // подсчитываем количество каждого символа
        for (char in input) {
            val upperChar = char.uppercaseChar() // приводим к верхнему регистру
            charCount[upperChar] = charCount.getOrDefault(upperChar, 0) + 1
        }

        // сортируем символы в алфавитном порядке
        val sortedChars = charCount.keys.sorted()

        // выводим результат
        println("Результат подсчета символов:")
        for (char in sortedChars) {
            println("$char - ${charCount[char]}")
        }
    }

fun task3() {
    println("Введите натуральное число в десятичной системе:")
    val input = readLine() ?: ""

    if (input.isEmpty()) {
        println("Ничего не введено.")
        return
    }

    // Проверяем, что введено число
    val number = input.toIntOrNull()
    if (number == null) {
        println("Ошибка: введено не число.")
        return
    }

    // проверяем, что число натуральное (положительное)
    if (number <= 0) {
        println("Ошибка: введите натуральное число (больше 0).")
        return
    }

    // преобразуем число в двоичную систему
    var tempNumber = number
    var binaryString = ""

    while (tempNumber > 0) {
        val remainder = tempNumber % 2
        binaryString = remainder.toString() + binaryString
        tempNumber /= 2
    }

    println("Десятичное число $number в двоичной системе: $binaryString")
}
fun task4() {
    println("Введите выражение в формате: ЧИСЛО1 ЧИСЛО2 ОПЕРАЦИЯ")
    println("Например: 10.5 2.5 *")
    println("Доступные операции: +, -, *, /")

    val input = readLine()?.trim() ?: ""

    if (input.isEmpty()) {
        println("Ничего не введено.")
        return
    }

    // разделяем ввод по пробелам
    val parts = input.split(" ")

    // проверяем, что введено три части
    if (parts.size != 3) {
        println("Ошибка: неверный формат ввода. Используйте: ЧИСЛО1 ЧИСЛО2 ОПЕРАЦИЯ")
        return
    }

    //парсим числа
    val num1 = parts[0].toDoubleOrNull()
    val num2 = parts[1].toDoubleOrNull()
    val operation = parts[2]

    if (num1 == null || num2 == null) {
        println("Ошибка: введены не числа.")
        return
    }

    // Выполняем операцию
    val result = when (operation) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> {
            if (num2 == 0.0) {
                println("Ошибка: деление на ноль.")
                return
            }
            num1 / num2
        }
        else -> {
            println("Ошибка: неизвестная операция '$operation'. Используйте: +, -, *, /")
            return
        }
    }

    val formattedResult = if (result % 1 == 0.0) {
        result.toInt().toString()
    } else {
        result.toString()
    }


    println("Результат: $formattedResult")
}

fun task5() {
    println("Введите целое число n:")
    val n = readLine()?.toIntOrNull()

    if (n == null) {
        println("Ошибка: введено не целое число")
        return
    }

    println("Введите основание степени x:")
    val x = readLine()?.toDoubleOrNull()

    if (x == null) {
        println("Ошибка: введено не число")
        return
    }

    val result = findExponent(n, x)
    println(result)
}

private fun findExponent(n: Int, x: Double): String {
    if (n == 0) {
        return if (x == 0.0) "Целочисленный показатель не существует" else "0"
    }

    if (x == 0.0) {
        return "Целочисленный показатель не существует"
    }

    if (x == 1.0) {
        return if (n == 1) "0" else "Целочисленный показатель не существует"
    }

    if (x == -1.0) {
        return if (n == 1 || n == -1) "0" else "Целочисленный показатель не существует"
    }

    if (n < 0 && x > 0) {
        return "Целочисленный показатель не существует"
    }

    val absN = Math.abs(n.toDouble())
    val absX = Math.abs(x)

    val candidate = Math.log(absN) / Math.log(absX)

    if (candidate.isNaN() || candidate.isInfinite()) {
        return "Целочисленный показатель не существует"
    }

    val candidates = listOf(
        Math.floor(candidate).toInt(),
        Math.ceil(candidate).toInt()
    )

    for (y in candidates) {
        if (checkPower(x, y, n)) {
            return y.toString()
        }
    }

    if (candidate > -1000 && candidate < 1000) {
        val extraCandidates = listOf(
            candidates[0] - 1,
            candidates[1] + 1
        )

        for (y in extraCandidates) {
            if (checkPower(x, y, n)) {
                return y.toString()
            }
        }
    }

    return "Целочисленный показатель не существует"
}

private fun checkPower(x: Double, y: Int, n: Int): Boolean {
    val value = try {
        Math.pow(x, y.toDouble())
    } catch (e: Exception) {
        return false
    }

    return Math.abs(value - n) < 1e-10 * Math.abs(n.toDouble())
}

fun task6() {
    println("Введите первую цифру:")
    val digit1 = readLine()?.toIntOrNull()

    println("Введите вторую цифру:")
    val digit2 = readLine()?.toIntOrNull()

    if (digit1 == null || digit2 == null) {
        println("Ошибка: введены не цифры")
        return
    }

    if (digit1 !in 0..9 || digit2 !in 0..9) {
        println("Ошибка: введены не цифры (должны быть от 0 до 9)")
        return
    }

    if (digit1 == digit2) {
        println("Цифры должны быть различны")
        return
    }

    // Пытаемся составить нечетное число из двух цифр
    val possibleNumbers = mutableListOf<Int>()

    // Первый вариант: первая цифра - десятки, вторая - единицы
    if (digit1 != 0) { // Число не может начинаться с 0
        possibleNumbers.add(digit1 * 10 + digit2)
    }

    // Второй вариант: вторая цифра - десятки, первая - единицы
    if (digit2 != 0) { // Число не может начинаться с 0
        possibleNumbers.add(digit2 * 10 + digit1)
    }

    // Ищем нечетное число среди возможных вариантов
    val oddNumber = possibleNumbers.firstOrNull { it % 2 != 0 }

    if (oddNumber != null) {
        println("Нечетное число: $oddNumber")
    } else {
        println("Создать нечетное число невозможно")
    }
}