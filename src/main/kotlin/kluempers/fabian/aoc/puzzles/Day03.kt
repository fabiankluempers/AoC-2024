package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput

object Day03 : Puzzle, Input by ReadInput.forDay(3) {
    override fun puzzleOne() = part1(input.reduce(String::plus))

    private fun part1(program: String) = mulPattern.findAll(program).sumOf {
        val (x, y) = it.destructured
        x.toInt() * y.toInt()
    }

    override fun puzzleTwo() =
        buildList { addDoFragments(input.reduce(String::plus)) }.map(this::part1).sum()


    private tailrec fun MutableList<String>.addDoFragments(
        program: String,
        nextInstruction: Instruction = Instruction.DONT
    ) {
        val fragment = program.substringBefore(nextInstruction.value)
        if (fragment == program) {
            if (nextInstruction == Instruction.DONT) {
                add(fragment)
            }
            return
        }
        val tail = program.substring(fragment.length)
        when (nextInstruction) {
            Instruction.DO -> {
                addDoFragments(tail, Instruction.DONT)
            }

            Instruction.DONT -> {
                add(fragment)
                addDoFragments(tail, Instruction.DO)
            }
        }
    }

    private enum class Instruction(val value: String) {
        DO("do()"), DONT("don't()")
    }

    private val mulPattern = Regex("mul\\((\\d+),(\\d+)\\)")
}