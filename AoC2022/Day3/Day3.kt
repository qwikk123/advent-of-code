package AoC2022.Day3

import java.io.File

fun main() {
    val input = File("AoC2022/Day3/input.txt").readLines()

    //PART 1
    var total = 0
    input.forEach { line ->
        val chunkedLine = line.chunked(line.length/2)
        val map = HashMap<Char, Char>()
        var duplicateChar: Char? = null
        chunkedLine[0].forEach {
            map[it] = it
        }
        chunkedLine[1].forEach {
            if (map.containsKey(it)) duplicateChar = it
        }
        val prio = if (duplicateChar!!.isLowerCase()) duplicateChar!!.code-96 else duplicateChar!!.code-64+26
        total += prio
    }
    println("PART 1: $total")

    //PART 2
    total = 0
    input.chunked(3).forEach { group ->
        var duplicateChar: Char? = null
        val elf1 = HashMap<Char, Char>()
        val elf2 = HashMap<Char, Char>()
        group[0].forEach { item -> elf1[item] = item }
        group[1].forEach { item -> elf2[item] = item }
        group[2].forEach { item -> if (elf1.containsKey(item) && elf2.containsKey(item)) duplicateChar = item}

        val prio = if (duplicateChar!!.isLowerCase()) duplicateChar!!.code-96 else duplicateChar!!.code-64+26
        total += prio
    }
    println("PART 2: $total")
}