package aoc2022.Day12

import java.awt.Point
import java.io.File

fun main() {
    BFS(listOf('S'), listOf('E'))
    BFS(listOf('E'), listOf('S','a'))
}

fun BFS(start: List<Char>, end: List<Char>) {
    val graph = File("aoc2022/Day12/input.txt").readLines().map { it.toList() }
    val visited = MutableList(graph.size) { index -> MutableList(graph[index].size) { false } }
    val queue = ArrayDeque<Point>()
    val stepsQueue = ArrayDeque<Int>()
    var steps = -1

    for ((y, line) in graph.withIndex()) for ((x, node) in line.withIndex()) {
        if (node in start) {
            queue.add(Point(x, y))
            stepsQueue.add(0)
            visited[y][x] = true
            break
        }
    }

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        val currentStep = stepsQueue.removeFirst()
        val c = graph[node.y][node.x]
        if (c in end) {
            steps = currentStep
            break
        }
        for (it in getNeighbors(node, graph, visited, start[0]=='E')) {
            queue.add(it)
            stepsQueue.add(currentStep+1)
            visited[it.y][it.x] = true
        }
    }
    println(steps)
}

fun getNeighbors(current: Point, graph: List<List<Char>>, visited: MutableList<MutableList<Boolean>>, partTwo: Boolean): List<Point> {
    return listOf(Point(current.x,current.y-1),
        Point(current.x,current.y+1),
        Point(current.x-1,current.y),
        Point(current.x+1,current.y)).filter {
        inBounds(it, graph[0].indices, graph.indices) && !visited[it.y][it.x] && inHeightRange(current,it,graph,partTwo)
    }
}
fun inHeightRange(current: Point, neighbor: Point, graph: List<List<Char>>, partTwo: Boolean):Boolean {
    val currentChar = graph[current.y][current.x]
    val neighborChar = graph[neighbor.y][neighbor.x]
    val currentCode = if (currentChar == 'S') 'a'.code else currentChar.code
    val neighborCode = if (neighborChar == 'E') 'z'.code else neighborChar.code
    return if (partTwo) neighborCode >= currentCode-1 else neighborCode <= currentCode+1
}

fun inBounds(point:Point, x:IntRange, y:IntRange) = point.x in x && point.y in y