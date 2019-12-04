package nl.timanema.aoc

const val code = "1,x,y,3,1,1,2,3,1,3,4,3,1,5,0,3,2,9,1,19,1,5,19,23,2,9,23,27,1,27,5,31,2,31,13,35,1,35,9,39,1,39,10,43,2,43,9,47,1,47,5,51,2,13,51,55,1,9,55,59,1,5,59,63,2,6,63,67,1,5,67,71,1,6,71,75,2,9,75,79,1,79,13,83,1,83,13,87,1,87,5,91,1,6,91,95,2,95,13,99,2,13,99,103,1,5,103,107,1,107,10,111,1,111,13,115,1,10,115,119,1,9,119,123,2,6,123,127,1,5,127,131,2,6,131,135,1,135,2,139,1,139,9,0,99,2,14,0,0"
const val testCode = "1,1,1,4,99,5,6,0,99"

private fun day1(noun: Int, verb: Int): Array<Int> {
    return IntCodeComputer.create(code, noun, verb).execute()
}

private fun day2(target: Int): Pair<Int, Int> {
    for (noun in 0..99) {
        for (verb in 0..99) {
            if (IntCodeComputer.create(code, noun, verb).execute()[0] == target) {
                return Pair(noun, verb)
            }
        }
    }

    return Pair(-1, -1)
}

fun main() {
    println("Day 1: " + day1(12, 2)[0])

    val result = day2(19690720);
    println("Day 2: " + (result.first * 100 + result.second))
}