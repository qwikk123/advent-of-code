package aoc2024.day3

import java.io.File

fun main() {
    val input = File("aoc2024/day3/input").readText()
    val regex = "mul\\((\\d+),(\\d+)\\)".toRegex()
    val multiply = { it: MatchResult -> it.groupValues[1].toInt() * it.groupValues[2].toInt() }

    val part1 = regex.findAll(input).sumOf(multiply)
    println(part1)

    val part2 = regex.findAll(input.split("do()").joinToString { it.substringBefore("don't()") }).sumOf(multiply)
    println(part2)
}