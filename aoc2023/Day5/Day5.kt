package aoc2023.Day5

import java.io.File

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
        .map { it.first()..<it.first()+it.last() }

    val part1 = findLocations(part1Seeds, groups).values.min()
    println(part1)

    val test = part2(ranges, groups)
    test.forEachIndexed { i, it ->
        println("----- INDEX: $i")
        println(it)
    }
    println(test.flatten().minOf { it.first })
}

private fun part2(ranges: List<LongRange>, groups: List<List<List<Long>>>): List<List<LongRange>> {
    return ranges.mapIndexed {index, range ->
        groups.fold(listOf(range)) { ranges, maps ->
            if (index == 6) {
                println(ranges)
                println(maps)
            }

            val splitRanges = mutableListOf<LongRange>()
            val unChangedRanges = ranges.toMutableList()
            maps.forEach { mapRange ->
                val (to, from, length) = mapRange
                val fromRange = from..<from+length
                val toRange = to..<to+length
                val offset = toRange.first-fromRange.first

                val rangesToSplit = unChangedRanges.filter {
                    it.first <= fromRange.last && fromRange.first <= it.last
                }
                unChangedRanges.removeAll { it in rangesToSplit }

                rangesToSplit.forEach {
                    //its end overlaps
                    if (it.first < fromRange.first && it.last <= fromRange.last) {
                        unChangedRanges.add(it.first..<from)
                        val newRange = fromRange.first.. it.last
                        splitRanges.add(newRange.first+offset .. newRange.last+offset)
                    }
                    //its start overlaps
                    else if (it.first >= fromRange.first && it.last > fromRange.last) {
                        unChangedRanges.add(fromRange.last()+1 .. it.last)
                        val newRange = it.first..fromRange.last
                        splitRanges.add(newRange.first+offset..newRange.last+offset)
                    }
                    //it is around from
                    else if (it.first < fromRange.first && it.last > fromRange.last) {
                        unChangedRanges.add(it.first..<fromRange.first)
                        unChangedRanges.add(fromRange.last+1..it.last)
                        splitRanges.add(toRange)
                    }
                    //from is around it
                    else {
                        unChangedRanges.add(it.first+offset..it.last+offset)
                    }
                }

                if (index == 6) println("rangestosplit $rangesToSplit in $mapRange -> $fromRange")

            }

            splitRanges+unChangedRanges
        }
    }
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