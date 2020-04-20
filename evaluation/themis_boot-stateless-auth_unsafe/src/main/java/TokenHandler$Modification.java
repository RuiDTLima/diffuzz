import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdriven.stateless.security.User;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
public final class TokenHandler$Modification {
    private static final String HMAC_ALGO = "HmacSHA256";

    static final String SEPARATOR = ".";

    private static final String SEPARATOR_SPLITTER = "\\.";

    public final Mac hmac;

    private final boolean safeMode;

    public TokenHandler$Modification(byte[] secretKey, boolean safeMode) {
        try {
            hmac = Mac.getInstance(HMAC_ALGO);
            hmac.init(new SecretKeySpec(secretKey, HMAC_ALGO));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException("failed to initialize HMAC: " + e.getMessage(), e);
        }
        this.safeMode = safeMode;
    }

    public User parseUserFromToken(String token) {
        if (safeMode) {
            return parseUserFromToken_safe(token);
        } else {
            return parseUserFromToken_unsafe(token);
        }
    }

    public User parseUserFromToken_safe(String token) {
        final String[] parts = token.split(SEPARATOR_SPLITTER);
        if (((parts.length == 2) && (parts[0].length() > 0)) && (parts[1].length() > 0)) {
            try {
                final byte[] userBytes = fromBase64(parts[0]);
                final byte[] hash = fromBase64(parts[1]);
                boolean validHash = isEqual(createHmac(userBytes), hash);
                if (validHash) {
                    final User user = fromJSON(userBytes);
                    if (new Date().getTime() < user.getExpires()) {
                        return user;
                    }
                    return user;
                }
            } catch (IllegalArgumentException e) {
            }
        }
        return null;
    }

    public User parseUserFromToken_unsafe(String token) {
        User $1 = null;
        final String[] parts = token.split(SEPARATOR_SPLITTER);
        if (((parts.length == 2) && (parts[0].length() > 0)) && (parts[1].length() > 0)) {
            try {
                final byte[] userBytes = fromBase64(parts[0]);
                final byte[] hash = fromBase64(parts[1]);
                boolean validHash = unsafe_isEqual(createHmac(userBytes), hash);
                if (validHash) {
                    final User user = fromJSON(userBytes);
                    if (new Date().getTime() < user.getExpires()) {
                        $1 = user;
                    }
                    $1 = user;
                }
            } catch (IllegalArgumentException e) {
            }
        }
        return $1;
    }

    public static boolean isEqual(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result |= a[i] ^ b[i];
        }
        return result == 0;
    }

    public static boolean unsafe_isEqual(byte[] a, byte[] a2) {
        if (a == a2)
            return true;

        if ((a == null) || (a2 == null))
            return false;

        int length = a.length;
        if (a2.length != length)
            return false;

        for (int i = 0; i < length; i++)
            if (a[i] != a2[i])
                return false;


        return true;
    }

    public static boolean unsafe_isEqual$Modification(byte[] a, byte[] a2) {
        boolean $1 = true;
        if (a == a2) {
            $1 = true;
        }
        if ((a == null) || (a2 == null)) {
            $1 = false;
        }
        int length = a.length;
        if (a2.length != length) {
            $1 = false;
        }
        for (int i = 0; i < length; i++) {
            if (i < a.length && i < a2.length && a[i] != a2[i]) {
                $1 = false;
            }
        }
        return $1;
    }

    public String createTokenForUser(User user) {
        byte[] userBytes = toJSON(user);
        byte[] hash = createHmac(userBytes);
        final StringBuilder sb = new StringBuilder(170);
        sb.append(toBase64(userBytes));
        sb.append(SEPARATOR);
        sb.append(toBase64(hash));
        return sb.toString();
    }

    public static final User VALID_USER = new User();

    static {
        VALID_USER.setExpires(new Date().getTime() + 100000);
    }

    private User fromJSON(final byte[] userBytes) {
        return VALID_USER;
    }

    private byte[] toJSON(User user) {
        try {
            return new ObjectMapper().writeValueAsBytes(user);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    private String toBase64(byte[] content) {
        return DatatypeConverter.printBase64Binary(content);
    }

    private byte[] fromBase64(String content) {
        return DatatypeConverter.parseBase64Binary(content);
    }

    private synchronized byte[] createHmac(byte[] content) {
        return hmac.doFinal(content);
    }
}