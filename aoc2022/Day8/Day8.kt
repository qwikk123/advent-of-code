package aoc2022.Day8

import java.io.File
import kotlin.math.max

fun main() {
    val input = File("aoc2022/Day8/input.txt").readLines()

    var visibleTrees = 0
    var highestScore = 0
    for ((rowNumber, row) in input.withIndex()) {
        for ((columnNumber, char) in row.withIndex()) {
            val currentHeight = char.toString().toInt()
            if (rowNumber == 0 || rowNumber == row.length-1 || columnNumber == 0 || columnNumber == input.size-1) {
                visibleTrees++
                continue
            }
            var (scoreRight, scoreLeft, scoreUp,scoreDown) = listOf(0,0,0,0)
            val right = row.substring(columnNumber+1).firstOrNull {
                scoreRight++
                it.toString().toInt() >= currentHeight
            }
            val left = row.substring(0..<columnNumber).reversed().firstOrNull {
                scoreLeft++
                it.toString().toInt() >= currentHeight
            }
            val up = input.subList(0,rowNumber).reversed().map { it[columnNumber] }.firstOrNull {
                scoreUp++
                it.toString().toInt() >= currentHeight
            }
            val down = input.subList(rowNumber+1,input.size).map { it[columnNumber] }.firstOrNull {
                scoreDown++
                it.toString().toInt() >= currentHeight
            }

            if (right == null || left == null || up == null || down == null) visibleTrees++

            highestScore = max(highestScore,scoreRight*scoreLeft*scoreUp*scoreDown)

        }
    }
    println(visibleTrees)
    println(highestScore)
}