package aoc2022.Day5

import java.io.File
import kotlin.collections.ArrayDeque

fun main() {
    val input = File("aoc2022/Day5/input.txt").readLines()
    val stackStrings = input.subList(0,9)
    val instructions = input.subList(10,input.size)
    val stackList = Array(10) { ArrayDeque<Char>() }
    val stackList2 = Array(10) { ArrayDeque<Char>() }

    stackStrings.forEach {
        for (i in 1..it.length step 4) {
            if (it[i].isLetter()) {
                stackList[i/4].addFirst(it[i])
                stackList2[i/4].addFirst(it[i])
            }
        }
    }
    instructions.forEach {
        val (n,from,to) = "\\d+".toRegex().findAll(it).toList()
        val tempQueue = ArrayDeque<Char>()

        for (i in 0..< n.value.toInt()) {
            //PART 1
            stackList[to.value.toInt()-1].addLast(stackList[from.value.toInt()-1].removeLast())
            //PART 2
            tempQueue.addFirst(stackList2[from.value.toInt()-1].removeLast())
        }
        stackList2[to.value.toInt()-1].addAll(tempQueue)
    }
    print("PART 1: ")
    stackList.forEach { if (it.isNotEmpty()) print(it.last()) }
    print("\nPART 2: ")
    stackList2.forEach { if (it.isNotEmpty()) print(it.last()) }
}