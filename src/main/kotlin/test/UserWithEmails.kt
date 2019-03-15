package test

data class UserWithEmails(
    val userName: String,
    val emails: MutableSet<String>
) {
    fun toOutputDataFormat(): String {
        return "$userName -> ${emails.joinToString(" ,")}"
    }
}
