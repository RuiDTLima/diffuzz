import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdriven.stateless.security.User;

public final class TokenHandler {

    private static final String HMAC_ALGO = "HmacSHA256";
    static final String SEPARATOR = ".";
    private static final String SEPARATOR_SPLITTER = "\\.";

    public final Mac hmac; // YN: changed to public
    private final boolean safeMode;

    public TokenHandler(byte[] secretKey, boolean safeMode) {
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
        if (parts.length == 2 && parts[0].length() > 0 && parts[1].length() > 0) {
            try {
                final byte[] userBytes = fromBase64(parts[0]);
                final byte[] hash = fromBase64(parts[1]);

                // safe!!!
                boolean validHash = isEqual(createHmac(userBytes), hash);
                if (validHash) {
                    final User user = fromJSON(userBytes);
                    if (new Date().getTime() < user.getExpires()) {
                        return user;
                    }
                    return user;
                }
            } catch (IllegalArgumentException e) {
                // log tempering attempt here
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
        boolean $2 = true;
        int $3 = 0;
        boolean $1 = true;
        if (a == a2) {
            $1 = true;
        } else {
            $2 = true;
        }
        if ((a == null) || (a2 == null)) {
            $1 = false;
        } else {
            $2 = false;
        }
        int length = 0;
        if (a != null) {
            length = a.length;
        } else {
            $3 = 0;
        }
        if ((a2 != null) && (a2.length != length)) {
            $1 = false;
        } else {
            $2 = false;
        }
        for (int i = 0; i < length; i++) {
            if ((((a != null) && (i < a.length)) && ((a2 != null) && (i < a2.length))) && (a[i] != a2[i])) {
                $1 = false;
            } else {
                $2 = false;
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

    /* YN changed for simplicity */
    public static final User VALID_USER = new User();
    static {
        VALID_USER.setExpires(new Date().getTime() + 100000);
    }

    private User fromJSON(final byte[] userBytes) {
        /*
         * try { return new ObjectMapper().readValue(new ByteArrayInputStream(userBytes), User.class); } catch
         * (IOException e) { throw new IllegalStateException(e); }
         */
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

    // synchronized to guard internal hmac object
    private synchronized byte[] createHmac(byte[] content) {
        return hmac.doFinal(content);
    }
}
