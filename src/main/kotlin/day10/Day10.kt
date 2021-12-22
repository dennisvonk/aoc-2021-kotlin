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

    fun part1(input: List<String>): Int {
        val invalidChars = getInvalidCharsPart1(input)
        val result = calculateScorePart1(invalidChars)
        println(result)
        return result
    }

    private fun calculateScorePart1(invalidChars: List<Char>): Int {
        val points = mapOf(
            (')' to 3),
            (']' to 57),
            ('}' to 1197),
            ('>' to 25137)
        )

        return invalidChars.sumOf { c -> points.getValue(c) }
    }

    private fun getInvalidCharsPart1(input: List<String>): List<Char> {
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

    fun part2(input: List<String>): Long {
        val lineScores = getMissingCharsPart2(input)
        val score = lineScores.sorted()[lineScores.size / 2]
        println(score)
        return score
    }

    private fun calculateScorePart2(invalidChars: List<Char>): Long {
        val points = mapOf(
            (')' to 1L),
            (']' to 2L),
            ('}' to 3L),
            ('>' to 4L)
        )

        var score = 0L
        invalidChars.forEach { c ->
             score = 5L * score + points.getValue(c)
        }
        println("score=$score")

        return score
    }

    private fun getMissingCharsPart2(input: List<String>): List<Long> {
        val missingChars = mutableListOf<Long>()

        input.forEach { line ->
            println("line=$line")
            var valid=true
            val stack = Stack<Chunk>()

            for (c in line.chars()) {
                val char = Char(c)
                val chunk = getChunk(char)
                if (isStart(char)) {
                    stack.push(chunk)
                } else {
                    val popped = stack.pop()
                    if (popped.end != char) {
                        valid=false
                        break
                    }
                }
            }
            if (valid && stack.isNotEmpty()) {
                println("input ended, but still elements on stack: $stack")
                val chars = mutableListOf<Char>()
                while (stack.isNotEmpty()) {
                    chars.add(stack.pop().end)
                }

                missingChars.add(calculateScorePart2(chars))
                println(missingChars)
            }
        }
        println("missingChars=$missingChars")
        return missingChars
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

