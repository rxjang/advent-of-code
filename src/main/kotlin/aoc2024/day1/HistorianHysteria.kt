package aoc2024.day1

import utils.println
import utils.readInput
import kotlin.math.absoluteValue

fun main() {
    val (left, right) = parse(readInput("2024/day1"))
    part1(left, right).println() // 2164381
    part2(left, right).println() // 20719933
}

fun part1(left: List<Int>, right: List<Int>): Int {
    return left.sorted()
        .zip(right.sorted())
        .sumOf { (it.first - it.second).absoluteValue }
}

fun part2(left: List<Int>, right: List<Int>): Int {
    val frequencies = right.groupingBy { it }.eachCount()
    return left.sumOf {
         it * frequencies.getOrDefault(it, 0)
    }
}

private fun parse(input: List<String>): Pair<List<Int>, List<Int>> {
    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()
    input.forEach {
        left.add(it.substringBefore(" ").toInt())
        right.add(it.substringAfter(" ").toInt())
    }
    return left to right
}