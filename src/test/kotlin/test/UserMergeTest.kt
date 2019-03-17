package test

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserMergeTest {
    @Test
    fun testMerge() {
        val testUsersData = sequenceOf(
            UserWithEmails(
                "user1",
                mutableSetOf("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")
            ),
            UserWithEmails(
                "user2",
                mutableSetOf("foo@gmail.com", "ups@pisem.net")
            ),
            UserWithEmails(
                "user3",
                mutableSetOf("xyz@pisem.net", "vasya@pupkin.com")
            ),
            UserWithEmails(
                "user4",
                mutableSetOf("ups@pisem.net", "aaa@bbb.ru")
            ),
            UserWithEmails(
                "user5",
                mutableSetOf("xyz@pisem.net")
            )
        )

        val expectedMergedUsers = listOf(
            UserWithEmails(
                "user1",
                mutableSetOf("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru")
            ),
            UserWithEmails(
                "user3",
                mutableSetOf("xyz@pisem.net", "vasya@pupkin.com")
            )
        )

        val actualMergedUsers = UserMerge.merge(testUsersData)

        Assertions.assertEquals(
            expectedMergedUsers,
            actualMergedUsers,
            "actual users not same as expected"
        )
    }

    @Test
    fun testMergeWithMultiUserMatch() {
        val testUsersData = sequenceOf(
            UserWithEmails(
                "user1",
                mutableSetOf("e1@ya.ru", "e2@ya.ru")
            ),
            UserWithEmails(
                "user2",
                mutableSetOf("e3@ya.ru", "e4@ya.ru")
            ),
            UserWithEmails(
                "user3",
                mutableSetOf("e1@ya.ru", "e3@ya.ru")
            )
        )

        val expectedMergedUsers = listOf(
            UserWithEmails(
                "user1",
                mutableSetOf("e1@ya.ru", "e2@ya.ru", "e3@ya.ru", "e4@ya.ru")
            )
        )

        val actualMergedUsers = UserMerge.merge(testUsersData)

        Assertions.assertEquals(
            expectedMergedUsers,
            actualMergedUsers,
            "actual users not same as expected"
        )
    }


}
