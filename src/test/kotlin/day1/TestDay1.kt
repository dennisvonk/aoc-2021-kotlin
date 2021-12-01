package day1

import readInput
import stringListToIntList

fun testPart1() {
    // test if implementation meets criteria from the description, like:
    val inputAsStringList = readInput("test","Day01_test")
    val inputAsIntList = stringListToIntList(inputAsStringList)
    val day01 = Day01()
    check(day01.part1(inputAsIntList) == 7)
}

fun testPart2() {
    // test if implementation meets criteria from the description, like:
    val inputAsStringList = readInput("test","Day01_test")
    val inputAsIntList = stringListToIntList(inputAsStringList)
    val day01 = Day01()
    check(day01.part2(inputAsIntList) == 5)
}

fun main() {
    testPart1()
    testPart2()
}