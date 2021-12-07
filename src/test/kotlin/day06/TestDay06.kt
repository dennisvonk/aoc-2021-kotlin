package day06

import classname
import commaSeparatedListToIntList
import readInput
import java.math.BigInteger

class TestDay06 {
    fun testPart1(nrDays: Int, expectedValue: Long) {
        val day = Day06(nrDays)
        val inputAsStringList = readInput("test", classname(day) + "_test")
        val inputAsIntList = commaSeparatedListToIntList(inputAsStringList)

        check(day.part1(inputAsIntList) == expectedValue)
    }

    fun testPart2(nrDays: Int, expectedValue: BigInteger) {
        val day = Day06(nrDays)
        val inputAsStringList = readInput("test", classname(day) + "_test")
        val inputAsIntList = commaSeparatedListToIntList(inputAsStringList)

        check(day.part2(inputAsIntList) == expectedValue)
    }
}

fun main() {
    val test = TestDay06()
    test.testPart1(18, 26L)
    test.testPart1(80, 5934L)
    test.testPart2(256, BigInteger.valueOf(26984457539))
}