package org.paperrockscissors.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ChoiceTest {
    @Test
    fun `Choices general`() {
        val choice1 = Choice.getRandom()
        val choice2 = Choice.getRandom()

        val score = choice1.getScore(choice2)

        assert(score is Int)
        assert(score in -1..1)
    }

    @Test
    fun `Score outcomes`() {
        assertEquals(1, Choice.PAPER.getScore(Choice.ROCK))
        assertEquals(0, Choice.PAPER.getScore(Choice.PAPER))
        assertEquals(-1, Choice.PAPER.getScore(Choice.SCISSORS))

        assertEquals(1, Choice.ROCK.getScore(Choice.SCISSORS))
        assertEquals(0, Choice.ROCK.getScore(Choice.ROCK))
        assertEquals(-1, Choice.ROCK.getScore(Choice.PAPER))

        assertEquals(1, Choice.SCISSORS.getScore(Choice.PAPER))
        assertEquals(0, Choice.SCISSORS.getScore(Choice.SCISSORS))
        assertEquals(-1, Choice.SCISSORS.getScore(Choice.ROCK))
    }

    @Test
    fun `Score alternative outcomes`() {
        assertEquals(1, Choice.PAPER.getScoreAlt(Choice.ROCK))
        assertEquals(0, Choice.PAPER.getScoreAlt(Choice.PAPER))
        assertEquals(-1, Choice.PAPER.getScoreAlt(Choice.SCISSORS))

        assertEquals(1, Choice.ROCK.getScoreAlt(Choice.SCISSORS))
        assertEquals(0, Choice.ROCK.getScoreAlt(Choice.ROCK))
        assertEquals(-1, Choice.ROCK.getScoreAlt(Choice.PAPER))

        assertEquals(1, Choice.SCISSORS.getScoreAlt(Choice.PAPER))
        assertEquals(0, Choice.SCISSORS.getScoreAlt(Choice.SCISSORS))
        assertEquals(-1, Choice.SCISSORS.getScoreAlt(Choice.ROCK))
    }
}