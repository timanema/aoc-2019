package nl.timanema.aoc

import kotlin.math.pow

class IntCodeComputer(private val code: Array<Int>, private val ops: HashMap<Int, Operation>) {
    class Operation(val execute: (cpu: IntCodeComputer, ip: Int, mode: Array<Int>) -> Int)

    private var ip = 0
    private var stopped = false;

    fun execute(): Array<Int> {
        while (!stopped && ip < code.size) {
            val instruction = code[ip]

            ip += ops.getOrDefault(instruction % 100, ops[99])!!.execute.invoke(
                this,
                ip,
                Array(4) { j -> (instruction / 100) / ten.pow(j).toInt() % 10 })
        }

        return code
    }

    fun stop() {
        stopped = true
    }

    companion object {
        fun create(code: String, noun: Int, verb: Int): IntCodeComputer {
            val stringParse = code
                .replace("x", noun.toString())
                .replace("y", verb.toString())
                .split(',')

            return IntCodeComputer(Array(stringParse.size) { i -> stringParse[i].toInt() }, getOps())
        }

        private fun getVal(cpu: IntCodeComputer, ip: Int, mode: Int): Int {
            return if (mode == 0) cpu.code[cpu.code[ip]] else cpu.code[ip]
        }

        private fun getOps(): HashMap<Int, Operation> {
            return hashMapOf(99 to Operation { cpu: IntCodeComputer, _: Int, _: Array<Int> ->
                // Stop execution
                cpu.stop()

                0
            }, 1 to Operation { cpu: IntCodeComputer, ip: Int, mode: Array<Int> ->
                // Add next to operands and store
                cpu.code[cpu.code[ip + 3]] = getVal(cpu, ip + 1, mode[0]) + getVal(cpu, ip + 2, mode[1])

                4
            }, 2 to Operation { cpu: IntCodeComputer, ip: Int, mode: Array<Int> ->
                // Multiply next to operands and store
                cpu.code[cpu.code[ip + 3]] = getVal(cpu, ip + 1, mode[0]) * getVal(cpu, ip + 2, mode[1])

                4
            }, 3 to Operation { cpu: IntCodeComputer, ip: Int, _: Array<Int> ->
                // Take input and save
                print("Input: ")
                cpu.code[cpu.code[ip + 1]] = readLine()!!.toInt()

                2
            }, 4 to Operation { cpu: IntCodeComputer, ip: Int, mode: Array<Int> ->
                // Output value
                println("Output: " + getVal(cpu, ip + 1, mode[0]))

                2
            }, 5 to Operation { cpu: IntCodeComputer, ip: Int, mode: Array<Int> ->
                // Jump if true
                if (getVal(cpu, ip + 1, mode[0]) != 0) getVal(cpu, ip + 2, mode[1]) - ip else 3
            }, 6 to Operation { cpu: IntCodeComputer, ip: Int, mode: Array<Int> ->
                // Jump if false
                if (getVal(cpu, ip + 1, mode[0]) == 0) getVal(cpu, ip + 2, mode[1]) - ip else 3
            }, 7 to Operation { cpu: IntCodeComputer, ip: Int, mode: Array<Int> ->
                // Set less-than bit
                cpu.code[cpu.code[ip + 3]] = if (getVal(cpu, ip + 1, mode[0]) < getVal(cpu, ip + 2, mode[1])) 1 else 0

                4
            }, 8 to Operation { cpu: IntCodeComputer, ip: Int, mode: Array<Int> ->
                // Set equal bit
                cpu.code[cpu.code[ip + 3]] = if (getVal(cpu, ip + 1, mode[0]) == getVal(cpu, ip + 2, mode[1])) 1 else 0

                4
            })
        }
    }
}