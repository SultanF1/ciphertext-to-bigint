import java.math.BigInteger;
import java.nio.ByteBuffer;

public class test {
    public static BigInteger ciphertextToNumber(byte[] ct) {
        ByteBuffer fixed = ByteBuffer.allocate(2 + ct.length);
        fixed.put((byte) 0x4D).put((byte) 0x42);
        fixed.put(ct);
        BigInteger number = new BigInteger(fixed.array());
        return number;
    }

    public static byte[] numberToCiphertext(BigInteger ctAsNumber) {
        // if the number is negative then the buffer will be too small
        if (ctAsNumber.signum() < 0 || ctAsNumber.bitLength() < 15) {
            throw new IllegalArgumentException("Magic of ciphertext number doesn't match");
        }
        ByteBuffer fixed = ByteBuffer.allocate((ctAsNumber.bitLength() + Byte.SIZE - 1) / Byte.SIZE);
        fixed.put(ctAsNumber.toByteArray());
        fixed.flip();
        if (fixed.get() != (byte) 0x4D || fixed.get() != (byte) 0x42) {
            throw new IllegalArgumentException("Magic of ciphertext number doesn't match");
        }
        System.out.println(fixed.remaining());
        byte[] ct = new byte[fixed.remaining()];
        fixed.get(ct);
        return ct;
    }
}
