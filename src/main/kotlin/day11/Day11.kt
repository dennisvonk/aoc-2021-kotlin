package day11

import AdventOfCodeDailyPuzzle
import classname
import readInput

class Day11 : AdventOfCodeDailyPuzzle() {

    fun part1(input: List<String>, steps: Int): Int {
        val field = transformInput(input)
        printField(field)
        var i = 0
        var nrFlashes = 0
        while (i++ < steps) {
            increaseAll(field)
            nrFlashes += check(field)
            println("After step $i")
            printField(field)
        }
        println("nrFlashes=$nrFlashes")
        return nrFlashes
    }

    private fun increaseAll(field: Array<IntArray>) {
        field.forEachIndexed { row, s ->
            s.forEachIndexed { col, _ ->
                run {
                    field[row][col] = field[row][col].inc()
                }
            }
        }
//        println("increase all")
//        printField(field)
    }

    private fun check(field: Array<IntArray>): Int {
        var nrFlashes = 0
        var flashed: Boolean

        do {
            flashed = false
            field.forEachIndexed { row, s ->
                s.forEachIndexed { col, _ ->
                    if (isFlash(field, row, col)) {
                        flashed = true
                        field[row][col] = 0
                        nrFlashes = nrFlashes.inc() // this field flashed
                        increaseNeighbours(field, row, col)
                    }
                }
            }
        } while (flashed)

//        println("check")
//        printField(field)

        return nrFlashes
    }

    private fun increaseNeighbours(field: Array<IntArray>, row: Int, col: Int) {
        val mask = listOf(
            Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
            Pair(0, -1), Pair(0, 1),
            Pair(1, -1), Pair(1, 0), Pair(1, 1)
        )

        mask.forEach { m ->
            run {
                val nextRow = row + m.first
                val nextCol = col + m.second
                if ((nextRow) >= 0
                    && (nextRow) <= (field.size - 1)
                    && (nextCol) >= 0
                    && (nextCol) <= (field[0].size - 1)
                    && field[nextRow][nextCol] > 0
                ) {
//                    println("row=$row, col=$col, mask=$m")
                    field[nextRow][nextCol] = field[nextRow][nextCol].inc()
                }
            }
        }
    }

    private fun isFlash(field: Array<IntArray>, row: Int, col: Int): Boolean {
        return field[row][col] > 9
    }

    private fun printField(field: Array<IntArray>) {
        field.forEach { s ->
            run {
                s.forEach { c -> print(c) }
                println()
            }
        }
        println()
    }

    private fun transformInput(input: List<String>): Array<IntArray> {
        val field = Array(10) { IntArray(10) { 0 } }
        input.forEachIndexed { row, s -> s.toCharArray().forEachIndexed { col, c -> field[row][col] = c.digitToInt() } }
        return field
    }

    fun part2(input: List<String>): Int {
        val field = transformInput(input)
        printField(field)
        var stepNr = 0
        var nrFlashes = 0
        while (!allOctopusFlashed(field)) {
            stepNr++
            increaseAll(field)
            nrFlashes += check(field)
            println("After step $stepNr")
            printField(field)
        }
        println("nrFlashes=$nrFlashes")
        return nrFlashes
    }

    private fun allOctopusFlashed(field: Array<IntArray>): Boolean {
        return field.maxOf { row -> row.sum() } == 0
//        return field.none { row ->
//            row.none { col -> col == 0 }
//        }
    }
}


fun main() {
    val day = Day11()
    val inputAsStringList = readInput("main", classname(day))

    println(classname(day) + " - 1: " + day.part1(inputAsStringList, 100))
    println(classname(day) + " - 2: " + day.part2(inputAsStringList))
}

