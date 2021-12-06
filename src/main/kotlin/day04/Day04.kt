package day04

import AdventOfCodeDailyPuzzle
import classname
import readInput

class Day04 : AdventOfCodeDailyPuzzle() {

    fun part1(input: List<String>): Int {
        val bingoData = BingoData(input)
//        println(bingoData.numbers)
//        println(bingoData.boards)
        bingoData.numbers.forEach { nrDrawn ->
            bingoData.boards.markNumber(nrDrawn)
            val winner = bingoData.boards.findFirstWinner()
            if (winner.isNotEmpty()) {
                val sumOfUnmarkedNumbers = winner
                    .filter { field -> !field.marked }
                    .sumOf { field -> field.nr }

                return sumOfUnmarkedNumbers * nrDrawn
            }
        }
        return 0
    }


    fun part2(input: List<String>): Int {
        val winningBoards = mutableListOf<MutableList<Field>>()
        var lastDrawnNr = -1
        val bingoData = BingoData(input)
//        println(bingoData.numbers)
//        println(bingoData.boards)
        bingoData.numbers.forEach { nrDrawn ->
            bingoData.boards.markNumber(nrDrawn)
            println ("Nr of boards: ${bingoData.boards.size}")
            val winners = bingoData.boards.findAllWinners()
            if (winners.isNotEmpty()) {
                println ("Nr drawn: $nrDrawn")
                println ("Winners: $winners")
//                println("Boards: ${bingoData.boards}")
                winningBoards.addAll(winners)
                bingoData.boards.removeAll(winners)
                lastDrawnNr = nrDrawn
            }
        }

        println (winningBoards.last())
        val sumOfUnmarkedNumbers = winningBoards.last()
            .filter { field -> !field.marked }
            .sumOf { field -> field.nr }

        println ("$sumOfUnmarkedNumbers * $lastDrawnNr = ${sumOfUnmarkedNumbers*lastDrawnNr}")
        return sumOfUnmarkedNumbers * lastDrawnNr
    }
}


private fun MutableList<MutableList<Field>>.findFirstWinner(): MutableList<Field> {
    for (board in this) {
        var hash = ""
        for (field in board) {
            hash += if (field.marked) "1" else "0"
        }
//        println("Board: $board")
//        println("Hash: $hash")
        val row = isWinner(hash, board)
//        println("Row: $row")
        if (row != -1) {
            return board
        }
    }
    return mutableListOf<Field>()
}

private fun MutableList<MutableList<Field>>.findAllWinners(): MutableList<MutableList<Field>> {
    var firstWinningBoards = mutableListOf<MutableList<Field>>()
    for (board in this) {
        var hash = ""
        for (field in board) {
            hash += if (field.marked) "1" else "0"
        }
//        println("Board: $board")
//        println("Hash: $hash")
        val row = isWinner(hash, board)
//        println("Row: $row")
        if (row != -1) {
            println("removing board, remaining ${this.size}")
            firstWinningBoards.add(board)
        }
    }
    return firstWinningBoards
}

fun isWinner(hash: String, board: MutableList<Field>): Int {

    val row1 = hash.subSequence(0, 5)
    val row2 = hash.subSequence(5, 10)
    val row3 = hash.subSequence(10, 15)
    val row4 = hash.subSequence(15, 20)
    val row5 = hash.subSequence(20, 25)

    if ((row1 == "11111") || (row2 == "11111") || (row3 == "11111") || (row4 == "11111") || (row5 == "11111")
        || (row1[0] == '1' && row2[0] == '1' && row3[0] == '1' && row4[0] == '1' && row5[0] == '1')
        || (row1[1] == '1' && row2[1] == '1' && row3[1] == '1' && row4[1] == '1' && row5[1] == '1')
        || (row1[2] == '1' && row2[2] == '1' && row3[2] == '1' && row4[2] == '1' && row5[2] == '1')
        || (row1[3] == '1' && row2[3] == '1' && row3[3] == '1' && row4[3] == '1' && row5[3] == '1')
        || (row1[4] == '1' && row2[4] == '1' && row3[4] == '1' && row4[4] == '1' && row5[4] == '1')
    ) {
        return board.filter { field -> !field.marked }.sumOf { field -> field.nr }
    }
    return -1
}

private fun MutableList<MutableList<Field>>.markNumber(nrDrawn: Int) {
    for (board: MutableList<Field> in this) {
        board.forEach { field ->
            if (field.nr == nrDrawn) field.marked = true
        }
    }
}

class Field(nr: Int) {
    var nr = nr
    var marked = false
    override fun toString(): String {
        val m = if (marked) "*" else " "
        return "$nr$m"
    }
}

class BingoData(input: List<String>) {
    val numbers: List<Int> = input[0].split(",").map { s -> s.toInt() }
    val width = 5
    val height = 5
    val boards = mutableListOf<MutableList<Field>>()

    init {
        var board = MutableList<Field>(width * height) { Field(0) }
        var cleanInput = input.subList(2, input.size)
        var boardCounter = 0
        var lineCounter = 0

        cleanInput.forEach { s ->
//            println("raw string (line): '$s'")
            if (s == "") {
                boards.add(board)
                board = MutableList(width * height) { Field(0) }
                lineCounter = 0
                boardCounter++
//                println ("start new board: $boardCounter, linecounter: $lineCounter")
            } else {

                val split = s.replace("^\\s+".toRegex(), "").replace("\\s\\s+".toRegex(), " ").split(" ")
//                println("split: $split, linecounter: $lineCounter")
                split.forEachIndexed() { index, boardNumberAsString ->
                    board[lineCounter * width + index].nr = boardNumberAsString.toInt()
                }
                lineCounter++
//                println(board)
            }
        }
        boards.add(board)
    }
}

fun main() {
    val day = Day04()
    val inputAsStringList = readInput("main", classname(day))

    println(classname(day) + " - 1: " + day.part1(inputAsStringList))
    println(classname(day) + " - 2: " + day.part2(inputAsStringList))
}

