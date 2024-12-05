package kluempers.fabian.aoc.puzzles

import kluempers.fabian.aoc.Input
import kluempers.fabian.aoc.Puzzle
import kluempers.fabian.aoc.ReadInput
import kluempers.fabian.aoc.utils.cons
import kluempers.fabian.aoc.utils.uncons

object Day05 : Puzzle, Input by ReadInput.forDay(5) {
    private val rules = input.takeWhile { it.isNotBlank() }.map(Rule::fromString)
    private val updates = input.dropWhile { it.isNotBlank() }.drop(1).map { line ->
        line.split(',').map(String::toInt)
    }

    override fun puzzleOne(): Int {
        return updates.filter { isOrdered(it, rules) }.sumOf { it[it.size / 2] }
    }

    override fun puzzleTwo(): Int {
        return updates.filterNot { isOrdered(it, rules) }.map { fixOrder(it, rules) }.sumOf { it[it.size / 2] }
    }

    private fun fixOrder(update: List<Int>, rules: List<Rule>): List<Int> {
        if (update.isEmpty()) return emptyList()
        val (head, tail) = update.uncons()
        val newRules = rules.filterNot { it.page == head }
        val brokenRule = newRules.find { it.mustPrecede == head && it.page in tail }
        return if (brokenRule != null) {
            fixOrder(
                brokenRule.page cons tail.takeWhile { it != brokenRule.page }
                        + head
                        + tail.dropWhile { it != brokenRule.page }.drop(1), rules
            )
        } else head cons fixOrder(tail, newRules)
    }

    private tailrec fun isOrdered(update: List<Int>, rules: List<Rule>): Boolean {
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