package aoc2023.Day2

import java.io.File

fun main() {
    val input = File("aoc2023/Day2/input.txt").readLines()
    val checks = mapOf("red" to 12, "green" to 13, "blue" to 14)
    val colorMap = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)

    val part1 = input.filter { line ->
        val (_, game) = line.split(":")
        val rounds = game.split(";")
        var possible = true
        rounds.forEach { round ->
            val colors = round.split(",").map { it.trim() }
            colors.forEach {
                val (amount, color) = it.split(" ")
                if (amount.toInt() > checks[color]!!) possible = false
            }
        }
        possible
    }.sumOf {line ->
        val (id, _) = line.split(":")
        id.filter(Char::isDigit).toInt()
    }
    println(part1)

    val part2 = input.sumOf { line ->
        val (_, game) = line.split(":")
        val rounds = game.split(";")
        rounds.forEach { round ->
            val colors = round.split(",").map { it.trim() }
            colors.forEach {
                val (amount, color) = it.split(" ")
                if (colorMap[color]!! < amount.toInt()) colorMap[color] = amount.toInt()
            }
        }
        colorMap.values.reduce { acc, i -> acc * i }
    }
    println(part2)
}