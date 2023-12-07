package aoc2023.Day7

import java.io.File

fun main() {
    val input = File("aoc2023/Day7/input.txt").readLines()
    val hands = input.map {
        val split = it.split(" ")
        Pair(split.first(), split.last().toInt())
    }
    val valueMap = mapOf("2" to 2,"3" to 3,"4" to 4,"5" to 5,"6" to 6,"7" to 7,"8" to 8,"9" to 9,
        "T" to 10, "J" to 1, "Q" to 12, "K" to 13, "A" to 14)

    val sorted = hands.sortedWith(compareBy(
        { pair ->
            val (hand, _) = pair
            val type = hand.groupBy { it }.toMutableMap()

            //PART 2
            if (type.containsKey('J') && type.size != 1) {
                val nJ = type['J']!!.size
                type.remove('J')
                val nX = type.values.maxBy { it.size }.first()
                val tempList = type[nX]!!.toMutableList()
                repeat(nJ){ tempList.add(nX) }
                type[nX] = tempList
            }

            val typeValue =
                if (type.size == 1) 6
                else if (type.size == 2 && type.any { it.value.size==4 }) 5
                else if ((type.size == 2 && type.any { it.value.size==3 })) 4
                else if (type.size == 3 && type.any { it.value.size==3 }) 3
                else if (type.size == 3 && type.filter { it.value.size ==2 }.size==2) 2
                else if (type.size == 4) 1
                else 0
            typeValue
        },
        { pair -> valueMap[pair.first[0].toString()] },
        { pair -> valueMap[pair.first[1].toString()] },
        { pair -> valueMap[pair.first[2].toString()] },
        { pair -> valueMap[pair.first[3].toString()] },
        { pair -> valueMap[pair.first[4].toString()] }
    ))

    val sum = sorted.mapIndexed{index, pair -> pair.second.toLong()*(index+1) }.sum()
    println(sum)
}