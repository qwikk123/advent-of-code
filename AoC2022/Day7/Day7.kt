package AoC2022.Day7

import java.io.File
import kotlin.math.min

fun main() {
    val input = File("AoC2022/Day7/input.txt").readLines()
    val root = Node("root",null)
    var currentDir = root

    input.forEach { line ->
        when {
            line.startsWith("\$ cd ") -> {
                val next = line.substringAfter("\$ cd ")
                currentDir = when (next) {
                    "/" -> root
                    ".." -> currentDir.parent!!
                    else -> currentDir.subDirs.first { it.name == next }
                }
            }
            line.startsWith("dir ") -> {
                val dirName = line.substringAfter("dir ")
                if (!currentDir.subDirs.any { it.name == dirName }) currentDir.subDirs.add(Node(dirName,currentDir))
            }
            "^\\d+".toRegex().containsMatchIn(line)-> {
                val (size, name) = line.split(" ")
                currentDir.files.add(Pair(name,size.toInt()))
            }
        }
    }

    //PART 1
    var sumOfSub100k = 0
    fun findSumOfSub100k(node: Node) {
        if (node.size < 100000) sumOfSub100k += node.size
        node.subDirs.forEach { findSumOfSub100k(it) }
    }
    findSumOfSub100k(root)
    println(sumOfSub100k)

    //PART 2
    val spaceTarget = 30000000 - (70000000 - root.size)
    var currentSmallest = Int.MAX_VALUE
    fun findDirToDelete(node: Node) {
        if (node.size > spaceTarget) currentSmallest = min(currentSmallest, node.size)
        node.subDirs.forEach { findDirToDelete(it) }
    }
    findDirToDelete(root)
    println(currentSmallest)
}

class Node(val name:String, val parent:Node?) {
    val subDirs = mutableListOf<Node>()
    val files = mutableListOf<Pair<String,Int>>()
    val size: Int
        get() = files.sumOf { it.second } + subDirs.sumOf { it.size }
    override fun toString() = name
}