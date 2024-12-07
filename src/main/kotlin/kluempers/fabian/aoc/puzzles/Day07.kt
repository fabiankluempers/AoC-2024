package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput

object Day07: Puzzle, Input by ReadInput.forDay(7) {
    override fun puzzleOne() =
        input.map(Equation::fromString).filter(Equation::solvable).map(Equation::result).sum()

    private data class Equation(val result: Long, val numbers: List<Long>) {

        val operators = listOf<(Long, Long) -> Long>(Long::times, Long::plus)

        fun solvable() : Boolean =
            numbers.drop(1).fold(listOf(numbers.first())) { possibleResults, right ->
                possibleResults.flatMap { left ->
                    operators.map { op ->
                        op(left, right)
                    }
                }
            }.contains(result)

        companion object {
            fun fromString(s : String) = s.split(": ").let { (result, numbers) ->
                Equation(result.toLong(), numbers.split(" ").map(String::toLong))
            }
        }
    }

    override fun puzzleTwo(): Any? {
        TODO("Not yet implemented")
    }
}