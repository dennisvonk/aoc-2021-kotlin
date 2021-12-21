package day08

import AdventOfCodeDailyPuzzle
import classname
import readInput

val uniquePatternsOnCount = mapOf<Int, Int>(2 to 1, 4 to 4, 3 to 7, 7 to 8)

class Day08 : AdventOfCodeDailyPuzzle() {
    private val patternsAsMap = mapOf<String, Int>(
        "1110111" to 0,
        "0010010" to 1,
        "1011101" to 2,
        "1011011" to 3,
        "0111010" to 4,
        "1101011" to 5,
        "1101111" to 6,
        "1010010" to 7,
        "1111111" to 8,
        "1111011" to 9
    )

    fun part1(input: List<String>): Int {
        val outputValues = input.map { it.split("|")[1] }.map { it.split(" ") }.filter { it.isNotEmpty() }.flatten()
        val result = outputValues.filter { uniquePatternsOnCount.containsKey(it.length) }.size
        println(result)
        return result
    }

    fun part2(input: List<String>): Int {
        val calculatedRows = mutableListOf<InputRow>()

        for (each in input) {
            val split = each.split("|")
            val row = InputRow(
                split[0].split(" ").filter { it.isNotEmpty() },
                split[1].split(" ").filter { it.isNotEmpty() }
            )
            println("row=$row")
            calculatedRows.add(row)

            row.calculateAll()

        }
        val result = 0
        println(result)
        return result
    }

}

class InputRow(val input: List<String>, val value: List<String>) {
    private val wiringSolution = mutableMapOf<String, String>() // connects wires to segments: wire a could be on to light segment d

    private fun isNotReady(): Boolean {
        return wiringSolution.size != 10
    }

    fun calculateAll() {
        while (isNotReady()) {
            input.forEach { wireConfig -> calculateSegment(wireConfig)}
        }
    }

    private fun calculateSegment(wireConfig: String) {
        if (uniquePatternsOnCount.containsKey(wireConfig.length)) {
            val displayValue = uniquePatternsOnCount[wireConfig.length]
            wiringSolution[wireConfig] = displayValue.toString()
            return
        }

        val binary = transformToBinary (wireConfig)
        println ("binary=$binary")
    }

    private fun transformToBinary(wireConfig: String): String {
        var wireConfigAsString = ""
        ('a'..'g').forEach{ v -> wireConfigAsString+=if (wireConfig.contains(v)) "1" else "0" }
        println ("param=$wireConfig, output=$wireConfigAsString")
        return wireConfigAsString
    }
}


fun main() {
    val day = Day08()
    val inputAsStringList = readInput("main", classname(day))

    println(classname(day) + " - 1: " + day.part1(inputAsStringList))
    println(classname(day) + " - 2: " + day.part2(inputAsStringList))
}
