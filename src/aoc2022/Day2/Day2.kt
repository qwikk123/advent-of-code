package aoc2022.Day2

import java.io.File

// A = ROCK     =   X
// B = PAPER    =   Y
// C = SCISSORS =   Z

fun main() {
    val input = File("aoc2022/Day2/input.txt").readLines()
    var score = 0

    //PART 1
    input.forEach {
        val (o, y) = it.split(" ")
        val opponent = o[0].code-64
        val player = y[0].code-87

        if (opponent == player) score += 3
        else if (
            (opponent == 1 && player == 2) ||
            (opponent == 2 && player == 3) ||
            (opponent == 3 && player == 1)
        ) score += 6
        score += player
    }
    println("PART 1: $score")

    //PART 2
    score = 0
    input.forEach {
        val (o, r) = it.split(" ")
        val opponent = o[0].code-64
        val result = r[0]

        when (result) {
            'X' -> {
                score += when (opponent) {
                    1 -> 3
                    2 -> 1
                    3 -> 2
                    else -> -1
                }
            }
            'Y' -> score += 3+opponent
            'Z' -> {
                score += 6 + when (opponent) {
                    1 -> 2
                    2 -> 3
                    3 -> 1
                    else -> -1
                }
            }
        }
    }
    println("PART 2: $score")
}
