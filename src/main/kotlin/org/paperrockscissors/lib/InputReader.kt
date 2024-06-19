package org.paperrockscissors.lib

interface InputReader {
    fun read(): String?
}

class ConsoleInputReader : InputReader {
    override fun read(): String? {
        return readlnOrNull()
    }
}