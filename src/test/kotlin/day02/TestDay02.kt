package day02

import classname
import readInput

class TestDay02 {
    fun testPart1() {
        val day = Day02()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part1(inputAsStringList) == 150)
    }

    fun testPart2() {
        val day = Day02()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part2(inputAsStringList) == 900)
    }
}

fun main() {
    val test = TestDay02()
    test.testPart1()
    test.testPart2()
}
