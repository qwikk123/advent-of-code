package aoc2023.Day5

import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main() {
    val input = File("aoc2023/Day5/input.txt").readText().split("\n\n")
    val part1Seeds = input.first().split(" ").drop(1).map(String::toLong).asSequence()
    val groups = input.drop(1).map {
        val lines = it.lines().drop(1)
        lines.map { mapping ->
            mapping.split(" ").map(String::toLong)
        }
    }

    val ranges = input.first().split(" ").drop(1)
        .map(String::toLong)
        .chunked(2)
        .map { it.first() to it.last() }

    val part1 = findLocations(part1Seeds, groups).values.min()
    println(part1)

    val part2 = part2(ranges, groups)
    println(part2)
}

private fun part2(ranges: List<Pair<Long, Long>>, groups: List<List<List<Long>>>): Long {
    return ranges.flatMap {range ->
        groups.fold(listOf(range)) { ranges, maps ->
            val splitRanges = mutableListOf<Pair<Long, Long>>()
            val unChangedRanges = ranges.toMutableList()

            maps.forEach { mapRange ->
                val (to, from, length) = mapRange
                val fromEnd = from+length

                val rangesToSplit = unChangedRanges.filter {
                    it.first <= fromEnd && from <= it.first+it.second
                }
                unChangedRanges.removeAll { it in rangesToSplit }

                rangesToSplit.forEach {
                    val start = it.first
                    val end = it.first+it.second

                    splitRanges += (to + max(start, from) - from) to min(end, fromEnd) - max(start, from)
                    if (start < from) unChangedRanges += start to (from - start)
                    if (end > fromEnd) unChangedRanges += fromEnd to (end-fromEnd)

                }
            }
            splitRanges+unChangedRanges
        }
    }.minOf { it.first }
}

private fun findLocations(seeds: Sequence<Long>, maps: List<List<List<Long>>>): MutableMap<Long, Long> {
    val seedLocationMap = seeds.associateWith { it }.toMutableMap()
    maps.forEach { map ->
        val tempMap = mutableMapOf<Long, Long>()
        map.forEach { mapping ->
            val (to, from, length) = mapping
            seeds.forEach { key ->
                val value = seedLocationMap[key]!!
                if (value in from..<from + length) {
                    tempMap[key] = value - from + to
                }
            }
        }
        tempMap.forEach {
            seedLocationMap[it.key] = it.value
        }
    }
    return seedLocationMap
}