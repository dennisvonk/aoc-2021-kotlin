package day05

import classname
import readInput
import stringListToIntList

class TestDay05 {
    fun testPart1() {
        val day = Day05()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part1(inputAsStringList) == 5)
    }

    fun testPart2() {
        val day = Day05()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part2(inputAsStringList) == 12)
    }
}

fun main() {
    val test = TestDay05()
    test.testPart1()
    test.testPart2()
}