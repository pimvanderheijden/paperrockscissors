package org.paperrockscissors.process

import org.paperrockscissors.domain.Choice
import org.paperrockscissors.domain.GameHistory
import org.paperrockscissors.domain.UserInput
import org.paperrockscissors.lib.ConsoleInputReader
import org.paperrockscissors.lib.InputReader

/*
    InputReader is provided in constructor, so it can be mocked
    GameHistory is created on ProcessHandler because their relation is one-to-one
    A new UserInput is created inside every while(true) because we receive a new input every time
 */
class ProcessHandler(private val inputReader: InputReader = ConsoleInputReader()) {
    val gameHistory: GameHistory = GameHistory()
    private var running: Boolean = true

    fun start() {
        println("Starting Rock Paper Scissors...\n")
        addShutdownHook()
        running = true

        while (running) {
            try {
                println("Type: ")
                val line = inputReader.read() ?: continue
                val userInput = UserInput.fromReadLn(line)

                val userChoice = userInput.toChoice()
                if (userChoice == null) {
                    println("Invalid choice\n")
                    continue
                }

                val computerChoice = Choice.getRandom()

                val score = userChoice.getScore(computerChoice)
                // val score = userChoice.getScoreAlt(computerChoice)

                gameHistory.add(userChoice, computerChoice, score)
                continue
            } catch (e: Exception) {
                // Just in case we want to do special
                throw (e)
            }
        }
    }

    fun stop() {
        println("Stopping Rock Paper Scissors...\n")
        running = false
    }

    private fun addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                running = false
                println(gameHistory)
            }
        })
    }
}