package aoc2022.Day4

import java.io.File

fun main() {
    val input = File("aoc2022/Day4/input.txt").readLines()

    var fullyContains = 0
    var overlappingPairs = 0
    input.forEach { line ->
        val (elf1, elf2) = line.split(",").map {
            val (low, high) = it.split("-").map(String::toInt)
            low..high
        }
        fun inside(range1:IntRange, range2:IntRange) = range2.first <= range1.first && range1.last <= range2.last

        //PART 1
        fullyContains += if (inside(elf1,elf2) || inside(elf2,elf1)) 1 else 0

        //PART 2
        overlappingPairs += if(elf1.first in elf2 || elf2.first in elf1) 1 else 0
    }
    println("PART1: $fullyContains")
    println("PART2: $overlappingPairs")
}