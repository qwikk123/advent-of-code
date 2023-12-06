package aoc2023.Day6

import java.io.File

fun main() {
    val input = File("aoc2023/Day6/input.txt").readLines()
    val times = "\\d+".toRegex().findAll(input[0]).map { it.value }
    val distances = "\\d+".toRegex().findAll(input[1]).map { it.value }
    val races = times.map(String::toInt).zip(distances.map(String::toInt))

    val part1 = races.map {
        val (time, record) = it
        var n = 0
        for (i in time downTo 1) if (i*(time-i) <= record) n++ else break
        (time+1)-(n*2)
    }.reduce { acc, i -> acc*i }

    val part2 = {
        val time = times.reduce{acc, s -> acc+s }.toLong()
        val record = distances.reduce{acc, s -> acc+s }.toLong()
        var n = 0
        for (i in time downTo 1) if (i*(time-i) <= record) n++ else break
        (time+1)-(n*2)
    }
    println(part1)
    println(part2())
}