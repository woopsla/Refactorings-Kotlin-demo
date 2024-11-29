package org.scarlet.demo.work

data class EmailAddress(
    val username: String,
    val domain: String,
) {
    override fun toString(): String {
        return "${username}@${domain}"
    }

    companion object {
        fun parse(value: String): EmailAddress =
            getPair(value, '@').let { (name, domain) ->
                EmailAddress(name, domain)
            }

        private fun getPair(value: String, separator: Char): Pair<String, String> =
            value.lastIndexOf(separator).let {
                require(it in 1..<value.length - 1) {
                    "Email address must be two parts separated by 'separator'"
                }
                Pair(
                    value.substring(0, it),
                    value.substring(it + 1)
                )
            }
    }
}