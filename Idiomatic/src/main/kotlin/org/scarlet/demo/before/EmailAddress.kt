package etc.demo1.before

data class EmailAddress(
    val username: String,
    val domain: String
) {
    override fun toString(): String {
        return "${username}@${domain}"
    }

    companion object {
        @JvmStatic
        fun parse(value: String): EmailAddress {
            val atIndex = value.lastIndexOf('@')
            require(atIndex >= 1 && atIndex < value.length - 1) {
                "Email address must be two parts separated by '@'"
            }
            return EmailAddress(
                value.substring(0, atIndex),
                value.substring(atIndex + 1)
            )
        }
    }

}