package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput
import kluempers.fabian.aoc.utils.*

object Day08 : Puzzle, Input by ReadInput.forDay(8) {
    override fun puzzleOne(): Int {
        val grid = input.arrayGrid()
        val frequencyMap = grid.mapIndexed { y, xs -> xs.mapIndexedNotNull { x, frequency -> if (frequency != '.') frequency to Index2d(x, y) else null } }
            .flatten().groupBy { it.first }.mapValues { (_, v) -> v.map { it.second } }
        return frequencyMap.mapValues { (k , v) ->
            v.crossproduct(v).filter { (p1,p2) -> p1 != p2 }.flatMap { (p1,p2) ->
                val distance = p1 distance p2
                val antinode1 = distance.reversed().from(p1)
                val antinode2 = distance.from(p2)
                listOf(antinode1, antinode2)
            }.filter { it.y in grid.indices && it.x in grid[it.y].indices }
        }.values.flatten().toSet().size
    }

    override fun puzzleTwo(): Any? {
        TODO("Not yet implemented")
    }
}