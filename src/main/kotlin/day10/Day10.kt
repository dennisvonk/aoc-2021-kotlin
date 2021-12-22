package day10

import AdventOfCodeDailyPuzzle
import classname
import readInput
import java.util.*

class Day10 : AdventOfCodeDailyPuzzle() {

    val validChunks = mapOf(
        ('{' to Chunk('{', '}')),
        ('(' to Chunk('(', ')')),
        ('[' to Chunk('[', ']')),
        ('<' to Chunk('<', '>'))
    )
    val points = mapOf(
        (')' to 3),
        (']' to 57),
        ('}' to 1197),
        ('>' to 25137)
    )

    fun part1(input: List<String>): Int {
        val invalidChars = getInvalidChars(input)
        val result = calculateScore(invalidChars)
        println(result)
        return result
    }

    private fun calculateScore(invalidChars: List<Char>): Int {
        return invalidChars.sumOf { c -> points.getValue(c) }
    }

    private fun getInvalidChars(input: List<String>): List<Char> {
        val invalidChars = mutableListOf<Char>()

        input.forEach { v ->
            println("line=$v")
            val stack = Stack<Chunk>()

            for (c in v.chars()) {
                val char = Char(c)
                val chunk = getChunk(char)
                if (isStart(char)) {
                    stack.push(chunk)
                } else {
                    val popped = stack.pop()
                    if (popped.end != char) {
                        println("expected ${popped.end} but, but found $char instead.")
                        invalidChars.add(char)
                        continue
                    }
                }
            }
        }
        println("invalidChars=$invalidChars")
        return invalidChars
    }

    private fun getChunk(c: Char): Chunk {
        try {
            return validChunks.getValue(c)
        } catch (e: NoSuchElementException) {
            return validChunks.filter { it.value.end == c }.map { it.value }.first()
        }

    }


    fun isStart(c: Char): Boolean {
        try {
            return c == validChunks.getValue(c).start
        } catch (e: NoSuchElementException) {
            return false
        }
    }

    fun part2(input: List<String>): Int {
        val result = 0
        println(result)
        return result
    }

    inner class Chunk(val start: Char, val end: Char) {
        override fun toString(): String {
            return "$start$end"
        }
    }
}


fun main() {
    val day = Day10()
    val inputAsStringList = readInput("main", classname(day))

    println(classname(day) + " - 1: " + day.part1(inputAsStringList))
    println(classname(day) + " - 2: " + day.part2(inputAsStringList))
}

