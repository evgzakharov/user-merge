package test

data class UserWithEmails(
    val userName: String,
    val emails: MutableSet<String>
) {
    fun toUserDataFormat(): String {
        return "$userName -> ${emails.joinToString(" ,")}"
    }
}
