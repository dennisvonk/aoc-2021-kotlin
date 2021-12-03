package day03

import classname
import readInput

class TestDay03 {
    fun testPart1() {
        val day = Day03()
        val inputAsStringList = readInput("test", classname(day) + "_test")

        check(day.part1(inputAsStringList, inputAsStringList[0].length) == 198)
    }

    fun testPart2() {
        val day = Day03()
        val inputAsStringList = readInput("test", classname(day) +  "_test")
        val part2 = day.part2(inputAsStringList, inputAsStringList[0].length)

        check(part2 == 230)
    }
}

fun main() {
    val test = TestDay03()
    test.testPart1()
    test.testPart2()
}
