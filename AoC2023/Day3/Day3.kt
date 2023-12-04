package AoC2023.Day3

import java.io.File
import kotlin.math.*

fun main() {
    val input = File("AoC2023/Day3/input.txt").readLines()

    val part1 = input.mapIndexed { y, s ->
        "\\d+".toRegex().findAll(s).filter { numberMatch ->
            val yRange = max(y-1, 0)..min(y+1, input.size-1)
            val xRange = max(numberMatch.range.first -1, 0)..min(numberMatch.range.last+1, s.length-1)

            input.subList(yRange.first, yRange.last+1).map { checkLine ->
                checkLine.substring(xRange).any { c -> !(c.isDigit() || c == '.')}
            }.any { it }
        }
    }.sumOf { matches -> matches.sumOf { m -> m.value.toInt()} }
    println(part1)

    val part2 = input.mapIndexed { y, s ->
        "\\*".toRegex().findAll(s).map { starMatch ->
            val yRange = max(y-1, 0)..min(y+1, input.size-1)

            val gearParts = input.subList(yRange.first, yRange.last+1).flatMap { checkLine ->
                "\\d+".toRegex().findAll(checkLine)
            }.filter { numberMatch ->
                val xRange = max(numberMatch.range.first -1, 0)..min(numberMatch.range.last+1, s.length-1)
                xRange.intersect(starMatch.range).isNotEmpty()
            }

            if (gearParts.size == 2) gearParts[0].value.toInt()*gearParts[1].value.toInt() else 0
        }
    }.filter { it.any() }.sumOf { it.sum() }
    println(part2)
}