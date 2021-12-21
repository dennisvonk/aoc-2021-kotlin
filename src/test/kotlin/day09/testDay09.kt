package day09

import classname
import commaSeparatedListToIntList
import readInput
import plusTorial
import kotlin.math.abs

class TestDay09 {
    fun testPart1(expectedValue: Int) {
        val day = Day09()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part1(inputAsStringList) == expectedValue)
    }

    fun testPart2(expectedValue: Int) {
        val day = Day09()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part2(inputAsStringList) == expectedValue)
    }
}

fun main() {
    val test = TestDay09()
    test.testPart1(15)
    test.testPart2(0)
}