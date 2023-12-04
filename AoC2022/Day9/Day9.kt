package AoC2022.Day9

import java.awt.Point
import java.io.File

fun main() {
    val input = File("AoC2022/Day9/input.txt").readLines()
    val rope = MutableList(10) { Point(0,0) }
    val part1Set = mutableSetOf(rope[1])
    val part2Set = mutableSetOf(rope[rope.size-1])

    input.map { line -> line.split(" ") }.forEach { (direction,amount) ->
        repeat(amount.toInt()) {
            rope[0] += when (direction) {
                "L" -> Point(-1, 0)
                "R" -> Point(1,0)
                "U" -> Point(0,1)
                "D" -> Point(0, -1)
                else -> Point(0,0)
            }
            for (index in 1..<rope.size) {
                if (outsideRange(rope[index-1],rope[index])) {
                    val directionPoint = rope[index-1] - rope[index]
                    val x = if (directionPoint.x > 0) 1 else if (directionPoint.x < 0) -1 else 0
                    val y = if (directionPoint.y > 0) 1 else if (directionPoint.y < 0) -1 else 0

                    rope[index] += Point(x,y)
                    if (index == 1) part1Set.add(rope[index])
                    if (index == rope.size-1) part2Set.add(rope[index])
                }
            }
        }
    }
    println(part1Set.size)
    println(part2Set.size)
}
private operator fun Point.minus(point: Point) = Point(this.x - point.x, this.y - point.y)
private operator fun Point.plus(point: Point) = Point(this.x + point.x, this.y + point.y)
fun outsideRange(a: Point, b: Point) = a.x !in b.x-1..b.x+1 || a.y !in b.y-1..b.y+1