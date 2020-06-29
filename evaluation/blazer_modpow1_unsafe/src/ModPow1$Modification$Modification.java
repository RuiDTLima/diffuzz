import java.math.BigInteger;
public class ModPow1$Modification$Modification {
    public static BigInteger modPow1_safe(BigInteger base, BigInteger exponent, BigInteger modulus, int width) {
        BigInteger s = BigInteger.valueOf(1);
        for (int i = 0; i < width; i++) {
            s = s.multiply(s).mod(modulus);
            if (exponent.testBit((width - i) - 1)) {
                s = standardMultiply(s, base).mod(modulus);
            } else {
                standardMultiply(s, base).mod(modulus);
            }
        }
        return s;
    }

    public static BigInteger modPow1_unsafe(BigInteger base, BigInteger exponent, BigInteger modulus, int width) {
        BigInteger s = BigInteger.valueOf(1);
        for (int i = 0; i < width; i++) {
            s = s.multiply(s).mod(modulus);
            if (exponent.testBit((width - i) - 1)) {
                s = standardMultiply(s, base).mod(modulus);
            }
        }
        return s;
    }

    public static BigInteger modPow1_unsafe$Modification(BigInteger base, BigInteger exponent, BigInteger modulus, int width) {
        BigInteger $1 = BigInteger.valueOf(1);
        BigInteger s = BigInteger.valueOf(1);
        for (int i = 0; i < width; i++) {
            s = s.multiply(s).mod(modulus);
            if (exponent.testBit((width - i) - 1)) {
                s = standardMultiply(s, base).mod(modulus);
            } else {
                $1 = standardMultiply(s, base).mod(modulus);
            }
        }
        return s;
    }

    public static BigInteger modPow1_unsafe$Modification$Modification(BigInteger base, BigInteger exponent, BigInteger modulus, int width) {
        BigInteger $1 = BigInteger.valueOf(1);
        BigInteger $1 = BigInteger.valueOf(1);
        BigInteger s = BigInteger.valueOf(1);
        for (int i = 0; i < width; i++) {
            s = s.multiply(s).mod(modulus);
            if (exponent.testBit((width - i) - 1)) {
                s = standardMultiply(s, base).mod(modulus);
            } else {
                $1 = standardMultiply(s, base).mod(modulus);
            }
        }
        return s;
    }

    public static BigInteger modPow1_unsafe_inline(BigInteger base, BigInteger exponent, BigInteger modulus) {
        BigInteger s = BigInteger.valueOf(1);
        int width = exponent.bitLength();
        for (int i = 0; i < width; i++) {
            s = s.multiply(s).mod(modulus);
            if (exponent.testBit((width - i) - 1)) {
                BigInteger x = s;
                BigInteger y = base;
                int xLen = x.bitLength();
                int yLen = y.bitLength();
                if (x.equals(BigInteger.ONE)) {
                    return y;
                }
                if (y.equals(BigInteger.ONE)) {
                    return x;
                }
                BigInteger ret = BigInteger.ZERO;
                int N = Math.max(xLen, yLen);
                if (N <= 800) {
                    ret = x.multiply(y);
                } else if (Math.abs(xLen - yLen) >= 32) {
                    ret = BigInteger.ZERO;
                    for (int j = 0; j < y.bitLength(); j++) {
                        if (y.testBit(j)) {
                            ret = ret.add(x.shiftLeft(j));
                        }
                    }
                } else {
                    N = (N / 2) + (N % 2);
                    BigInteger b = x.shiftRight(N);
                    BigInteger a = x.subtract(b.shiftLeft(N));
                    BigInteger d = y.shiftRight(N);
                    BigInteger c = y.subtract(d.shiftLeft(N));
                    BigInteger ac = fastMultiply_1(a, c);
                    BigInteger bd = fastMultiply_1(b, d);
                    BigInteger crossterms = fastMultiply_1(a.add(b), c.add(d));
                    ret = ac.add(crossterms.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2 * N));
                }
                s = ret.mod(modulus);
            }
        }
        return s;
    }

    public static BigInteger fastMultiply_1(BigInteger x, BigInteger y) {
        int xLen = x.bitLength();
        int yLen = y.bitLength();
        if (x.equals(BigInteger.ONE)) {
            return y;
        }
        if (y.equals(BigInteger.ONE)) {
            return x;
        }
        BigInteger ret = BigInteger.ZERO;
        int N = Math.max(xLen, yLen);
        if (N <= 800) {
            ret = x.multiply(y);
        } else if (Math.abs(xLen - yLen) >= 32) {
            ret = x.multiply(y);
        } else {
            N = (N / 2) + (N % 2);
            BigInteger b = x.shiftRight(N);
            BigInteger a = x.subtract(b.shiftLeft(N));
            BigInteger d = y.shiftRight(N);
            BigInteger c = y.subtract(d.shiftLeft(N));
            BigInteger ac = fastMultiply_1(a, c);
            BigInteger bd = fastMultiply_1(b, d);
            BigInteger crossterms = fastMultiply_1(a.add(b), c.add(d));
            ret = ac.add(crossterms.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2 * N));
        }
        return ret;
    }

    public static BigInteger fastMultiply_inline(BigInteger x, BigInteger y) {
        int xLen = x.bitLength();
        int yLen = y.bitLength();
        if (x.equals(BigInteger.ONE)) {
            return y;
        }
        if (y.equals(BigInteger.ONE)) {
            return x;
        }
        BigInteger ret = BigInteger.ZERO;
        int N = Math.max(xLen, yLen);
        if (N <= 800) {
            ret = x.multiply(y);
        } else if (Math.abs(xLen - yLen) >= 32) {
            ret = BigInteger.ZERO;
            for (int i = 0; i < y.bitLength(); i++) {
                if (y.testBit(i)) {
                    ret = ret.add(x.shiftLeft(i));
                }
            }
        } else {
            N = (N / 2) + (N % 2);
            BigInteger b = x.shiftRight(N);
            BigInteger a = x.subtract(b.shiftLeft(N));
            BigInteger d = y.shiftRight(N);
            BigInteger c = y.subtract(d.shiftLeft(N));
            BigInteger ac = fastMultiply_1(a, c);
            BigInteger bd = fastMultiply_1(b, d);
            BigInteger crossterms = fastMultiply_1(a.add(b), c.add(d));
            ret = ac.add(crossterms.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2 * N));
        }
        return ret;
    }

    public static BigInteger standardMultiply(BigInteger x, BigInteger y) {
        BigInteger ret = BigInteger.ZERO;
        for (int i = 0; i < y.bitLength(); i++) {
            if (y.testBit(i)) {
                ret = ret.add(x.shiftLeft(i));
            }
        }
        return ret;
    }
}