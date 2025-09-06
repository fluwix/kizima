fun main() {
    println("Введите последовательность символов.")
    val input = readLine()!!.toUpperCase() // Преобразуем в верхний регистр
    if (input.isEmpty()) {
        println("Ничего не введено.")
        return
    }

    val result = StringBuilder()
    var currentChar = input[0]
    var count = 1

    for (i in 1 until input.length) {
        val char = input[i]
        if (char == currentChar) {
            count++
        } else {
            if (count > 1) {
                result.append("$currentChar$count")
            } else {
                result.append(currentChar)
            }
            currentChar = char
            count = 1
        }
    }

    if (count > 1) {
        result.append("$currentChar$count")
    } else {
        result.append(currentChar)
    }

    println(result.toString())
}