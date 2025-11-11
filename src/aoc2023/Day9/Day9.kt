package aoc2023.Day9

import java.io.File

fun main() {
    val input = File("aoc2023/Day9/input.txt").readLines().map { it.split(" ").map(String::toInt) }

    val part1 = input.sumOf { line -> extrapolate(line) }
    val part2 = input.sumOf { line -> extrapolate(line.reversed()) }
    println(part1)
    println(part2)
}

private fun extrapolate(line: List<Int>): Int {
    val levels = mutableListOf(line)
    while (levels.last().toSet().size != 1) {
        val next = levels.last().windowed(2).map { it.last() - it.first() }
        levels.add(next)
    }
    return levels.sumOf { it.last() }
}