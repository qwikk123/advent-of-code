package aoc2024.day2

import java.io.File

fun main() {
    val input = File("aoc2024/day2/input").readLines().map { it.split(" ").map { it.toInt() } }

    val isSafe = { r: List<Int>  ->
        val diffs = r.zipWithNext { a, b -> b - a }
        val range = if (diffs.first() > 0) 1..3 else -3..-1
        diffs.all { it in range }
    }
    val isSafeDampened = { r: List<Int> -> r.indices.any { remove -> isSafe(r.filterIndexed { i, _ -> i != remove }) } }

    val part1 = input.count(isSafe)
    val part2 = input.count(isSafeDampened)

    println(part1)
    println(part2)
}

