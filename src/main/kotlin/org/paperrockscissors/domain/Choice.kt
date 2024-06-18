package org.paperrockscissors.domain

enum class Choice {
    PAPER, ROCK, SCISSORS;

    fun getSCoreAlt(other: Choice): Int {
        val choiceAsInt = choiceToInt(this)
        val otherAsInt = choiceToInt(other)

        return when (choiceAsInt - otherAsInt) {
            1, -2 -> 1
            -1, 2 -> -1
            else -> 0
        }
    }

    fun getSCore(other: Choice): Int {
        return when (Pair(this, other)) {
            Pair(PAPER, ROCK) -> 1
            Pair(PAPER, SCISSORS) -> -1

            Pair(ROCK, SCISSORS) -> 1
            Pair(ROCK, PAPER) -> -1

            Pair(SCISSORS, PAPER) -> 1
            Pair(SCISSORS, ROCK) -> -1

            else -> 0
        }
    }

    companion object {
        fun fromString(line: String): Choice? {
            return when (line) {
                "rock" -> ROCK
                "paper" -> PAPER
                "scissors" -> SCISSORS
                else -> null
            }
        }

        fun getRandom(): Choice {
            return entries.toTypedArray().random()
        }

        fun choiceToInt(choice: Choice): Int {
            return when (choice) {
                PAPER -> 2
                ROCK -> 1
                SCISSORS -> 0
            }
        }

        fun scoreToString(score: Int): String {
            return when (score) {
                1 -> "You won!"
                0 -> "It's a tie!"
                -1 -> "You lost!"
                else -> throw Exception("GameHistory Invalid sign")
            }
        }
    }
}