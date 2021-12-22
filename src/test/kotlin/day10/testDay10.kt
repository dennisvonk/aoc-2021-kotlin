package day10

import classname
import commaSeparatedListToIntList
import readInput
import plusTorial
import kotlin.math.abs

class TestDay10 {
    fun testPart1(expectedValue: Int) {
        val day = Day10()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part1(inputAsStringList) == expectedValue)
    }

    fun testPart2(expectedValue: Int) {
        val day = Day10()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part2(inputAsStringList) == expectedValue)
    }
}

fun main() {
    val test = TestDay10()
    test.testPart1(26397)
    test.testPart2(0)
}