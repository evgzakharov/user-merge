package test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserDataParserTest {
    @Test
    fun test() {
        val testData = listOf(
            "user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru" to UserWithEmails(
                "user1",
                mutableSetOf("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")
            )
        )

        testData.forEach { (testString, expectedUser) ->
            val actualUser = testString.toUserWithEmails()

            assertEquals(expectedUser, actualUser, "actual user with emails is not as expected")
        }
    }
}