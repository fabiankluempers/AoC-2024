package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput
import kluempers.fabian.aoc.utils.map
import kluempers.fabian.aoc.utils.toPair
import kotlin.math.abs

object Day01 : Puzzle, Input by ReadInput.forDay(1) {
    private fun inputs() = input
        .map { it.split(Regex("\\s+")).toPair().map(String::toInt) }
        .unzip()

    override fun puzzleOne() = inputs()
            .map { it.sorted() }
            .run { first.zip(second) { l, r -> abs( l - r) } }
            .sum()

    override fun puzzleTwo() = inputs().run {
        first.sumOf { l -> l * second.count { r -> l == r } }
    }
}