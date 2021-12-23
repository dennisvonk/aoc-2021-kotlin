package day11

import classname
import readInput

class TestDay11 {
    fun testPart1(steps: Int, expectedValue: Int) {
        val day = Day11()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part1(inputAsStringList, steps) == expectedValue)
    }

    fun testPart2(expectedValue: Int) {
        val day = Day11()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part2(inputAsStringList) == expectedValue)
    }
}

fun main() {
    val test = TestDay11()
    test.testPart1(10, 204)
    test.testPart1(100, 1656)
    test.testPart2(195)
}