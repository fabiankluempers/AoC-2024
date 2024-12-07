package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput
import kluempers.fabian.aoc.utils.identity

object Day06 : Puzzle, Input by ReadInput.forDay(6) {
    private fun grid() = input.map { it.toCharArray() }.toTypedArray()
    override fun puzzleOne(): Int =
        Walk().apply { walk() }.uniquePositions


    override fun puzzleTwo(): Any? {
        TODO("Not yet implemented")
    }

    private class Walk(
        val grid: Array<CharArray> = grid(),
        var y: Int = grid.indexOfFirst { '^' in it },
        var x: Int = grid[y].indexOf('^'),
        var currentDirection: Direction = Direction.UP,
        var uniquePositions: Int = 1
    ) {
        enum class Direction(val modifyX: (Int) -> Int, val modifyY: (Int) -> Int) {
            UP(::identity, Int::dec), RIGHT(Int::inc, ::identity), DOWN(::identity, Int::inc), LEFT(
                Int::dec,
                ::identity
            );

            fun next() = when (this) {
                UP -> RIGHT
                RIGHT -> DOWN
                DOWN -> LEFT
                LEFT -> UP
            }

            fun opposite() = next().next()
        }

        fun apply(direction: Direction) {
            x = direction.modifyX(x)
            y = direction.modifyY(y)
        }

        fun walk() {
            try {
                while (true) {
                    step()
                }
            } catch (_: ArrayIndexOutOfBoundsException) {

            }
        }

        fun step() {
            apply(currentDirection)
            val newPos = grid[y][x]
            when (newPos) {
                '.' -> {
                    uniquePositions++
                    grid[y][x] = '*'
                }

                '#' -> {
                    apply(currentDirection.opposite())
                    currentDirection = currentDirection.next()
                }
            }
        }
    }
}