package test

data class UserWithEmails(
    val userName: String,
    val emails: MutableSet<String>
)
