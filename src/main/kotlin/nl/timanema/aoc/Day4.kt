package nl.timanema.aoc

import kotlin.math.pow

const val ten = 10f

private fun day1(start: Int, end: Int): Int {
    return helper(start, end) { entry ->
        entry.value >= 2
    }
}

private fun day2(start: Int, end: Int): Int {
    return helper(start, end) { entry ->
        entry.value == 2
    }
}

private fun helper(start: Int, end: Int, check: (_: Map.Entry<Int, Int>) -> Boolean): Int {
    var cnt = 0

    for (num in start..end) {
        val numbers = Array(6) { j -> num / ten.pow(j).toInt() % 10 }

        if (numbers.asSequence().windowed(2).all { (prev, cur) -> prev >= cur }) {
            val count = numbers.groupingBy { it }.eachCount()

            for (entry in count.entries) {
                if (check(entry)) {
                    cnt += 1
                    break
                }
            }
        }
    }

    return cnt
}

fun main() {
    println("Day 1: " + day1(171309, 643603)) // 1625
    println("Day 2: " + day2(171309, 643603)) // 1111
}