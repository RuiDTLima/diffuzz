// package org.springframework.security.authentication.encoding;

// import org.springframework.security.crypto.codec.Utf8;

import java.io.UnsupportedEncodingException;

/**
 * Utility for constant time comparison to prevent against timing attacks.
 *
 * @author Rob Winch
 */
class PasswordEncoderUtils {

    /**
     * Constant time comparison to prevent against timing attacks.
     * @param expected
     * @param actual
     * @return
     */
    static boolean equals_safe(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
        int actualLength = actualBytes == null ? -1 : actualBytes.length;

        int result = expectedLength != actualLength ? 1 : 0;
        for (int i = 0; i < actualLength; i++) {
            result |= expectedBytes[i % expectedLength] ^ actualBytes[i % actualLength];
        }
        return result == 0;
    }

    static boolean equals_unsafe(String expected, String actual) {
        int $3 = 0;
        boolean $2;
        boolean $1;
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = (expectedBytes == null) ? -1 : expectedBytes.length;
        int actualLength = (actualBytes == null) ? -1 : actualBytes.length;
        if (expectedLength != actualLength) {
            $1 = false;
        } else {
            $2 = false;
        }
        int result = 0;
        for (int i = 0; i < expectedLength; i++) {
            if ((i < expectedLength) && (i < actualLength)) {
                result |= expectedBytes[i] ^ actualBytes[i];
            } else {
                $3 |= 0;
            }
        }
        $1 = result == 0;
        return $1;
    }

    private static byte[] bytesUtf8(String s) {
        try {
            if (s == null) {
                return null;
            }

            return s.getBytes("UTF-8");
        // return Utf8.encode(s);
        }catch(UnsupportedEncodingException e) {
                // e.printStackTrace();
                System.out.println("Error in UTF-8...");
                return null;
         }
    }

    // private PasswordEncoderUtils() {
    // }
}
