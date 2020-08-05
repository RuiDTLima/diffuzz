package org.apache.ftpserver.usermanager;
import edu.cmu.sv.kelinci.Mem;
import java.security.SecureRandom;
import org.apache.ftpserver.util.EncryptUtils;
public class SaltedPasswordEncryptor$Modification implements PasswordEncryptor {
    private SecureRandom rnd = new SecureRandom();

    private static final int MAX_SEED = 99999999;

    private static final int HASH_ITERATIONS = 1000;

    private boolean safeMode;

    public SaltedPasswordEncryptor$Modification() {
        this.safeMode = false;
    }

    public SaltedPasswordEncryptor$Modification(boolean safeMode) {
        this.safeMode = safeMode;
    }

    public String encrypt(String password, String salt) {
        long tmpCost = Mem.instrCost;
        String hash = salt + password;
        for (int i = 0; i < HASH_ITERATIONS; i++) {
            hash = EncryptUtils.encryptMD5(hash);
        }
        return (salt + ":") + hash;
    }

    public String encrypt(String password) {
        String seed = Integer.toString(rnd.nextInt(MAX_SEED));
        return encrypt(password, seed);
    }

    public boolean matches(String passwordToCheck, String storedPassword) {
        if (storedPassword == null) {
            throw new NullPointerException("storedPassword can not be null");
        }
        if (passwordToCheck == null) {
            throw new NullPointerException("passwordToCheck can not be null");
        }
        int divider = storedPassword.indexOf(':');
        if (divider < 1) {
            throw new IllegalArgumentException("stored password does not contain salt");
        }
        String storedSalt = storedPassword.substring(0, divider);
        if (safeMode) {
            return PasswordUtil.secureCompare(encrypt(passwordToCheck, storedSalt), storedPassword, 1024);
        } else {
            return equalsIgnoreCase(encrypt(passwordToCheck, storedSalt), storedPassword);
        }
    }

    public boolean matches$Modification(String passwordToCheck, String storedPassword) {
        boolean $1;
        if (storedPassword == null) {
            throw new NullPointerException("storedPassword can not be null");
        }
        if (passwordToCheck == null) {
            throw new NullPointerException("passwordToCheck can not be null");
        }
        int divider = storedPassword.indexOf(':');
        if (divider < 1) {
            throw new IllegalArgumentException("stored password does not contain salt");
        }
        String storedSalt = storedPassword.substring(0, divider);
        if (safeMode) {
            $1 = PasswordUtil.secureCompare(encrypt(passwordToCheck, storedSalt), storedPassword, 1024);
        } else {
            $1 = equalsIgnoreCase(encrypt(passwordToCheck, storedSalt), storedPassword);
        }
        return $1;
    }

    public boolean equalsIgnoreCase(String thisString, String anotherString) {
        return thisString == anotherString ? true : ((anotherString != null) && (anotherString.length() == thisString.length())) && regionMatches(thisString, true, 0, anotherString, 0, thisString.length());
    }

    public boolean regionMatches(String thisValue, boolean ignoreCase, int toffset, String other, int ooffset, int len) {
        char[] ta = thisValue.toCharArray();
        int to = toffset;
        char[] pa = other.toCharArray();
        int po = ooffset;
        if ((((ooffset < 0) || (toffset < 0)) || (toffset > (((long) (thisValue.length())) - len))) || (ooffset > (((long) (other.length())) - len))) {
            return false;
        }
        while ((len--) > 0) {
            char c1 = ta[to++];
            char c2 = pa[po++];
            if (c1 == c2) {
                continue;
            }
            if (ignoreCase) {
                char u1 = Character.toUpperCase(c1);
                char u2 = Character.toUpperCase(c2);
                if (u1 == u2) {
                    continue;
                }
                if (Character.toLowerCase(u1) == Character.toLowerCase(u2)) {
                    continue;
                }
            }
            return false;
        } 
        return true;
    }
}