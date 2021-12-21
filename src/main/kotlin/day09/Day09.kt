package day09

import AdventOfCodeDailyPuzzle
import classname
import readInput

class Day09 : AdventOfCodeDailyPuzzle() {
    fun part1(input: List<String>): Int {
        var result = 0
        println ("input=$input")

        input.forEachIndexed { row, v ->
            println ("row=$v")
            run {
                v.forEachIndexed { i, c ->
                    run {
                        val neighbours = getNeighbours(input, row, i)
                        if (neighbours.none { c.digitToInt() >= it }) result += 1 + c.digitToInt()
                    }
                }
            }
        }

        println(result)
        return result
    }

    private fun getNeighbours(input: List<String>, row: Int, i: Int): List<Int> {
        val neighbours = mutableListOf<Int>()
        val lastRight = input[0].length

        when (i) {
            0 -> {
                // only right neighbours
                neighbours.add(input[row][i + 1].digitToInt())
            }
            lastRight-1 -> {
                // only left neighbours
                neighbours.add(input[row][i - 1].digitToInt())
            }
            else -> {
                // left and right neighbours
                neighbours.add(input[row][i - 1].digitToInt())
                neighbours.add(input[row][i + 1].digitToInt())
            }
        }

        when (row) {
            0 -> {
                // only bottom neighbours
                neighbours.add(input[row + 1][i].digitToInt())
            }
            input.size-1 -> {
                // only top neighbours
                neighbours.add(input[row-1][i].digitToInt())
            }
            else -> {
                // top and bottom neighbours
                neighbours.add(input[row + 1][i].digitToInt())
                neighbours.add(input[row - 1][i].digitToInt())
            }
        }

        return neighbours
    }

    fun part2(input: List<String>): Int {
        var result = 0
        println(result)
        return result
    }

}


fun main() {
    val day = Day09()
    val inputAsStringList = readInput("main", classname(day))

    println(classname(day) + " - 1: " + day.part1(inputAsStringList))
    println(classname(day) + " - 2: " + day.part2(inputAsStringList))
}
