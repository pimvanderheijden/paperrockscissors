package org.paperrockscissors.domain

import kotlin.math.sign

typealias Log = Triple<Choice, Choice, Int>

class GameHistory {
    var entries = mutableListOf<Log>()

    fun add(userChoice: Choice, computerChoice: Choice, score: Int) {
        val scoreStr = Choice.scoreToString(score)
        println("$scoreStr Computer chose: $computerChoice")

        val log = Log(userChoice, computerChoice, score)
        entries.add(log)
    }

    override fun toString(): String {
        val wins = entries.count { it.third == 1 }
        val loses = entries.count { it.third == -1 }
        val ties = entries.count { it.third == 0 }
        val net = wins - loses

        var str = "\n"

        str += "The game ends. Overview: \n\n"
        str += Choice.scoreToString(net.sign)
        str += "\nWins: $wins, Loses: $loses, Ties: $ties"

        return str
    }
}