package day07

import classname
import commaSeparatedListToIntList
import readInput
import plusTorial
import kotlin.math.abs

class TestDay07 {
    fun testPart1(expectedValue: Int) {
        val day = Day07()
        val inputAsStringList = readInput("test", classname(day) + "_test")
        val inputAsIntList = commaSeparatedListToIntList(inputAsStringList)
        val collectedInput = inputAsIntList.groupingBy { it }.eachCount()

        check(day.part1(collectedInput){ pos: Int, key: Int, cnt: Int -> abs(pos - key) * cnt } == expectedValue)
    }

    fun testPart2(expectedValue: Int) {
        val day = Day07()
        val inputAsStringList = readInput("test", classname(day) + "_test")
        val inputAsIntList = commaSeparatedListToIntList(inputAsStringList)
        val collectedInput = inputAsIntList.groupingBy { it }.eachCount()

        check(day.part1(collectedInput){pos: Int, key: Int, cnt: Int -> abs(pos - key).plusTorial() * cnt} == expectedValue)
    }
}

fun main() {
    val test = TestDay07()
    test.testPart1(37)
    test.testPart2(168)
}