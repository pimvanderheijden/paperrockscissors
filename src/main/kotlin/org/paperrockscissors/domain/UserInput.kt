package org.paperrockscissors.domain

class UserInput(private val line: String) {
    fun toChoice(): Choice? {
        return Choice.fromString(line)
    }

    override fun toString(): String {
        return "UserInput line=$line"
    }

    companion object {
        fun fromReadLn(line: String): UserInput {
            return UserInput(line = sanitize(line))
        }

        private fun sanitize(line: String): String {
            return line.trim().lowercase()
        }
    }
}