package test

internal fun String.toUserWithEmails(): UserWithEmails {
    val (user, emails) = this.split("->", limit = 2)

    return UserWithEmails(user.trim(), emails.toUniqEmails())
}

private fun String.toUniqEmails(): MutableSet<String> = this.split(",")
    .asSequence()
    .map { it.trim() }
    .toMutableSet()
