package aoc2023.Day1

import java.io.File
fun main() {
    val digits = mutableMapOf(
        "one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5",
        "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9"
    )
    repeat(9) { digits[(it+1).toString()] = (it+1).toString() }
    val input = File("aoc2023/Day1/input.txt").readLines()

    val part1 = input.sumOf { "${it.first(Char::isDigit)}${it.last(Char::isDigit)}".toInt() }
    println(part1)

    val part2 = input.sumOf {
        val find = digits[it.findAnyOf(digits.keys)?.second]!!
        val findLast = digits[it.findLastAnyOf(digits.keys)?.second]!!
        "$find$findLast".toInt()
    }
    println(part2)
}