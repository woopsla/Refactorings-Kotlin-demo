package refactoring.lab.work

object CustomerUnderageException : Exception() {
    private fun readResolve(): Any = CustomerUnderageException
}
