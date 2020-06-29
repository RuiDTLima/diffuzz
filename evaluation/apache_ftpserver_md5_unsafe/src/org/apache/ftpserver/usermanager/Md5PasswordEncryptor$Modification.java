package org.apache.ftpserver.usermanager;
import org.apache.ftpserver.util.EncryptUtils;
public class Md5PasswordEncryptor$Modification implements PasswordEncryptor {
    private boolean safeMode;

    public Md5PasswordEncryptor$Modification() {
        this.safeMode = false;
    }

    public Md5PasswordEncryptor$Modification(boolean safeMode) {
        this.safeMode = safeMode;
    }

    public String encrypt(String password) {
        return EncryptUtils.encryptMD5(password);
    }

    public boolean matches(String passwordToCheck, String storedPassword) {
        if (storedPassword == null) {
            throw new NullPointerException("storedPassword can not be null");
        }
        if (passwordToCheck == null) {
            throw new NullPointerException("passwordToCheck can not be null");
        }
        if (safeMode) {
            return PasswordUtil.secureCompare(encrypt(passwordToCheck), storedPassword, 1024);
        } else {
            return equalsIgnoreCase(encrypt(passwordToCheck), storedPassword);
        }
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

    public boolean regionMatches$Modification(String thisValue, boolean ignoreCase, int toffset, String other, int ooffset, int len) {
        boolean $2 = true;
        boolean $1 = true;
        char[] ta = thisValue.toCharArray();
        int to = toffset;
        char[] pa = other.toCharArray();
        int po = ooffset;
        if ((((ooffset < 0) || (toffset < 0)) || (toffset > (((long) (thisValue.length())) - len))) || (ooffset > (((long) (other.length())) - len))) {
            $1 = false;
        } else {
            $2 = false;
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
            $1 = false;
        } 
        return $1;
    }
}