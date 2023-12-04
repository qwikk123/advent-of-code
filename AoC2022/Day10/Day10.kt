package AoC2022.Day10

import java.io.File

fun main() {
    val input = File("AoC2022/Day10/input.txt").readLines()
    val signalChecks = listOf(20,60,100,140,180,220)
    val signalStrengths = mutableListOf<Int>()
    var crtLine = 40
    var cycle = 1
    var x = 1
    var image = ""

    input.forEach { line ->
        var addx = 0
        val cyclesToDo = when {
            line == "noop" -> 1
            line.startsWith("addx ") -> {
                addx = line.substringAfter("addx ").toInt()
                2
            }
            else -> -1
        }
        repeat(cyclesToDo) {
            if (cycle in signalChecks) signalStrengths.add(x * cycle)
            if (cycle-1 == crtLine) {
                image += "\n"
                crtLine += 40
            }
            val crtPixel = cycle-1-(crtLine-40)
            image += if (crtPixel in x-1..x+1) "#" else "."
            cycle++
        }
        x += addx
    }
    println(image)
    println(signalStrengths.sum())
}