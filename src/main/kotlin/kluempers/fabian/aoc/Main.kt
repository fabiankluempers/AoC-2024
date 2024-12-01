package kluempers.fabian.aoc

import kluempers.fabian.aoc.puzzles.Puzzles
import kotlin.time.DurationUnit
import kotlin.time.measureTimedValue

interface Puzzle {
    fun puzzleOne(): String
    fun puzzleTwo(): String
}

interface Input {
    val input: List<String>
}

class ReadInput private constructor(private val day: Int) : Input {
    private fun readInput() =
        (this::class.java.classLoader
            .getResourceAsStream("day${day.toString().padStart(2, '0')}.txt")
            ?: error("input not found for day $day"))
            .readAllBytes()
            .decodeToString()
            .lines()

    override val input: List<String> by lazy { readInput() }

    companion object {
        fun forDay(day: Int) = ReadInput(day)
    }
}



fun main() {
    for (puzzle in Puzzles) {
        println("${puzzle::class.simpleName}:")
        val one = measureTimedValue { puzzle.puzzleOne() }
        println("\tPart 1 solved in ${one.duration.toString(DurationUnit.MILLISECONDS).padEnd(5, ' ')}: ${one.value}")
        val two = measureTimedValue { puzzle.puzzleTwo() }
        println("\tPart 2 solved in ${two.duration.toString(DurationUnit.MILLISECONDS).padEnd(5, ' ')}: ${two.value}")
        println("-".repeat(80))
    }
}