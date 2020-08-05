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

        @Override
        public boolean equals$Modification(Object other) {
            boolean $2;
            boolean $1;
            if (PasswordCredentials.Impl.this == other) {
                $1 = true;
            } else {
                $2 = true;
            }
            if ((other == null) || (getClass() != other.getClass())) {
                $1 = false;
            } else {
                $2 = false;
            }
            PasswordCredentials.Impl impl = ((PasswordCredentials.Impl) (other));
            $1 = ArraysIsEquals(password, impl.password);
            return $1;
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

        @Override
        public int hashCode() {
            return Arrays.hashCode(password);
        }
    }
}