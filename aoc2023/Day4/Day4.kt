package aoc2023.Day4

import java.io.File

fun main() {
    val input = File("aoc2023/Day4/input.txt").readLines()
        .map { it.split(":")[1].split("|").map { it.trim().replace("  ", " ") } }

    val amount = mutableMapOf<Int, Int>()

    val part1 = input.mapIndexed { index, l ->
        val (winning, numbers) = l.map { it.split(" ") }
        val matches = numbers.filter { it in winning }.size

        //Part 2
        val nCards = amount.getOrPut(index) { 1 }
        repeat(matches) { j ->
            val element = index + j + 1
            amount[element] = amount.getOrPut(element) { 1 } + nCards
        }

        if (matches != 0) 1 shl matches - 1 else 0
    }.sum()

    println(part1)
    println(amount.values.sum())
}