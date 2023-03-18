import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

import java.math.BigInteger
object Main {
  def main(args: Array[String]): Unit = {
    val string = "n" * 5000
    val ct = string.getBytes(StandardCharsets.UTF_8)

    val byteBuffer = ByteBuffer.allocate(1 + ct.length)
    byteBuffer.put(0x48.toByte)
    byteBuffer.put(ct)

    val ctAsNumber = new BigInteger(byteBuffer.array)
    println(ctAsNumber)

    val fixed = ByteBuffer.allocate((ctAsNumber.bitLength + 8 - 1) / 8)
    fixed.put(ctAsNumber.toByteArray)
    fixed.flip()
    fixed.get()
    val ct2 = new Array[Byte](fixed.remaining)
    fixed.get(ct2)

    val string2 = new String(ct2, StandardCharsets.UTF_8)
    println(string.equals(string2))






  }

def ciphertextToNumber(ct: Array[Byte]): BigInt = {
  val fixed = ByteBuffer.allocate(1 + ct.length)
  fixed.put(0x53.toByte)
  println(new BigInteger(fixed.array))
  fixed.put(ct)
  println(fixed.array)
  val number = new BigInteger(fixed.array)
  number
}

  def numberToCiphertext(ctAsNumber: BigInt): Array[Byte] = {
    val fixed = ByteBuffer.allocate((ctAsNumber.bitLength + 8 - 1) / 8)
    fixed.put(ctAsNumber.toByteArray)
    fixed.flip()
    fixed.get()
    val ct = new Array[Byte](fixed.remaining)
    fixed.get(ct)
    ct
  }
}