package day04

import readInput
import classname

class TestDay04 {
    fun testPart1() {
        val day = Day04()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part1(inputAsStringList) == 4512)
    }

    fun testPart2() {
        val day = Day04()
        val inputAsStringList = readInput("test", classname(day) + "_test")
        val part2 = day.part2(inputAsStringList)

        check(part2 == 1924)
    }
}

fun main() {
    val test = TestDay04()
    test.testPart1()
    test.testPart2()
}
