package test

class UserMerge private constructor(private val usersWithEmails: Sequence<UserWithEmails>) {
    private val emailsToUsersWithEmails = mutableMapOf<String, UserWithEmails>()
    private val uniqUsers = mutableListOf<UserWithEmails>()

    private fun merge(): List<UserWithEmails> {
        usersWithEmails.forEach { userWithEmails ->
            val (_, emails) = userWithEmails

            var findUser: UserWithEmails? = null
            val notFindEmails = mutableSetOf<String>()

            emails.forEach { email ->
                emailsToUsersWithEmails[email]?.let { existUserWithEmails ->
                    if (findUser == null) {
                        findUser = existUserWithEmails
                        existUserWithEmails.emails += emails
                    } else if (findUser != existUserWithEmails) {
                        findUser?.let {
                            it.emails += existUserWithEmails.emails
                            emailsToUsersWithEmails[email] = it
                        }
                        uniqUsers.remove(existUserWithEmails)
                    }
                } ?: run {
                    notFindEmails += email
                }
            }

            if (findUser == null) {
                uniqUsers += userWithEmails
            }

            val userToAssign = findUser ?: userWithEmails
            notFindEmails.forEach { email ->
                emailsToUsersWithEmails[email] = userToAssign
            }
        }

        return uniqUsers
    }

    companion object {
        fun merge(usersWithEmails: Sequence<UserWithEmails>): List<UserWithEmails> {
            return UserMerge(usersWithEmails).merge()
        }
    }
}

