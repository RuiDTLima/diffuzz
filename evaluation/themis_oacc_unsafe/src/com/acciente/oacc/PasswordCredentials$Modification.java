package com.acciente.oacc;
import java.util.Arrays;
public abstract class PasswordCredentials$Modification implements Credentials {
    public abstract char[] getPassword();

    public static PasswordCredentials newInstance(char[] password) {
        return new PasswordCredentials.Impl(password);
    }

    private static class Impl extends PasswordCredentials {
        private final char[] password;

        private Impl(char[] password) {
            PasswordCredentials.Impl.this.password = password;
        }

        @Override
        public char[] getPassword() {
            return password;
        }

        @Override
        public boolean equals(Object other) {
            if (PasswordCredentials.Impl.this == other) {
                return true;
            }
            if ((other == null) || (getClass() != other.getClass())) {
                return false;
            }
            PasswordCredentials.Impl impl = ((PasswordCredentials.Impl) (other));
            return ArraysIsEquals(password, impl.password);
        }

        public static boolean ArraysIsEquals(char[] a, char[] a2) {
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

        public static boolean ArraysIsEquals$Modification(char[] a, char[] a2) {
            boolean $1 = true;
            if (a == a2) {
                $1 = true;
            }
            if ((a == null) || (a2 == null)) {
                $1 = false;
            }
            int length = 0;
            if (a != null)
                length = a.length;

            if ((a2 != null) && (a2.length != length)) {
                $1 = false;
            }
            for (int i = 0; i < length; i++) {
                if ((((a != null) && (i < a.length)) && ((a2 != null) && (i < a2.length))) && (a[i] != a2[i])) {
                    $1 = false;
                }
            }
            return $1;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(password);
        }
    }
}