import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("aoc2024/day1/input").readLines().map { it.split(" ") }
    val one = input.map { it.first().toInt() }.sorted()
    val two = input.map { it.last().toInt() }.sorted()
    val sum = one.zip(two) { a, b -> abs(a - b) }.sum()
    val similarity = one.sumOf { o -> two.filter { t -> o == t }.size*o }
    println("$sum, $similarity")
}