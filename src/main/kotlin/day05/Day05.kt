package day05

import AdventOfCodeDailyPuzzle
import classname
import readInput
import java.lang.Integer.max
import java.lang.Integer.min

class Day05 : AdventOfCodeDailyPuzzle() {
    private var width = 0
    private var height = 0
    private var map = mutableListOf<Int>()

    fun part1(input: List<String>): Int {
        val normalizedInput = normalize(input)
        map = initMapSize(normalizedInput)
        println("$width x $height = ${map.size}")
        populateMap(normalizedInput)

        return getScore()
    }

    private fun getScore(): Int {
        return map.count { nrLines -> nrLines >= 2 }
    }

    private fun normalize(input: List<String>): MutableMap<Point, Point> {
        val normalizedInput = mutableMapOf<Point, Point>()
        input.forEach { line ->
            run {
                val pairs = line.split("->")
                val nrsFrom = pairs[0].split(",")
                val nrsTo = pairs[1].split(",")
                normalizedInput[Point(nrsFrom[0].clean().toInt(), nrsFrom[1].clean().toInt())] =
                    Point(nrsTo[0].clean().toInt(), nrsTo[1].clean().toInt())
            }
        }

        println(normalizedInput)
        return normalizedInput
    }

    private fun populateMap(input: MutableMap<Point, Point>, includeCrossLines: Boolean = false): MutableList<Int> {

        input.forEach { (key, value) ->
            run {
                val fromX = key.x
                val fromY = key.y
                val toX = value.x
                val toY = value.y
                print("($fromX,$fromY) -> ($toX,$toY) ")

                if (fromX == toX) { // vertical lines
                    println("vertical")
                    for (y in min(fromY, toY)..max(fromY, toY)) {
                        map[y * width + fromX] += 1
                    }
                } else if (fromY == toY) { // horizontal lines
                    println("horizontal")
                    for (x in min(fromX, toX)..max(fromX, toX)) {
                        map[fromY * width + x] += 1
                    }
                } else if (includeCrossLines) {
                    println("crossed")
                    var x = fromX
                    var y = fromY
                    val maxX = max(toX, fromX)
                    val minX = min(toX, fromX)
                    val maxY = max(toY, fromY)
                    while (if (fromX == maxX) x >= minX else x <= maxX) {
                        map[x + y * width] += 1
                        x += if (fromX == maxX) -1 else 1
                        y += if (fromY == maxY) -1 else 1
                    }
                } else {
                    println("skipped")
                }
            }
        }

        map.forEachIndexed { index, nrLines ->
            run {
                if (index % width == 0) println()
                print(nrLines)
            }
        }
        println()
        return map
    }

    private fun initMapSize(input: MutableMap<Point, Point>): MutableList<Int> {
        var x = 0
        var y = 0

        input.forEach { (key, value) ->
            run {
                x = if (key.x > x) key.x else x
                y = if (key.y > y) key.y else y
                x = if (value.x > x) value.x else x
                y = if (value.y > y) value.y else y
            }
        }

        width = x + 1
        height = y + 1
        return MutableList<Int>(width * height) { 0 }
    }

    fun part2(input: List<String>): Int {
        val normalizedInput = normalize(input)
        map = initMapSize(normalizedInput)
        println("$width x $height = ${map.size}")
        populateMap(normalizedInput, true)

        return getScore()
    }
}

private fun String.clean(): String {
    return this.replace(" ", "")
}

class Point(val x: Int, val y: Int) {
    override fun toString(): String {
        return "($x,$y)"
    }
}

fun main() {
    val day = Day05()
    val inputAsStringList = readInput("main", classname(day))

    println(classname(day) + " - 1: " + day.part1(inputAsStringList))
    println(classname(day) + " - 2: " + day.part2(inputAsStringList))
}
