package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput

object Day07 : Puzzle, Input by ReadInput.forDay(7) {
    private val partOneOperators = listOf<(Long, Long) -> Long>(Long::times, Long::plus)

    private fun solution(operators: List<(Long, Long) -> Long>) =
        input.map(Equation::fromString).filter { it.solvableWith(operators) }.map(Equation::result).sum()

    override fun puzzleOne() = solution(partOneOperators)

    private data class Equation(val result: Long, val numbers: List<Long>) {
        fun solvableWith(operators: List<(Long, Long) -> Long>): Boolean =
            numbers.drop(1).fold(listOf(numbers.first())) { possibleResults, right ->
                possibleResults.flatMap { left ->
                    operators.map { op ->
                        op(left, right)
                    }
                }
            }.contains(result)

        companion object {
            fun fromString(s: String) = s.split(": ").let { (result, numbers) ->
                Equation(result.toLong(), numbers.split(" ").map(String::toLong))
            }
        }
    }

    override fun puzzleTwo() = solution(partOneOperators + { l, r -> (l.toString() + r.toString()).toLong() })
}