package refactoring.lab.before

object CustomerUnderageException : Exception() {
    private fun readResolve(): Any = CustomerUnderageException
}
