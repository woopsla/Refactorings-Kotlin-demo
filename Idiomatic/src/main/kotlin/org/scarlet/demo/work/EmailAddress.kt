package etc.demo1.work

data class EmailAddress(
    val username: String,
    val domain: String
) {
    override fun toString(): String = "${username}@${domain}"

    companion object {
        @JvmStatic
        fun parse(value: String): EmailAddress =
            value.separate('@').let { (username, domain) ->
                EmailAddress(username, domain)
            }
    }

}

private fun String.separate(sep: Char): Pair<String, String> =
    lastIndexOf(sep).let { atIndex ->
        require(atIndex in 1..<length - 1) {
            "Email address must be two parts separated by '@'"
        }
        Pair(substring(0, atIndex), substring(atIndex + 1))
    }