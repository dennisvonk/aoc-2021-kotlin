package day03

import AdventOfCodeDailyPuzzle
import classname
import readInput
import kotlin.math.pow

class Day03: AdventOfCodeDailyPuzzle() {

    fun part1(input: List<String>, nrBitsPerByte: Int): Int {
        val cntList = MutableList<Int>(nrBitsPerByte) { 0 }
        input.forEach { byte ->
            byte.forEachIndexed { index, bit ->
                if (bit == '1') cntList[index]++
            }
        }

        val threshold = input.size / 2.0
        val gammaRateByte = MutableList<Int>(nrBitsPerByte) { 0 }
        val epsilonRateByte = MutableList<Int>(nrBitsPerByte) { 0 }
        cntList.forEachIndexed { index, cntr ->
            run {
                gammaRateByte[index] = (if (cntr > threshold) 1 else 0)
                epsilonRateByte[index] = (if (cntr > threshold) 0 else 1)
            }
        }

        val gammaRate = calcDecimalFromBinaryList(gammaRateByte.toList())
        val epsilonRate = calcDecimalFromBinaryList(epsilonRateByte.toList())

        return gammaRate * epsilonRate
    }

    private fun calcDecimalFromBinaryList(byteList: List<Int>): Int {
        var decimal = 0
        var pow = byteList.size - 1
        val base = 2.0
        byteList.forEach { bit ->
            run {
                decimal += (bit * base.pow(pow).toInt())
                pow--
            }
        }
        return decimal
    }

    fun part2(input: List<String>, nrBitsPerByte: Int): Int {
        var oxygenRatingList = MutableList<String>(input.size) { input[it] }
        var co2RatingList = MutableList<String>(input.size) { input[it] }
        var bitPos = 0

        do {
            var cnt = 0
            oxygenRatingList.forEach {
                if (it[bitPos] == '1') cnt++
            }
            val searchBit = if (cnt >= oxygenRatingList.size / 2.0) 1 else 0

            oxygenRatingList = getRating(oxygenRatingList, bitPos, searchBit)

        } while (++bitPos < nrBitsPerByte)

        val oxygenRatingListAsIntList = List<Int>(nrBitsPerByte) { oxygenRatingList[0][it].digitToInt()}
        val oxygenRating = calcDecimalFromBinaryList(oxygenRatingListAsIntList)

        bitPos = 0
        do {
            var cnt = 0
            co2RatingList.forEach {
                if (it[bitPos] == '0') cnt++
            }
            val searchBit = if (cnt > co2RatingList.size / 2.0) 1 else 0

            co2RatingList = getRating(co2RatingList, bitPos, searchBit)

        } while (++bitPos < nrBitsPerByte)

        val co2RatingListAsIntList = List<Int>(nrBitsPerByte) { co2RatingList[0][it].digitToInt()}
        val co2Rating = calcDecimalFromBinaryList(co2RatingListAsIntList)

        print(oxygenRating)
        print (" * ")
        print (co2Rating)
        print (" = ")
        return oxygenRating * co2Rating
    }

    private fun getRating(input: List<String>, idx: Int, bit: Int): MutableList<String> {
        if (input.size == 1)
            return MutableList<String>(input.size) { input[it] }

        val result = MutableList<String>(input.size) { "" }
        var ctr = 0

        input.forEach {
            if (it[idx].digitToInt() == bit) {
                result[ctr++] = it
            }
        }
        result.removeIf { it.isEmpty() }
        return result
    }
}

fun main() {
    val day = Day03()
    val inputAsStringList = readInput("main", classname(day))

    println(classname(day) + " - 1: " + day.part1(inputAsStringList, inputAsStringList[0].length))
    println(classname(day) + " - 2: " + day.part2(inputAsStringList, inputAsStringList[0].length))
}

