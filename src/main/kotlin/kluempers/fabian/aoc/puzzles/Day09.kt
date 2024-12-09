package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput

object Day09 : Puzzle, Input by ReadInput.forDay(9) {
    override fun puzzleOne(): Any {
        val disk = input[0].map { it.digitToInt() }.flatMapIndexed { index, size ->
            if (index % 2 == 0) List(size) { index / 2 } else List(size) { -1 }
        }.toTypedArray()
        var l = disk.indices.first
        var r = disk.indices.last
        while (l < r) {
            if (disk[l] != -1) {
                l++
                continue
            }
            if (disk[r] == -1) {
                r--
                continue
            }
            disk[l] = disk[r].also { disk[r] = disk[l] }
        }
        return disk.takeWhile { it != -1 }.mapIndexed { index, id -> (index * id).toLong() }.sum()
    }


    override fun puzzleTwo(): Any? {
        TODO("Not yet implemented")
    }
}