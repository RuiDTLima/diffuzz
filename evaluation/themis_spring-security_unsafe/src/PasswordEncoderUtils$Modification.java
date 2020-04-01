import java.io.UnsupportedEncodingException;
class PasswordEncoderUtils$Modification {
    static boolean equals_safe(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = (expectedBytes == null) ? -1 : expectedBytes.length;
        int actualLength = (actualBytes == null) ? -1 : actualBytes.length;
        int result = (expectedLength != actualLength) ? 1 : 0;
        for (int i = 0; i < actualLength; i++) {
            result |= expectedBytes[i % expectedLength] ^ actualBytes[i % actualLength];
        }
        return result == 0;
    }

    static boolean equals_unsafe(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = (expectedBytes == null) ? -1 : expectedBytes.length;
        int actualLength = (actualBytes == null) ? -1 : actualBytes.length;
        if (expectedLength != actualLength) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < expectedLength; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }

    static boolean equals_unsafe$Modification(String expected, String actual) {
        boolean $1 = result==0;
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = (expectedBytes == null) ? -1 : expectedBytes.length;
        int actualLength = (actualBytes == null) ? -1 : actualBytes.length;
        if (expectedLength != actualLength) {
            $1 = false;
        }
        int result = 0;
        for (int i = 0; i < expectedLength; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return $1;
    }

    private static byte[] bytesUtf8(String s) {
        try {
            if (s == null) {
                return null;
            }
            return s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error in UTF-8...");
            return null;
        }
    }
}