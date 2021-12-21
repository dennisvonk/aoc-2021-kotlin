package day08

import classname
import commaSeparatedListToIntList
import readInput
import plusTorial
import kotlin.math.abs

class TestDay08 {
    fun testPart1(expectedValue: Int) {
        val day = Day08()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part1(inputAsStringList) == expectedValue)
    }

    fun testPart2(expectedValue: Int) {
        val day = Day08()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part2(inputAsStringList) == expectedValue)
    }
}

fun main() {
    val test = TestDay08()
    test.testPart1(26)
    test.testPart2(61229)
}