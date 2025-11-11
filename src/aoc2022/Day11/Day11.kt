package aoc2022.Day11

import java.io.File

fun main() {
    val input = File("aoc2022/Day11/input.txt").readLines()
    val monkeyList = input.filter { it.isNotBlank() }.chunked(6).map { monkey ->
        val id = "\\d+".toRegex().find(monkey[0])!!.value.toInt()
        val items = "\\d+".toRegex().findAll(monkey[1]).map { it.value.toLong() }.toMutableList()
        val operator = "[+\\-*]".toRegex().find(monkey[2])!!.value
        val y = "\\d+".toRegex().find(monkey[2])?.value ?: "old"
        val operation:(Long) -> Long = when (operator) {
            "+" -> {x -> x+y.toLong()}
            "-" -> {x -> x-y.toLong()}
            "*" -> {x -> (if(y=="old") x*x else x*y.toLong())}
            else -> error(y)
        }
        val divideBy = "\\d+".toRegex().find(monkey[3])!!.value.toLong()
        val t = "\\d+".toRegex().find(monkey[4])!!.value.toInt()
        val f = "\\d+".toRegex().find(monkey[5])!!.value.toInt()
        val test: (Long) -> Int = { check -> when {
            check % divideBy == 0L -> t
            else -> f
        }}
        Monkey(id, items, operation, divideBy, test)
    }
    val modProduct = monkeyList.map { it.divideBy }.reduce { acc, i -> acc*i }
    repeat(10000) { monkeyList.forEach { it.throwItems(monkeyList, modProduct) } }
    val sorted = monkeyList.map { it.inspected }.sortedDescending()
    println(sorted[0]*sorted[1])
}
class Monkey(
    val id: Int, private val items: MutableList<Long>, val operation: (Long) -> Long,
    val divideBy: Long, private val test:(Long) -> Int) {
    var inspected = 0L
    fun throwItems(monkeyList: List<Monkey>, monkeyMod:Long) {
        repeat(items.size) {
            val item = items.removeFirst()
            var newItem = operation(item)
            newItem %= monkeyMod
            monkeyList[test(newItem)].items.add(newItem)
            inspected++
        }
    }
}