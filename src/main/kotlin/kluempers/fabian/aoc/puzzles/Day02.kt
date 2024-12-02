package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput
import kotlin.math.abs

object Day02 : Puzzle, Input by ReadInput.forDay(2) {
    override fun puzzleOne() = reports().map { it.analyseDistance() && it.analyseDirection() }.count { it }

    override fun puzzleTwo() = reports().map { report ->
        report.indices.map {
            val mutated = report.take(it) + report.drop(it + 1)
            mutated.analyseDirection() && mutated.analyseDistance()
        }.any { it }
    }.count { it }

    private fun reports(): List<List<Int>> = input.map { it.split(' ').map(String::toInt) }

    private fun List<Int>.analyseDirection() = zipWithNext { a, b ->
        when {
            a < b -> Direction.INCREASING
            a > b -> Direction.DECREASING
            else -> Direction.EQUAL
        }
    }.let { analysed ->
        analysed.all { it == Direction.INCREASING } || analysed.all { it == Direction.DECREASING }
    }

    private fun List<Int>.analyseDistance() = zipWithNext { a, b ->
        abs(a - b)
    }.all { it in 1..3 }

    private enum class Direction { INCREASING, DECREASING, EQUAL }
}