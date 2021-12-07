package day07

import AdventOfCodeDailyPuzzle
import classname
import commaSeparatedListToIntList
import plusTorial
import readInput
import kotlin.math.abs

class Day07 : AdventOfCodeDailyPuzzle() {

    fun part1(input: Map<Int, Int>, calc: (pos: Int, key: Int, count: Int) -> Int): Int { // key=position, value=count
        val sortedInput = input.keys.sorted()
        val lowest = sortedInput[0]
        val highest = sortedInput.last()
        var bestFuel = Integer.MAX_VALUE

        for (pos in lowest..highest) {
            var tmpFuel = 0
            for (krab in input) {
                tmpFuel += calc (pos, krab.key, krab.value)
            }
            if (tmpFuel < bestFuel) {
                bestFuel = tmpFuel
            }
        }
        println(bestFuel)
        return bestFuel
    }
}

fun main() {
    val day = Day07()
    val inputAsStringList = readInput("main", classname(day))
    val inputAsIntList = commaSeparatedListToIntList(inputAsStringList)
    val collectedInput = inputAsIntList.groupingBy { it }.eachCount()

    println(classname(day) + " - 1: " + day.part1(collectedInput) { pos: Int, key: Int, cnt: Int -> abs(pos - key) * cnt })
    println(classname(day) + " - 2: " + day.part1(collectedInput) {pos: Int, key: Int, cnt: Int -> abs(pos - key).plusTorial() * cnt})
}
