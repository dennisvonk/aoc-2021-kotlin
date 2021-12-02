package day02

import readInput

class TestDay02 {
    fun testPart1() {
        // test if implementation meets criteria from the description, like:
        val inputAsStringList = readInput("test", "Day02_test")
        val day = Day02()
        check(day.part1(inputAsStringList) == 150)
    }

    fun testPart2() {
        // test if implementation meets criteria from the description, like:
        val inputAsStringList = readInput("test", "Day02_test")
        val day = Day02()
        check(day.part2(inputAsStringList) == 900)
    }
}

fun main() {
    val test = TestDay02()
    test.testPart1()
    test.testPart2()
}
