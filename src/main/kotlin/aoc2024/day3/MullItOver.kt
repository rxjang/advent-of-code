package aoc2024.day3

import utils.println
import utils.readInput

fun main() {
    val input = readInput("2024/day3").joinToString("")
    part1(input).println()
    part2(input).println()
}

fun part1(input: String): Int {
    return """mul\((\d{1,3}),(\d{1,3})\)"""
        .toRegex()
        .findAll(input)
        .sumOf { result ->
            result.groupValues[1].toInt() * result.groupValues[2].toInt()
        }
}

fun part2(input: String): Int {
    return """(^|do\(\)).*?($|don't\(\))"""
        .toRegex()
        .findAll(input)
        .map {
            part1(it.value)
        }
        .sum()
}
