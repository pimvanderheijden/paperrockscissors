package org.paperrockscissors.process

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.paperrockscissors.domain.Choice
import org.paperrockscissors.lib.InputReader
import kotlin.test.assertEquals

class MockInputReader(private val mockData: String, private val times: Int = 1) : InputReader {
    private var count: Int = 0

    override fun read(): String? {
        return if (++count <= times) {
            mockData
        } else {
            // TODO better way to fake behavior of waiting for user input
            runBlocking {
                Thread.sleep(1000)
            }
            null
        }
    }
}

class ProcessHandlerTest {
    @Test
    fun `User input`() = runBlocking {
        val mockInputReader = MockInputReader("Rock \n")  // capitalize and add space
        val processHandler = ProcessHandler(inputReader = mockInputReader)

        launch {
            processHandler.start()
        }

        // TODO Need some kind of event to emitted from processHandler to know when a line has been processed
        delay(100)
        processHandler.stop()

        assertEquals(1, processHandler.gameHistory.entries.size)

        val entry = processHandler.gameHistory.entries[0]

        assertEquals(Choice.ROCK, entry.first)
        assert(entry.third in -1..1)
    }
}