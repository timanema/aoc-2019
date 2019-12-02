package nl.timanema.aoc

val masses = setOf(144475,
    145308,
    100615,
    56900,
    128773,
    65519,
    74165,
    99081,
    141047,
    149128,
    148282,
    109528,
    55909,
    70885,
    115049,
    149631,
    52276,
    101944,
    113005,
    102876,
    64365,
    71178,
    122767,
    86272,
    139199,
    78631,
    71958,
    81288,
    70401,
    77582,
    118275,
    115648,
    91350,
    121735,
    130339,
    55146,
    137351,
    101940,
    112657,
    133288,
    81503,
    136812,
    67015,
    142573,
    125537,
    99231,
    61693,
    85719,
    80659,
    148431,
    101176,
    77853,
    108201,
    138945,
    81804,
    55795,
    141837,
    113490,
    57932,
    81023,
    76756,
    79023,
    73527,
    75874,
    63332,
    62055,
    76124,
    54254,
    68482,
    141113,
    84335,
    58747,
    84723,
    137564,
    132605,
    94970,
    50312,
    89127,
    143858,
    124587,
    52272,
    138039,
    53782,
    93085,
    83456,
    94432,
    121481,
    93700,
    114222,
    117849,
    147460,
    110324,
    75337,
    130464,
    88805,
    109489,
    71109,
    95625,
    115832,
    123252
)

val testMass = setOf(100756)

fun helper(mass: Int): Int {
    return mass / 3 - 2
}

fun day1(set: Set<Int>): Int {
    var cnt = 0

    for (mass in set) {
        cnt += helper(mass)
    }

    return cnt
}

fun day2(set: Set<Int>): Int {
    var cnt = 0

    for (mass in set) {
        cnt += day2helper(helper(mass))
    }

    return cnt
}

fun day2helper(fuel: Int): Int {
    val f = helper(fuel)

    return fuel + if (f < 0) 0 else day2helper(f)
}

fun main() {
    println("Day 1: " + day1(masses))
    println("Day 2: " + day2(masses))
}