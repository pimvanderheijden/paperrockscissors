package org.paperrockscissors.domain

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.Timer
import kotlin.concurrent.timerTask

class ProcessHandlerTest {
    @Timeout(4)
    @Test
    @Suppress
    fun `Async`() {
        val channel = Channel<Int>(1)

        runBlocking {
            Timer().schedule(timerTask {
                runBlocking {
                    channel.send(1)
                }
            }, 200)

            withTimeout(500) { channel.receive() }
        }

        assertEquals(1, 1)
        channel.close()
    }
}