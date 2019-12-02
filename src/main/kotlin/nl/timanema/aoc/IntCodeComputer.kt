package nl.timanema.aoc

class IntCodeComputer(private val code: Array<Int>, private val ops: HashMap<Int, Operation>) {
    class Operation(val execute: (cpu: IntCodeComputer, ip: Int) -> Int)

    private var ip = 0
    private var stopped = false;

    fun execute(): Array<Int> {
        while (!stopped) {
            ip += ops.getOrDefault(code[ip], ops[99])!!.execute.invoke(this, ip)
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

        private fun getOps(): HashMap<Int, Operation> {
            return hashMapOf(99 to Operation { cpu: IntCodeComputer, _: Int ->
                // Stop execution
                cpu.stop()

                0
            }, 1 to Operation { cpu: IntCodeComputer, ip: Int ->
                // Add next to operands and store
                cpu.code[cpu.code[ip + 3]] = cpu.code[cpu.code[ip + 1]] + cpu.code[cpu.code[ip + 2]]

                4
            }, 2 to Operation { cpu: IntCodeComputer, ip: Int ->
                // Multiply next to operands and store
                cpu.code[cpu.code[ip + 3]] = cpu.code[cpu.code[ip + 1]] * cpu.code[cpu.code[ip + 2]]

                4
            })
        }
    }
}