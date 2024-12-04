package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput
import kluempers.fabian.aoc.utils.identity

object Day04 : Puzzle, Input by ReadInput.forDay(4) {
    override fun puzzleOne(): Int {
        var count = 0
        for (y in input.indices)
            for (x in input[y].indices)
                count += directionsToCheck.map { it(x, y, 4) }.count { it == "XMAS" || it == "SAMX" }
        return count
    }

    override fun puzzleTwo(): Int {
        var count = 0
        for (y in input.indices) {
            for (x in input[y].indices) {
                if (takeDiagonalRight(x, y, 3) == "MAS"
                    && takeRight(x, y, 3)?.get(2) == 'S'
                    && takeDown(x, y, 3)?.get(2) == 'M'
                ) count++
                if (takeDiagonalRight(x, y, 3) == "SAM"
                    && takeRight(x, y, 3)?.get(2) == 'S'
                    && takeDown(x, y, 3)?.get(2) == 'M'
                ) count++
                if (takeDiagonalRight(x, y, 3) == "MAS"
                    && takeRight(x, y, 3)?.get(2) == 'M'
                    && takeDown(x, y, 3)?.get(2) == 'S'
                ) count++
                if (takeDiagonalRight(x, y, 3) == "SAM"
                    && takeRight(x, y, 3)?.get(2) == 'M'
                    && takeDown(x, y, 3)?.get(2) == 'S'
                ) count++
            }
        }
        return count
    }

    private val directionsToCheck = listOf(::takeDiagonalRight, ::takeDiagonalLeft, ::takeRight, ::takeDown)

    private fun takeDiagonalRight(x: Int, y: Int, length: Int) = take(x, y, length, Int::inc, Int::inc)
    private fun takeDiagonalLeft(x: Int, y: Int, length: Int) = take(x, y, length, Int::dec, Int::inc)
    private fun takeRight(x: Int, y: Int, length: Int) = take(x, y, length, Int::inc, ::identity)
    private fun takeDown(x: Int, y: Int, length: Int) = take(x, y, length, ::identity, Int::inc)

    private fun take(x: Int, y: Int, length: Int, modifyX: (Int) -> Int, modifyY: (Int) -> Int): String? {
        if (y !in input.indices || x !in input[y].indices) {
            return null
        }
        if (length <= 0) return null
        if (length == 1) return input[y][x].toString()
        return input[y][x].toString() + take(modifyX(x), modifyY(y), length - 1, modifyX, modifyY)
    }


}