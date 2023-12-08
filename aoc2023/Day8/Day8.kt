package aoc2023.Day8

import java.io.File
import kotlin.math.abs

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
    val start = networkStrings.map { it.take(3) }.filter { it.endsWith("A") }
    val counts = mutableListOf(*start.map{0L}.toTypedArray())
    start.forEachIndexed { index, s ->
        var current = s
        while(current.last() != 'Z') {
            for (d in leftRight) {
                if (current.last() == 'Z') break
                counts[index]++
                val pair = network[current]!!
                when(d) {
                    'L' -> current = pair.first
                    'R' -> current = pair.second
                }
            }
        }
    }

    var lcm = 1L
    counts.forEach {
        lcm = lcm(lcm, it)
    }
    println(counts)
    println(lcm)
}

fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a % b)
}

fun lcm(a: Long, b: Long): Long {
    return abs(a * b) / gcd(a, b)
}