package day06

import AdventOfCodeDailyPuzzle
import classname
import commaSeparatedListToIntList
import readInput
import java.math.BigInteger
import java.util.AbstractMap

class Day06(private var nrDays: Int) : AdventOfCodeDailyPuzzle() {

    fun part1(startGeneration: List<Int>): Long {
        var curGeneration = startGeneration.toIntArray()
        println("Initial state}: ${curGeneration.asList()}")

        for (day in 0..nrDays - 1) {
            curGeneration = nextGen(curGeneration)
        }

        val size = curGeneration.size
        println("After $nrDays generations there are $size fishes")
        return size.toLong()
    }

    private fun nextGen(curGeneration: IntArray): IntArray {
        var curGeneration1 = curGeneration
        val nextGeneration = mutableListOf<Int>()
        val newGeneration = mutableListOf<Int>()
        for (age in curGeneration1) {
            if (age == 0) {
                nextGeneration.add(6)
                newGeneration.add(8)
            } else {
                nextGeneration.add(age - 1)
            }
        }
        nextGeneration.addAll(newGeneration)
        curGeneration1 = nextGeneration.toIntArray()
        //            println ("Day ${day+1}: ${curGeneration.asList()}")
        return curGeneration1
    }

    fun recursive(day: Int, startGeneration: IntArray): IntArray {
        if (day == 0) {
            return startGeneration
        }

        return recursive(day - 1, nextGen(startGeneration))
    }

    fun part2(startGeneration: List<Int>): BigInteger {
        var flatGeneration = startGeneration
            .groupingBy { it }
            .eachCount()
            .mapValues { entry -> entry.value.toBigInteger() }// key=age, value=count

        for (day in 0 until nrDays) {
            val nextGeneration = mutableMapOf<Int, BigInteger>()
            for (fish in flatGeneration) {
                if (fish.key == 0) {
                    nextGeneration[6] = if (nextGeneration[6]==null) fish.value else nextGeneration[6]!!.plus(fish.value)
                    nextGeneration[8] = fish.value
                } else {
                    nextGeneration[fish.key.minus (1)] = if (nextGeneration[fish.key.minus(1)]==null) fish.value else nextGeneration[fish.key.minus(1)]!!.plus(fish.value)
                }
            }
            println("Day ${day + 1}: ${nextGeneration}")
            flatGeneration = nextGeneration
        }

        val size = flatGeneration.values.sumOf { value -> value }
        println("After $nrDays generations there are $size fishes")
        return size
    }
}

fun main() {
    val day = Day06(80)
    val inputAsStringList = readInput("main", classname(day))
    val inputAsIntList = commaSeparatedListToIntList(inputAsStringList)

    println(classname(day) + " - 1: " + day.part1(inputAsIntList))
    val dayPart2 = Day06(256)
    println(classname(day) + " - 2: " + dayPart2.part2(inputAsIntList))
}

