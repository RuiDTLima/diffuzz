import OGlobalConfiguration.SECURITY_USER_PASSWORD_SALT_CACHE_SIZE;
import OGlobalConfiguration.SECURITY_USER_PASSWORD_SALT_ITERATIONS;
import com.orientechnologies.common.collection.OLRUCache;
import com.orientechnologies.common.exception.OException;
import com.orientechnologies.common.log.OLogManager;
import com.orientechnologies.orient.core.exception.OConfigurationException;
import com.orientechnologies.orient.core.exception.OSecurityException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
public class OSecurityManager$Modification {
    public static final String HASH_ALGORITHM = "SHA-256";

    public static final String HASH_ALGORITHM_PREFIX = ("{" + HASH_ALGORITHM) + "}";

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static final String PBKDF2_ALGORITHM_PREFIX = ("{" + PBKDF2_ALGORITHM) + "}";

    public static final String PBKDF2_SHA256_ALGORITHM = "PBKDF2WithHmacSHA256";

    public static final String PBKDF2_SHA256_ALGORITHM_PREFIX = ("{" + PBKDF2_SHA256_ALGORITHM) + "}";

    public static final int SALT_SIZE = 24;

    public static final int HASH_SIZE = 24;

    private static final OSecurityManager instance = new OSecurityManager();

    private MessageDigest md;

    private static Map<String, byte[]> SALT_CACHE = null;

    static {
        final int cacheSize = SECURITY_USER_PASSWORD_SALT_CACHE_SIZE.getValueAsInteger();
        if (cacheSize > 0) {
            SALT_CACHE = Collections.synchronizedMap(new OLRUCache<String, byte[]>(cacheSize));
        }
    }

    public OSecurityManager$Modification() {
        try {
            md = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            OLogManager.instance().error(this, "Cannot use OSecurityManager", e);
        }
    }

    public static String createHash(final String iInput, String iAlgorithm) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (iAlgorithm == null)
            iAlgorithm = HASH_ALGORITHM;

        final MessageDigest msgDigest = MessageDigest.getInstance(iAlgorithm);
        return byteArrayToHexStr(msgDigest.digest(iInput.getBytes("UTF-8")));
    }

    public static OSecurityManager instance() {
        return instance;
    }

    public static boolean isEqual_inline(byte[] digesta, byte[] digestb) {
        if (digesta == digestb)
            return true;

        if ((digesta == null) || (digestb == null)) {
            return false;
        }
        if (digesta.length != digestb.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < digesta.length; i++) {
            result |= digesta[i] ^ digestb[i];
        }
        return result == 0;
    }

    public boolean equals_inline(String iPassword, String iHash) {
        int n = iPassword.length();
        if (n == iHash.length()) {
            char[] v1 = iPassword.toCharArray();
            char[] v2 = iHash.toCharArray();
            int i = 0;
            while ((n--) != 0) {
                if (v1[i] != v2[i])
                    return false;

                i++;
            } 
            return true;
        }
        return false;
    }

    public boolean equals_inline$Modification(String iPassword, String iHash) {
        boolean $1 = false;
        int n = iPassword.length();
        if (n == iHash.length()) {
            char[] v1 = iPassword.toCharArray();
            char[] v2 = iHash.toCharArray();
            int i = 0;
            $1 = true;
            while ((n--) != 0) {
                if ((i < v1.length) && (i < v2.length) && v1[i] != v2[i]) {
                    $1 = false;
                }
                i++;
            } 
        }
        return $1;
    }

    public boolean checkPassword_safe(final String iPassword, final String iHash) {
        if (iHash.startsWith(HASH_ALGORITHM_PREFIX)) {
            final String s = iHash.substring(HASH_ALGORITHM_PREFIX.length());
            return createSHA256(iPassword).equals(s);
        } else if (iHash.startsWith(PBKDF2_ALGORITHM_PREFIX)) {
            final String s = iHash.substring(PBKDF2_ALGORITHM_PREFIX.length());
            return checkPasswordWithSalt(iPassword, s, PBKDF2_ALGORITHM);
        } else if (iHash.startsWith(PBKDF2_SHA256_ALGORITHM_PREFIX)) {
            final String s = iHash.substring(PBKDF2_SHA256_ALGORITHM_PREFIX.length());
            return checkPasswordWithSalt(iPassword, s, PBKDF2_SHA256_ALGORITHM);
        }
        return isEqual_inline(digestSHA256(iPassword), digestSHA256(iHash));
    }

    public boolean checkPassword_unsafe(final String iPassword, final String iHash) {
        if (iHash.startsWith(HASH_ALGORITHM_PREFIX)) {
            final String s = iHash.substring(HASH_ALGORITHM_PREFIX.length());
            return createSHA256(iPassword).equals(s);
        } else if (iHash.startsWith(PBKDF2_ALGORITHM_PREFIX)) {
            final String s = iHash.substring(PBKDF2_ALGORITHM_PREFIX.length());
            return checkPasswordWithSalt(iPassword, s, PBKDF2_ALGORITHM);
        } else if (iHash.startsWith(PBKDF2_SHA256_ALGORITHM_PREFIX)) {
            final String s = iHash.substring(PBKDF2_SHA256_ALGORITHM_PREFIX.length());
            return checkPasswordWithSalt(iPassword, s, PBKDF2_SHA256_ALGORITHM);
        }
        return equals_inline(iPassword, iHash);
    }

    public String createSHA256(final String iInput) {
        return byteArrayToHexStr(digestSHA256(iInput));
    }

    public String createHash(final String iInput, final String iAlgorithm, final boolean iIncludeAlgorithm) {
        if (iInput == null)
            throw new IllegalArgumentException("Input string is null");

        if (iAlgorithm == null)
            throw new IllegalArgumentException("Algorithm is null");

        final StringBuilder buffer = new StringBuilder(128);
        if (iIncludeAlgorithm) {
            buffer.append('{');
            buffer.append(iAlgorithm);
            buffer.append('}');
        }
        final String transformed;
        if (HASH_ALGORITHM.equalsIgnoreCase(iAlgorithm)) {
            transformed = createSHA256(iInput);
        } else if (PBKDF2_ALGORITHM.equalsIgnoreCase(iAlgorithm)) {
            transformed = createHashWithSalt(iInput, SECURITY_USER_PASSWORD_SALT_ITERATIONS.getValueAsInteger(), iAlgorithm);
        } else if (PBKDF2_SHA256_ALGORITHM.equalsIgnoreCase(iAlgorithm)) {
            transformed = createHashWithSalt(iInput, SECURITY_USER_PASSWORD_SALT_ITERATIONS.getValueAsInteger(), iAlgorithm);
        } else
            throw new IllegalArgumentException(("Algorithm '" + iAlgorithm) + "' is not supported");

        buffer.append(transformed);
        return buffer.toString();
    }

    public synchronized byte[] digestSHA256(final String iInput) {
        if (iInput == null)
            return null;

        try {
            return md.digest(iInput.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            final String message = "The requested encoding is not supported: cannot execute security checks";
            OLogManager.instance().error(this, message, e);
            throw OException.wrapException(new OConfigurationException(message), e);
        }
    }

    public String createHashWithSalt(final String iPassword) {
        return createHashWithSalt(iPassword, SECURITY_USER_PASSWORD_SALT_ITERATIONS.getValueAsInteger(), PBKDF2_SHA256_ALGORITHM);
    }

    public String createHashWithSalt(final String iPassword, final int iIterations, final String algorithm) {
        final SecureRandom random = new SecureRandom();
        final byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        final byte[] hash = getPbkdf2(iPassword, salt, iIterations, HASH_SIZE, algorithm);
        return (((byteArrayToHexStr(hash) + ":") + byteArrayToHexStr(salt)) + ":") + iIterations;
    }

    public boolean checkPasswordWithSalt(final String iPassword, final String iHash) {
        return checkPasswordWithSalt(iPassword, iHash, OSecurityManager.PBKDF2_SHA256_ALGORITHM);
    }

    public boolean checkPasswordWithSalt(final String iPassword, final String iHash, final String algorithm) {
        final String[] params = iHash.split(":");
        if (params.length != 3)
            throw new IllegalArgumentException("Hash does not contain the requested parts: <hash>:<salt>:<iterations>");

        final byte[] hash = hexToByteArray(params[0]);
        final byte[] salt = hexToByteArray(params[1]);
        final int iterations = Integer.parseInt(params[2]);
        final byte[] testHash = getPbkdf2(iPassword, salt, iterations, hash.length, algorithm);
        return MessageDigest.isEqual(hash, testHash);
    }

    private byte[] getPbkdf2(final String iPassword, final byte[] salt, final int iterations, final int bytes, final String algorithm) {
        String cacheKey = null;
        final String hashedPassword = createSHA256(iPassword + new String(salt));
        if (SALT_CACHE != null) {
            cacheKey = (((((hashedPassword + "|") + Arrays.toString(salt)) + "|") + iterations) + "|") + bytes;
            final byte[] encoded = SALT_CACHE.get(cacheKey);
            if (encoded != null)
                return encoded;

        }
        final PBEKeySpec spec = new PBEKeySpec(iPassword.toCharArray(), salt, iterations, bytes * 8);
        final SecretKeyFactory skf;
        try {
            skf = SecretKeyFactory.getInstance(algorithm);
            final byte[] encoded = skf.generateSecret(spec).getEncoded();
            if (SALT_CACHE != null) {
                SALT_CACHE.put(cacheKey, encoded);
            }
            return encoded;
        } catch (Exception e) {
            throw OException.wrapException(new OSecurityException(("Cannot create a key with '" + PBKDF2_ALGORITHM) + "' algorithm"), e);
        }
    }

    public static String byteArrayToHexStr(final byte[] data) {
        if (data == null)
            return null;

        final char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            final byte current = data[i];
            final int hi = (current & 0xf0) >> 4;
            final int lo = current & 0xf;
            chars[2 * i] = ((char) ((hi < 10) ? '0' + hi : ('A' + hi) - 10));
            chars[(2 * i) + 1] = ((char) ((lo < 10) ? '0' + lo : ('A' + lo) - 10));
        }
        return new String(chars);
    }

    private static byte[] hexToByteArray(final String data) {
        final byte[] hex = new byte[data.length() / 2];
        for (int i = 0; i < hex.length; i++)
            hex[i] = ((byte) (Integer.parseInt(data.substring(2 * i, (2 * i) + 2), 16)));

        return hex;
    }
}