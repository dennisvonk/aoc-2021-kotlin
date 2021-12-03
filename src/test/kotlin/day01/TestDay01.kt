package day01

import classname
import readInput
import stringListToIntList

class TestDay01 {
    fun testPart1() {
        val day = Day01()
        val inputAsStringList = readInput("test", classname(day) + "_test")
        val inputAsIntList = stringListToIntList(inputAsStringList)

        check(day.part1(inputAsIntList) == 7)
    }

    fun testPart2() {
        val day = Day01()
        val inputAsStringList = readInput("test", classname(day) + "_test")
        val inputAsIntList = stringListToIntList(inputAsStringList)

        check(day.part2(inputAsIntList) == 5)
    }
}

fun main() {
    val test = TestDay01()
    test.testPart1()
    test.testPart2()
}