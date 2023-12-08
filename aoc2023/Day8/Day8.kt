package aoc2023.Day8

import java.io.File

fun main() {
    val input = File("aoc2023/Day8/input.txt").readText().split("\n\n")
    val leftRight = input.first()
    val networkStrings = input.last().lines()
    val network = mutableMapOf<String, Pair<String,String>>()
    networkStrings.forEach {
        val key = it.take(3)
        val pair = it.takeLast(10).trim('(',')').split(", ")
        network[key] = Pair(pair.first(), pair.last())
    }
    var currentKey = "AAA"
    var count = 0
    while (currentKey != "ZZZ") {
        for (d in leftRight) {
            if (currentKey == "ZZZ") break
            val pair = network[currentKey]!!
            when(d) {
                'L' -> currentKey = pair.first
                'R' -> currentKey = pair.second
            }
            count++
        }
    }
    println(leftRight)
    println(network)
    println(count)
    println(currentKey)
}