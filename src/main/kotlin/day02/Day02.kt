package day02

import AdventOfCodeDailyPuzzle
import classname
import readInput

class Day02: AdventOfCodeDailyPuzzle() {

    fun part1(input: List<String>): Int {
        var hor = 0
        var depth = 0

        input.forEach {
            val score = it.substring(it.indexOf(" ") + 1).toInt()
            if (it.startsWith("forward", false)) {
                hor += score
            }
            else if (it.startsWith("down", false)) {
                depth += score
            }
            else if (it.startsWith("up", false)) {
                depth -= score
            }
        }

        return hor*depth
    }

    fun part2(input: List<String>): Int {
        var hor = 0
        var depth = 0
        var aim = 0

        input.forEach {
            val score = it.substring(it.indexOf(" ") + 1).toInt()
            if (it.startsWith("forward", false)) {
                hor += score
                depth += score * aim
            }
            else if (it.startsWith("down", false)) {
                aim += score

            }
            else if (it.startsWith("up", false)) {
                aim -= score
            }
        }

        return hor*depth
    }
}

fun main() {
    val day = Day02()
    val inputAsStringList = readInput("main", classname(day))

    println(classname(day) + " - 1: " + day.part1(inputAsStringList))
    println(classname(day) + " - 2: " + day.part2(inputAsStringList))
}

