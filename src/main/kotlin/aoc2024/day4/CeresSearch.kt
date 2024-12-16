package aoc2024.day4

import utils.println
import utils.readInput

fun main() {
    val input = readInput("2024/day4")
    part1(input).println()
    part2(input).println()
}

fun part1(input: List<String>): Int {
    val directions = listOf(
        -1 to -1, -1 to 0, -1 to 1,
        0 to -1,            0 to 1,
        1 to -1, 1 to 0, 1 to 1,
    )

    fun findLocation(target: String, x:Int, y: Int, location: Pair<Int, Int>): Boolean =
        when {
            target.isEmpty() -> true
            y !in input.indices || x !in input[y].indices -> false
            target.first() != input[y][x] -> false
            else -> findLocation(target.substring(1), x + location.first, y + location.second, location)
        }

    return input
        .flatMapIndexed { y, row ->
            row.mapIndexed { x, char ->
                if (char == 'X') {
                    directions.count {location ->
                        findLocation("XMAS", x, y, location)
                    }
                } else 0
        }
    }.sum()
}

fun part2(input: List<String>): Int {
    val directions = listOf(
        -1 to -1, -1 to 1,
        1 to -1, 1 to 1,
    )

    fun isOverSize(x: Int, y: Int): Boolean {
        return y !in input.indices || x !in input[y].indices
    }

    return input
        .flatMapIndexed { y, row ->
            row.mapIndexed { x, char ->
                if (char == 'A') {
                    directions
                        .map {
                            if (isOverSize(x + it.first, y + it.second)) ""
                            else input[y + it.second][x + it.first]
                        }
                        .joinToString("") in listOf("MMSS", "SSMM", "MSMS", "SMSM")
                } else false
            }
        }.count { it }
}
