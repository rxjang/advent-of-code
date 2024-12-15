package aoc2024.day2

import utils.println
import utils.readInput
import kotlin.math.abs

fun main() {
    val reports = parse(readInput("2024/day2"))
    part1(reports).println()
    part2(reports).println()
}

fun part1(reports: List<List<Int>>): Int {
    return reports.count { isSafe(it) }

}

fun part2(reports: List<List<Int>>): Int {
    return reports.count { report ->
        report.indices.any {
            isSafe(report.filterIndexed { index, _ -> it != index })
        }
    }
}

private fun isSafe(report: List<Int>): Boolean {
    val diffs = report.zipWithNext().map { (left, right) ->
        right - left
    }
    return diffs.all { it in 1..3 } || diffs.all { it in -3..-1 }
}

private fun parse(input: List<String>): List<List<Int>> {
    return input.map {
        it.split(" ").map { row -> row.toInt() }.toList()
    }
}