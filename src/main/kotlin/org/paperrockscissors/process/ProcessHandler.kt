package org.paperrockscissors.process

import org.paperrockscissors.domain.Choice
import org.paperrockscissors.domain.GameHistory
import org.paperrockscissors.domain.UserInput

class ProcessHandler() {
    val gameHistory: GameHistory = GameHistory()

    fun start() {
        println("Starting Rock Paper Scissors...")
        println("")

        addShutdownHook()

        while (true) {
            println("Type: ")

            try {
                val userInput = UserInput.fromReadLn() ?: continue

                val userChoice = userInput.toChoice()
                if (userChoice == null) {
                    println("Invalid choice")
                    println("")
                    continue
                }

                val computerChoice = Choice.getRandom()

                val score = userChoice.getScore(computerChoice)
                // val score = userChoice.getScoreAlt(computerChoice)

                gameHistory.add(userChoice, computerChoice, score)
                continue
            } catch (e: Exception) {
                println(gameHistory)
                throw (e)
            }
        }
    }

    private fun addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                println(gameHistory)
            }
        })
    }
}