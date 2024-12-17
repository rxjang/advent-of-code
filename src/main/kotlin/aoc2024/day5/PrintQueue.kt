package aoc2024.day5

import utils.println
import utils.readInput

fun main() {
    val (rules, pages) = parse(readInput("2024/day5"))
    part1(rules, pages).println()
    part2(rules, pages).println()
}

fun part1(rules: Set<String>, pages: List<List<String>>): Int {
    return pages
        .map {
            val sorted = it.sortedWith() { a, b ->
                    when {
                        "$a|$b" in rules -> -1
                        "$b|$a" in rules -> 1
                        else -> 0
                    }
            }
            it to sorted
        }
        .filter { it.first == it.second }
        .sumOf { it.first[it.first.lastIndex / 2].toInt() }
}

fun part2(rules: Set<String>, pages: List<List<String>>): Int {
    return pages
        .map {
            val sorted = it.sortedWith() { a, b ->
                when {
                    "$a|$b" in rules -> -1
                    "$b|$a" in rules -> 1
                    else -> 0
                }
            }
            it to sorted
        }
        .filter { it.first !=  it.second }
        .sumOf { it.second[it.second.lastIndex / 2].toInt() }
}

fun parse(lines: List<String>): Pair<Set<String>, List<List<String>>> {
    val rules = lines
        .takeWhile { it.isNotEmpty() }
        .toSet()
    val pages = lines
        .dropWhile { it.isNotEmpty() }
        .drop(1)
        .map { row -> row.split(",") }
    return rules to pages
}
