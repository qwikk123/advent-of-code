package aoc2024.day5

import java.io.File

fun main() {
    val input = File("aoc2024/day5/input").readLines()
    val splitOn = input.indexOf("")
    val rules = input.subList(0, splitOn)
    val updates = input.subList(splitOn+1, input.size).map { it.split(",") }

    val sorter = { updateList: List<String>, ruleList: List<String> ->
        updateList.sortedWith {a, b -> if ("$a|$b" in ruleList) -1 else 1 }
    }

    val correctList = updates.map { sorter(it, rules) }.intersect(updates.toSet())
    val part1 = correctList.sumOf { it[it.lastIndex / 2].toInt() }
    val fixedList = updates.map { it to sorter(it, rules) }
        .filter { (original, sorted) -> original != sorted }
        .map { (_, sorted) -> sorted }
    val part2 = fixedList.sumOf { it[it.lastIndex / 2].toInt() }

    println(part1)
    println(part2)
}