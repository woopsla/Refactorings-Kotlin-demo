import java.math.BigInteger

fun factorial(n: Int): BigInteger =
    if (n == 0) BigInteger.ONE
    else BigInteger.valueOf(n.toLong()) * factorial(n - 1)

tailrec fun factTailRec(acc: BigInteger, n: Int): BigInteger =
    if (n == 0) acc
    else factTailRec(acc * BigInteger.valueOf(n.toLong()), n - 1)

fun main() {
//    println(factorial(20_000))
    println(factTailRec(BigInteger.ONE, 20_000))
}
