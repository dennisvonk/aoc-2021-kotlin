package day01

import readInput
import stringListToIntList

class Day01 {

    fun part1(input: List<Int>): Int {
        var cnt = 0
        var prev = -1
        var cur: Int

        input.forEach {
            cur = it
            if (cur > prev && prev != -1) {
                cnt++
            }
            prev = cur
        }

        return cnt
    }

    fun part2(input: List<Int>): Int {
        val measurement = mutableListOf<Int>()

        for (j in 0 until input.size - 2) {
            var cnt = 0

            for (i in j until j + 3) {
                cnt = cnt.plus(input[i])
            }
            measurement.add(cnt)
        }

        return part1(measurement)
    }
}

fun main() {
    val day = Day01()

    val inputAsStringList = readInput("main", "Day01")
    val inputAsIntList = stringListToIntList(inputAsStringList)

    println("Day01 - 1: " + day.part1(inputAsIntList))
    println("Day01 - 2: " + day.part2(inputAsIntList))
}

