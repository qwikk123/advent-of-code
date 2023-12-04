package aoc2022.Day6

import java.io.File

fun main() {
    val input = File("aoc2022/Day6/input.txt").readText()
    val findIndex = { size: Int ->
         input.windowed(size).withIndex().first {(_, value) -> value.toSet().size == size }.index+size
    }
    println("${findIndex(4)} ${findIndex(14)}")
}