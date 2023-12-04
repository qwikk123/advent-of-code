package AoC2022.Day1

import java.io.File
import kotlin.math.max

fun main() {
    val input = File("AoC2022/Day1/input.txt").readLines()
    var highestCalories = 0
    var currentCalories = 0
    val top3 = mutableListOf(0,0,0)
    input.forEach {
        if (it == "") {
            highestCalories = max(currentCalories, highestCalories)
            top3.add(currentCalories)
            top3.sortDescending()
            top3.removeLast()
            currentCalories = 0
        }
        else currentCalories += it.toInt()
    }
    println("PART 1: $highestCalories")
    println("PART 2: ${top3.sum()}")
}