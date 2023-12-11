package aoc2023.Day10

import java.awt.Point
import java.io.File

fun main() {
    val input = File("aoc2023/Day10/input.txt").readLines()
    val path = mutableListOf<Point>()
    val startY = input.indexOfFirst { it.contains('S') }
    val startX = input[startY].indexOfFirst { it == 'S' }
    val start = Point(startX,startY)
    path.add(start)
    val first =
        if (input[startY-1][startX] in listOf('|', '7', 'F')) Point(startX, startY-1)
        else if (input[startY+1][startX] in listOf('|', 'J', 'L')) Point(startX, startY+1)
        else if (input[startY][startX+1] in listOf('-', '7', 'J')) Point(startX+1, startY)
        else Point(startX-1, startY)

    iterate(first, path, input)
    println(path.size/2)

    val part2 = input.mapIndexed { y, s ->
        s.mapIndexed { x, c ->
            if (Point(x,y) in path) 0
            else {
                val crossingsX = path.filter { it.y == y && it.x > x && input[it.y][it.x] in listOf('|', 'L', 'J', 'S') }
                if (crossingsX.size % 2 != 0) 1
                else 0
            }
        }.sum()
    }.sum()

    println(part2)
}

fun iterate(point: Point, path: MutableList<Point>, input: List<String>) {
    path.add(point)
    var n = getNext(point, path, input)
    while (n != null) {
        path.add(n)
        n = getNext(n, path, input)
    }
}

fun getNext(current: Point, path: MutableList<Point>, input: List<String>): Point? {
    val next = when(input[current.y][current.x]) {
        '|' -> if (Point(current.x, current.y+1) in path)
            Point(current.x, current.y-1)
            else Point(current.x, current.y+1)

        'F' -> if (Point(current.x, current.y+1) in path)
            Point(current.x+1, current.y)
            else Point(current.x, current.y+1)

        '7' -> if (Point(current.x, current.y+1) in path)
            Point(current.x-1, current.y)
            else Point(current.x, current.y+1)

        '-' -> if (Point(current.x+1, current.y) in path)
            Point(current.x-1, current.y)
        else Point(current.x+1, current.y)

        'L' -> if (Point(current.x+1, current.y) in path)
            Point(current.x, current.y-1)
        else Point(current.x+1, current.y)

        'J' -> if (Point(current.x-1, current.y) in path)
            Point(current.x, current.y-1)
        else Point(current.x-1, current.y)

        else -> null

    }
    return if (next == null) null else if (next in path) null else next
}