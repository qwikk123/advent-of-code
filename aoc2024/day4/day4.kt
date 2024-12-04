package aoc2024.day4

import java.io.File

fun main() {
    val input = File("aoc2024/day4/input").readLines()

    val check = "MAS"
    val isValid = { x: Int, y: Int, c: Char ->
       try {
           input[y][x] == c
       } catch (e: IndexOutOfBoundsException) {
           false
       }
    }
    val getChar = { x: Int, y:Int ->
        try {input[y][x]}
        catch (e: IndexOutOfBoundsException) {
            '2'
        }
    }

    val part1 = input.mapIndexed { y, row -> row.mapIndexed { x, char ->
            if (char == 'X') {
                Direction.entries.map {
                    if (
                        isValid(x+it.x, y+it.y, 'M') &&
                        isValid(x+it.x*2, y+it.y*2, 'A') &&
                        isValid(x+it.x*3, y+it.y*3, 'S')
                    ) 1 else 0
                }.sum()
            } else 0
        }.sum()
    }.sum()
    println(part1)

    val part2 = input.mapIndexed { y, row -> row.mapIndexed { x, char ->
        if (char == 'A') {
            val first = "${getChar(x+Direction.LEFT_UP.x, y+Direction.LEFT_UP.y)}A${getChar(x+Direction.RIGHT_DOWN.x, y+Direction.RIGHT_DOWN.y)}"
            val second = "${getChar(x+Direction.LEFT_DOWN.x, y+Direction.LEFT_DOWN.y)}A${getChar(x+Direction.RIGHT_UP.x, y+Direction.RIGHT_UP.y)}"
            val firstMatch = first==check || first.reversed()==check
            val secondMatch = second==check || second.reversed()==check
            if (firstMatch && secondMatch) 1 else 0
        } else 0
    }.sum()
    }.sum()

    println(part2)
}

enum class Direction(val x: Int, val y: Int) {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    LEFT_UP(-1, -1),
    LEFT_DOWN(-1, 1),
    RIGHT_UP(1, -1),
    RIGHT_DOWN(1, 1);
}