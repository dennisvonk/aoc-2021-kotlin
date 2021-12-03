import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(rootFolder: String, name: String) = File("src/$rootFolder/resources", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * transform a List of Strings to a List of Ints.
 */
fun stringListToIntList(input: List<String>) = input.map { i -> i.toInt() }

/**
 * returns the class simplename
 */
fun classname(c: AdventOfCodeDailyPuzzle) = c::class.simpleName!!