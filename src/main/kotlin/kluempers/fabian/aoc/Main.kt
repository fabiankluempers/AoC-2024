package kluempers.fabian.aoc

import kluempers.fabian.aoc.puzzles.Puzzles
import kotlin.time.DurationUnit
import kotlin.time.measureTimedValue

interface Puzzle {
    fun puzzleOne(): Any?
    fun puzzleTwo(): Any?
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


fun main(args: Array<String>) {
    when {
        args.contains("-a") -> runAll()
        args.contains("-i") -> args.getOrNull(args.indexOf("-i") + 1)?.toIntOrNull()?.let(Puzzles::getOrNull)?.run()
            ?: runLast()

        args.contains("-day") -> args.getOrNull(args.indexOf("-day") + 1)?.toIntOrNull()?.minus(1)
            ?.let(Puzzles::getOrNull)?.run() ?: runLast()

        else -> runLast()
    }
}

private fun Puzzle.run() {
    println("${this::class.simpleName}:")
    // load input before measuring
    if (this is Input) {
        input
    }
    val one = measureTimedValue {
        try {
            puzzleOne()
        } catch (_: NotImplementedError) {
            "Not Implemented"
        }
    }
    println("\tPart 1 solved in ${one.duration.toString(DurationUnit.MILLISECONDS).padEnd(5, ' ')}: ${one.value}")
    val two = measureTimedValue {
        try {
            puzzleTwo()
        } catch (_: NotImplementedError) {
            "Not Implemented"
        }
    }
    println("\tPart 2 solved in ${two.duration.toString(DurationUnit.MILLISECONDS).padEnd(5, ' ')}: ${two.value}")
}


private fun runLast() {
    Puzzles.last().run()
}

private fun runAll() {
    for (puzzle in Puzzles) {
        puzzle.run()
        println("-".repeat(80))
    }
}