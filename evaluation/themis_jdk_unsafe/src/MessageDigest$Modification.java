import java.security.MessageDigestSpi;
public abstract class MessageDigest$Modification extends MessageDigestSpi {
    public static boolean isEqual_unsafe(byte[] digesta, byte[] digestb) {
        if (digesta.length != digestb.length)
            return false;

        for (int i = 0; i < digesta.length; i++) {
            if (digesta[i] != digestb[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEqual_unsafe$Modification(byte[] digesta, byte[] digestb) {
        boolean $2 = true;
        boolean $1 = true;
        if (digesta.length != digestb.length) {
            $1 = false;
        } else {
            $2 = false;
        }
        for (int i = 0; i < digestb.length; i++) {
            if (i < digesta.length) {
                if (((i < digesta.length) && (i < digestb.length)) && (digesta[i] != digestb[i])) {
                    $1 = false;
                } else {
                    $2 = false;
                }
            } else if (((i < digesta.length) && (i < digestb.length)) && (digesta[i] != digestb[i])) {
                $1 = false;
            } else {
                $2 = false;
            }
        }
        return $1;
    }

    public static boolean isEqual_safe(byte[] digesta, byte[] digestb) {
        if (digesta.length != digestb.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < digesta.length; i++) {
            result |= digesta[i] ^ digestb[i];
        }
        return result == 0;
    }
}