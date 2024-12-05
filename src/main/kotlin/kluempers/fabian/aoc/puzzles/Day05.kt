package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput
import kluempers.fabian.aoc.utils.uncons

object Day05 : Puzzle, Input by ReadInput.forDay(5) {
    override fun puzzleOne(): Int {
        val rules = input.takeWhile { it.isNotBlank() }.map(Rule::fromString)
        val updates = input.dropWhile { it.isNotBlank() }.drop(1).map { line ->
            line.split(',').map(String::toInt)
        }
        return updates.filter { isOrdered(it, rules) }.sumOf { it[it.size / 2] }
    }

    override fun puzzleTwo(): Any? {
        TODO("Not yet implemented")
    }

    private tailrec fun isOrdered(update: List<Int>, rules: List<Rule>) : Boolean {
        if (update.isEmpty()) {
            return true
        }
        val (head, tail) = update.uncons()
        val newRules = rules.filterNot { it.page == head }
        if (newRules.any { it.mustPrecede == head && it.page in tail }) {
            return false
        }
        return isOrdered(tail, newRules)
    }

    private data class Rule(val page: Int, val mustPrecede: Int) {
        companion object {
            fun fromString(string: String) = string.split('|').map(String::toInt).let { Rule(it[0], it[1]) }
        }
    }
}